package edu.ncsu.csc216.pack_scheduler.util;



/**
 * Queue interface for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public interface Queue<E> {
	
	/**
	 * Adds the element to the back of the Queue
	 * @param element the element being added to Queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes the element from the front of the Queue
	 * @return the element being removed from the Queue
	 */
	E dequeue();
	
	/**
	 * Determines if the queue is empty
	 * @return true if the queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Determines the number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	int size();
	
	/**
	 * Sets the Queue's capacity
	 * @param capacity the maximum number of elements that can be added to Queue
	 */
	void setCapacity(int capacity);
	

}
