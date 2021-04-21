package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all faculties enrolled at NC State. All faculties
 * have a unique id. This directory is comprised of a sortedList. The faculty
 * records are sorted by lastName, firstName, and ID.
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet
 */
public class FacultyDirectory {

	/** List of faculties in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Creates an empty faculty directory object
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates an empty faculty directory. All faculties in the previous list are
	 * list unless saved by the user.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Constructs the faculty directory by reading in faculty information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of faculties
	 * @throws IllegalArgumentException if the file passed in from FacultyRecordIO
	 *                                  can not be found or read
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Faculty to the directory. Returns true if the faculty is added and
	 * false if the Faculty is unable to be added because their id matches another
	 * Faculties id.
	 * 
	 * This method also hashes the Faculties password for internal storage.
	 * 
	 * @param firstName      Faculties first name
	 * @param lastName       Faculties last name
	 * @param id             Faculties id
	 * @param email          Faculties email
	 * @param password       Faculties password
	 * @param repeatPassword Faculties repeated password
	 * @param maxCourses     Faculties max credits.
	 * @return true if added
	 * @throws IllegalArgumentException if the parameterized password is invalid,
	 *                                  the hash algorithm is invalid, or the repeat
	 *                                  password does not match the original
	 *                                  password
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());

			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		// If an IllegalArgumentException is thrown, it's passed up from Faculty
		// to the GUI
		Faculty faculty = null;
		if (maxCourses < 1) {
			faculty = new Faculty(firstName, lastName, id, email, hashPW);
		} else {
			faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		}

		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		facultyDirectory.add(faculty);
		return true;
	}

	/**
	 * Removes the Faculty with the given ID from the list of Faculties. Returns
	 * true if the Faculty is removed and false if the Faculty is not in the list.
	 * 
	 * @param facultyId the Faculty id
	 * @return true if the Faculty is successfully removed from the directory
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns all Faculty in the directory with a column for first name, last name,
	 * and id.
	 * 
	 * @return two-dimensional String array containing Faculty first name, last
	 *         name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}

	/**
	 * Saves all Faculty in the directory to a file.
	 * 
	 * @param fileName name of file to save Faculty to
	 * @throws IllegalArgumentException if unable to write the FacultyDirectory to
	 *                                  the file passed in from FacultyRecordIO
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Returns the Faculty by the ID that is passed to the method
	 * 
	 * @param id ID of the Faculty to return
	 * @return faculty returns the Faculty of the given ID
	 */
	public Faculty getFacultyById(String id) {
		Faculty tempFaculty = null;
		for (int i = 0; i < facultyDirectory.size(); i++) {
			tempFaculty = facultyDirectory.get(i);
			if (tempFaculty.getId().equals(id)) {
				return tempFaculty;
			} else {
				tempFaculty = null;
			}
		}
		return tempFaculty;
	}

}
