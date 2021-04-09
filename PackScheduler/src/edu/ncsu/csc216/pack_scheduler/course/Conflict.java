package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creates a Conflict interface for use in Activity.
 * 
 * @author Matthew Church
 *
 */
public interface Conflict {
	/**
	 * Defines the program behavior if a conflicting Activity is found. Throws a
	 * ConflictException if a schedule conflict is found.
	 * 
	 * @param possibleConflictActivity the activity to check for a schedule conflict
	 * @throws ConflictException if  a schedule conflict is found
	 */
	void checkConflict(Activity possibleConflictActivity) throws ConflictException;
}
