package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creates the ConflictException class. This exception is thrown when a conflict
 * exists between class added to a Student's schedule and onw that they are
 * trying to add.
 * 
 * @author Matthew Church
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Passes the message for use in other classes.
	 * 
	 * @param message the error message to pass
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructor for ConflictException. Sets the error message.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
