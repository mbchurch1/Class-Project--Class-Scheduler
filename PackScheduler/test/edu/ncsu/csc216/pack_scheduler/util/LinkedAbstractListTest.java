package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

//import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList.ListNode;


/**
 * LinkedAbstractListTest class 
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 * 
 */
public class LinkedAbstractListTest {

	/**
	 * tests the constructor
	 */

	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		assertEquals(10, list.getCapacity());
	}

	/**
	 * Test the add() method
	 */
	@Test
	public void testAddToLinkedAbstractList() {
		LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(10);
		assertEquals(0, list.size());
		list.add(0, "Test");
		assertEquals("Test", list.get(0));
		assertEquals(1, list.size());
		list.add(1, "Test2");
		assertEquals("Test2", list.get(1));
		assertEquals(2, list.size());
		list.add("Test3");
		assertEquals(3, list.size());
		assertEquals("Test3", list.get(2));
		list.add("apple");
		try {
			list.add("apple");
			fail("This should'nt have been added");
		} catch (IllegalArgumentException e) {
			assertEquals("Element is a duplicate.", e.getMessage());
		}
		//ListNode string = null;
		
		//Test adding apple at 0, orange at 0, banana at 1 - like TS test case
		LinkedAbstractList<Object> list2 = new LinkedAbstractList<Object>(10);
		list2.add(0, "apple");
		assertEquals("apple", list2.get(0));
		list2.add(0, "orange");
		assertEquals("orange", list2.get(0));
		assertEquals("apple", list2.get(1));
		list2.add(1, "banana");
		assertEquals("orange", list2.get(0));
		assertEquals("apple", list2.get(1));
		//assertEquals(3, list.size());
		assertEquals("banana", list2.get(2));
		//assertEquals(3, list.size());
	}
	
	/**
	 * Test the setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(10);
		LinkedAbstractList<Object> list2 = new LinkedAbstractList<Object>(8);
		assertEquals(10, list.getCapacity());
		assertEquals(8, list2.getCapacity());
		list.setCapacity(15);
		assertEquals(15, list.getCapacity());
	}

	/**
	 * tests setE
	 */
	@Test
	public void testSetE() {
		LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(10);
		list.add(0, "Test");
		assertEquals("Test", list.get(0));
		assertEquals(1, list.size());
		list.add(0, "Test2");
		assertEquals(2, list.size());
		assertEquals("Test2", list.get(0));
		assertEquals("Test", list.get(1));
		list.set(0, "Test3");
		assertEquals("Test3", list.get(0));
		assertEquals(2, list.size());
		assertEquals("Test", list.get(1));
	}

	/**
	 * tests removeE
	 */
	@Test
	public void testRemoveE() {
		LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(10);
		assertEquals(0, list.size());
		list.add(0, "Test");
		assertEquals("Test", list.get(0));
		assertEquals(1, list.size());
		list.add(1, "Test2");
		assertEquals("Test2", list.get(1));
		assertEquals(2, list.size());
		list.add(2, "Test3");
		assertEquals("Test3", list.get(2));
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals("Test2", list.get(0));
		assertEquals(2, list.size());
		list.remove(1);
		assertEquals(1, list.size());
	}
	
	/**
	 * Test null and invalid fields
	 */
	@Test
	public void testInvaldFields() {
		LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(10);
		//Try element is null
		try {
			list.add(0, null);
		} catch (NullPointerException e) {
			assertEquals("Element is null.", e.getMessage());
		}
		//Try Index out of bounds
		try {
			list.add(-1, "Test");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		try {
			list.add(1, "Test");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Try adding past capacity
		LinkedAbstractList<Object> list2 = new LinkedAbstractList<Object>(2);
		list2.add(0, "Test");
		list2.add(1, "Test2");
		try {
			list2.add(0, "Test3");
		} catch (IllegalArgumentException e) {
			assertEquals("Capacity has been met.", e.getMessage());
		}
		//Try adding duplicate
		list2.remove(1);
		try {
			list2.add(1, "Test");
		} catch (IllegalArgumentException e) {
			assertEquals("Element is a duplicate.", e.getMessage());
		}
		//Try removing from index out of bounds
		try {
			list2.remove(3);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		try {
			list2.set(3, "Test4");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		try {
			list2.set(0, null);
		} catch (NullPointerException e) {
			assertEquals("Element is null.", e.getMessage());
		}
		try {
			list2.get(3);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
	}
}
