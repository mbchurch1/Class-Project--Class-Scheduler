package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This class takes in a course name and determines whether it follows the format requirements
 * for course names: starts with 1 to 4 letters, followed by exactly 3 digits, followed by an 
 * optional 1 letter suffix, and with a total course name string length of 4 to 8. It uses an 
 * inner class that establishes the currentState of the course name as each character is analyzed,
 * and adjusts the state per format requirements as each subsequent character is analyzed. 
 * 
 * @author Will Goodwin
 * @author Matt Church
 * @author John Firlet
 *
 */
public class CourseNameValidator {

	/** Boolean for valid end state */
	private boolean validEndState;
	/** Counter for letters */
	private int letterCount;
	/** Counter for numbers */
	private int numberCount;
	/** Current State */
	private State currentState;
	/** Initial State */
	private final State initialState = new InitialState();
	/** Letter State */
	private final State letterState = new LetterState();
	/** Digit State */
	private final State digitState = new DigitState();
	/** Suffix State */
	private final State suffixState = new SuffixState();
	
	
	/**
	 * Determines whether the course name string follows the format requirements, i.e. starts with 1 to 4 letters, followed by
	 * exactly 3 digits, followed by an optional 1 letter suffix, and with a total course name string length of 4 to 8
	 * characters
	 * @param course the name of a course
	 * @return the truth value of validEndState, i.e. true if course follows the accurate format parameters
	 * @throws InvalidTransitionException if the current state does not permit either a letter or digit to be read in 
	 * 		next from the course String
	 */
	public boolean isValid(String course) throws InvalidTransitionException {
		int charIndex = 0;
		char c;
		currentState = initialState;
		validEndState = false;
		numberCount = 0;
		letterCount = 0;
		
		while (charIndex < course.length()) {
			c = course.charAt(charIndex);
			
			if (Character.isLetter(c)) {
				currentState.onLetter();
			} else if (Character.isDigit(c)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
			charIndex++;
		}
		
		return validEndState;
	}
	
	/**
	 * State class that transitions between the different FSM states
	 * 
	 * @author Will Goodwin
	 * @author Matt Church
	 * @author John Firlet
	 * @throws InvalidTransitionException when a character other than a letter or digit 
	 * 		is read in from course
	 */
	abstract class State  {
		
		abstract void onLetter() throws InvalidTransitionException;
		
		abstract void onDigit() throws InvalidTransitionException;
		
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * The first state within the FSM that a course is assigned to 
	 * 
	 * @author Will Goodwin
	 * @author Matt Church
	 * @author John Firlet
	 */
	private class InitialState extends State {

		/**
		 * Actions that are performed on course when in the initial state
		 * and a letter is the current character under analysis
		 */
		@Override
		void onLetter() {
			letterCount++;
			currentState = letterState;
		}

		/**
		 * Actions that are performed on course when in the initial state
		 * and a digit is the current character under analysis
		 * @throws InvalidTransitionException If first character is a digit
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
	}
	
	/**
	 * The state within the FSM that a course is assigned to after a letter
	 * has been read in from course 
	 * @author Will Goodwin
	 * @author Matt Church
	 * @author John Firlet
	 */
	private class LetterState extends State {

		/**
		 * Actions that are performed on course when in the letter state
		 * and a letter is the current character under analysis
		 * @throws InvalidTransitionException If first character is a digit
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			letterCount++;
			if(letterCount > 4) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * Actions that are performed on course when in the letter state
		 * and a digit is the current character under analysis
		 */
		@Override
		void onDigit() {
			numberCount++;
			currentState = digitState;
			
		}
		
	}
	
	/**
	 * The state within the FSM that a course is assigned to after a digit
	 * has been read in from course 
	 * @author Will Goodwin
	 * @author Matt Church
	 * @author John Firlet
	 */
	private class DigitState extends State {

		/**
		 * Actions that are performed on course when in the digit state
		 * and a letter is the current character under analysis
		 * @throws InvalidTransitionException If invalid course name
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			if(numberCount == 3) {
				currentState = suffixState;
				validEndState = true;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * Actions that are performed on course when in the digit state
		 * and a digit is the current character under analysis
		 * @throws InvalidTransitionException If invalid course name
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			numberCount++;
			if(numberCount == 3) {
				validEndState = true;
			} else if (numberCount > 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
		
	}
	
	/**
	 * The state within the FSM that a course is assigned to after a letter or digit
	 * has been read in from course following reading in 1 to 4 letters and exactly 
	 * 3 digits from course
	 * @author Will Goodwin
	 * @author Matt Church
	 * @author John Firlet
	 */
	private class SuffixState extends State {

		/**
		 * Invalidates course if a letter is read in following a single 
		 * suffix letter being read in from course 
		 * @throws InvalidTransitionException if there is a letter read in 
		 * 		from course following 1 suffix character being read in
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * Invalidates course if a digit is read in following a single 
		 * suffix letter being read in from course 
		 * @throws InvalidTransitionException if there is a letter read in 
		 * 		from course following 1 suffix character being read in
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}
		
	}
	
}
