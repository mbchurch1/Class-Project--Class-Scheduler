/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import java.util.EmptyStackException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;
import edu.ncsu.csc216.pack_scheduler.util.Queue;

/**
 * 
 * CourseRoll class that works with the CourseCatalog and StudentDirectory
 * classes
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 */
public class CourseRoll {

	/** An LinkedAbstractList of students */
	LinkedAbstractList<Student> roll;
	/** Enrollment capacity field */
	private int enrollmentCap;
	/** the course waitlist */
	private LinkedQueue<Student> waitlist;
	/** the course the roll is attached to */
	private Course course;
	/** Minimum number of enrolled students */
	private static final int MIN_ENROLLMENT = 10;
	/** Maximum number of enrolled students */
	private static final int MAX_ENROLLMENT = 250;
	/** Number for a waitlist size */
	private static final int WAITLIST_SIZE = 10;

	/**
	 * Constructor for CourseRoll
	 * 
	 * @param course        the course to enroll
	 * @param enrollmentCap the Course enrollment cap
	 */
	public CourseRoll(Course course, int enrollmentCap) {

		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
		setCourse(course);
		waitlist = new LinkedQueue(WAITLIST_SIZE);
	}

	private void setCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("The course cannot be null.");
		}
		this.course = course;
	}

	/**
	 * Method to return the enrollmentCap
	 * 
	 * @return enrollmentCap Enrollment capacity
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets the enrollmentCap field
	 * 
	 * @param enrollmentCap capacity of the course
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment capacity.");
		}
		if (enrollmentCap < this.enrollmentCap && enrollmentCap < roll.size()) {
			throw new IllegalArgumentException("Invalid enrollment capacity.");
		}

		roll.setCapacity(enrollmentCap);
		this.enrollmentCap = roll.getCapacity();
	}

	/**
	 * Enroll method to add a student to a course
	 * 
	 * @param student Student to be added to a course
	 */
	public void enroll(Student student) {
		if (student == null || !canEnroll(student)) {
			throw new IllegalArgumentException("Student cannot be added.");
		}
		if (roll.size() == enrollmentCap) {
			waitlist.enqueue(student);
		} else {
			roll.add(roll.size(), student);
		}
	
//		try {
//			roll.add(roll.size(), student);
//		} catch (IllegalArgumentException e) {
//			throw new IllegalArgumentException("Student cannot be enrolled.");
//		}
	}

	/**
	 * Drop method to drop a student from a course
	 * 
	 * @param student Student to be dropped from a course
	 */
	public void drop(Student student) {
		if (student == null || roll == null) {
			throw new IllegalArgumentException("Student cannot be dropped.");
		}
		int index = -1;
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student)) {
				index = i;
			}
		}

		try {
			roll.remove(index);
		} catch (IndexOutOfBoundsException e) {
			// throw new IllegalArgumentException("Student cannot be enrolled.");
		}
		try {
			roll.add(waitlist.dequeue());
		} catch (EmptyStackException e) {
			// doesn't add if the waitlist is empty
		}

	}

	/**
	 * Gets the number of open seats for a course
	 * 
	 * @return openSeats Number of open seats in the course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();

	}

	/**
	 * Returns true or false if a student can enroll in a course
	 * 
	 * @param student Student to check if they can be added to the course
	 * @return True or false if the student can be added
	 */
	public boolean canEnroll(Student student) {
		if (roll.size() >= enrollmentCap && waitlist.size() >= WAITLIST_SIZE) {
			return false;
		}

		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student)) {
				return false;
			}
		}
		// implement a contains in LinkedQueue

		LinkedQueue<Student> copiedWaitlist = waitlist;

		return true;
	}

	/**
	 * Returns the number on the waitlist
	 * 
	 * @return the int of students on the waitlist for the course
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
