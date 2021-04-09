package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * CourseNameValidatorTest class that tests the CourseNameValidator class
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Testing valid course names
	 * @throws InvalidTransitionException if the course name is invalid
	 */
	@Test
	public void testValidCourses() throws InvalidTransitionException {
		CourseNameValidator a = new CourseNameValidator();
		assertTrue(a.isValid("e115"));
		CourseNameValidator b = new CourseNameValidator();
		assertTrue(b.isValid("e115a"));
		CourseNameValidator c = new CourseNameValidator();
		assertTrue(c.isValid("hi123"));
		CourseNameValidator d = new CourseNameValidator();
		assertTrue(d.isValid("hi123a"));
		CourseNameValidator e = new CourseNameValidator();
		assertTrue(e.isValid("csc216"));
		CourseNameValidator f = new CourseNameValidator();
		assertTrue(f.isValid("csc216a"));
		CourseNameValidator g = new CourseNameValidator();
		assertTrue(g.isValid("abcd123"));
		CourseNameValidator h = new CourseNameValidator();
		assertTrue(h.isValid("abcd123a"));
		CourseNameValidator i = new CourseNameValidator();
		assertTrue(i.isValid("CSC216"));
		CourseNameValidator j = new CourseNameValidator();
		assertTrue(j.isValid("E115"));
		CourseNameValidator k = new CourseNameValidator();
		assertTrue(k.isValid("CSC116"));
		CourseNameValidator l = new CourseNameValidator();
		assertTrue(l.isValid("CS405"));
		CourseNameValidator m = new CourseNameValidator();
		assertTrue(m.isValid("PSY200"));
		CourseNameValidator n = new CourseNameValidator();
		assertTrue(n.isValid("PY208"));
		CourseNameValidator o = new CourseNameValidator();
		assertTrue(o.isValid("PY208L"));
		CourseNameValidator p = new CourseNameValidator();
		assertTrue(p.isValid("CSC491B"));
		CourseNameValidator q = new CourseNameValidator();
		assertTrue(q.isValid("CH101"));
		CourseNameValidator r = new CourseNameValidator();
		assertTrue(r.isValid("ENG101"));
		CourseNameValidator s = new CourseNameValidator();
		assertTrue(s.isValid("HESA101"));
		
		//fail("Not yet implemented");
	}

	/**
	 * Tests Invalid course names
	 * 
	 */
	@Test
	public void testInvalidCourseStuckInNonThrowingState() {
		try {
		CourseNameValidator a = new CourseNameValidator();
		// test only one number
		assertFalse(a.isValid("CSC1"));
		
		// test one letter and two numbers
		CourseNameValidator b = new CourseNameValidator();
		assertFalse(b.isValid("e11"));
		} catch (InvalidTransitionException e) {
			fail("Excepetion was thrown");
		}
	}
	/**
	 * test invalid courses that throw an exception from their state
	 */
	@Test
	public void testInvalidCourseWithThrows() {
		CourseNameValidator a = new CourseNameValidator();
		try {
			a.isValid("csccs216");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		CourseNameValidator a1 = new CourseNameValidator();
		try {
			a1.isValid("csc216a1");
			fail("This name didn't throw an exception");
		} catch(InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		CourseNameValidator b = new CourseNameValidator();
		try {
			b.isValid("*sc216ab");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		CourseNameValidator c = new CourseNameValidator();
		try {
			c.isValid("cscc216ab");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		CourseNameValidator d = new CourseNameValidator();
		try {
			d.isValid("123r");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		CourseNameValidator f = new CourseNameValidator();
		try {
			f.isValid("hi1234");
			fail("This name didn't throw an exception.");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
	}

}
