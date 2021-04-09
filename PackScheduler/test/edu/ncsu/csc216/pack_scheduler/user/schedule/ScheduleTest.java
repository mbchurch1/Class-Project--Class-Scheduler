package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * ScheduleTest class to test the Schedule class
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 */

public class ScheduleTest {
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Course cap */
	private static final int E_CAP = 30;

	/** CourseNoConflict name */
	private static final String NAMECNC = "CSC116";
	/** CourseNoConflict title */
	private static final String TITLECNC = "Intro to Programming - Java";
	/** CourseNoConflict section */
	private static final String SECTIONCNC = "002";
	/** CourseNoConflict credits */
	private static final int CREDITSCNC = 3;
	/** CourseNoConflict instructor id */
	private static final String INSTRUCTOR_IDCNC = "spbalik";
	/** CourseNoConflict meeting days */
	private static final String MEETING_DAYSCNC = "MW";
	/** CourseNoConflict start time */
	private static final int START_TIMECNC = 1120;
	/** CourseNoConflict end time */
	private static final int END_TIMECNC = 1310;
	/** CourseNoConflict cap */
	private static final int E_CAPCNC = 25;

	/**
	 * test schedule constructor
	 */
	@Test

	public void testSchedule() {
		Schedule testSchedule = new Schedule();
		assertEquals("My Schedule", testSchedule.getTitle());
	}

	/**
	 * test addCourseToSchedule
	 */
	@Test
	public void testAddCourseToSchedule() {
		// add one course to schedule
		Schedule s1 = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertTrue(s1.addCourseToSchedule(c1));
		// test adding a course with the same name
		try {

			Course c2 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME,
					END_TIME);


			assertFalse(s1.addCourseToSchedule(c2));
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
		// assertFalse(s1.addCourseToSchedule(c2));

//		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
//		assertFalse(s1.addCourseToSchedule(c2));

		try {
			Course c3 = new Course(NAME, "Not Same", "003", 5, "mbchurch", E_CAPCNC, "MW", START_TIMECNC, END_TIMECNC);
			assertFalse(s1.addCourseToSchedule(c3));
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}

		// assertFalse(s1.addCourseToSchedule(c3));
	}

	/**
	 * test removeCourseFromSchedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s2 = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME, END_TIME);
		s2.addCourseToSchedule(c1);
		assertTrue(s2.removeCourseFromSchedule(c1));
		// try removing the same course
		assertFalse(s2.removeCourseFromSchedule(c1));
		assertFalse(s2.removeCourseFromSchedule(null));
	}

	/**
	 * test resetSchedule
	 */
	@Test
	public void testResetSchedule() {
		Schedule s3 = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME, END_TIME);
		// check that the Course was added
		assertTrue(s3.addCourseToSchedule(c1));
		s3.setTitle("Schedules are cool");
		// check that the title was changed
		assertEquals("Schedules are cool", s3.getTitle());
		s3.resetSchedule();
		assertEquals("My Schedule", s3.getTitle());
	}

	/**
	 * test getScheduledCourses
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule s4 = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, E_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		s4.addCourseToSchedule(c1);
		s4.addCourseToSchedule(c2);
		String[][] sa = s4.getScheduledCourses();
		assertEquals(2, sa.length);
		assertEquals(NAMECNC, sa[1][0]);
		assertEquals(SECTIONCNC, sa[1][1]);
		assertEquals(TITLECNC, sa[1][2]);
		assertEquals(c2.getMeetingString(), sa[1][3]);
		assertEquals(NAME, sa[0][0]);
		assertEquals(SECTION, sa[0][1]);
		assertEquals(TITLE, sa[0][2]);
		assertEquals(c1.getMeetingString(), sa[0][3]);
	}

	/**
	 * test setTitle
	 */
	@Test
	public void testSetTitle() {
		Schedule s5 = new Schedule();
		s5.setTitle("Schedules, man");
		assertEquals("Schedules, man", s5.getTitle());
		try {
			s5.setTitle("");
			fail("This title passed");
		} catch (IllegalArgumentException e) {
			assertEquals("Schedule title cannot be blank.", e.getMessage());
		}
		try {
			s5.setTitle(null);
			fail("This title passed");
		} catch (IllegalArgumentException e) {
			assertEquals("Schedule title cannot be blank.", e.getMessage());
		}
	}
	/**
	 * tests getScheduleCredits
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s6 = new Schedule();
		assertEquals(0, s6.getScheduleCredits());
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, E_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		s6.addCourseToSchedule(c1);
		s6.addCourseToSchedule(c2);
		assertEquals(6, s6.getScheduleCredits());

	}
	/**
	 * tests canAdd
	 */
	@Test
	public void testCanAdd() {
		Schedule s7 = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, E_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, E_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		assertTrue(s7.canAdd(c1));
		s7.addCourseToSchedule(c1);
		assertFalse(s7.canAdd(c1));
		assertTrue(s7.canAdd(c2));
		
	}
}
