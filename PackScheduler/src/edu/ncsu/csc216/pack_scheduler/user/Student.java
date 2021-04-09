package edu.ncsu.csc216.pack_scheduler.user;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;


/**
 * Creates the Student object, provides student object getters and setters,
 * overrides standard equals and hashcode methods, and contains a toString
 * method.
 * 
 * @author Matthew Church
 * @author John Firlet
 * @author Will Goodwin
 */
public class Student extends User implements Comparable<Student> {

	/** students max credits */
	private int maxCredits;

	/** minimum credits a student can have */
	public static final int MIN_CREDITS = 3;

	/** max credits a student can have */
	public static final int MAX_CREDITS = 18;
	/** student' schedule */
	private Schedule schedule = new Schedule();

	/**
	 * Creates the Student object based on the details of the necessary parameters
	 * for a student object
	 * 
	 * @param firstName  students first name
	 * @param lastName   students last name
	 * @param id         students id
	 * @param email      students email
	 * @param hashPW     students password in SHA-256 encryption
	 * @param maxCredits students max credits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
	}

	/**
	 * Creates a student object with the full max credit hours of 18
	 * 
	 * @param firstName student first name
	 * @param lastName  student last name
	 * @param id        student Id
	 * @param email     student email
	 * @param hashPW    student password in SHA-256 encryption
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}

	/**
	 * Gets the student's max credit hours
	 * 
	 * @return maxCredits Returns the max credits a Student can take
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the student's credits. If the credits are less than 3 or more than 18,
	 * throws IllegalArgument Exception.
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if maxCredits is less than 3 or more than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns the student's schedule
	 * 
	 * @return a schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * Returns a string of the student's first and last name, id number, email,
	 * encrypted password, and max credit hours.
	 * 
	 * @return the students string
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCredits;
	}

	/**
	 * Compares two Students' first name, last name or ID to sort the Student list.
	 * Overrides the compareTo in the added library. Throws a NullPointerException
	 * if the student is Null. Throws a ClassCastException if the object provided is
	 * not a Student.
	 * 
	 * @param s the Student to compare
	 * @throws NullPointerException     if s is null
	 * @throws IllegalArgumentException if s is not a valid Student object
	 * @return comparable An int signifying order of the two Students, positive if s
	 *         is higher, negative if the original is higher.
	 */
	@Override
	public int compareTo(Student s) {
		int comparable = 0;
		try {
			int compareLast = getLastName().compareTo(s.getLastName());
			int compareFirst = getFirstName().compareTo(s.getFirstName());
			int compareId = getId().compareTo(s.getId());

			if (compareLast != 0) {
				comparable = compareLast;
			} else if (compareFirst != 0) {
				comparable = compareFirst;
			} else {
				comparable = compareId;
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Student cannot be null.");
		}
		return comparable;
	}

	/**
	 * Generates a hashCode for Student using all fields.
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
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
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
//			return false;
//		return true;
	}
	
	/**
	 * Returns true or false if a course can be added
	 * @param course Course to check if it can be added
	 * @return true or false if course can be added
	 */
	public boolean canAdd(Course course) {
		int potentialCredits = schedule.getScheduleCredits() + course.getCredits();
		
		if (!schedule.canAdd(course)) {
			return false;
		} else {
			return potentialCredits <= maxCredits;
		} 
	}

}
