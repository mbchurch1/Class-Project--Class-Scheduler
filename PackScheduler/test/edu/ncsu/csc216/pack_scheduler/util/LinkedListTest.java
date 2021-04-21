package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class LinkedListTest<E> {
	
	/** First name */
	private static final String FIRST_NAME = "Joe";
	/** Last Name */
	private static final String LAST_NAME = "Smith";
	/** Student ID */
	private static final String STUDENT_ID = "jsmith";
	/** Student email */
	private static final String STUDENT_EMAIL = "jsmith@gmail.com";
	/** Student password */
	private static final String STUDENT_PASSWORD = "Just$omep@ssword";
	/** Student maximum credits */
	private static final int STUDENT_MAX_CREDITS = 15;

	@Test
	public void testSize() {
		LinkedList<E> list = new LinkedList<E>();
		assertEquals(0, list.size());
	}

	@Test
	public void testLinkedList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIntObject() {
		fail("Not yet implemented");
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testAddStudentObject() {
		LinkedList<E> list = new LinkedList<E>();
		Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, STUDENT_MAX_CREDITS);
		
		list.add(0, s1);
		assertEquals(1, list.size());
		assertEquals(s1, list.get(0));
		
		Student s2 = null;
		
		try {
			list.add(1, s2);
			fail("Cannot add a null element to the list");
		} catch (NullPointerException e) {
			assertEquals("Cannot set a null element.", e.getMessage());
			assertEquals(1, list.size());
		}
		
		Student s3 = new Student("Bob", "LastnameBob", "blastnamebob", "junk@gmail.com", "NotReal!", 14);
		
		list.add(1, s3);
		assertEquals(2, list.size());
		assertEquals(s3, list.get(1));
		assertEquals(s1, list.get(0));
		
		Student s4 = new Student("Rob", "Roberts", "rroberts", "thisemail@gmail.com", "NotWay!", 15);
		list.add(0, s4);
		assertEquals(3, list.size());
		assertEquals(s4, list.get(0));
		assertEquals(s3, list.get(2));
		assertEquals(s1, list.get(1));
	}

	@Test
	public void testListIteratorInt() {
		fail("Not yet implemented");
	}

}
