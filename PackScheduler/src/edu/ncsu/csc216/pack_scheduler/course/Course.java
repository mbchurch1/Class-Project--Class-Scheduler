package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * This class creates a Course Object with all field values, a Course Object for
 * arranged Courses and overrides the hashCode and equals methods. Course is a
 * child method for an Activity that has a set of its own fields and overrides
 * the setMeetingDays, isDuplicate, toString, hashCode, equals, and getDisplay
 * methods from Activity.
 * 
 * @author Matthew Church
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** length of the section String */
	private static final int SECTION_LENGTH = 3;
	/** maximum credits a course can have */
	private static final int MAX_CREDITS = 5;
	/** minimum credits a course can have */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** name validator instance */
	private CourseNameValidator validator;
	/** list of student's currently enrolled in the course */
	private CourseRoll roll;

	/**
	 * Constructs a course object with values for all fields, including a name,
	 * title, section, credits instructor Id, meeting days, and start and end times.
	 * 
	 * @param name         name of a course
	 * @param title        title of a course
	 * @param section      section of course
	 * @param credits      course credit hours
	 * @param instructorId course instructor ID
	 * @param enrollmentCap course enrollment capacity
	 * @param meetingDays  course meeting days
	 * @param startTime    course start time
	 * @param endTime      course end time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         course name
	 * @param title        course title
	 * @param section      course section
	 * @param credits      course credit hours
	 * @param instructorId course instructor ID
	 * @param enrollmentCap course enrollment capacity
	 * @param meetingDays  course meeting days as a series of characters
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Returns course name
	 * 
	 * @return the Course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course name. The name must start with 1 to 4 letters, 
	 * followed by exactly 3 digits, followed by an optional 1 letter 
	 * suffix, and with a total course name string length of 4 to 8
	 * characters, otherwise an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException  if the name is null
	 * @throws IllegalArgumentExcepetion if the name parameter is invalid
	 */
	private void setName(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid name");
		}
		validator = new CourseNameValidator();
		try {
			if (validator.isValid(name)) {
				this.name = name;
			} else {
				throw new IllegalArgumentException("Invalid name");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid name");
		}
	}

	/**
	 * Returns the Course section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course section. If the section length is not exactly 3 characters,
	 * or is null, or if any of the characters aren't digits, throws an
	 * IllegalArgumentExcepetion.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section isn't valid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Section should be three digits.");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the Course credit hours as an int
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course credit hours. If credit hours is null or is not between 1 and
	 * 5, inclusive, throws new IllegalArgumentException.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credit hours are invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course instructor ID as a String
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course instructor ID. If instructorID is null or empty, throws
	 * IllegalArgumentExcepetion
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is invalid
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets the meeting days and times for a course, then calls the super to finish
	 * setting. Overrides the parent method due to a separate set of valid meeting
	 * days. Throws an IllegalArgumentException if the meeting days are null, empty,
	 * contain more than 1 day of the week, or anything other than M, T, W, H, F, or
	 * A.
	 * 
	 * @param meetingDays the Course meeting days
	 * @param startTime   the Course start time
	 * @param endTime     the Course end time
	 * @throws IllegalArgumentException if the meeting days are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		int startTimeVal = startTime;
		int endTimeVal = endTime;
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		if ("A".equals(meetingDays)) {
			startTimeVal = 0;
			endTimeVal = 0;
		} else {
			int meetingM = 0;
			int meetingT = 0;
			int meetingW = 0;
			int meetingH = 0;
			int meetingF = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					meetingM++;
				} else if (meetingDays.charAt(i) == 'T') {
					meetingT++;
				} else if (meetingDays.charAt(i) == 'W') {
					meetingW++;
				} else if (meetingDays.charAt(i) == 'H') {
					meetingH++;
				} else if (meetingDays.charAt(i) == 'F') {
					meetingF++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			if (meetingM > 1 || meetingT > 1 || meetingW > 1 || meetingH > 1 || meetingF > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTimeVal, endTimeVal);
	}

	/**
	 * Compares two Courses name, or Section to sort the Course list. Overrides the
	 * compareTo in the added library. Throws a NullPointerException if the Course
	 * is Null.
	 * 
	 * @param s the Student to compare
	 * @throws NullPointerException     if s is null
	 * @throws IllegalArgumentException if s is not a valid Student object
	 * @return an int signifying order of the two Students, positive if s is higher,
	 *         negative if the original is higher.
	 */
	@Override
	public int compareTo(Course s) {
		int comparable = 0;
		try {
			int compareName = name.compareTo(s.getName());
			int compareSection = section.compareTo(s.getSection());

			if (compareName != 0) {
				comparable = compareName;
			} else {
				comparable = compareSection;
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Course cannot be null.");
		}
		return comparable;

	}

	/**
	 * Compares an Activity with other Courses to check for duplicates. Overrides
	 * the parent class due to checking child contained fields.
	 * 
	 * @param activity the activity to compare to
	 * @return isDuplicate true if the the compared activity is a Course with the
	 *         same name
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		
		return activity instanceof Course && name.equals(((Course) activity).getName());
	}

	/**
	 * Generates a String of the Course. Overrides the parent class due to separate
	 * set of meting days. Overrides parent class due to separate set of valid
	 * meeting days.
	 * 
	 * @return the Course String
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," 
				+ getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashcode for a Course with all fields. Overrides the parent class
	 * due to fields contained in child.
	 * 
	 * @return the Course hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Checks if this object is the same as another object for all fields. Overrides
	 * the parent class due to fields contained in child.
	 * 
	 * @param obj the object to compare
	 * @return true if the objects are the same for all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Creates a short display of Course with the name, section, title, and Meeting
	 * String. Overrides the parent class due to using child contained fields.
	 * 
	 * @return a String array with the name, section, title, and meeting String
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];
		shortDisplay[0] = name;
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = "" + this.roll.getOpenSeats();
		return shortDisplay;
	}

	/**
	 * Creates a long display of Course with the name, section, title, credits,
	 * instructorId, meeting String, and an empty String for a field that Event will
	 * have that Course does not. Overrides parent class due to child contained
	 * fields.
	 * 
	 * @return a String array of all fields of a Course
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = name;
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = "" + credits;
		longDisplay[4] = instructorId;
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}
	
	/**
	 * Gets the CourseRoll object associated with this course and 
	 * containing a list of students currently enrolled in the course
	 * @return roll a CourseRoll object
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
