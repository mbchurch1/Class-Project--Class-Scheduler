package edu.ncsu.csc216.pack_scheduler.user;


/**
 * Creates the Faculty object, provides Faculty object getters and setters,
 * overrides standard equals and hashcode methods, and contains a toString
 * method.
 * 
 * @author Matthew Church
 * @author John Firlet
 * @author Will Goodwin
 */
public class Faculty extends User implements Comparable<Faculty>{
	
	
	/** Faculty max courses */
	private int maxCourses;
	/** minimum courses faculty can have */
	public static final int MIN_COURSES = 1;
	/** max courses a faculty can have */
	public static final int MAX_COURSES = 3;
	
	
	/**
	 * Creates the Faculty object based on the details of the necessary parameters
	 * for a Faculty object
	 * 
	 * @param firstName   faculty first name
	 * @param lastName    faculty last name
	 * @param id          faculty id
	 * @param email       faculty email
	 * @param hashPW      faculty password in SHA-256 encryption
	 * @param maxCourses  faculty max courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW); 
		setMaxCourses(maxCourses);
	}

	/**
	 * Creates a faculty object with the full max courses of 3
	 * 
	 * @param firstName   faculty first name
	 * @param lastName    faculty last name
	 * @param id          faculty id
	 * @param email       faculty email
	 * @param hashPW      faculty password in SHA-256 encryption
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_COURSES);
	}
	

	/**
	 * Sets the faculy max courses
	 * 
	 * @param maxCourses  max courses to set
	 * @throws IllegalArgumentException if courses are less than 1 and greater than 3
	 */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses.");
		}
		this.maxCourses = maxCourses;
		
	}
	
	
	/**
	 * Returns the max courses of faculty
	 * 
	 * @return maxCourses  Returns max courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}


	
	/**
	 * Compares two Faculties first name, last name or ID to sort the Faculty list.
	 * Overrides the compareTo in the added library. Throws a NullPointerException
	 * if the faculty is Null. Throws a ClassCastException if the object provided is
	 * not a Faculty.
	 * 
	 * @param f the Faculty to compare
	 * @throws NullPointerException     if f is null
	 * @throws IllegalArgumentException if f is not a valid Faculty object
	 * @return comparable An int signifying order of the two Faculties, positive if s
	 *         is higher, negative if the original is higher.
	 */
	@Override
	public int compareTo(Faculty f) {
		int comparable = 0;
		try {
			int compareLast = getLastName().compareTo(f.getLastName());
			int compareFirst = getFirstName().compareTo(f.getFirstName());
			int compareId = getId().compareTo(f.getId());

			if (compareLast != 0) {
				comparable = compareLast;
			} else if (compareFirst != 0) {
				comparable = compareFirst;
			} else {
				comparable = compareId;
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Faculty cannot be null.");
		}
		return comparable;
	}


	/**
	 * Generates a hashCode for Faculty using all fields.
	 * 
	 * @return hashCode for Faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}


	/**
	 * Compares a given object to this object for equality in all fields.
	 * 
	 * @param obj of the object to compare
	 * @return true of the object are the same in all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}


	/**
	 * Returns a string of the faculties first and last name, id number, email,
	 * encrypted password, and max courses.
	 * 
	 * @return the facilties string
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}

	
	
}
