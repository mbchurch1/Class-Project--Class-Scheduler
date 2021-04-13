package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * ArrayQueue class for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public class ArrayQueue<E> implements Queue<E> {

	/** Generic ArrayStack */
	private ArrayList<E> list;
	/** The number of elements in the ArrayStack */
	private int size;
	/** The maximum number of elements that can be added to ArrayStack */
	private int capacity;
	
	public ArrayQueue() {
		
		list = new ArrayList<E>();
		size = 0;
		capacity = 0;
	}
	
	public void enqueue(E element) {
		//need to check if list has has room to add an element
		list.add(element);
	}
	
	public E dequeue() {
		if(list.isEmpty()) {
			throw new NoSuchElementException("List is empty.");
		}
		return list.remove(0);
	}
	
	public boolean isEmpty() {
		
		return list.size() == 0;
	}
	
	public int size() {
		
		return list.size();
	}
	
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		} else {
			this.capacity = capacity;
		}
		
		//Need to implement setCapacity in ArrayStack, not ArrayList class.
		
	}
	
}
