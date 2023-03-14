/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests the SortedList class.
 * @author Cole Sanders
 *
 */
public class SortedListTest {
	/**
	 * Tests constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> sl = new SortedList<String>();
		assertEquals(0, sl.size());
	}
	
	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		SortedList<String> sl = new SortedList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		assertEquals(10, sl.size());
		sl.add("A");
		assertEquals("A", sl.get(0));
		sl.add("String44");
		assertEquals("String4", sl.get(5));
		assertEquals("String44", sl.get(6));
		assertEquals("String5", sl.get(7));
		assertEquals(12, sl.size());
		
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		SortedList<String> sl = new SortedList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		assertEquals("String0", sl.remove(0));
		assertEquals("String1", sl.get(0));
		assertEquals(9, sl.size());
		assertEquals("String5", sl.remove(4));
		assertEquals("String9", sl.get(7));
		assertEquals(8, sl.size());
		assertEquals("String9", sl.remove(7));
		assertEquals(7, sl.size());
	}
	
	/**
	 * Tests contains()
	 */
	@Test
	public void testContains() {
		SortedList<String> sl = new SortedList<String>();
		assertFalse(sl.contains("String0"));
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		assertTrue(sl.contains("String0"));
		assertFalse(sl.contains("1"));
		assertTrue(sl.contains("String4"));
	}
	
	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		SortedList<String> sl = new SortedList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> sl.get(30));
		assertEquals("Invalid index.", e.getMessage());
	}
	
	/**
	 * Tests size()
	 */
	@Test
	public void testSize() {
		SortedList<String> sl = new SortedList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		assertEquals(10, sl.size());
		sl.remove(0);
		sl.remove(0);
		assertEquals(8, sl.size());
	}
	
	
}
