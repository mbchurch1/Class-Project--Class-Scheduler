package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;



/**
 * Tests the Faculty class
 * 
 * @author John Firlet
 * @author Matthew Church
 * @author Will Goodwin
 *
 */
public class FacultyTest {

	/** First name */
	private static final String FIRST_NAME = "Joe";
	/** Last Name */
	private static final String LAST_NAME = "Smith";
	/** Faculty ID */
	private static final String FACULTY_ID = "jsmith";
	/** Faculty email */
	private static final String FACULTY_EMAIL = "jsmith@gmail.com";
	/** Faculty password */
	private static final String FACULTY_PASSWORD = "Just$omep@ssword";
	/** Faculty maximum courses */
	private static final int FACULTY_MAX_COURSES = 3;

	/**
	 * Tests the Faculty constructor with all field parameters.
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {
		Faculty f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(FACULTY_ID, f.getId());
			assertEquals(FACULTY_EMAIL, f.getEmail());
			assertEquals(FACULTY_PASSWORD, f.getPassword());
			assertEquals(FACULTY_MAX_COURSES, f.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the Faculty class with only 5 parameters. Max courses are not passed.
	 */
	@Test
	public void testFacultyStringStringStringStringString() {
		Faculty f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD);
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(FACULTY_ID, f.getId());
			assertEquals(FACULTY_EMAIL, f.getEmail());
			assertEquals(FACULTY_PASSWORD, f.getPassword());
			assertEquals(3, f.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests setFirstName() method with both valid and invalid first names
	 */
	@Test
	public void testSetFirstName() {
		// Testing valid first names
		try {
			User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("Joe", f1.getFirstName());

			User f2 = new Faculty("Fred", LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("Fred", f2.getFirstName());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid first name using null
		User f = null;
		try {
			f = new Faculty(null, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid first name", e.getMessage());
		}

		// Testing invalid first name using an empty string
		f = null;
		try {
			f = new Faculty("", LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid first name", e.getMessage());
		}
	}

	/**
	 * Tests setlastName() method with both valid and invalid last names
	 */
	@Test
	public void testSetLastName() {
		// Testing valid last names
		try {
			User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("Smith", f1.getLastName());

			User f2 = new Student(FIRST_NAME, "Flintstone", FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("Flintstone", f2.getLastName());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid last name using null
		User f = null;
		try {
			f = new Faculty(FIRST_NAME, null, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid last name", e.getMessage());
		}

		// Testing invalid last name using an empty string
		f = null;
		try {
			f = new Faculty(FIRST_NAME, "", FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid last name", e.getMessage());
		}
	}

	/**
	 * Tests setId() method with both valid and invalid ID's
	 */
	@Test
	public void testSetId() {
		// Testing valid ID's
		try {
			User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("jsmith", f1.getId());

			User f2 = new Faculty(FIRST_NAME, LAST_NAME, "fflintstone", FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("fflintstone", f2.getId());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid ID using null
		User f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, null, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid id", e.getMessage());
		}

		// Testing invalid ID using an empty string
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, "", FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid id", e.getMessage());
		}
	}

	/**
	 * Tests setEmail() with valid and invalid email addresses
	 */
	@Test
	public void testSetEmail() {
		// Testing valid email addresses
		try {
			User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("jsmith@gmail.com", f1.getEmail());

			User f2 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "fred.flintstone@bedrock.com", FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("fred.flintstone@bedrock.com", f2.getEmail());
		} catch (IllegalArgumentException e) {
			fail();
		}

		// Test invalid email address using null
		User f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, null, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid email", e.getMessage());
		}
		// Test invalid email address using an empty string
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "", FACULTY_PASSWORD, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid email", e.getMessage());
		}

		// Test invalid email address using no @ symbol
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "george.jetson.sprockets.com", FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid email", e.getMessage());
		}
		// Test invalid email address using no "."
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "tomandjerry@catmousecom", FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid email", e.getMessage());
		}
		// Test invalid email address using no "." after the @ symbol
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "iron.man@avengerscom", FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid email", e.getMessage());
		}
	}

	/**
	 * Test setPassword() with valid and invalid passwords
	 */
	@Test
	public void testSetPassword() {
		// Testing valid password
		try {
			User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals("Just$omep@ssword", f1.getPassword());

			User f2 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, "!tsThe$uperB@wl",
					FACULTY_MAX_COURSES);
			assertEquals("!tsThe$uperB@wl", f2.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid password using null
		User f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, null, FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid password", e.getMessage());
		}

		// Testing invalid password using an empty string
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, "", FACULTY_MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Testing the setMaxCredits() with valid and invalid numbers
	 */
	@Test
	public void testSetMaxCredits() {
		// Testing valid max credits
		try {
			Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
					FACULTY_MAX_COURSES);
			assertEquals(FACULTY_MAX_COURSES, f1.getMaxCourses());
			
			f1.setMaxCourses(2);
			assertEquals(2, f1.getMaxCourses());

			Faculty f2 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, 2);
			assertEquals(2, f2.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid max credits with a value of 0
		User f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
			assertEquals("Invalid max courses.", e.getMessage());
		}
		// Testing invalid max courses with a value of 4
		f = null;
		Faculty a = null;
		try {
			a = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, 4);
			fail("Max courses are too high.");
		} catch (IllegalArgumentException e) {
			assertNull(a);
			assertEquals("Invalid max courses.", e.getMessage());
		}
	}

	/**
	 * Test the equalsObject by testing each field of the Student object
	 */
	@Test
	public void testEqualsObject() {
		User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		User f3 = new Faculty("Spider", LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
		User f4 = new Faculty(FIRST_NAME, "Man", FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
		User f5 = new Faculty(FIRST_NAME, LAST_NAME, "sman", FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
		User f6 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "spider.man@avengers.com", FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		User f7 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, "Web$!inger", FACULTY_MAX_COURSES);
		User f8 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, 2);

		// Test for equality in both directions
		assertTrue(f1.equals(f1));

		// Test for each of the fields
		assertFalse(f1.equals(f3));
		assertFalse(f1.equals(f4));
		assertFalse(f1.equals(f5));
		assertFalse(f1.equals(f6));
		assertFalse(f1.equals(f7));
		assertFalse(f1.equals(f8));

	}

	/**
	 * Testing the hashCode() using each field of the Student object
	 */
	@Test
	public void testHashCode() {
		User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		User f2 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		User f3 = new Faculty("Spider", LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
		User f4 = new Faculty(FIRST_NAME, "Man", FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
		User f5 = new Faculty(FIRST_NAME, LAST_NAME, "sman", FACULTY_EMAIL, FACULTY_PASSWORD, FACULTY_MAX_COURSES);
		User f6 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, "spider.man@avengers.com", FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		User f7 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, "Web$!inger", FACULTY_MAX_COURSES);
		User f8 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD, 2);

		// Test for the same hash code for the same values
		assertEquals(f1.hashCode(), f2.hashCode());

		// Test for each of the fields
		assertNotEquals(f1.hashCode(), f3.hashCode());
		assertNotEquals(f1.hashCode(), f4.hashCode());
		assertNotEquals(f1.hashCode(), f5.hashCode());
		assertNotEquals(f1.hashCode(), f6.hashCode());
		assertNotEquals(f1.hashCode(), f7.hashCode());
		assertNotEquals(f1.hashCode(), f8.hashCode());
	}

	/**
	 * Testing that toString() returns the correct comma-separated value.
	 */
	@Test
	public void testToString() {
		User f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		String string1 = "Joe,Smith,jsmith,jsmith@gmail.com,Just$omep@ssword,3";
		assertEquals(string1, f1.toString());

		User f2 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD);
		String string2 = "Joe,Smith,jsmith,jsmith@gmail.com,Just$omep@ssword,3";
		assertEquals(string2, f2.toString());
	}

	/**
	 * Test compareTo, ensuring that it can accurately compare the fields of two student objects
	 */
	@Test
	public void testcompareTo() {
		Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
				FACULTY_MAX_COURSES);
		Faculty f2 = new Faculty("Matt", "Church", "mbchurch", "mbchurch@ncsu.edu", "hamsandwhich", 1);
		int x = f1.compareTo(f2);
		int y = f2.compareTo(f1);
		assertTrue(x > 0);
		// Test reverse
		assertTrue(y < 0);
		// Test same first name
		Faculty f3 = new Faculty("Matt", "Firlet", "mbfirlet", "mbfirlet@ncsu.edu", "ham2sando", 2);
		int z = f2.compareTo(f3);
		assertTrue(z < 0);
		// Test same last name
		Faculty f4 = new Faculty("John", "Church", "jmchurc1", "jmchurch@ncsu.edu", "turkeyclub", 3);
		int a = f2.compareTo(f4);
		assertTrue(a > 0);
		// Test Faculty Id
		Faculty f5 = new Faculty("John", "Church", "jmchurc2", "jmchurc1@ncsu.edu", "beachclub", 1);
		int b = f4.compareTo(f5);
		assertTrue(b < 0);
		// Test catches
		Faculty f6 = null;
		try {
			f5.compareTo(f6);
			fail("Failed to throw an exception");
		} catch (IllegalArgumentException e) {
			assertEquals("Faculty cannot be null.", e.getMessage());
		}

	}
	
//	/**
//	 * Test canAdd(), ensuring that it can accurately determine whether it is legal to add a proposed course
//	 * to a Faculty schedule 
//	 */
//	@Test
//	public void testCanAdd() {
//		Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, FACULTY_ID, FACULTY_EMAIL, FACULTY_PASSWORD,
//				FACULTY_MAX_COURSES);
//		Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
//		f1.getSchedule().addCourseToSchedule(c1);
//		
//		Course c2 = new Course("CSC226", "Discrete Math", "004", 5, "professor2", 50, "A");
//		assertTrue(f1.canAdd(c2));
//		f1.getSchedule().addCourseToSchedule(c2);
//		assertEquals(10, f1.getSchedule().getScheduleCredits());
//		
//		Course c3 = null;
//		try {
//			assertFalse(f1.canAdd(c3));
//			f1.getSchedule().addCourseToSchedule(c3);
//			fail("Cannot add null course to schedule.");
//		} catch (NullPointerException e) {
//			assertEquals(10, f1.getSchedule().getScheduleCredits());
//		}
//		
//		Course c4 = new Course("CSC500", "More Programming", "002", 5, "professor3", 28, "TH", 1100, 1230);
//		assertTrue(f1.canAdd(c4));
//		f1.getSchedule().addCourseToSchedule(c4);
//		assertEquals(15, f1.getSchedule().getScheduleCredits());
//		
//		Course c5 = new Course("CSC900", "Another Programming Course", "004", 3, "professor9", 50, "A");
//		assertFalse(f1.canAdd(c5));
//	}

}

