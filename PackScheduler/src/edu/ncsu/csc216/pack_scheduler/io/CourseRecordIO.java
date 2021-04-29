package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course current = courses.get(i);
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						duplicate = true;
						break;
					}

				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// Line is invalid, skip
			}
		}
		fileReader.close();
		return courses;
	}

	private static Course readCourse(String nextLine) {

		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter(",");
		String name = null;
		String title = null;
		String section = null;
		int creditHours = 0;
		String instructorId = null;
		int enrollmentCap = 0;
		String meetingDays = null;
		int startTime = 0;
		int endTime = 0;
		try {
			name = scan.next();
			title = scan.next();
			section = scan.next();
			creditHours = scan.nextInt();
			instructorId = scan.next();
			enrollmentCap = scan.nextInt();
			meetingDays = scan.next();
			if (scan.hasNext()) {
				if ("A".equals(meetingDays)) {
					scan.close();
					throw new IllegalArgumentException("Invalid course record");
				} else {
					startTime = scan.nextInt();
					endTime = scan.nextInt();
				}
			}
			scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid course record");
		}
		Course arrangedCourse = null;
		Course onCampus = null;
		if ("A".equals(meetingDays)) {
			arrangedCourse = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays);
			// check if a Valid Faculty matches instructorId
			if (RegistrationManager.getInstance().getFacultyDirectory() != null
					&& RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {

				RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule()
						.addCourseToSchedule(arrangedCourse);

				return arrangedCourse;

			} else {
				return arrangedCourse;
			}

//			if (instructorId != null) {
//				if (fd.getFacultyById(instructorId) != null) {
//					arrangedCourse = new Course(name, title, section, creditHours, instructorId, enrollmentCap, meetingDays);
//				}
//				arrangedCourse = new Course(name, title, section, creditHours, instructorId, enrollmentCap, meetingDays);
			// if(newManager.getFacultyDirectory().getFacultyById(instructorId) != null) {
//					Faculty faculty = newManager.getFacultyDirectory().getFacultyById(instructorId);
//					faculty.getSchedule().addCourseToSchedule(arrangedCourse);
//					arrangedCourse.setInstructorId(instructorId);
			// arrangedCourse = new Course(name, title, section, creditHours, instructorId,
			// enrollmentCap, meetingDays);
			// }
//				FacultySchedule arrangedAdd = new FacultySchedule(instructorId);
//				arrangedAdd.addCourseToSchedule(arrangedCourse);
			// }

		} else {
			onCampus = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays, startTime,
					endTime);
			// check if a Valid Faculty matches instructorId
			if (RegistrationManager.getInstance().getFacultyDirectory() != null
					&& RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {

				RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule()
						.addCourseToSchedule(arrangedCourse);

				return arrangedCourse;

			} else {
				return onCampus;
			}

//			if (fd.getFacultyById(instructorId) != null) {
//				onCampus = new Course(name, title, section, creditHours, instructorId, enrollmentCap, meetingDays,
//						startTime, endTime);
//			}
//			if (instructorId != null) {
//				FacultyDirectory fd2 = new FacultyDirectory();
//				if (fd2.getFacultyById(instructorId) != null) {
//					onCampus = new Course(name, title, section, creditHours, instructorId, enrollmentCap, meetingDays,
//							startTime, endTime);
//				}
////				onCampus = new Course(name, title, section, creditHours, instructorId, enrollmentCap, meetingDays,
////						startTime, endTime);
//				//if(newManager.getFacultyDirectory().getFacultyById(instructorId) != null) {
////					Faculty faculty = newManager.getFacultyDirectory().getFacultyById(instructorId);
////					faculty.getSchedule().addCourseToSchedule(onCampus);
////					onCampus.setInstructorId(instructorId);
//					//onCampus = new Course(name, title, section, creditHours, instructorId, enrollmentCap, meetingDays,
//					//startTime, endTime);
//				//}
////				FacultySchedule campusAdd = new FacultySchedule(instructorId);
////				campusAdd.addCourseToSchedule(arrangedCourse);
//			}

		}

	}

	/**
	 * Writes the given list of Courses to a chosen file. Throws an
	 * IllegalArgumentException if the file cannot be written.
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param courses  list of Courses to write
	 * @throws IOException if cannot write to file
	 */

	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}
		fileWriter.close();
	}

}