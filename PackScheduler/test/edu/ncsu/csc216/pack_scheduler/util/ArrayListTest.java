/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ArrayListTest class 
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 */
public class ArrayListTest {

	/**
	 * Testing the constructor
	 */
	@Test
	public void testArrayList() {
//		E[] list = (E[]) new Object[2];
		ArrayList<Object> list = new ArrayList<Object>();
		assertEquals(0, list.size());		
	}
	
	/**
	 * Test the add() method
	 */
	@Test
	public void testAddToArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "Test");
		assertEquals("Test", list.get(0));
		list.add(1, "Test2");
		assertEquals("Test2", list.get(1));
		assertEquals(2, list.size());
	}
	
	/**
	 * Test the remove() method
	 */
	@Test
	public void testRemoveFromArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "Test");
		assertEquals("Test", list.get(0));
		list.add(1, "Test2");
		assertEquals("Test2", list.get(1));
		assertEquals(2, list.size());
		list.remove(1);
		assertEquals(1, list.size());
	}
	
	/**
	 * Test the set() method
	 */
	@Test
	public void testSetElementFromArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "Test");
		assertEquals("Test", list.get(0));
		list.add(1, "Test2");
		list.set(0, "Test3");
		assertEquals("Test3", list.get(0));
	}
	
	/**
	 * Test growArray ()
	 */
	@Test
	public void testGrowArray() {
		ArrayList list = new ArrayList();
		list.add(0, "Test1");
		list.add(1, "Test2");
		list.add(2, "Test3");
		list.add(3, "Test4");
		list.add(4, "Test5");
		list.add(5, "Test6");
		list.add(6, "Test7");
		list.add(7, "Test8");
		list.add(8, "Test9");
		list.add(9, "Test10");
		list.add(10, "Test11");
		assertEquals(11, list.size());
	}
	
	
	
	/**
	 * Test invalid elements
	 */
	@Test
	public void testInvalidElementsFromArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "Test");
		list.add(1, "Test2");
		//Test adding with a -1 value 
		try {
			list.add(-1, "Test");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Try adding a value greater than size
		try {
			list.add(5, "Test");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Test removing with a -1 value
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Test removing with a value greater than size
		try {
			list.remove(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Test get with a -1 value
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Test get with a value greater than size
		try {
			list.get(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Test set with a -1 value
		try {
			list.set(-1, "Test2");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		}
		//Test set with a value greater than size
		try {
			list.set(5, "Test2");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("List size invalid.", e.getMessage());
		} 
		//Test set with a null object
		try {
			list.set(0, null);
		} catch (NullPointerException e) {
			assertEquals("Element is null.", e.getMessage());
		} 
		//Test set with a duplicate object
		try {
			list.set(2, "Test");
		} catch (IllegalArgumentException e) {
			assertEquals("Element is a duplicate.", e.getMessage());
		}
	}
}


