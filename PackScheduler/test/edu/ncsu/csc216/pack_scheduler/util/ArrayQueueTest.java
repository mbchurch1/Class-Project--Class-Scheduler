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
		ArrayQueue list = new ArrayQueue();
		assertTrue(list.isEmpty());
		list.enqueue("Test");
		list.enqueue("Test2");
		assertEquals(2, list.size());
		assertEquals("Test", list.dequeue());
		
	}


	@Test
	public void testDequeueEmptyQueue() {
		ArrayQueue list = new ArrayQueue();
		assertTrue(list.isEmpty());
		try {
			list.dequeue();
		} catch (NoSuchElementException e) {
			assertEquals("List is empty.", e.getMessage());
		}
	}

	@Test
	public void testSetCapacity() {
		ArrayQueue list = new ArrayQueue();
		assertTrue(list.isEmpty());
		//Try setting invalid capacity
		try {
			list.setCapacity(-1);
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid capacity.", e.getMessage());
		}
		
	}

}
