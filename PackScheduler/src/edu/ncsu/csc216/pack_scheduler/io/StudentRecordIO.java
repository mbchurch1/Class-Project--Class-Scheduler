package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Students records from text files.  Writes a set of Student records to a file.
 * @author John Firlet
 * @author Will Goodwin
 * @author Matthew Church
 */
public class StudentRecordIO {

	/**
	 * Reads student records from a file by the given filename and the records are
	 * returned in a SortedList 
	 * @param fileName  file to read in student records
	 * @throws FileNotFoundException  if the file does not exist
	 * @return an SortedList of student records
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
			SortedList<Student> students = new SortedList<Student>();
			while(fileReader.hasNextLine()) {
				try {
					Student student = processStudent(fileReader.nextLine()); 
					boolean duplicate = false;
					for (int i = 0; i < students.size(); i++) {
						User otherStudent = students.get(i);
						if (student.equals(otherStudent)) {
							duplicate = true;
							break;
						}
					}
					if (!duplicate) {
						students.add(student);
					}
		         } catch(IllegalArgumentException e) {
		        	 //This line is an invalid Student record, so it gets skipped
		         }
			}
		fileReader.close();		
		return students;
	}

	
	
	/**
	 * Reads in a line from a file and creates a student record to return
	 * @param nextLine  line from file to be read into a student record
	 * @throws IllegalArgumentException if the file has an invalid entry
	 * @return s a Student record object 
	 */
	private static Student processStudent(String nextLine) {
		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter(",");
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String password = null;
		int maxCredits = 0;
		
		//since there are only 6 variables, no hasNext is needed
		try {
		firstName = scan.next();
		lastName = scan.next();
		id = scan.next();
		email = scan.next();
		password = scan.next();
		maxCredits = scan.nextInt();
		scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid student record");
		}
		Student s = new Student(firstName, lastName, id, email, password, maxCredits);
		return s;
	}





	/**
	 * Writes a list of student records to a file
	 * 
	 * @param fileName  file to write student records to
	 * @param studentDirectory list of students to write to fileName
	 * @throws IOException if cannot write to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}
}
