/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * ArrayList class to create a generic ArrayList of objects
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 * @param <E> ArrayList of generic objects
 */
public class ArrayList<E> extends AbstractList<E> {
	
	
	/** A constant value for initializing the list size */
	private static final int INIT_SIZE = 10;
	/** An ArrayList of generic type e */
	private E[] list;
	/** A constant value for size of the list */
	private int size;
	
	
	/**
	 * Constructor for ArrayList 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/**
	 * Add method for adding a generic ArrayList
	 * @param idx Index of object to be added
	 * @param value Object to be added at index
	 */
	@Override	
	public void add(int idx, E value) {
		if(idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		if(value == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		for(int i = 0; i < size; i++) {
			if(list[i].equals(value)) {
				throw new IllegalArgumentException("Element is a duplicate.");
			}
		}
		
		//Need to check
		if(size == list.length) {
			growArray();
		}
		
		for(int i = size - 1; i >= idx; i--) {
			list[i + 1] = list[i];
		}
		
		list[idx] = value;
		size++;
		
	}
	
	/**
	 * growArray method to double the size of the current array
	 */
	private void growArray() {
		E[] temp = (E[]) new Object[size * 2];
//		temp = list;
		for(int i = 0; i < size; i++) {
			temp[i] = list[i];
		}
		list = (E[]) new Object[size * 2];
//		list = temp;
		for(int i = 0; i < size; i++) {
			list[i] = temp[i];
		}
	}
	
	/**
	 * remove method to remove an object at given index
	 * @param idx  Index of the object to be removed
	 */
	@Override	
	public E remove(int idx) {
		if(idx < 0 || idx >= size || size == 0) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		
		E rtn = list[idx];
		for(int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		size--;
		return rtn;
	}
	
	/**
	 * set method to set an object to the given index value
	 * @param idx  Index of where the object is to be added
	 * @param value  Object to be added at given index
	 */
	@Override	
	public E set(int idx, E value) {
		if(idx < 0 || idx > size || size == 0) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		if(value == null) {
			throw new NullPointerException("Element is null.");
		}
		E rtn = list[idx];
		for(int i = 0; i < size; i++) {
			if(value.equals(list[i])) {
				throw new IllegalArgumentException("Element is a duplicate.");
			}
		}
		list[idx] = value;
		return rtn;
	}
	
	/**
	 * get method to get a current object at the given index
	 * @param idx  Index of the object to get
	 * @return list[idx] Returns object at given index
	 */
	@Override	
	public E get(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("List size invalid.");
		}
		
		return list[idx];
	}

	/**
	 * Returns the size of the ArrayList
	 * @return size  size of the ArrayList
	 */
	@Override
	public int size() {
		return size;
	}

}
