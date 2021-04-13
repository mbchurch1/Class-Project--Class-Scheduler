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
	private ArrayList<E> list;
	/** The number of elements in the ArrayStack */
	private int size;
	/** The maximum number of elements that can be added to ArrayStack */
	private int capacity;
	
	/**
	 * Constructor for ArrayStack using amount for the capacity
	 * @param amount  Integer to set capacity to
	 */
	public ArrayStack(int amount) {
		list = new ArrayList<E>();
		size = 0;
		capacity = amount;
	}
	
	/**
	 * Constructor using default values
	 */
	public ArrayStack() {
		list = new ArrayList<E>();
		size = 0;
		capacity = 10;
	}
		 
	/**
	 * Push method to add an element to the Stack
	 * @param element  Element to be added
	 * @throws IllegalArgumentException if no room is left to add to the list
	 */
	@Override
	public void push(E element) {
		//need to check if list has has room to add an element
		if(list.size() >= capacity) {
			throw new IllegalArgumentException("Cannot add to list.");
		}
		list.add(0, element);
		
		//To be used when we implement Stacks
		//((Stack<E>) list).push(element);
	}

	/**
	 * Pop method to remove an element off the top of the Stack
	 * @return value  Value of the element that is removed
	 * @throws EmptyStackException if the list is empty
	 */
	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		//I don't think we need to cast this as E 
		// I think 
		//E value = (E) list.get(list.size() - 1);
		E value = (E) list.get(0);
		list.remove(0);
		return value;
		
		
		//To be used when we implement Stacks
		//return ((Stack<E>) list).pop();
	}

	/**
	 * isEmpty method to return true or false if the list is empty
	 * @return boolean  true or false if list is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
		
		//return list.isEmpty();
	}

	/**
	 * Size method to return the size of the list
	 * @return Size of the list
	 */
	@Override
	public int size() {
		
		return list.size();
	}

	/**
	 * setCapacity method to set the capacity of the wait list
	 * @param capacity  Capacity of the wait list
	 * @throws IllegalArgumentException if capacity is less than zero or less than the list size
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		} else {
			this.capacity = capacity;
		}
		
		//Need to implement setCapacity in ArrayStack, not ArrayList class.
		
	}


}

