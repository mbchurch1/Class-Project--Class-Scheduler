package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager class to help users keep track of courses and activities
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet
 *
 */
public class RegistrationManager {

	/** Registration Manager Instance */
	private static RegistrationManager instance;
	/** Course Catalog field */
	private CourseCatalog courseCatalog = new CourseCatalog();
	/** Student Directory field */
	private StudentDirectory studentDirectory = new StudentDirectory();
	/** Faculty Directory field */
	private FacultyDirectory facultyDirectory = new FacultyDirectory();
	/** Registrar user */
	private User registrar;
	/** Current user field */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Registrar properties file */
	private static final String PROP_FILE = "registrar.properties";

	private RegistrationManager() {
		createRegistrar();
	}

	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns an instance for a user
	 * 
	 * @return instance Instance to be returned
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the Course Catalog
	 * 
	 * @return courseCatalog Course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the Student Directory
	 * 
	 * @return studentDirectory Student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	
	/**
	 * Returns the Faculty Directory
	 * 
	 * @return facultyDirectory Faculty Directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	
	
	
	/**
	 * Returns true if the ID and password match
	 * 
	 * @param id       ID of the user
	 * @param password Password of the user
	 * @return boolean Returns true or false if the ID and password match
	 */
	public boolean login(String id, String password) {

		if (currentUser != null) {
			return false;
		}
		try {
			if (registrar.getId().equals(id)) {
				MessageDigest digest1;

				digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
				digest1.update(password.getBytes());
				String localHashPW = new String(digest1.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} else if(facultyDirectory.getFacultyById(id) != null) {
				Faculty f = facultyDirectory.getFacultyById(id);
				if(f != null) {
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (f.getPassword().equals(localHashPW)) {
						currentUser = f;
						return true;
					}
				}
			} else if (studentDirectory.getStudentById(id) != null) {
				Student s = studentDirectory.getStudentById(id);
				if (s != null) {
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (s.getPassword().equals(localHashPW)) {
						currentUser = s;
						return true;
					} else {
						return false;
					}

				}
			//}
		} else {
			throw new IllegalArgumentException("User doesn't exist.");

		} 
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}

		return false;
	}

	/**
	 * Logs the current user out
	 */
	public void logout() {
		currentUser = null; // registrar;
	}

	/**
	 * Returns the current user
	 * 
	 * @return currentUser Returns the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Resets the course catalog and student directory to be new catalog and
	 * directory objects
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}
	
	/**
	 * Adds a Course to a Faculty object's FacultySchedule
	 * @param c the course that is being attempted to be added to faculty's FacultySchedule
	 * @param faculty the faculty member to which the course is attempting to be added
	 * @throws IllegalArgumentException if the course cannot be added to the faculty member's schedule
	 * @return true if the Course is added to the faculty member's FacultySchedule 
	 */
	public boolean addFacultyToCourse(Course c, Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			try {
				return faculty.getSchedule().addCourseToSchedule(c);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("The course cannot be added to this faculty member's schedule.");
			}
		} else {
			throw new IllegalArgumentException("Cannot add a course to a faculty's schedule if not logged in as Registrar.");
		}
	}
	
	
	/**
	 * Removes Course from a Faculty object's FacultySchedule
	 * @param c the course that is being attempted to be removed from faculty's FacultySchedule
	 * @param faculty the faculty member to which the course is attempting to be removed
	 * @return true if the Course is removed from the faculty member's FacultySchedule 
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			return faculty.getSchedule().removeCourseFromSchedule(c);
		} else {
			return false;
		}
	}
	
	/**
	 * Resets the faculty object's FacultySchedule to contain zero Courses
	 * @param faculty the faculty member whose FactultySchedule is being reset
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			faculty.getSchedule().resetSchedule();
		} 
	}
	

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName First name of user
		 * @param lastName  Last name of user
		 * @param id        ID of user
		 * @param email     email of user
		 * @param hashPW    password of user
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}