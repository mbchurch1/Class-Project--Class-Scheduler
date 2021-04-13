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

	
	/**
	 * LinkedStack constructor
	 * @param capacity  Capacity of the waitlist
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Push method to add an element to the Stack
	 * @param element  Element to be added
	 * @throws IllegalArgumentException if capacity is less than list size
	 */
	@Override
	public void push(E element) {
		if(list.size() >= list.getCapacity()) {
			throw new IllegalArgumentException("Cannot add to list.");
		}
		list.add(0, element);
		//((Stack<E>) list).push(element);
	}

	/**
	 * Pop method to remove an element from the list
	 * @return element to be removed
	 * @throws EmptyStackException if list is empty
	 */
	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(0);
		//return ((Stack<E>) list).pop();
	}

	/**
	 * isEmpty method if list is empty
	 * @return true or false if list is empty
	 */
	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	/**
	 * Size method to return the size of the list
	 * @return size of the list
	 */
	@Override
	public int size() {

		return list.size();
	}

	/**
	 * setCapacity to set the capacity of the waitlist
	 * @param capacity  Capacity of the waitlist
	 * @throws IllegalArgumentException if capacity is less than zero or less than the list size
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		list.setCapacity(capacity);
	}


	/**
	 * getCapacity to return the capacity of the waitlist
	 * @return capacity of the waitlist
	 */
	public int getCapacity() {
		return list.getCapacity();
		
	}

}
