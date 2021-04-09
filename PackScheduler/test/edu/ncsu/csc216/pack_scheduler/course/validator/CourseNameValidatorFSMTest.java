package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * CourseNameValidatorFSMTest class 
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet 
 *
 */
public class CourseNameValidatorFSMTest {

	
	/**
	 * Testing valid course names
	 * @throws InvalidTransitionException Throw the InvalidTransitionException
	 *         if course name is invalid
	 */
	@Test
	public void testValidCourses() throws InvalidTransitionException {
		CourseNameValidatorFSM a = new CourseNameValidatorFSM();
		assertTrue(a.isValid("e115"));
		assertTrue(a.isValid("e115a"));
		assertTrue(a.isValid("hi123"));
		assertTrue(a.isValid("hi123a"));
		assertTrue(a.isValid("csc216"));
		assertTrue(a.isValid("csc216a"));
		assertTrue(a.isValid("abcd123"));
		assertTrue(a.isValid("abcd123a"));
		
		//fail("Not yet implemented");
	}

	/**
	 * Tests Invalid course names
	 */
	@Test
	public void testInvalidCourseStuckInNonThrowingState() {
		try {
		CourseNameValidatorFSM a = new CourseNameValidatorFSM();
		// test only one number
		assertFalse(a.isValid("CSC1"));
		// test one letter and two numbers
		assertFalse(a.isValid("e11"));
		} catch (InvalidTransitionException e) {
			fail("exception was thrown");
		}
	}
	/**
	 * test invalid courses that throw an exception from their state
	 */
	@Test
	public void testInvalidCourseWithThrows() {
		CourseNameValidatorFSM a = new CourseNameValidatorFSM();
		try {
			a.isValid("csccs216");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		try {
			a.isValid("*sc216ab");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			a.isValid("csc216ab");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		try {
			a.isValid("123r");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		try {
			a.isValid("hi1234");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			a.isValid("hi1e234");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			a.isValid("hi12e34");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			a.isValid("hi123e4");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}
	
	
}
