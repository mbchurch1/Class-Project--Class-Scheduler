package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the WolfScheduler class.
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
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
	/** Course enrollment capacity */
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

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
	/** CourseNoConflict enrollment capacity */
	private static final int ENROLLMENT_CAPCNC = 10;
	/** CourseNoConflict meeting days */
	private static final String MEETING_DAYSCNC = "MW";
	/** CourseNoConflict start time */
	private static final int START_TIMECNC = 1120;
	/** CourseNoConflict end time */
	private static final int END_TIMECNC = 1310;

	/** CourseWithConflict name */
	private static final String NAMECONF = "CSC217";
	/** CourseWithConflict title */
	private static final String TITLECONF = "Software Development Fundamentals Lab";
	/** CourseWithConflict section */
	private static final String SECTIONCONF = "202";
	/** CourseWithConflict credits */
	private static final int CREDITSCONF = 1;
	/** CourseWithConflict instructor id */
	private static final String INSTRUCTOR_IDCONF = "sesmith5";
	/** CourseWithConflict enrollment capacity */
	private static final int ENROLLMENT_CAPCONF = 10;
	/** CourseWithConflict meeting days */
	private static final String MEETING_DAYSCONF = "M";
	/** CourseWithConflict start time */
	private static final int START_TIMECONF = 1040;
	/** CourseWithConflict end time */
	private static final int END_TIMECONF = 1230;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws IOException if unable to reset the sourcePath to the destinationPath
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog() to make sure it's initialized as an empty list
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("CSC216", "001"));
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.newCourseCatalog() to ensure that calling
	 * newCourseCatalog() clears all course entries from the catalog
	 */
	@Test
	public void testNewCourseCatalog() {
		// Test that if there are courses in the directory, they
		// are removed after calling newCourseCatalog().
		try {
			CourseCatalog cc = new CourseCatalog();

			cc.loadCoursesFromFile(validTestFile);
			assertEquals(13, cc.getCourseCatalog().length);

			cc.newCourseCatalog();
			assertEquals(0, cc.getCourseCatalog().length);
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Tests loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		// Test with file that does not exist. Should have be unable to read fileName.
		CourseCatalog cc = new CourseCatalog();

		try {
			cc.loadCoursesFromFile("test-files/this_dir_doesnt_exist/this_file_doesnt_either");
			fail("The file that was attemtped to be loaded does not exist");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file test-files/this_dir_doesnt_exist/this_file_doesnt_either",
					e.getMessage());
			assertEquals(0, cc.getCourseCatalog().length);
		}

		// Test with file that does exist and contains valid Course records
		try {
			cc.loadCoursesFromFile(validTestFile);
			assertEquals(13, cc.getCourseCatalog().length);
		} catch (IllegalArgumentException e) {
			fail("The test file that was attempted to be loaded does exist and contains 13 valid course records");
		}

		// Test with file that does exist and contains invalid Course records
		try {
			cc.loadCoursesFromFile(invalidTestFile);
			assertEquals(0, cc.getCourseCatalog().length);
		} catch (IllegalArgumentException e) {
			fail("The test file that was attempted to be loaded does exist and contains 0 valid course records");
		}
	}

	/**
	 * Tests CourseCatalog.addCourseToCatalog() to ensure that it only adds valid
	 * course records that are not duplicates of course records currently in the
	 * catalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Test adding 1 valid Course record to the Catalog
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		String[][] courseCatalogArray = cc.getCourseCatalog();
		assertEquals(1, courseCatalogArray.length);
		assertEquals(NAME, courseCatalogArray[0][0]);
		assertEquals(SECTION, courseCatalogArray[0][1]);
		assertEquals(TITLE, courseCatalogArray[0][2]);
		Course validFirstCourse = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME);
		assertEquals(validFirstCourse.getMeetingString(), courseCatalogArray[0][3]);

		// Test adding a Course with duplicate name and section
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME));
		assertEquals(1, courseCatalogArray.length);

		// Test adding another Course but with non-duplicate name and section
		cc.addCourseToCatalog(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, ENROLLMENT_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		String[][] secondCourseCatalogArray = cc.getCourseCatalog();
		assertEquals(2, secondCourseCatalogArray.length);
		assertEquals(2, cc.getCourseCatalog().length);
		assertEquals(NAMECNC, secondCourseCatalogArray[0][0]);
		assertEquals(SECTIONCNC, secondCourseCatalogArray[0][1]);
		assertEquals(TITLECNC, secondCourseCatalogArray[0][2]);
		Course validSecondCourse = new Course(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, ENROLLMENT_CAPCNC,
				MEETING_DAYSCNC, START_TIMECNC, END_TIMECNC);
		assertEquals(validSecondCourse.getMeetingString(), secondCourseCatalogArray[0][3]);

		// Test adding an invalid Course record
		try {
			cc.addCourseToCatalog(NAMECONF, TITLECONF, null, CREDITSCONF, INSTRUCTOR_IDCONF, ENROLLMENT_CAPCONF, MEETING_DAYSCONF,
					START_TIMECONF, END_TIMECONF);
			fail("The test file that was attempted to be added has an invalid section");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid section.", e.getMessage());
			assertEquals(2, cc.getCourseCatalog().length);
		}
	}

	/**
	 * Tests CourseCatalog.removeCourseFromCatalog() to ensure that it accurately
	 * removes the course from the catalog designated by the parameterized name and
	 * section
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Test adding three valid courses to the catalog and removing one
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, ENROLLMENT_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		cc.addCourseToCatalog(NAMECONF, TITLECONF, SECTIONCONF, CREDITSCONF, INSTRUCTOR_IDCONF, ENROLLMENT_CAPCONF, MEETING_DAYSCONF,
				START_TIMECONF, END_TIMECONF);
		assertTrue(cc.removeCourseFromCatalog(NAMECNC, SECTIONCNC));
		assertEquals(2, cc.getCourseCatalog().length);

		// Test removing a course that is not in the catalog
		assertFalse(cc.removeCourseFromCatalog("CSC845", "010"));
		assertEquals(2, cc.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.getCourseFromCatalog() to ensure that it accurately gets
	 * the details of the course from the catalog designated by the parameterized
	 * name and section
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Create 3 course object for comparison to courses that will be added & pulled
		// from the catalog
		Course firstCourseAdded = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME);

		// Test adding three valid courses to the catalog and getting one
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, ENROLLMENT_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		cc.addCourseToCatalog(NAMECONF, TITLECONF, SECTIONCONF, CREDITSCONF, INSTRUCTOR_IDCONF, ENROLLMENT_CAPCONF, MEETING_DAYSCONF,
				START_TIMECONF, END_TIMECONF);
		Course firstCoursePulled = cc.getCourseFromCatalog(NAME, SECTION);
		assertEquals(firstCourseAdded, firstCoursePulled);

		// Test getting a course not in the catalog
		assertNull(cc.getCourseFromCatalog("CSC845", "010"));
	}

	/**
	 * Tests CourseCatalog.getCourseCatalog() to ensure that it accurately gets the
	 * current course catalog
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Test adding 1 valid Course record to the Catalog
		cc.addCourseToCatalog(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, ENROLLMENT_CAPCNC, MEETING_DAYSCNC,
				START_TIMECNC, END_TIMECNC);
		String[][] courseCatalogArray = cc.getCourseCatalog();
		assertEquals(1, courseCatalogArray.length);
		assertEquals(NAMECNC, courseCatalogArray[0][0]);
		assertEquals(SECTIONCNC, courseCatalogArray[0][1]);
		assertEquals(TITLECNC, courseCatalogArray[0][2]);
		Course validFirstCourse = new Course(NAMECNC, TITLECNC, SECTIONCNC, CREDITSCNC, INSTRUCTOR_IDCNC, ENROLLMENT_CAPCNC,
				MEETING_DAYSCNC, START_TIMECNC, END_TIMECNC);
		assertEquals(validFirstCourse.getMeetingString(), courseCatalogArray[0][3]);

		// Test adding another valid Course and ensuring that it was added in the
		// correct sorted order
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		String[][] secondCourseCatalogArray = cc.getCourseCatalog();
		assertEquals(2, secondCourseCatalogArray.length);
		assertEquals(2, cc.getCourseCatalog().length);
		assertEquals(NAME, secondCourseCatalogArray[1][0]);
		assertEquals(SECTION, secondCourseCatalogArray[1][1]);
		assertEquals(TITLE, secondCourseCatalogArray[1][2]);
		Course validSecondCourse = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME);
		assertEquals(validSecondCourse.getMeetingString(), secondCourseCatalogArray[1][3]);
	}

	/**
	 * Tests saveCourseCatalog() to ensure that it either writes to file accurately
	 * or throws an exception if the file can not be written to
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, cc.getCourseCatalog().length);
		try {
		cc.saveCourseCatalog("test-files/actual_CC_records.txt");
		checkFiles("test-files/expected_CC_records.txt", "test-files/actual_CC_records.txt");
		} catch (IllegalArgumentException e) {
			fail("Could not save the file.");
		}
		// Test saving to a nonexistant file
		try {
			cc.saveCourseCatalog("test-files/this_dir_doesnt_exist/this_is_also_nonexistent");
			fail("The file to which the catalog was being saved does not exist");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to write to file test-files/this_dir_doesnt_exist/this_is_also_nonexistent",
					e.getMessage());
		}
	}
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}