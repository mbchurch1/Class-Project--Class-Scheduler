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
public class ArrayQueue<E> {

	/** Generic ArrayStack */
	private ArrayList<E> list;
	
	
	public ArrayQueue() {
		
		list = new ArrayList<E>();
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
		
		return list.isEmpty();
	}
	
	public int size() {
		
		return list.size();
	}
	
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		
		//Need to implement setCapacity in ArrayStack, not ArrayList class.
		
	}
	
}
