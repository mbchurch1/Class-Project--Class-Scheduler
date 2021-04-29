package edu.ncsu.csc216.pack_scheduler.util;

//import java.awt.List;
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
 * @param <E> LinkedList of generic objects
 */
public class LinkedList<E> extends AbstractSequentialList<E>  {

	
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
	
	
	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		dummyFront = null;
		dummyBack = null;
		
		dummyFront = new ListNode(null, null, dummyBack);
		dummyBack = new ListNode(null, dummyFront, null);
		front = dummyFront;
		back = dummyBack;
		front.next = back;
		back.prev = front;
		size = 0;
	}
	

	/**
	 * Add method to add an element to the given index
	 */
	@Override
	public void add(int index, Object element) {
		
		if (this.contains(element)) {
			throw new IllegalArgumentException("Cannot add a duplicate element");
		}
		
		this.listIterator(index).add((E) element);
		size++;
		
	}
		//add(element);
		//ListIterator<E> i = listIterator(index);
		//LinkedListIterator linkedIterator = new LinkedListIterator(index);
		// TODO Auto-generated method stub
		//super.add(index, element);
		
	

	///**
//	 * Set method to set an element at a given index
//	 * 
//	 * @param index  Index of element to set
//	 * @param element  Element to set to 
//	 * @return element  Returns the element that is set
//	 */
//	public E set(int index, Object element) {
//		
//		//TODO Need to check if element is duplicate
////		if(LinkedList.contains((E) element)) {
////			throw new IllegalArgumentException("Duplicate element.");
////		}
//		
//		this.listIterator(index).set((E) element);
//	
//		return (E) element;
//	}
	/**
	 * Method from AbstractSequentialList
	 * @param index  Index of element to set
	 * @param element  Element to set to 
	 * @return element  Returns the element that is set
	 */
	@Override
	public E set(int index, E element) {
		
		return super.set(index, element);
	}

	/**
	 * listIterator that calls the LinkedListIterator method
	 */
	@Override
	public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
		
		return new LinkedListIterator(index);
	}

	/**
	 * Size method to return the size of the LinkedList
	 */
	@Override
	public int size() {
		return size;
	}
	
	
	/**
	 * LinkedListIterator provides functionality for all standard list methods 
	 * by implementing them in terms of an Iterator
	 * 
	 * @author Matthew Church
	 * @author Will Goodwin
	 * @author John Firlet
	 */
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
		
		
		/**
		 * LinkedListIterator constructor using an index
		 * 
		 * @param index Index of where to position the iterator
		 * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
		 */
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
            //   Traversing next to point to index & previous to point to index - 1
            //   May need a check in here so that it doesn't traverser out of bounds
            //   Bounds are indicated when previous.data == null and next.data == null
            //   But since the iterator starts at these spots, I'm not sure when the check should factor in
            for (int i = 0; i < index; i++) {
                next = next.next;
                nextIndex++;
                previous = previous.next;
                previousIndex++;
//                if (i < index - 1) {
//                    previous = previous.next;
//                    previousIndex++;
//                }
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
		
		
		/**
		 * Checks to see if the list has a next ListNode
		 * @return true or false if list has a next ListNode
		 */
		@Override
		public boolean hasNext() {
			
			return next.next != null;
		}

		/**
		 * Returns the next node on the LinkedList
		 * 
		 * @throws NoSuchElementException if the list has no more elements on the list
		 */
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

		/**
		 * Checks to see if the list has a previous Listnode
		 * @return true or false if the list has a previous ListNode
		 */
		@Override
		public boolean hasPrevious() {
			
			return previousIndex >= 0;
		}

		/**
		 * Returns the previous node on the LinkedList
		 * 
		 * @throws NoSuchElementException if the list has no more previous elements on the list
		 */
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
			previous = previous.prev;
			//previous = (LinkedList<E>.ListNode) get(previousIndex);
			
			return rtn;
		}

		/**
		 * Returns the nextIndex
		 * @return nextIndex  returns the next index value
		 * 
		 */
		@Override
		public int nextIndex() {
			
			return nextIndex;
		}

		/**
		 * Returns the previousIndex
		 * @return previousIndex  returns the previous index value
		 * 
		 */
		@Override
		public int previousIndex() {
			
			return previousIndex;
		}

		/**
		 * Removes an element from the list
		 * 
		 * @throws IllegalStateException if lastRetrieved equals null
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalStateException("Invalid operation.");
			}
			
			if(lastRetrieved == previous) {
				previous = previous.prev;
				previousIndex--;
			} else {
				next = next.next;
			}
			
			
			lastRetrieved = null;
			size--;
		}


		/**
		 * Sets the lastRetrieved node to the element passed in
		 * 
		 * @throws IllegalStateException if lastRetrieved equals null
		 * @throws NullPointerException if the element passed in equals null
		 */
		@Override
		public void set(E e) {
			if(lastRetrieved == null) {
				throw new IllegalStateException("Cannot set a null element."); 
			}
			if(e == null) {
				throw new NullPointerException("Cannot set a null element.");
			}
			
			if(lastRetrieved == previous) {
				//previous = (LinkedList<E>.ListNode) e;
				previous.data = e;
			} else {
				//next = (LinkedList<E>.ListNode) e;
				next.data = e;
			}
			
			lastRetrieved.data = e;
			
		}

		/**
		 * Adds an element to the list
		 * 
		 * @throws NullPointerException if element passed in equals null
		 */
		@Override
		public void add(E e) {
			if(e == null) {
				throw new NullPointerException("Cannot set a null element.");
			}
			ListNode temp = new ListNode(e, previous, next);
			previous.next = temp;
			next.prev = temp;

			nextIndex++;
			previousIndex++;
			lastRetrieved = null;
			
		}
		
	}
	
	
	
	
	/**
	 * ListNode inner class to create ListNodes for the LinkedList class
	 * 
	 * @author Matthew Church
	 * @author Will Goodwin
	 * @author John Firlet
	 *
	 */
	private class ListNode { 

		/**Private data field for the current ListNode  */
		public E data;
		/** Next node on the list   */
		public ListNode next;
		/** Previous node on the list */
		public ListNode prev;
		
//		/**
//		 * ListNode constructor
//		 * 
//		 * @param data Data to be used for constructing a ListNode
//		 */
//		public ListNode(E data) {
//			this(data, null, null);
//		}
		
		/**
		 * ListNode constructor for the iterator
		 * 
		 * @param data Data of the ListNode
		 * @param prev Previous ListNode in the list
		 * @param next Next ListNode in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	
	}
	
}
