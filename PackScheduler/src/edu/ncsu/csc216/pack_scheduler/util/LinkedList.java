package edu.ncsu.csc216.pack_scheduler.util;

import java.awt.List;
import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList.ListNode;




/**
 * LinkedList class that provides functionality for all standard list methods
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 * @param <E>
 */
public class LinkedList<E> extends AbstractSequentialList  {

	
	/** Front field for front of the list */
	private ListNode front;
	/** Points to the last node in the list */
	private ListNode back;
	/** Size of the list */
	private int size;
	
	
	
	public LinkedList() {
		front = null;
		front.next = back;
		back = null;
		back.next = front;
		size = 0;
	}
	

	@Override
	public void add(int index, Object element) {
		
		
		// TODO Auto-generated method stub
		super.add(index, element);
	}



	@Override
	public ListIterator<E> listIterator(int index) {
		
		return new LinkedListIterator(0);
	}

	@Override
	public int size() {
		return size;
	}

	
	
	
	
	
	private class LinkedListIterator implements ListIterator<E> {

		/** Next node on the list   */
		public ListNode next;
		/** Previous node on the list */
		public ListNode previous;
		/** Next index to be returned */
		public int nextIndex;
		/** Previous index to be returned */
		public int previousIndex;
		/** Last ListNode that was returned */
		public ListNode lastRetrieved;
		/** Current ListNode */
		private ListNode current;
		
		
		
		public LinkedListIterator(int index) {
			
			if(index < 0 || index > size) {
				throw new IndexOutOfBoundsException("Index out of bounds.");
			}
			current = front;
			previous = (LinkedList<E>.ListNode) get(index - 1);
			next = (LinkedList<E>.ListNode) next();
			previousIndex = previousIndex();
			nextIndex = nextIndex();
			lastRetrieved = null;
			   
			}
		
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException("Invalid element.");
			}
			E rtn = current.data;
			lastRetrieved = current;
			current = current.next;
			previousIndex++;
			nextIndex++;
			
			return rtn;
		}

		@Override
		public boolean hasPrevious() {
			
			return previousIndex >= 0;
		}

		@Override
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException("Invalid element.");
			}
			E rtn = current.data;
			lastRetrieved = current;
			current = previous;
			previousIndex--;
			nextIndex--;
			next = previous;
			previous = (LinkedList<E>.ListNode) get(previousIndex);
			return rtn;
		}

		@Override
		public int nextIndex() {
			
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			
			return previousIndex;
		}

		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalStateException("Invalid operation.");
			}
				
				
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(E e) {
			if(lastRetrieved == null) {
				throw new IllegalStateException("Cannot set a null element."); 
			}
			if(e == null) {
				throw new NullPointerException("Cannot set a null element.");
			}
			
			
			// TODO Set the last element returned by last call previous() or next()
			current.data = e;
			
		}

		@Override
		public void add(E e) {
			if(e == null) {
				throw new NullPointerException("Cannot set a null element.");
			}
			
			// TODO Auto-generated method stub
			
			
			lastRetrieved = null;
		}
		
		
		
	}
	
	
	
	
	
	private class ListNode { 

		/**Private data field for the current ListNode  */
		public E data;
		/** Next node on the list   */
		public ListNode next;
		/** Previous node on the list */
		public ListNode prev;
		
		
		public ListNode(E data) {
			this(data, null, null);
		}
		
		
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	
	
	
	}
	
	
}
