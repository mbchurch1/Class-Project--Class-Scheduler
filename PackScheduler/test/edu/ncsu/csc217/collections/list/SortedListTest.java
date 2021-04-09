package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing the SortedList
 * 
 * @author Matthew Church
 * @author John Firlet
 * @author Will Goodwin
 */
public class SortedListTest {

	/**
	 * Tests the sortedlist constructor to ensure that it can accurately produce 
	 * a sortedList object
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		

		SortedList<String> list2 = new SortedList<String>();
		String[] test = {"a1", "b2", "c3", "d4", "e5", "f6", "g7", "h8", "i9", "j10", "k11"};
		for(int i = 0; i < test.length; i++) {
			list2.add(test[i]);
		}
		assertEquals(11, list2.size());
		
		
	}

	/**
	 * Testing the add() method to ensure that it can accurately add elements to the sortedList
	 * in alphabetical order
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list 
		list.add("apple"); //adding to beginning
		assertEquals("apple", list.get(0));
		assertEquals(2, list.size());
		list.add("carrot"); //adding to the end
		assertEquals("carrot", list.get(2));
		assertEquals(3, list.size());
		list.add("apricot"); //adding to the middle
		assertEquals("apricot", list.get((list.size() - 1) / 2));
		assertEquals(4, list.size());
		
		
		//Test adding a null element
	
		try {
				list.add(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}

		
		
		//Test adding a duplicate element
		try {
			list.add("apple");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("apricot", list.get(1));
		}
		
		
	}
	
	/**
	 * Testing the get() method to ensure that it can accurately get the details of 
	 * an element of the SortedList at a given index
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add("a1");
		list.add("b2");
		list.add("c3");
		assertEquals(3, list.size());
		
		//Test getting an element at an index < 0
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
			assertEquals("b2", list.get(1));
		}
		
		//Test getting an element at size
		try {
			list.get(list.size());
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
			assertEquals("c3", list.get(2));
		}
		
	}
	
	/**
	 * Testing the remove() method to ensure that it can accurately remove an element 
	 * from the SortedList at the designated index and that the remaining elements
	 * of the SortedList will shift accordingly
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add("a1");
		list.add("b2");
		list.add("c3");
		list.add("d4");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("a1", list.get(0));
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("d4", list.get(3));
		}
		
		//Test removing a middle element
		list.remove((list.size() - 1) / 2);
		assertFalse(list.contains("b2"));
		assertEquals(3, list.size());
		
		//Test removing the last element
		list.remove(list.size() - 1);
		assertFalse(list.contains("d4"));
		assertEquals(2, list.size());
		
		//Test removing the first element
		list.remove(0);
		assertFalse(list.contains("a1"));
		assertEquals(1, list.size());
		
		//Test removing the last element
		list.remove(list.size() - 1);
		assertFalse(list.contains("c3"));
		assertEquals(0, list.size());
	}
	
	/**
	 * Testing the indexOf() method with indexes of elements within the SortedList and 
	 * elements not in the list to ensure that it can accurately find the designated 
	 * string in the SortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		try {
			list.indexOf(list.get(0));
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add("a1");
		list.add("b2");
		list.add("c3");
		list.add("d4");
		
		//Test various calls to indexOf for elements in the list 
		assertEquals(1, list.indexOf("b2"));
		assertEquals(3, list.indexOf("d4"));
		assertEquals(-1, list.indexOf("e5"));
		
		//Test checking the index of null
		try {                                                    
			list.indexOf(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Testing the clear() method to ensure that it removes all elements from the SortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add("a1");
		list.add("b2");
		list.add("c3");
		list.add("d4");
		
		//Clear the list
		assertEquals(4, list.size());
		list.clear();
		
		//Test that the list is empty
		assertEquals(0, list.size());
		assertFalse(list.contains("a1"));
		assertFalse(list.contains("d4"));
	}

	/**
	 * Testing the isEmpty() method to ensure that it accurately detects when there are 
	 * no elements in SortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		
		list.add("a1");
		
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
	}

	/**
	 * Testing the contains() method to ensure that it accurately detects when 
	 * a specified string is an element in the SortedList
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("a1"));
		assertFalse(list.contains("b2"));
		assertEquals(0, list.size());
		
		list.add("a1");
		list.add("b2");
		list.add("c3");
		
		//Test some true and false cases
		assertTrue(list.contains("a1"));
		assertTrue(list.contains("b2"));
		assertFalse(list.contains("d4"));
		assertFalse(list.contains("e5"));
		assertEquals(3, list.size());
	}
	
	/**
	 * Testing the equals() method to ensure that it accurately compares the contents of two
	 * SortedList objects
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("a1");
		list1.add("b2");
		list2.add("a1");
		list2.add("b2");
		list3.add("c3");
		list3.add("d4");
		
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list2));
		
	}
	
	/**
	 * Testing the hashCode() method to ensure that it accurately sets the haschode 
	 * of a SortedList object by comparing two equal SortedList objects
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("a1");
		list1.add("b2");
		list2.add("a1");
		list2.add("b2");
		list3.add("c3");
		list3.add("d4");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
		
	}

}
 