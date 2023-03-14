/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the Log class.
 * @author Cole Sanders
 *
 */
public class LogTest {

	/**
	 * Tests the Log constructor.
	 */
	@Test
	public void testLog() {
		Log<String> log = new Log<String>();
		assertEquals(0, log.size());
	}
	
	/**
	 * Tests the add() method.
	 */
	@Test
	public void testAdd() {
		Log<String> log = new Log<String>();
		for (int i = 0; i < 20; ++i) {
			log.add("String" + i);
			assertEquals("String" + i, log.get(i));
		}
	}
	
	/**
	 * Tests the get() method
	 */
	@Test
	public void testGet() {
		Log<String> log = new Log<String>();
		log.add("1");
		log.add("2");
		log.add("3");
		assertEquals("3", log.get(2));
		assertEquals("1", log.get(0));
		assertEquals("2", log.get(1));
		Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> log.get(3));
		assertEquals("Invalid index.", e.getMessage());
	}
	
	/**
	 * Tests the size() method
	 */
	@Test
	public void testSize() {
		Log<String> log = new Log<String>();
		for (int i = 0; i < 20; ++i) {
			log.add("String" + i);
			assertEquals(i + 1, log.size());
		}
	}
}
