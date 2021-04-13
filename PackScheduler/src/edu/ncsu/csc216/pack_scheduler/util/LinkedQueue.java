package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;


/**
 * LinkedQueue class for CourseRoll waitlist
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> List of generic objects
 */
public class LinkedQueue<E> implements Queue<E> {

	/** Generic ArrayStack */
	private LinkedAbstractList<E> list;
	/** The index at which an element should be added to the LinkedAbstractList */
	private int indexForAdding;
	
	
	/**
	 * LinkedQueue constructor using the capacity field
	 * @param capacity Capacity to set the wait list to
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
		indexForAdding = 0;
	}
	
	/**
	 * Enqueue method to add an element to the queue
	 * @param element  Element to be added
	 * @throws IllegalArgumentException if element cannot be added to the list
	 */
	public void enqueue(E element) {
		if(list.size() >= list.getCapacity()) {
			throw new IllegalArgumentException("Cannot add to list.");
		}
		list.add(indexForAdding, element);
		indexForAdding++;
	}
	
	/**
	 * Dequeue method to remove an element from the list
	 * @return element that is removed
	 * @throws EmptyStackException if list is empty
	 */
	public E dequeue() {
		if(isEmpty()) {
			throw new EmptyStackException();
		} 
//		if(list.isEmpty()) {
//			throw new NoSuchElementException("List is empty.");
//		} 
		else {
			indexForAdding--;
			return list.remove(0);
		}
	}
	
	/**
	 * isEmpty method to return if a list is empty
	 * @return boolean if list is empty
	 */
	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Size method to return the size of the list
	 * @return size of the list
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * setCapacity method to set the waitlist capacity
	 * @param capacity  Capacity of the waitlist
	 * @throws IllegalArgumentException if capacity is less than zero or less than the list size
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		list.setCapacity(capacity);
	}
	
	/**
	 * getCapacity method to return the capacity of the waitlist
	 * @return capacity of the waitlist
	 */
	public int getCapacity() {
		return list.getCapacity();
		
	}
	
}
