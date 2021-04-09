package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Student class
 * 
 * @author John Firlet
 * @author Matthew Church
 * @author Will Goodwin
 *
 */
public class StudentTest {

	/** First name */
	private static final String FIRST_NAME = "Joe";
	/** Last Name */
	private static final String LAST_NAME = "Smith";
	/** Student ID */
	private static final String STUDENT_ID = "jsmith";
	/** Student email */
	private static final String STUDENT_EMAIL = "jsmith@gmail.com";
	/** Student password */
	private static final String STUDENT_PASSWORD = "Just$omep@ssword";
	/** Student maximum credits */
	private static final int STUDENT_MAX_CREDITS = 15;

	/**
	 * Tests the Student constructor with all field parameters.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(STUDENT_ID, s.getId());
			assertEquals(STUDENT_EMAIL, s.getEmail());
			assertEquals(STUDENT_PASSWORD, s.getPassword());
			assertEquals(STUDENT_MAX_CREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the Student class with only 5 parameters. Max credits are not passed.
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(STUDENT_ID, s.getId());
			assertEquals(STUDENT_EMAIL, s.getEmail());
			assertEquals(STUDENT_PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
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
			User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("Joe", s1.getFirstName());

			User s2 = new Student("Fred", LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("Fred", s2.getFirstName());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid first name using null
		User s = null;
		try {
			s = new Student(null, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid first name", e.getMessage());
		}

		// Testing invalid first name using an empty string
		s = null;
		try {
			s = new Student("", LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
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
			User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("Smith", s1.getLastName());

			User s2 = new Student(FIRST_NAME, "Flintstone", STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("Flintstone", s2.getLastName());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid last name using null
		User s = null;
		try {
			s = new Student(FIRST_NAME, null, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid last name", e.getMessage());
		}

		// Testing invalid last name using an empty string
		s = null;
		try {
			s = new Student(FIRST_NAME, "", STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
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
			User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("jsmith", s1.getId());

			User s2 = new Student(FIRST_NAME, LAST_NAME, "fflintstone", STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("fflintstone", s2.getId());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid ID using null
		User s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, null, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid id", e.getMessage());
		}

		// Testing invalid ID using an empty string
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, "", STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
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
			User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("jsmith@gmail.com", s1.getEmail());

			User s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "fred.flintstone@bedrock.com", STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("fred.flintstone@bedrock.com", s2.getEmail());
		} catch (IllegalArgumentException e) {
			fail();
		}

		// Test invalid email address using null
		User s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, null, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid email", e.getMessage());
		}
		// Test invalid email address using an empty string
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "", STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid email", e.getMessage());
		}

		// Test invalid email address using no @ symbol
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "george.jetson.sprockets.com", STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid email", e.getMessage());
		}
		// Test invalid email address using no "."
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "tomandjerry@catmousecom", STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid email", e.getMessage());
		}
		// Test invalid email address using no "." after the @ symbol
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "iron.man@avengerscom", STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
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
			User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals("Just$omep@ssword", s1.getPassword());

			User s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, "!tsThe$uperB@wl",
					STUDENT_MAX_CREDITS);
			assertEquals("!tsThe$uperB@wl", s2.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid password using null
		User s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, null, STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid password", e.getMessage());
		}

		// Testing invalid password using an empty string
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, "", STUDENT_MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
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
			Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
					STUDENT_MAX_CREDITS);
			assertEquals(STUDENT_MAX_CREDITS, s1.getMaxCredits());
			
			s1.setMaxCredits(17);
			assertEquals(17, s1.getMaxCredits());

			Student s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 12);
			assertEquals(12, s2.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing invalid max credits with a value of 2
		User s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals("Invalid max credits", e.getMessage());
		}
		// Testing invalid max credits with a value of 20
		s = null;
		Student a = null;
		try {
			a = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 20);
			fail("Max credits are too high.");
		} catch (IllegalArgumentException e) {
			assertNull(a);
			assertEquals("Invalid max credits", e.getMessage());
		}
	}

	/**
	 * Test the equalsObject by testing each field of the Student object
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		User s3 = new Student("Spider", LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "Man", STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "sman", STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "spider.man@avengers.com", STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, "Web$!inger", STUDENT_MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 12);

		// Test for equality in both directions
		assertTrue(s1.equals(s1));

		// Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));

	}

	/**
	 * Testing the hashCode() using each field of the Student object
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		User s3 = new Student("Spider", LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "Man", STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "sman", STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "spider.man@avengers.com", STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, "Web$!inger", STUDENT_MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 12);

		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Testing that toString() returns the correct comma-separated value.
	 */
	@Test
	public void testToString() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		String string1 = "Joe,Smith,jsmith,jsmith@gmail.com,Just$omep@ssword,15";
		assertEquals(string1, s1.toString());

		User s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD);
		String string2 = "Joe,Smith,jsmith,jsmith@gmail.com,Just$omep@ssword,18";
		assertEquals(string2, s2.toString());
	}

	/**
	 * Test compareTo, ensuring that it can accurately compare the fields of two student objects
	 */
	@Test
	public void testcompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		Student s2 = new Student("Matt", "Church", "mbchurch", "mbchurch@ncsu.edu", "hamsandwhich", 16);
		int x = s1.compareTo(s2);
		int y = s2.compareTo(s1);
		assertTrue(x > 0);
		// Test reverse
		assertTrue(y < 0);
		// Test same first name
		Student s3 = new Student("Matt", "Firlet", "mbfirlet", "mbfirlet@ncsu.edu", "ham2sando", 15);
		int z = s2.compareTo(s3);
		assertTrue(z < 0);
		// Test same last name
		Student s4 = new Student("John", "Church", "jmchurc1", "jmchurch@ncsu.edu", "turkeyclub", 14);
		int a = s2.compareTo(s4);
		assertTrue(a > 0);
		// Test student Id
		Student s5 = new Student("John", "Church", "jmchurc2", "jmchurc1@ncsu.edu", "beachclub", 17);
		int b = s4.compareTo(s5);
		assertTrue(b < 0);
		// Test catches
		Student s6 = null;
		try {
			s5.compareTo(s6);
			fail("Failed to throw an exception");
		} catch (IllegalArgumentException e) {
			assertEquals("Student cannot be null.", e.getMessage());
		}

	}
	
	/**
	 * Test canAdd(), ensuring that it can accurately determine whether it is legal to add a proposed course
	 * to a Student's schedule 
	 */
	@Test
	public void testCanAdd() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD,
				STUDENT_MAX_CREDITS);
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
		s1.getSchedule().addCourseToSchedule(c1);
		
		Course c2 = new Course("CSC226", "Discrete Math", "004", 5, "professor2", 50, "A");
		assertTrue(s1.canAdd(c2));
		s1.getSchedule().addCourseToSchedule(c2);
		assertEquals(10, s1.getSchedule().getScheduleCredits());
		
		Course c3 = null;
		try {
			assertFalse(s1.canAdd(c3));
			s1.getSchedule().addCourseToSchedule(c3);
			fail("Cannot add null course to schedule.");
		} catch (NullPointerException e) {
			assertEquals(10, s1.getSchedule().getScheduleCredits());
		}
		
		Course c4 = new Course("CSC500", "More Programming", "002", 5, "professor3", 28, "TH", 1100, 1230);
		assertTrue(s1.canAdd(c4));
		s1.getSchedule().addCourseToSchedule(c4);
		assertEquals(15, s1.getSchedule().getScheduleCredits());
		
		Course c5 = new Course("CSC900", "Another Programming Course", "004", 3, "professor9", 50, "A");
		assertFalse(s1.canAdd(c5));
	}

}
