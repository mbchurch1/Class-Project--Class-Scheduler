package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

public class FacultyRecordIOTest {

	/** Valid file for testing */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** invalid file for testing */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Faculty for testing 1 */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,2";
	/** Faculty for testing 2 */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,3";
	/** Faculty for testing 3 */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,1";
	/** Faculty for testing 4 */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,3";
	/** Faculty for testing 5 */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,1";
	/** Faculty for testing 6 */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,3";
	/** Faculty for testing 7 */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,1";
	/** Faculty for testing 8 */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,2";
	/** String array of valid faculty for testing */
	private String[] validFaculty = { validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4,
			validFaculty5, validFaculty6, validFaculty7 };
	/** String array of sorted valid faculty for testing */
	private String[] sortedValidFaculty = { validFaculty3, validFaculty6, validFaculty2, validFaculty5, validFaculty1,
			validFaculty4, validFaculty7, validFaculty0, };
	
	/** sets up a hashed password for testing */
	private String hashPW;
	/** hash algorithm used in storing passwords */
	private static final String HASH_ALGORITHM = "SHA-256";
	/**
	 * Hashes the password for storage. Throws a NoSuchAlgorithmException if the algorithm can't be found.
	 * @throws Exception if the password was unable to be hashed
	 */
	@Before
	public void setUp() throws Exception {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());
			for (int i = 0; i < validFaculty.length; i++) {
				validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
				sortedValidFaculty[i] = sortedValidFaculty[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	
	/**
	 * Tests ReadFacultyRecords for the correct number read in, and that the faculty were read in correctly.
	 */
	@Test
	public void testReadFacultyRecords() {
		try {
			SortedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());

			for (int i = 0; i < validFaculty.length; i++) {
				assertEquals(sortedValidFaculty[i], faculty.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	/**
	 * Tests that ReadFacultyRecords when passing in an invalid file.
	 * This should result in no faculty being added to the directory.
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		SortedList<Faculty> faculty;
		try {
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
//	/**
//	 * Tests WriteFacultyRecords, ensuring that it properly writes the records to the designated file.
//	 * It uses the companion method, checkFiles, to ensure that the Faculty record file written to in 
//	 * this method matched a known standard Faculty record file, thus checking that writeFacultyRecords
//	 * is functioning properly.
//	 */
//	@Test
//	public void testWriteFacultyRecords() {
//		SortedList<Faculty> faculty = new SortedList<Faculty>();
//		faculty.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
//		faculty.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
//		faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
//		try {
//			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
//		} catch (IOException e) {
//			fail("Cannot write to course records file");
//		}
//
//		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
//
//	}
	
	/**
	 * Tests writeFacultyRecords for when there is no permissions to write the file. This test 
	 * should confirm that a file can not be written to when permissions are not provided. 
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
	    SortedList<Faculty> faculty = new SortedList<Faculty>();
	    faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	    	FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", faculty);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("\\home\\sesmith5\\actual_faculty_records.txt (The system cannot find the path specified)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	
	
	
	
	/**
	 * Compares two files to confirm they contain the same information. This method is a companion method 
	 * that helps ensure that writeStudentFiles is writing the correct information to file by comparing 
	 * contents of a sample file against a file in which the structure of the contents is known.
	 * 
	 * @param expFile the expected file
	 * @param actFile program output file
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + "Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading file");
		}
	}

}
