package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;



/**
 * LinkedAbstractList class 
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 * @param <E> LinkedList of generic objects
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/** Size of the list */
	private int size;
	/** Capacity of the list */
	private int capacity;
	/** Front field for front of the list */
	private ListNode front;
	/** Points to the last node in the list */
	private ListNode back;
	
	
	/**
	 * LinkedAbstractList constructor
	 * @param capacity the capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		setCapacity(capacity);
	}
	
	/**
	 * setCapacity method to set the capacity of the course
	 * @param capacity the capacity of the list
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
			}
		this.capacity = capacity;
	}
	
	
	
	/**
	 * Get method to get E at index
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		ListNode current = front;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Set method to set the element E at given index
	 * 
	 * @param index  Index of element to set new data to
	 * @param e  Element to set to given index
	 * @return value  Old element that was changed
	 */
	@Override
	public E set(int index, E e) {
		ListNode current = front;
		if(index < 0 || index > size || size == 0) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		if(e == null) {
			throw new NullPointerException("Element is null.");
		}
		for(int i = 0; i < size; i++) {
			if(current.data.equals(e)) {
				throw new IllegalArgumentException("Element is a duplicate.");
			}
			current = current.next;
		}
		current = front;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		E value = current.data;
		current.data = e;
		//current = new ListNode(e, current);
		return value;
		
	}
	
	/**
	 * Add method to add an element E at given index
	 * 
	 * @param index  Index of where element is to be added
	 * @param e  Element to be added
	 */
	@Override
	public void add(int index, E e) {
		ListNode current = front; 
		ListNode current2 = front;
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		if(size == capacity) {
			throw new IllegalArgumentException("Capacity has been met.");
		}
		if(e == null) {
			throw new NullPointerException("Element is null.");
		}
		
		for(int i = 0; i < size; i++) {
			if(current.data.equals(e)) {
				throw new IllegalArgumentException("Element is a duplicate.");
			}
			current = current.next;
		}
		
		if(index > 0 && size > 0) {
			for (int i = 0; i < size; i++) {
				current2 = current2.next;
			}
			back = current2;
		}
		
		if(index == 0 && size == 0) {
			front = new ListNode(e);
		} else if (index == (size - 1) && size != 0 && index != 0) {
			back = new ListNode(e);
		} else if(index == 0) {
			front = new ListNode(e, front);
		} else {
			current = front;
			for( int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(e, current.next);
		}
		size++;
	}
	
	/**
	 * Remove method to remove element E at given index
	 * 
	 * @param index  Index of element to be removed
	 * @return value  Returns element that was removed 
	 */
	@Override
	public E remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		
		E value =  null;
		if(index == 0) {
			value = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}
	
	
	/**
	 * Size method to get size of the linked list
	 */
	@Override
	public int size() {
//		ListNode current = front;
//		while(current != null) {
//			current = current.next;
//			size++;
//		}
		return size;
	}
	
	
	/**
	 * getcapacity that returns the capacity for the course
	 *
	 *@return capacity  Capacity for the course
	 */
	public int getCapacity() {
		return capacity;
	}
	
	
	
	
	
	private class ListNode {
		
		/**Private data field for the current ListNode  */
		private E data;
		/** Next node on the list   */
		private ListNode next;
		
		
		public ListNode(E data) {
			this(data, null);
		}
		
		
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	
	}



}
