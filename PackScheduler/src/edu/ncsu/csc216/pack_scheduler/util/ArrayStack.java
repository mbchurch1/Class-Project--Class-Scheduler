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
	
	public ArrayStack(int amount) {
		list = new ArrayList<E>();
		size = 0;
		capacity = amount;
	}
	public ArrayStack() {
		list = new ArrayList<E>();
		size = 0;
		capacity = 10;
	}
		 
	@Override
	public void push(E element) {
		//need to check if list has has room to add an element
		list.add(0, element);
		
		//To be used when we implement Stacks
		//((Stack<E>) list).push(element);
	}

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

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
		
		//return list.isEmpty();
	}

	@Override
	public int size() {
		
		return list.size();
	}

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

