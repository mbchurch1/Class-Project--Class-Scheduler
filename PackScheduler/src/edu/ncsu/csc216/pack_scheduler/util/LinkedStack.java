package edu.ncsu.csc216.pack_scheduler.util;



/**
 * LinkedStack class for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public class LinkedStack<E> implements Stack<E> {

	/** Generic LinkedList */
	private LinkedAbstractList  list;
	
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList(capacity);
	}
	
	
	@Override
	public void push(E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		
	}

}
