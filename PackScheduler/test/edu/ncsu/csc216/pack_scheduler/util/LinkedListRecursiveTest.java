package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests LinkedListRecursive
 * 
 * @author Matthew Church
 * @author Will Goodwin
 * @author John Firlet
 */
public class LinkedListRecursiveTest {
	/**
	 * Tests LinkedListRecursiveConstructor
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> a = new LinkedListRecursive<String>();
		assertEquals(0, a.size());
	}
	/**
	 * Tests isEmpty
	 */
	@Test
	public void testIsEmpty() {
		LinkedListRecursive<String> b = new LinkedListRecursive<String>();
		assertEquals(0, b.size());
		assertTrue(b.isEmpty());
		b.add("empty test");
		assertEquals(1, b.size());
		assertFalse(b.isEmpty());
	}
	/**
	 * Tests size
	 */
	@Test
	public void testSize() {
		LinkedListRecursive<String> c = new LinkedListRecursive<String>();
		assertEquals(0, c.size());
		c.add("size test1");
		c.add("size test 2");
		c.add("size test 3");
		assertEquals(3, c.size());
	}
	/**
	 * Tests the add method with only the element parameter
	 */
	@Test
	public void testAdd1Param() {
		LinkedListRecursive<String> d = new LinkedListRecursive<String>();
		assertEquals(0, d.size());
		d.add("Add 1 param 1");
		assertEquals("Add 1 param 1", d.get(0));
		d.add("Add 1 param 2");
		assertEquals("Add 1 param 2", d.get(1));
		try {
			d.add(null);
			fail("Null shouldnt be added");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add element.", e.getMessage());
		}
		try {
			d.add("Add 1 param 1");
			fail("This duplicate should not be added");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add element.", e.getMessage());
		}
	}
	/**
	 * Tests the add method with an index and the element parameter
	 */
	@Test
	public void testAdd2Param() {
		LinkedListRecursive<String> e = new LinkedListRecursive<String>();
		assertEquals(0, e.size());
		e.add(0, "2Param add 1");
		e.add(1, "2Param add 2");
		e.add(1, "2Param add 3");
		assertEquals("2Param add 1", e.get(0));
		assertEquals("2Param add 3", e.get(1));
		assertEquals("2Param add 2", e.get(2));
		assertEquals(3, e.size());
		try {
			e.add(0, null);
			fail("Null shouldnt be added");
		} catch (NullPointerException a) {
			assertEquals("Element is null.", a.getMessage());
		}
		try {
			e.add(0, "2Param add 2");
			fail("This duplicate should not be added");
		} catch (IllegalArgumentException a) {
			assertEquals("Duplicate element.", a.getMessage());
		}
		try {
			e.add(8, "Throw this add, man");
			fail("Index 8 is out of bounds for a list size of 3");
		} catch (IndexOutOfBoundsException a) {
			assertEquals("Invalid index.", a.getMessage());
		}
		try {
			e.add(-1, "Throw this add, man");
			fail("Index 8 is out of bounds for a list size of 3");
		} catch (IndexOutOfBoundsException a) {
			assertEquals("Invalid index.", a.getMessage());
		}
	}
	/**
	 * Tests get
	 */
	@Test
	public void testGet() {
		LinkedListRecursive<String> f = new LinkedListRecursive<String>();
		assertEquals(0, f.size());
		f.add("get1");
		f.add("get2");
		assertEquals("get1", f.get(0));
		assertEquals("get2", f.get(1));
		try {
			f.get(-1);
			fail("This index should be out of bounds");
		} catch(IndexOutOfBoundsException e) {
			assertEquals("-1 out of bounds for list of size 2", e.getMessage());
		}
		try {
			f.get(8);
			fail("This index should be out of bounds");
		} catch(IndexOutOfBoundsException e) {
			assertEquals("8 out of bounds for list of size 2", e.getMessage());
		}
	}
	/**
	 * Tests the element remove
	 */
	@Test
	public void testRemove1Param() {
		LinkedListRecursive<String> g = new LinkedListRecursive<String>();
		assertEquals(0, g.size());
		String a = "a";
		String b = "b";
		String c = "c";
		g.add(a);
		g.add(b);
		assertEquals(2, g.size());
		assertTrue(g.remove(b));
		assertEquals(1, g.size());
		assertTrue(g.remove(a));
		assertFalse(g.remove(c));
	}

}