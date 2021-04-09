package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Contains getters, setters, and tools to add or remove, and save the Course
 * catalog.
 * 
 * @author Matthew Church
 * @author John Firlet
 * @author Will Goodwin
 */
public class CourseCatalog {
	/** a sorted catalog of Courses */
	private SortedList<Course> catalog;

	/**
	 * CourseCatalog constructor. Creates a new, blank Course Catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Creates a new, blank Course Catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Loads the Courses from a file. Throws an IllegalArgumentException if the file
	 * name is not found
	 * 
	 * @param fileName the file to load from
	 * @throws IllegalArgumentException if the file name is not found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Course to the schedule if the Course has not previously been added.
	 * 
	 * @param name         the Course name
	 * @param title        the Course title
	 * @param section      the Course section
	 * @param credits      the Course credits
	 * @param instructorId the Course instructor
	 * @param enrollmentCap  Enrollment capacity of course
	 * @param meetingDays  the Course meeting days
	 * @param startTime    the Course start time
	 * @param endTime      the Course end time
	 * @return true if the Course was added to the schedule
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return false;
			}
		}
		catalog.add(new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime));
		return true;
	}

	/**
	 * Removes a Course from the catalog, if the course is found in the current Course Catalog.
	 * 
	 * @param name    the Course name
	 * @param section the Course section
	 * @return true if the Course was removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the Course information from the catalog. If the Course is not found,
	 * returns null.
	 * 
	 * @param name    the Course name
	 * @param section the Course section
	 * @return The Course if it is found in the catalog, null if it is not found.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns the Course catalog information as a String array for display.
	 * 
	 * @return courseCatalog A String array of the Course catalog information.
	 */
	public String[][] getCourseCatalog() {
		String[][] courseCatalog = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			courseCatalog[i][0] = catalog.get(i).getName();
			courseCatalog[i][1] = catalog.get(i).getSection();
			courseCatalog[i][2] = catalog.get(i).getTitle();
			courseCatalog[i][3] = catalog.get(i).getMeetingString();
			courseCatalog[i][4] = catalog.get(i).getShortDisplayArray()[4];
		}
		return courseCatalog;

	}

	/**
	 * Saves a file with the current Course catalog written in it. Throws an
	 * IllegalArgumentException if the file can not be written.
	 * 
	 * @param fileName the file name to save the catalog under
	 * @throws IllegalArgumentException if the file can not be saved under the given
	 *                                  name.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}
