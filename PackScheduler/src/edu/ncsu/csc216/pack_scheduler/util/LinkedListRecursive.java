package edu.ncsu.csc216.pack_scheduler.util;

/**
 * 
 * LinkedListRecursive class that creates a custom implementation of a recursive linked list 
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E> LinkedListRecursive of generic objects
 */
public class LinkedListRecursive<E> {
	
	/** Front field for front of the list */
	private ListNode front;
	/** Size of the list */
	private int size;
	
	/**
	 * Constructor for LinkedListRecursive
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns true if the list equals zero
	 * @return true or false if size of the list equals zero
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Size method to return the size of the LinkedListRecursive
	 * @return size  Size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add method to check if an element can be added to the list
	 * 
	 * @param element Element to be added
	 * @return True or false if element can be added
	 */
	public boolean add(E element) {
		//TODO
		return false;	
	}
	
	/**
	 * Adds the element to the given index
	 * 
	 * @param index Index of where to add the element
	 * @param element Element to be added
	 */
	public void add(int index, E element) {
		//TODO
		
	}

	/**
	 * Returns an element at the given index
	 * 
	 * @param index Index of the element to be returned
	 * @return e Element to be returned at the given index
	 */
	public E get(int index) {
		
		//TODO
		return null;
	}
	
	/**
	 * Returns true or false if element can be removed
	 * 
	 * @param element Element to be removed
	 * @return true or false if element can be removed
	 */
	public boolean remove(E element) {
		
		//TODO
		return false;
	}
	
	/**
	 * Removes the element at the given index
	 * 
	 * @param index Index of the element to be removed
	 * @return e Returns the element that was removed
	 */
	public E remove(int index) {
		
		//TODO
		return null;
	}
	
	/**
	 * Sets the element to the index given
	 * 
	 * @param index Index of the element to set
	 * @param element Element to set 
	 * @return e Element that was previously set to
	 */
	public E set(int index, E element) {
		
		//TODO
		return null;
	}
	
	
	/**
	 * Contains method to determine if an element is a duplicate
	 * 
	 * @param element Element to be checked to see if it's a duplicate
	 * @return True or false if the element is a duplicate
	 */
	public boolean contains(E element) {
		
		//TODO 
		return false;
	}
	
	
	
	
	/**
	 * ListNode inner class to create ListNodes for the LinkedList class
	 * 
	 * @author Matthew Church
	 * @author Will Goodwin
	 * @author John Firlet
	 *
	 */
	public class ListNode { 

		/**Private data field for the current ListNode  */
		public E data;
		/** Next node on the list   */
		public ListNode next;
	
		
		/**
		 * ListNode constructor for the iterator
		 * 
		 * @param data Data of the ListNode
		 * @param next Next ListNode in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds the element at the given index
		 * 
		 * @param index Index of the element to add
		 * @param element Element to add at the given index
		 */
		public void add(int index, E element) {
			
			//TODO
			
		}
	
		/**
		 * Returns the element at the given index
		 * 
		 * @param index Index of the element to be returned
		 * @return e Element to be returned at the given index
		 */
		public E get(int index) {
			
			//TODO
			return null;
		}
		
		/**
		 * Removes an element at a given index
		 * 
		 * @param index Index of the element to be removed
		 * @return e Element that was removed
		 */
		public E remove(int index) {
			
			//TODO
			return null;
		}
		
		/**
		 * Returns true or false if an element can be removed from the list
		 * 
		 * @param element Element to be removed
		 * @return True or false if element can be removed
		 */
		public boolean remove(E element) {
			
			//TODO
			return false;
		}
		
		/**
		 * Sets an element at the given index
		 *  
		 * @param index Index of the element to set
		 * @param element Element to set at the given index
		 * @return e Element that was previously set to
		 */
		public E set(int index, E element) {
			
			//TODO
			return null;
		}
		
		/**
		 * Contains method to determine if an element is a duplicate
		 * 
		 * @param element Element to be checked to see if it's a duplicate
		 * @return True or false if the element is a duplicate
		 */
		public boolean contains(E element) {
			
			//TODO 
			return false;
		}
		
	}

}
