package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creates a parent class for Course and Event. Includes all common behaviors
 * (getters and setters). This class implements the conflictException class with
 * an override by checkConflict. This method checks for schedule conflcits.
 * 
 * @author Matthew Church
 */
public abstract class Activity implements Conflict {

	/** upper value for an hour */
	private static final int UPPER_HOUR = 24;
	/** upper value for a minute */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * A constructor for the parent class Activity
	 * 
	 * @param title       the Activity title
	 * @param meetingDays the Activity meeting days
	 * @param startTime   the Activity start time
	 * @param endTime     the Activity end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Activity title as a String
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity title. If the title is null, or the the title length is
	 * zero, throws new IllegalArgumentException.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is invalid
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity meeting days as a String
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity start time as an int
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course end time as an int
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Takes in the parameters from the overridden child methods and checks that the
	 * start and end times are valid, then sets the meetingDays and times. Throws an
	 * IllegalArgumentException if the times are invalid.
	 * 
	 * @param meetingDays the Activity meeting days
	 * @param startTime   the Activity start time
	 * @param endTime     the Activity end time
	 * @throws IllegalArgumentException if the times are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		int startTimeHour = startTime / 100;
		int startTimeMin = startTime % 100;
		int endTimeHour = endTime / 100;
		int endTimeMin = endTime % 100;
		if (startTimeHour < 0 || startTimeHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		if (startTimeMin < 0 || startTimeMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		if (endTimeHour < 0 || endTimeHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		if (endTimeMin < 0 || endTimeMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException("End time cannot be before start time.");
		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * This method returns a String with the times in 12 hour format, or Arranged if
	 * Arranged.
	 * 
	 * @return String of the meeting times or "Arranged"
	 */
	public String getMeetingString() {
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}
		return meetingDays + " " + convertTime(startTime) + "-" + convertTime(endTime);
	}

	/**
	 * Converts the time from 24 to 12 hour.
	 * 
	 * @param time the input time
	 * @return a string with a 12 hour time.
	 */
	private String convertTime(int time) {
		int timeHour = time / 100;
		int timeMin = time % 100;
		String timeMinStr = timeMin + "";
		if (timeMin < 10) {
			timeMinStr = "0" + timeMin;
		}
		if (timeHour > 12) {
			timeHour -= 12;
			return timeHour + ":" + timeMinStr + "PM";
		} else if (timeHour == 12) {
			return timeHour + ":" + timeMinStr + "PM";
		} else {
			return timeHour + ":" + timeMinStr + "AM";
		}

	}

	/**
	 * An overridden method that returns a String array of size 4. Overridden in
	 * Event and Course.
	 * 
	 * @return A short String array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * An overridden method that returns a String array of size 7. Overridden in
	 * Course and Event.
	 * 
	 * @return A long String array
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Generates a hashcode for an Activity using all fields. Returns an integer of the has code.
	 * @return an integer representation of the Activity's hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Checks for a a duplicate Activity, overridden in Course and Event.
	 * 
	 * @param activity the activity to compare
	 * @return true if the activity is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Compares a given Activity to this object for equality in all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same for all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Overrides the checkConflict in Conflict. Checks an activity against the
	 * schedule for possible time conflicts. Throws a ConflictException if the
	 * schedule conflicts by overlap, or wrapping.
	 * 
	 * @param possibleConflictActivity the new Activity to check for conflicts
	 * @throws ConflictException if the schedule conflicts
	 */
	@Override
	public void checkConflict(Activity possibleConflictActivity) throws ConflictException {
		if (!"A".equals(getMeetingDays()) && !"A".equals(possibleConflictActivity.getMeetingDays())) {
			int dayMatch = 0;
			int dayMatchCount = 0;
			for (int i = 0; i < getMeetingDays().length(); i++) {
				char day = getMeetingDays().charAt(i);
				dayMatch = possibleConflictActivity.getMeetingDays().indexOf(day);
				if (dayMatch > -1) {
					dayMatchCount++;
				}
			}
			int newActivityStart = possibleConflictActivity.getStartTime();
			int newActivityEnd = possibleConflictActivity.getEndTime();
			int startTimeOg = getStartTime();
			int endTimeOg = getEndTime();
			// smaller booleans to uncomplicate the if statement.
			boolean startTimeAfterStart = startTimeOg <= newActivityStart;
			boolean startTimeBeforeEnd = newActivityStart <= endTimeOg;
			boolean endTimeAfterStart = startTimeOg <= newActivityEnd;
			boolean endTimeBeforeEnd = newActivityEnd <= endTimeOg;
			boolean activityWraps = newActivityStart <= startTimeOg && newActivityEnd >= endTimeOg;
			boolean activityOverlap = startTimeAfterStart && startTimeBeforeEnd
					|| endTimeAfterStart && endTimeBeforeEnd;
			if (dayMatchCount > 0 && (activityWraps || activityOverlap)) {
				throw new ConflictException();
			}
		}
	}

}