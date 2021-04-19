package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "Ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	
	/**
	 * Tests FacultyDirectory() to make sure it's initialized as an empty list
	 */
	@Test
	public void testFacultyDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.newFacultyDirectory() to ensure that calling
	 * newFacultyDirectory() clears all faculty entries from the directory
	 */
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		try {
			FacultyDirectory fd = new FacultyDirectory();

			fd.loadFacultyFromFile(validTestFile);
			assertEquals(8, fd.getFacultyDirectory().length);

			fd.newFacultyDirectory();
			assertEquals(0, fd.getFacultyDirectory().length);
		} catch (IllegalArgumentException e) {
			// assertEquals("Unable to read file " + validTestFile, e.getMessage());
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	
	/**
	 * Tests FacultyDirectory.loadFacultyFromFile() to ensure that it only
	 * loads valid faculty records to the directory
	 */
	@Test
	public void testLoadFacultyFromFile() {

		FacultyDirectory fd = new FacultyDirectory();
		try {
			// Test valid file
			fd.loadFacultyFromFile(validTestFile);
			assertEquals(8, fd.getFacultyDirectory().length);

			// Test invalid file
			fd.newFacultyDirectory();
			fd.loadFacultyFromFile(invalidTestFile);
			assertEquals(0, fd.getFacultyDirectory().length);
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	
	/**
	 * Tests FacultyDirectory.addFaculty() to ensure that it only adds valid faculty
	 * records to the directory based on parameters such as password
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);

		// Test emptyString Faculty password
		fd.newFacultyDirectory();
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test null Faculty password
		fd.newFacultyDirectory();
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test emptyString Faculty repeatPassword
		fd.newFacultyDirectory();
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test null Faculty repeatPassword
		fd.newFacultyDirectory();
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test whether hashPW and repeatHashPW match
		fd.newFacultyDirectory();
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "qm", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Passwords do not match", e.getMessage());
		}
	}
	
	
	/**
	 * Tests FacultyDirectory.removeFaculty() to ensure that it can remove faculty records
	 * from the directory and the remaining records will be unaffected
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add students and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("nbrady"));
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Lacey", facultyDirectory[5][0]);
		assertEquals("Walls", facultyDirectory[5][1]);
		assertEquals("lwalls", facultyDirectory[5][2]);
	}
	
	
	/**
	 * Tests FacultyDirectory.saveFacultyDirectory() to ensure that following editing the 
	 * directory, the directory can be accurately saved when compared to the expected directory
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add a faculty
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", 2);
		fd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", 3);
		fd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", 1);
		assertEquals(3, fd.getFacultyDirectory().length);
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	
	/**
	 * Test the getFacultyByID
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory fd2 = new FacultyDirectory();
		fd2.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		// test faculty with found ID
		Faculty f = fd2.getFacultyById(ID);
		FacultyDirectory fd1 = new FacultyDirectory();
		fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		// test faculty with found ID
		Faculty f1 = fd1.getFacultyById(ID);
		assertEquals(f1.toString(), f.toString());
		assertNull(fd2.getFacultyById("Matt"));
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
