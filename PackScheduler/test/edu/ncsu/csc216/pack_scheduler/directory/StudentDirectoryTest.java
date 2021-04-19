package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory. These tests now accommodate for the StudentDirectory now being a sortedList.
 * These tests ensure that a new directory can be created, that students can be added to and
 * removed from the directory, and that the directory can be accurately written to file for saving. 
 * 
 * @author Sarah Heckman
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet 
 * 
 */
public class StudentDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory() to make sure it's initialized as an empty list
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.newStudentDirectory() to ensure that calling
	 * newStudentDirectory() clears all student entries from the directory
	 */
	@Test
	public void testNewStudentDirectory() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		try {
			StudentDirectory sd = new StudentDirectory();

			sd.loadStudentsFromFile(validTestFile);
			assertEquals(10, sd.getStudentDirectory().length);

			sd.newStudentDirectory();
			assertEquals(0, sd.getStudentDirectory().length);
		} catch (IllegalArgumentException e) {
			// assertEquals("Unable to read file " + validTestFile, e.getMessage());
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile() to ensure that it only
	 * loads valid student records to the directory
	 */
	@Test
	public void testLoadStudentsFromFile() {

		StudentDirectory sd = new StudentDirectory();
		try {
			// Test valid file
			sd.loadStudentsFromFile(validTestFile);
			assertEquals(10, sd.getStudentDirectory().length);

			// Test invalid file
			sd.newStudentDirectory();
			sd.loadStudentsFromFile(invalidTestFile);
			assertEquals(0, sd.getStudentDirectory().length);
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Tests StudentDirectory.addStudent() to ensure that it only adds valid student
	 * records to the directory based on parameters such as password
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);

		// Test emptyString Student password
		sd.newStudentDirectory();
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test null Student password
		sd.newStudentDirectory();
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test emptyString Student repeatPassword
		sd.newStudentDirectory();
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test null Student repeatPassword
		sd.newStudentDirectory();
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test whether hashPW and repeatHashPW match
		sd.newStudentDirectory();
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "qm", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Passwords do not match", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.removeStudent() to ensure that it can remove student records
	 * from the directory and the remaining records will be unaffected
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory() to ensure that following editing the 
	 * directory, the directory can be accurately saved when compared to the expected directory
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * Test the getStudentByID
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd2 = new StudentDirectory();
		sd2.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		// test student with found ID
		Student s = sd2.getStudentById(ID);
		StudentDirectory sd1 = new StudentDirectory();
		sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		// test student with found ID
		Student s1 = sd1.getStudentById(ID);
		assertEquals(s1.toString(), s.toString());
		assertNull(sd2.getStudentById("Matt"));
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
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
