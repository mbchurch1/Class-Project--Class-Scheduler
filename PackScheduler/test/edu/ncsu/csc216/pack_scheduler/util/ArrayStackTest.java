
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
		fail("Not yet implemented");
	}

	
}

