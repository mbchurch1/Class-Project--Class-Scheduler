package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule class that manages the users schedule
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 */
public class Schedule {

	/** Constant for title of schedule */
	private String title;
	/** ArrayList of courses */
	private ArrayList<Course> schedule;

	/**
	 * Schedule constructor
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Method to add course to current schedule
	 * 
	 * @param course Course to be added
	 * @return True or false on if course was added
	 */
	public boolean addCourseToSchedule(Course course) {
		boolean courseAdded = false;
		if (course == null) {
			throw new NullPointerException("Invalid course name");
		}
		for (int i = 0; i < schedule.size(); i++) {
//			if (schedule.get(i).getName().equals(course.getName())) {
//				return false;
//			}
			if (schedule.get(i).isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
				// return false;
			}

			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(course);
		courseAdded = true;
		return courseAdded;
	}

	/**
	 * Remove course from current schedule
	 * 
	 * @param course Course to be removed.
	 * @return true or false if course was removed
	 */
	public boolean removeCourseFromSchedule(Course course) {
//		if(course == null) {
//			throw new NullPointerException("Course cannot be null.");
//		}
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(course.getName())
					&& schedule.get(i).getSection().equals(course.getSection())) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule to empty
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Returns a string of courses with the name, section, title and meeting info
	 * 
	 * @return mySchedule String of scheduled courses
	 */
	public String[][] getScheduledCourses() {
		String[][] mySchedule = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			mySchedule[i][0] = schedule.get(i).getShortDisplayArray()[0];
			mySchedule[i][1] = schedule.get(i).getShortDisplayArray()[1];
			mySchedule[i][2] = schedule.get(i).getShortDisplayArray()[2];
			mySchedule[i][3] = schedule.get(i).getShortDisplayArray()[3];
			mySchedule[i][4] = schedule.get(i).getShortDisplayArray()[4];
		}
		return mySchedule;
	}

	/**
	 * Sets the title of the schedule
	 * 
	 * @param title Title of the current schedule
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Schedule title cannot be blank.");
		}
		this.title = title;
	}

	/**
	 * Returns the title of the current schedule
	 * 
	 * @return title Title of current schedule
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns the total credits of all courses in the schedule
	 * 
	 * @return an int of all scheduled credit hours
	 */
	public int getScheduleCredits() {
		int totalHours = 0;
		if (schedule.size() == 0) {
			return 0;
		}
		for (int i = 0; i < schedule.size(); i++) {
			totalHours += schedule.get(i).getCredits();
		}
		return totalHours;
	}

	/**
	 * Returns a boolean if the student can add the course with more hours vs their
	 * max credits
	 * 
	 * @param course the new course to add
	 * @return a boolean thats true if the course can be added.
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		if(schedule.size() == 0) {
			return true;
		}
		for (int i = 0; i < schedule.size(); i++) {

			if (schedule.get(i).isDuplicate(course)) {
				return false;
			}
			try {
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
