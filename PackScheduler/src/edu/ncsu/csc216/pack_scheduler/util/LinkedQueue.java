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
	
	
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
		indexForAdding = 0;
	}
	
	public void enqueue(E element) {
		if(list.size() >= list.getCapacity()) {
			throw new IllegalArgumentException("Cannot add to list.");
		}
		list.add(indexForAdding, element);
		indexForAdding++;
	}
	
	public E dequeue() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		} else {
			indexForAdding--;
			return list.remove(0);
		}
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
		list.setCapacity(capacity);
	}
	
	public int getCapacity() {
		return list.getCapacity();
		
	}
	
}
