package edu.ncsu.csc216.pack_scheduler.util;



/**
 * LinkedQueue class for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public class LinkedQueue<E> {

	/** Generic ArrayStack */
	private LinkedAbstractList<E> list;
	
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	public void enqueue(E element) {
		
	}
	
	public E dequeue() {
		return null;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public int size() {
		return 0;
	}
	
	public void setCapacity(int capacity) {
		
	}
	
	
	
}
