package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
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

	@Test
	public void testArrayQueue() {
		ArrayQueue list = new ArrayQueue(10);
		assertTrue(list.isEmpty());
		list.enqueue("Test");
		list.enqueue("Test2");
		assertEquals(2, list.size());
		assertEquals("Test", list.dequeue());
		
	}


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

}
