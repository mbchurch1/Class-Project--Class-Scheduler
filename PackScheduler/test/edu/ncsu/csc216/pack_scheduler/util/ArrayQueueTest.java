package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;


/**
 * ArrayQueueTest class 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 */
public class ArrayQueueTest {

	
	/**
	 * Testing the constructor, enqueue and size methods
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue list = new ArrayQueue(10);
		assertTrue(list.isEmpty());
		list.enqueue("Test");
		list.enqueue("Test2");
		assertEquals(2, list.size());
		assertEquals("Test", list.dequeue());
		
	}


	/**
	 * Testing the dequeue method
	 */
	@Test
	public void testDequeueEmptyQueue() {
		ArrayQueue list = new ArrayQueue(10);
		assertTrue(list.isEmpty());
		try {
			list.dequeue();
		} catch (NoSuchElementException e) {
			assertEquals("List is empty.", e.getMessage());
		}
	}

	/**
	 * Testing the setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue list = new ArrayQueue(10);
		assertTrue(list.isEmpty());
		//Try setting invalid capacity
		try {
			list.setCapacity(-1);
			fail("Capacity cannot be negative.");
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid capacity.", e.getMessage());
		}
		
		list.enqueue("Test1");
		list.enqueue("Test2");
		list.enqueue("Test3");
		assertEquals(3, list.size());
		
		try {
			list.setCapacity(2);
			fail("Capacity cannot be less than size of ArrayQueue.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid capacity.", e.getMessage());
		}
		
		list.setCapacity(50);
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Testing enqueue to add an element at max capacity
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue list = new ArrayQueue(10);
		assertTrue(list.isEmpty());
		
		list.setCapacity(5);
		
		list.enqueue("Test1");
		list.enqueue("Test2");
		list.enqueue("Test3");
		list.enqueue("Test4");
		list.enqueue("Test5");
		assertEquals(5, list.size());
		
		try {
			list.enqueue("Test6");
			fail("Cannot add when list is at capacity.");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add to queue.", e.getMessage());
		}
	}

}
