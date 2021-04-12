package edu.ncsu.csc216.pack_scheduler.util;


/**
 * Stack interface for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public interface Stack<E> {
	
	/**
	 * Adds the element to the top of the Stack
	 * @param element the element being added to top of Stack
	 */
	void push(E element);
	
	/**
	 * Removes the element at top of Stack
	 * @return the element that was removed from top of Stack
	 */
	E pop();
	
	/**
	 * Determines if the Stack is empty
	 * @return true if the Stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Determines the number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	int size();
	
	/**
	 * Sets the Stack's capacity
	 * @param capacity the maximum number of elements that can be added to Stack
	 */
	void setCapacity(int capacity);

}
