package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;


/**
 * LinkedStackTest class 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 * 
 */
public class LinkedStackTest {

	/**
	 * Testing the constructor, push, pop, isEmpty and size methods
	 */
	@Test
	public void testLinkedStack() {
		LinkedStack list = new LinkedStack(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		list.push("Test");
		list.push("Test2");
		assertEquals("Test2", list.pop());
		
	}


	/**
	 * Testing the isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		LinkedStack list = new LinkedStack(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * Testing the pop method from an empty list
	 */
	@Test
	public void testRemoveFromEmptyList() {
		LinkedStack list = new LinkedStack(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		try {
			list.pop();
		} catch(EmptyStackException e) {
			assertEquals(null, e.getMessage());
		}
	}
	
	/**
	 * Testing the setCapacity method with valid and invalid values
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack list = new LinkedStack(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertEquals(10, list.getCapacity());
		list.setCapacity(8);
		assertEquals(8, list.getCapacity());
		//Set capacity to invalid value
		try {
			list.setCapacity(-1);
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid capacity.", e.getMessage());
		}
		
	}
	
	/**
	 * Testing the push method to a full list
	 */
	@Test
	public void testAddingToAFullList() {
		LinkedStack list = new LinkedStack(2);
		list.push("Test");
		list.push("Test2");
		try {
			list.push("Test3");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add to list.", e.getMessage());
		}
	
	
	}

}
