/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests the SwapList class.
 * @author Cole Sanders
 *
 */
public class SwapListTest {
	/**
	 * Tests the constructor.
	 */
	@Test
	public void testSwapList() {
		SwapList<String> sl = new SwapList<String>();
		assertEquals(0, sl.size());
	}
	
	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		SwapList<String> sl = new SwapList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		sl.remove(0);
		sl.remove(4);
		sl.add("String11");
		sl.remove(5);
		sl.moveDown(7);
		sl.moveUp(6);
		sl.moveToBack(3);
		sl.moveToFront(7);
		sl.add("String12");
		sl.add("String13");
		sl.add("String14");
		assertEquals(11, sl.size());
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		SwapList<String> sl = new SwapList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		assertEquals("String0", sl.remove(0));
		assertEquals("String4", sl.remove(3));
		assertEquals("String9", sl.remove(7));
		assertEquals(7, sl.size());
	}
	
	/**
	 * Tests moveUp()
	 */
	@Test
	public void testMoveUp() {
		SwapList<String> sl = new SwapList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		sl.moveUp(1);
		assertEquals("String1", sl.get(0));
		assertEquals("String0", sl.get(1));
		sl.moveUp(5);
		assertEquals("String5", sl.get(4));
		assertEquals("String3", sl.get(3));
		assertEquals("String4", sl.get(5));
		sl.moveUp(9);
		assertEquals("String9", sl.get(8));
		assertEquals("String8", sl.get(9));
	}
	
	/**
	 * Tests moveDown()
	 */
	@Test
	public void testMoveDown() {
		SwapList<String> sl = new SwapList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		sl.moveDown(0);
		assertEquals("String1", sl.get(0));
		assertEquals("String0", sl.get(1));
		sl.moveDown(5);
		assertEquals("String5", sl.get(6));
		assertEquals("String4", sl.get(4));
		assertEquals("String6", sl.get(5));
		sl.moveDown(8);
		assertEquals("String9", sl.get(8));
		assertEquals("String8", sl.get(9));
	}
	
	/**
	 * Tests moveToFront()
	 */
	@Test
	public void testMoveFront() {
		SwapList<String> sl = new SwapList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		sl.moveToFront(1);
		assertEquals("String1", sl.get(0));
		assertEquals("String0", sl.get(1));
		sl.moveToFront(5);
		assertEquals("String5", sl.get(0));
		assertEquals("String4", sl.get(5));
		assertEquals("String6", sl.get(6));
		sl.moveToFront(9);
		assertEquals("String9", sl.get(0));
		assertEquals("String8", sl.get(9));
	}
	
	/**
	 * Tests moveToBack()
	 */
	@Test
	public void testMoveBack() {
		SwapList<String> sl = new SwapList<String>();
		for (int i = 0; i < 10; ++i) {
			sl.add("String" + i);
			assertEquals("String" + i, sl.get(i));
		}
		sl.moveToBack(0);
		assertEquals("String1", sl.get(0));
		assertEquals("String0", sl.get(9));
		sl.moveToBack(4);
		assertEquals("String5", sl.get(9));
		assertEquals("String4", sl.get(3));
		assertEquals("String6", sl.get(4));
		sl.moveToBack(6);
		assertEquals("String9", sl.get(6));
		assertEquals("String8", sl.get(9));
	}
	
	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		SwapList<String> sl = new SwapList<String>();
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
		SwapList<String> sl = new SwapList<String>();
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
