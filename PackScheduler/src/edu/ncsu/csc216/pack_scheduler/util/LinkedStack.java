package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;



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
	private LinkedAbstractList<E>  list;

	
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	
	@Override
	public void push(E element) {
		if(list.size() >= list.getCapacity()) {
			throw new IllegalArgumentException("Cannot add to list.");
		}
		list.add(0, element);
		//((Stack<E>) list).push(element);
	}

	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(0);
		//return ((Stack<E>) list).pop();
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
		list.setCapacity(capacity);
	}


	public int getCapacity() {
		return list.getCapacity();
		
	}

}
