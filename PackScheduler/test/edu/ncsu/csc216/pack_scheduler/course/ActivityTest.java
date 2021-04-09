package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Activity checkConflict method.
 * 
 * @author Matthew Church
 *
 */
public class ActivityTest {

	/**
	 * Test method for checkConflict in Activity with no conflict.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330,
				1445);
		try {
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
	}

	/**
	 * Test method for checkConflict in Activity with no conflict in reverse.
	 */
	@Test
	public void testCheckConflictReverse() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330,
				1445);
		try {
			a2.checkConflict(a1);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
	}

	/**
	 * Tests checkConflict in Activity with a conflict.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "W", 1400, 1430);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330,
				1445);
		try {
			a1.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "W 2:00PM-2:30PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM",
					a2.getMeetingString());
		}
	}

	/**
	 * Tests checkConflict with two Courses, one only conflicting on one day.
	 */
	@Test
	public void testCheckConflictWithConflictOnOneDay() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MTWHF", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		try {
			a1.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MTWHF 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM",
					a2.getMeetingString());
		}
	}

	/**
	 * Tests checkConflict in Activity with a conflict.
	 */
	@Test
	public void testCheckConflictWithConflictEndTimeStartTime() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1445, 1530);
		try {
			a1.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 2:45PM-3:30PM",
					a2.getMeetingString());
		}
	}

	/**
	 * Tests checkConflict with arranged Courses.
	 */
	@Test
	public void testCheckConflictWithArrangedCourses() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
		try {
			a1.checkConflict(a2);
		} catch (ConflictException e) {
			fail("A ConflictException was thrown with two arranged Activities.");
		}
	}

}
