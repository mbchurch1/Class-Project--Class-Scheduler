package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
/**
 * Tests the CourseRoll class
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 */
public class CourseRollTest {
	/** Student 1 */
	private Student s1 = new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk", "pw", 18);
	/** Student 2 */
	private Student s2 = new Student("Lane", "Berg", "lberg", "sociis@non.org", "pw", 14);
	/** Student 3 */
	private Student s3 = new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw", 12);
	/** Student 4 */
	private Student s4 = new Student("Emerald", "Frost", "efrost", "adipiscing@acipsumPhasellus.edu", "pw", 3);
	/** Student 5 */
	private Student s5 = new Student("Althea", "Hicks", "ahicks", "Phasellus.dapibus@luctusfelis.com", "pw", 11);
	/** Student 6 */
	private Student s6 = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15);
	/** Student 7 */
	private Student s7 = new Student("Dylan", "Nolan", "dnolan", "placerat.Cras.dictum@dictum.net", "pw", 5);
	/** Student 8 */
	private Student s8 = new Student("Rylee", "Puckett", "rpuckett", "rpuckett@ncsu.edu", "pw", 15);
	/** Student 9 */
	private Student s9 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", "pw", 4);
	/** Student 10 */
	private Student s10 = new Student("Griffith", "Stone", "gstone", "porta@magnamalesuadavel.net", "pw", 17);
	/** Student 11 */
	private Student s11 = new Student("Matt", "Church", "mbchurch", "mbchurch@ncsu.edu", "pw", 16);
	
	/**
	 * tests CourseRoll Constructor
	 */
	@Test
	public void testCourseRoll() {
		Course c1 = new Course("CSC217", "SDF Lab", "601", 1, "mbchurch", 30, "A");
		CourseRoll a1 = c1.getCourseRoll();
		assertEquals(30, a1.getEnrollmentCap());
		assertEquals(30, a1.getOpenSeats());
		// essentially moot with new changes
//		try {
//			CourseRoll a2 = c1.getCourseRoll();
//			// do something with a2
//			a2.getEnrollmentCap();
//			fail("The CourseRoll was created, it should'nt have been");
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid enrollment capacity.", e.getMessage());
//		}
	}
	/**
	 * tests getEnrollmentCap()
	 */
	@Test
	public void testGetEnrollmentCap() {
		Course c2 = new Course("CSC101", "Program right", "001", 3, "mbchurch", 20, "A");
		CourseRoll b1 = c2.getCourseRoll();
		assertEquals(20, b1.getEnrollmentCap());
	}
	/**
	 * tests setEnrollmentCap()
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course c3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 30, "A");
		CourseRoll c1 = c3.getCourseRoll();
		assertEquals(30, c1.getEnrollmentCap());
		c1.setEnrollmentCap(25);
		assertEquals(25, c1.getEnrollmentCap());
	}
	/**
	 * tests enroll()
	 */
	@Test
	public void testEnroll() {
		Course c4 = new Course("CSC216", "Programming Concepts - Java", "001", 3, "sesmith5", 10, "A");
		CourseRoll d1 = c4.getCourseRoll();
		d1.enroll(s1);
		d1.enroll(s2);
		d1.enroll(s3);
		d1.enroll(s4);
		//test with different method
		assertEquals(6, d1.getOpenSeats());
		d1.enroll(s5);
		d1.enroll(s6);
		d1.enroll(s7);
		
		try {
			d1.enroll(s1);
			fail("This student should not have been enrolled.");
		} catch (IllegalArgumentException e) {
			assertEquals("Student cannot be added.", e.getMessage());
		}
		d1.enroll(s8);
		d1.enroll(s9);
		d1.enroll(s10);
		assertEquals(0, d1.getOpenSeats());
		d1.enroll(s11);
		assertTrue(d1.canEnroll(s11));
//		try {
//			d1.enroll(s11);
//			fail("The class is full, but the student was enrolled anyway.");
//		} catch (IllegalArgumentException e) {
//			assertEquals("Student cannot be enrolled.", e.getMessage());
//		}
		
	}
	/**
	 * tests drop
	 */
	@Test
	public void testDrop() {
		Course c5 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll g1 = c5.getCourseRoll();
		g1.enroll(s1);
		g1.enroll(s2);
		assertEquals(8, g1.getOpenSeats());
		g1.drop(s2);
		assertEquals(9, g1.getOpenSeats());
	}
	/**
	 * tests getOpenSeats
	 */
	@Test
	public void testGetOpenSeats() {
		Course c6 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll e1 = c6.getCourseRoll();
		assertEquals(10, e1.getOpenSeats());
		e1.enroll(s1);
		assertEquals(9, e1.getOpenSeats());
	}
	/**
	 * tests canEnroll
	 */
	@Test
	public void testCanEnroll() {
		Course c7 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll f1 = c7.getCourseRoll();
		f1.enroll(s1);
		assertFalse(f1.canEnroll(s1));
		assertTrue(f1.canEnroll(s2));
	}

}
