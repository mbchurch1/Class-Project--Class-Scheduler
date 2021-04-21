package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


/**
 * Reads Faculty records from text files.  Writes a set of Faculty records to a file.
 * @author John Firlet
 * @author Will Goodwin
 * @author Matthew Church
 */
public class FacultyRecordIO {
	
	/**
	 * Reads Faculty records from a file by the given filename and the records are
	 * returned in a SortedList 
	 * @param fileName  file to read in Faculty records
	 * @throws FileNotFoundException  if the file does not exist
	 * @return an SortedList of Faculty records
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
			LinkedList<Faculty> faculties = new LinkedList<Faculty>();
			while(fileReader.hasNextLine()) {
				try {
					Faculty faculty = processFaculty(fileReader.nextLine()); 
					boolean duplicate = false;
					for (int i = 0; i < faculties.size(); i++) {
						User otherFaculty = faculties.listIterator(i).next();
						if (faculty.equals(otherFaculty)) {
							duplicate = true;
							break;
						}
					}
					if (!duplicate) {
						faculties.add(faculty);
					}
		         } catch(IllegalArgumentException e) {
		        	 //This line is an invalid Faculity record, so it gets skipped
		         }
			}
		fileReader.close();		
		return faculties;
	}

	
	
	/**
	 * Reads in a line from a file and creates a faculty record to return
	 * @param nextLine  line from file to be read into a faculity record
	 * @throws IllegalArgumentException if the file has an invalid entry
	 * @return f a Faculty record object 
	 */
	private static Faculty processFaculty(String nextLine) {
		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter(",");
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String password = null;
		int maxCourses = 0;
		
		//since there are only 6 variables, no hasNext is needed
		try {
		firstName = scan.next();
		lastName = scan.next();
		id = scan.next();
		email = scan.next();
		password = scan.next();
		maxCourses = scan.nextInt();
		scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid faculty record");
		}
		Faculty f = new Faculty(firstName, lastName, id, email, password, maxCourses);
		return f;
	}





	/**
	 * Writes a list of Faculty records to a file
	 * 
	 * @param fileName  file to write faculty records to
	 * @param facultyDirectory list of faculties to write to fileName
	 * @throws IOException if cannot write to file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
		    fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}
