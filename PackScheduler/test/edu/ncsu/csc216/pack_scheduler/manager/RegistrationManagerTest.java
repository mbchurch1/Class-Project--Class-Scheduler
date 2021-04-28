package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Tests Registration Manager class.
 * 
 * @author Matthew Church
 * @author John Firlet
 * @author Will Goodwin
 *
 */

public class RegistrationManagerTest {

	/** Instance of RegistrationManager */
	private RegistrationManager manager;
	/** Registrar user name */
	private String registrarUsername;
	/** Registrar password */
	private String registrarPassword;
	/** Registrar properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Sets up the CourseManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.logout();
		manager.clearData();
		
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			registrarUsername = prop.getProperty("id");
			registrarPassword = prop.getProperty("pw");
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot process properties file.");
		}
	}

	/**
	 * Tests whether the Course Catalog can be pulled properly
	 */
	@Test
	public void testGetCourseCatalog() {

		RegistrationManager secondManager = RegistrationManager.getInstance();

		assertEquals(secondManager.getCourseCatalog(), manager.getCourseCatalog());

	}

	/**
	 * Tests whether the Student Directory can be pulled properly
	 */
	@Test
	public void testGetStudentDirectory() {
		RegistrationManager thirdManager = RegistrationManager.getInstance();

		assertEquals(thirdManager.getStudentDirectory(), manager.getStudentDirectory());
	}

	/**
	 * Tests logging into PackScheduler
	 */
	@Test
	public void testLogin() {
		Properties prop = new Properties();
		String userId = "";
		String userPw = "";

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			userId = prop.getProperty("id");
			userPw = prop.getProperty("pw");

		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create user.");
		}

		assertTrue(manager.login(userId, userPw));
		assertFalse(manager.login(userId, "junk"));
		manager.logout();

		manager.getStudentDirectory().addStudent("Jeff", "Wilkes", "jwilkes", "jw@ncsu.edu", "pw", "pw", 17);

		// assertTrue(manager.getCurrentUser(), )
		assertTrue(manager.login("jwilkes", "pw"));
		try {
			manager.login("kevin", "pwe");
		} catch (IllegalArgumentException e) {
			assertEquals("User doesn't exist.", e.getMessage());
		}
		
		manager.getStudentDirectory().addStudent("Bob", "Boats", "bboats", "bb@ncsu.edu", "mypass", "mypass", 15);

		assertFalse(manager.login("bboats", "mypass"));
		
		manager.logout();
		//Test logging in as Faculty
		Faculty faculty = new Faculty("Fac", "Ulty", "fulty", "fulty@ncsu.edu", "pw", 3);
		manager.getFacultyDirectory().addFaculty("Fac", "Ulty", "fulty2", "fulty@ncsu.edu", "pw", "pw", 3);
		manager.login("fulty2", "pw");
		String[][] facultyDirectory = manager.getFacultyDirectory().getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals("Fac", facultyDirectory[0][0]);
		assertEquals("Ulty", facultyDirectory[0][1]);
		assertEquals("fulty2", facultyDirectory[0][2]);
	}

	/**
	 * Tests logging out of PackScheduler
	 */
	@Test
	public void testLogout() {
		RegistrationManager fourthManager = RegistrationManager.getInstance();

		assertEquals(fourthManager.getCurrentUser(), manager.getCurrentUser());

		manager.logout();
		fourthManager.logout();

		assertEquals(fourthManager.getCurrentUser(), manager.getCurrentUser());
	}

	/**
	 * Tests whether the Current User can be pulled properly
	 */
	@Test
	public void testGetCurrentUser() {
		RegistrationManager fifthManager = RegistrationManager.getInstance();

		assertEquals(fifthManager.getCurrentUser(), manager.getCurrentUser());
	}
	
	/**
	 * Tests RegistrationManager.enrollStudentInCourse()
	 */
	@Test
	public void testEnrollStudentInCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		
		manager.logout(); //In case not handled elsewhere
		
		//test if not logged in
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
		}
		
		//test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.", registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();
		
		manager.login("efrost", "pw");
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		
		//Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC216-001\n", 3, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC216-001\n", 1, scheduleFrostArray.length);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "CSC216", scheduleFrostArray[0][0]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "001", scheduleFrostArray[0][1]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "Software Development Fundamentals", scheduleFrostArray[0][2]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "TH 1:30PM-2:45PM", scheduleFrostArray[0][3]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "9", scheduleFrostArray[0][4]);
				
		manager.logout();
		
		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
		
		//Check Student Schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 9, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 3, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "8", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC226", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[1][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC116", scheduleHicksArray[2][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "003", scheduleHicksArray[2][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Intro to Programming - Java", scheduleHicksArray[2][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[2][4]);
		
		manager.logout();
	}
	
	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		
		manager.logout(); //In case not handled elsewhere
		
		//test if not logged in
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
		}
		
		//test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.", registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();
		
		manager.login("efrost", "pw");
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		
		assertFalse("Action: drop\nUser: efrost\nCourse: CSC217-211\nResults: False - student not enrolled in the course", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		
		//Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC226-001, then removed\n", 0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC226-001, then removed\n", 0, scheduleFrostArray.length);
		
		manager.logout();
		
		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
		
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 9, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 3, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC226", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[1][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC116", scheduleHicksArray[2][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "003", scheduleHicksArray[2][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Intro to Programming - Java", scheduleHicksArray[2][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[2][4]);
		
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		
		//Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", 6, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", 2, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "9", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "CSC116", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "003", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "Intro to Programming - Java", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "9", scheduleHicksArray[1][4]);
		
		assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		
		//Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", 3, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", 1, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "CSC116", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "003", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "Intro to Programming - Java", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "9", scheduleHicksArray[0][4]);
		
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		
		//Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001, CSC116-003\n", 0, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001, CSC116-003\n", 0, scheduleHicksArray.length);
		
		manager.logout();
	}
	
	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		
		manager.logout(); //In case not handled elsewhere
		
		//Test if not logged in
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
		}
		
		//test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.", registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();
		
		manager.login("efrost", "pw");
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		
		manager.resetSchedule();
		//Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC226-001, then schedule reset\n", 0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC226-001, then schedule reset\n", 0, scheduleFrostArray.length);
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC226", "001").getCourseRoll().getOpenSeats());
		
		manager.logout();
		
		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
		
		manager.resetSchedule();
		//Check Student schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, then schedule reset\n", 0, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, then schedule reset\n", 0, scheduleHicksArray.length);
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC226", "001").getCourseRoll().getOpenSeats());
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC216", "001").getCourseRoll().getOpenSeats());
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC116", "003").getCourseRoll().getOpenSeats());
		
		manager.logout();
	}
	
	/**
	 * Tests adding a course, removing a course, and resetting a faculty's FacultySchedule
	 */
	@Test
	public void testAddRemoveResetFacultyToCourse() {
//		Faculty - (String firstName, String lastName, String id, String email, String password,
//				String repeatPassword, int maxCourses)
//		private static final String FIRST_NAME = "Fac";
//		/** Test last name */
//		private static final String LAST_NAME = "Ulty";
//		/** Test id */
//		private static final String ID = "fulty";
//		/** Test email */
//		private static final String EMAIL = "fulty@ncsu.edu";
//		/** Test password */
//		private static final String PASSWORD = "pw";
//		/** Test max credits */
//		private static final int MAX_COURSES = 3;
		
//		Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
//				String meetingDays, int startTime, int endTime)
//	    /** Course name */
//	    private static final String NAME = "CSC216";
//	    /** Course title */
//	    private static final String TITLE = "Software Development Fundamentals";
//	    /** Course section */
//	    private static final String SECTION = "001";
//	    /** Course credits */
//	    private static final int CREDITS = 3;
//	    /** Course instructor id */
//	    private static final String INSTRUCTOR_ID = "sesmith5";
//	    /** Max number of students accepted for enrollment in the Course */
//	    private static final int ENROLLMENT_CAP = 50;
//	    /** Course meeting days */
//	    private static final String MEETING_DAYS = "MW";
//	    /** Course start time */
//	    private static final int START_TIME = 1330;
//	    /** Course end time */
//	    private static final int END_TIME = 1445;
		
		//TS tests may require that the faculty member be contained within RegistrationManager.getFacultyDirectory -->
		//   if so, I can use the below to add a Faculty to the FacultyDirectory, then pass that into the addFacutyToCourse() method
		//manager.getFacultyDirectory().addFaculty("Fac", "Ulty", "fulty", "fulty@ncsu.edu", "pw", "pw", 3);
		
		Course course = new Course("CSC216", "Software Development Fundamentals", "001", 3, null, 50, "MW", 1330, 1445);
		Faculty faculty = new Faculty("Fac", "Ulty", "fulty", "fulty@ncsu.edu", "pw", 3);
		RegistrationManager newManager = RegistrationManager.getInstance();
		
		newManager.login(registrarUsername, registrarPassword);
		assertTrue(newManager.addFacultyToCourse(course, faculty));
		assertEquals(1, faculty.getSchedule().getNumScheduledCourses());
		
		StudentDirectory directory = newManager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		newManager.logout();
		newManager.login("efrost", "pw");
		assertFalse(newManager.addFacultyToCourse(course, faculty));
		
		newManager.logout();
		newManager.login(registrarUsername, registrarPassword);
		assertTrue(newManager.removeFacultyFromCourse(course, faculty));
		Course course2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "notNull3", 50, "MW", 1330, 1445);
		try {
			newManager.addFacultyToCourse(course2, faculty);
			fail("Course2 already has an instructor, so it cannot be added to faculty's schedule.");
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added to this faculty member's schedule.", e.getMessage());
			assertEquals(0, faculty.getSchedule().getNumScheduledCourses());
		}
		
		newManager.addFacultyToCourse(course, faculty);
		assertEquals(1, faculty.getSchedule().getNumScheduledCourses());
		newManager.logout();
		newManager.login("efrost", "pw");
		assertFalse(newManager.removeFacultyFromCourse(course, faculty));
		
		newManager.logout();
		newManager.login(registrarUsername, registrarPassword);
		assertEquals(1, faculty.getSchedule().getNumScheduledCourses());
		newManager.resetFacultySchedule(faculty);
		assertEquals(0, faculty.getSchedule().getNumScheduledCourses());
		
		newManager.addFacultyToCourse(course, faculty);
		assertEquals(1, faculty.getSchedule().getNumScheduledCourses());
		newManager.logout();
		newManager.login("efrost", "pw");
		newManager.resetFacultySchedule(faculty);
		assertEquals(1, faculty.getSchedule().getNumScheduledCourses());
	}
	
	
//	/**
//	 * Tests whether enrollStudentInCourse() properly detects a Student vs.
//	 * Registrar and can enroll a student in a course
//	 */
//	@Test
//	public void testEnrollStudentInCourse() {
//		//Test logging in as a registrar and trying to enroll in a course as a registrar
//		manager.logout();
//		RegistrationManager sixthManager = RegistrationManager.getInstance();
//		Course c = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
//		try {
//			sixthManager.enrollStudentInCourse(c);
//			fail("Must be logged in as a Student to add a course.");
//		} catch (IllegalArgumentException e) {
//			assertEquals("Illegal Action", e.getMessage());
//		}
//		manager.logout();
//		
//		//Test logging in as a student and successfully enrolling in a course
//		manager.getStudentDirectory().addStudent("Jeff", "Wilkes", "jwilkes", "jw@ncsu.edu", "pw", "pw", 17);
//		assertTrue(manager.login("jwilkes", "pw"));
//		assertTrue(manager.enrollStudentInCourse(c));
//		
//		//Test enrolling in a duplicate Course as a student user
//		Course c2 = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
//		assertFalse(manager.enrollStudentInCourse(c2));
//		manager.logout();
//	}
//
//	/**
//	 * Tests whether a student currentUser can drop a course 
//	 */
//	@Test
//	public void testDropStudentFromCourse() {
//		//Test logging in as a student, adding a course to schedule, and then dropping that course
//		manager.logout();
//		manager.getStudentDirectory().addStudent("Roe", "Wonka", "rwonka", "jw@ncsu.edu", "po", "po", 17);
//		manager.login("rwonka", "po");
//		Course c = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
//		assertTrue(manager.enrollStudentInCourse(c));
//		assertTrue(manager.dropStudentFromCourse(c));
//		
//		//Test dropping a course that is not on the student's schedule
//		Course c2 = new Course("CSC116", "Software", "002", 3, "professor", 10, "MW", 1330, 1445);
//		
////		Course c2 = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
////		assertFalse(manager.dropStudentFromCourse(c2));
//		
//		//Test logging in as a registrar and attempting to drop a course
//		manager.logout();
//		RegistrationManager seventhManager = RegistrationManager.getInstance();
//		try {
//			seventhManager.dropStudentFromCourse(c);
//			fail("Must be logged in as a Student to drop a course.");
//		} catch (IllegalArgumentException e) {
//			assertEquals("Illegal Action", e.getMessage());
//		}
//		manager.logout();
//		
//	}
//	
//	/**
//	 * Tests whether a student currentUser can reset their schedule 
//	 */
//	@Test
//	public void testResetSchedule() {
//		//Test logging in as a student, adding a course to schedule, and then resetting the schedule
//		manager.logout();
//		manager.getStudentDirectory().addStudent("Roe", "Wonka", "rwonka", "jw@ncsu.edu", "po", "po", 17);
//		manager.login("rwonka", "po");
//		CourseCatalog catalog = new CourseCatalog();
//		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
//		Course c = new Course("CSC216", "Software Development Fundamentals", "001", 5, "sesmith5", 50, "MW", 1330, 1445);
//		assertEquals(c, catalog.getCourseFromCatalog("CSC216", "001"));
//		assertTrue(manager.enrollStudentInCourse(c));
////		manager.resetSchedule();
//		
//		//Test logging in as a registrar and attempting to drop a course
//		manager.logout();
//		RegistrationManager eighthManager = RegistrationManager.getInstance();
//		try {
//			eighthManager.resetSchedule();
//			fail("Must be logged in as a Student to reset a student schedule.");
//		} catch (IllegalArgumentException e) {
//			assertEquals("Illegal Action", e.getMessage());
//		}
//		manager.logout();
//	}
}