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
	
	public void enqueue(E element);
	
	public E dequeue();
	
	public boolean isEmpty();
	
	public int size();
	
	public void setCapacity(int capacity);
	

}
