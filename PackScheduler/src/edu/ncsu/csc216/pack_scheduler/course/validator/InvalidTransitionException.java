/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Creates the InvalidTransitionException class.  This exception is thrown
 * when the an invalid course name occurs.
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet 
 *
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Passes the message for use in other classes.
	 * 
	 * @param message the error message to pass
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Constructor for InvalidTransitionException. Sets the error message.
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	
}
