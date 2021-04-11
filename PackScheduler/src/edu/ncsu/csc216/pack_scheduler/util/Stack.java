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
	
	public void push(E element);
	
	public E pop();
	
	public boolean isEmpty();
	
	public int size();
	
	public void setCapacity(int capacity);

}
