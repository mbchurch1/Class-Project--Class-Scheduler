package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack class for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public class ArrayStack<E> implements Stack<E> {

	/** Generic ArrayStack */
	private ArrayList list;
	
	public ArrayStack() {
		list = new ArrayList<E>();
	}
		
	@Override
	public void push(E element) {
		//need to check if list has has room to add an element
		
		((Stack<E>) list).push(element);
	}

	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		return ((Stack<E>) list).pop();
	}

	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	@Override
	public int size() {
		
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		
		//Need to implement setCapacity in ArrayStack, not ArrayList class.
		
	}


}
