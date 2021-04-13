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
	
	/**
	 * ArrayQueue constructor
	 * @param amount the capacity of the waitlist
	 */
	public ArrayQueue(int amount) {
		
		list = new ArrayList<E>();
		size = 0;
		this.capacity = amount;
	}
	
	/**
	 * Enqueue method to add an element to the queue
	 * @param element Element to be added
	 * @throws IllegalArgumentException  if element cannot be added
	 */
	public void enqueue(E element) {
		//need to check if list has has room to add an element
		if (size >= capacity) {
			throw new IllegalArgumentException("Cannot add to queue.");
		}
		list.add(element);
	}
	
	/**
	 * Dequeue method to remove an element from the queue
	 * @return element that was removed
	 * @throws NoSuchElementException if list is empty
	 */
	public E dequeue() {
		if(list.isEmpty()) {
			throw new NoSuchElementException("List is empty.");
		}
		return list.remove(0);
	}
	
	/**
	 * isEmpty method to return true if list is empty
	 * @return boolean  true or false if list is empty
	 */
	public boolean isEmpty() {
		
		return list.size() == 0;
	}
	
	/**
	 * Size method to return the size of the list
	 * @return size of the list
	 */
	public int size() {
		
		return list.size();
	}
	
	/**
	 * setCapacity method to set the capacity of the waitlist
	 * @param capacity  Capacity of the waitlist
	 * @throws IllegalArgumentException if capacity is less than zero or greater than size
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		} else {
			this.capacity = capacity;
		}
		
		//Need to implement setCapacity in ArrayStack, not ArrayList class.
		
	}
	
}
