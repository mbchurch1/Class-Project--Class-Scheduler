package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;


//import edu.ncsu.csc216.pack_scheduler.course.InvalidTransitionException;

/**
 * InvalidTransitionExceptionTest class that test the 
 * InvalidTransitionException class
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Test method for a custom message with InvalidTransitionException.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for parameterless InvalidTransitionException constructor.
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ce.getMessage());
	}
}
