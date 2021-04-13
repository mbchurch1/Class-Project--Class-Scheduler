
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;


/**
 * ArrayStackTest 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 *
 */
public class ArrayStackTest {

	@Test
	public void testArrayStack() {
		ArrayStack list = new ArrayStack();
		assertTrue(list.isEmpty());
		list.push("Test");
		assertEquals(1, list.size());
		assertEquals("Test", list.pop());
	}
	
	@Test
	public void testArrayStackInt() {
		ArrayStack list = new ArrayStack(10);
		assertTrue(list.isEmpty());
		list.push("Test");
		assertEquals(1, list.size());
		list.setCapacity(1);
		try {
			list.push("Test2");
			fail("Cannot push when capacity has been met.");
		} catch(IllegalArgumentException e) {
			assertEquals("Cannot add to list.", e.getMessage());
			assertEquals(1, list.size());
		}
	}

	@Test
	public void testPopEmptyStack() {
		ArrayStack list = new ArrayStack();
		assertTrue(list.isEmpty());
		try {
			list.pop();
		} catch (EmptyStackException e) {
			assertEquals(null, e.getMessage());
		}
	}
	
	
	@Test
	public void testSetCapacity() {
		ArrayStack list = new ArrayStack();
		try {
			list.setCapacity(-20);
			fail("Capacity cannot be negative.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid capacity.", e.getMessage());
		}
		
		list.push("Test1");
		list.push("Test2");
		list.push("Test3");
		assertEquals(3, list.size());
		try {
			list.setCapacity(2);
			fail("Capacity cannot be less than size of ArrayStack.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid capacity.", e.getMessage());
		}
		
		list.setCapacity(50);
		assertFalse(list.isEmpty());
	}
}

