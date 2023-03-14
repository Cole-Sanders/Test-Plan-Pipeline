/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the TestResult class.
 * @author Cole Sanders
 *
 */
public class TestResultTest {
	/**
	 * Tests constructor
	 */
	@Test
	public void testTestResult() {
		TestResult r = new TestResult(true, "Test");
		assertTrue(r.isPassing());
		assertEquals("Test", r.getActualResults());
	}
	
	/**
	 * Tests getActualResults() method.
	 */
	@Test
	public void testGetActualResults() {
		TestResult r = new TestResult(true, "Test");
		assertEquals("Test", r.getActualResults());
		TestResult r2 = new TestResult(true, "Test2");
		assertEquals("Test2", r2.getActualResults());
	}
	
	/**
	 * Tests isPassing() method.
	 */
	@Test
	public void testIsPassing() {
		TestResult r = new TestResult(true, "Test");
		assertTrue(r.isPassing());
		TestResult r2 = new TestResult(false, "Test");
		assertFalse(r2.isPassing());
	}
	
	/**
	 * Tests toString() method.
	 */
	@Test
	public void testToString() {
		TestResult r = new TestResult(true, "Test");
		TestResult r2 = new TestResult(false, "Test2");
		assertEquals("PASS: Test", r.toString());
		assertEquals("FAIL: Test2", r2.toString());
	}
}
