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
	/** Dummy node at the beginning of the linkedList */
	private ListNode dummyFront;
	/** Dummy node at the end of the linkedList */
	private ListNode dummyBack;
	/** Size of the list */
	private int size;
	
	
	
	public LinkedList() {
		dummyFront = new ListNode(null, null, dummyBack);
		dummyBack = new ListNode(null, dummyFront, null);
		front = dummyFront;
		back = dummyBack;
		front.next = back;
		back.prev = front;
		size = 0;
	}
	

	@Override
	public void add(int index, Object element) {
		
		
		// TODO Auto-generated method stub
		super.add(index, element);
		size++;
	}


	public E set(int index, Object element) {
		
		//TODO Implement set
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
		
		return new LinkedListIterator(index);
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
		
		
		
		public LinkedListIterator(int index) {

            if(index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index out of bounds.");
            }
            //previous = (LinkedList<E>.ListNode) get(index - 1);
            //next = (LinkedList<E>.ListNode) next();

            previousIndex = -1;
            nextIndex = 0;
            //Need to traverse through to index - 1
            previous = dummyFront;
            //Need to traverse through to index
            next = dummyFront.next;
            //Traversing next to point to index & previous to point to index - 1
            //TODO: May need a check in here so that it doesn't traverser out of bounds
            //   Bounds are indicated when previous.data == null and next.data == null
            //   But since the iterator starts at these spots, I'm not sure when the check should factor in
            for (int i = 0; i < index; i++) {
                next = next.next;
                nextIndex++;
                if (i < index - 1) {
                    previous = previous.next;
                    previousIndex++;
                }
            }

//            previousIndex = previousIndex();
//            nextIndex = nextIndex();
            lastRetrieved = null;

            }
		
		
		
//		public LinkedListIterator(int index) {
//			
//			if(index < 0 || index > size) {
//				throw new IndexOutOfBoundsException("Index out of bounds.");
//			}
//			//previous = (LinkedList<E>.ListNode) get(index - 1);
//			//next = (LinkedList<E>.ListNode) next();
//			
//			//Need to traverse through to index - 1
//			previous = front;
//			//Need to traverse through to index
//			next = front.next();
//			previousIndex = previousIndex();
//			nextIndex = nextIndex();
//			lastRetrieved = null;
//			   
//			}
		
		@Override
		public boolean hasNext() {
			
			return next.next != null;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException("Invalid element.");
			}
			E rtn = next.data;
			lastRetrieved = next;
			next = next.next;
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
			E rtn = previous.data;
			lastRetrieved = previous;
			next = previous;
			previousIndex--;
			nextIndex--;
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
			lastRetrieved.data = e;
			
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
