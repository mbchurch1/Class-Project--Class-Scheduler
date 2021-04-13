package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.Test;



/**
 * LinkedQueueTest class 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 */
public class LinkedQueueTest {

	@Test
	public void testLinkedQueue() {
		LinkedQueue list = new LinkedQueue(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		list.enqueue("Test");
		assertEquals(1, list.size());
		list.enqueue("Test2");
		assertEquals(2, list.size());
		
	}

	@Test
	public void testIsEmpty() {
		LinkedQueue list = new LinkedQueue(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testRemoveFromList() {
		LinkedQueue list = new LinkedQueue(10);
		list.enqueue("Test");
		list.enqueue("Test2");
		list.enqueue("Test3");
		list.enqueue("Test4");
		assertEquals("Test", list.dequeue());
		assertEquals("Test2", list.dequeue());
		assertEquals(2, list.size());
	}
	
	@Test
	public void testRemoveFromEmptyList() {
		LinkedQueue list = new LinkedQueue(10);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		try {
			list.dequeue();
		} catch(NoSuchElementException e) {
			assertEquals("List is empty.", e.getMessage());
		}
	}

	@Test
	public void testSetCapacity() {
		LinkedQueue list = new LinkedQueue(10);
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
	
	@Test
	public void testAddingToAFullList() {
		LinkedQueue list = new LinkedQueue(2);
		list.enqueue("Test");
		list.enqueue("Test2");
		try {
			list.enqueue("Test3");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add to list.", e.getMessage());
		}
	}

}
