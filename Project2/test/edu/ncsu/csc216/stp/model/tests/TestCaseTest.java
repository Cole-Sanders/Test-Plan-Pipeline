/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

/**
 * Tests the TestCase class.
 * @author Cole Sanders
 *
 */
public class TestCaseTest {
	
	/**
	 * Tests constructor
	 */
	@Test
	public void testTestCase() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		assertEquals("ID", c.getTestCaseId());
		assertEquals("Type", c.getTestType());
		assertEquals("Description", c.getTestDescription());
		assertEquals("Expected Results", c.getExpectedResults());
	}
	
	/**
	 * Tests addTestResult() method.
	 */
	@Test
	public void testAddTestResult() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		assertEquals("", c.getActualResultsLog());
		c.addTestResult(true, "Actual Results");
		assertEquals("- PASS: Actual Results\n", c.getActualResultsLog());
		c.addTestResult(false, "Actual Results");
		assertEquals("- PASS: Actual Results\n- FAIL: Actual Results\n", c.getActualResultsLog());
	}
	
	/**
	 * Tests isTestCasePassing() method.
	 */
	@Test
	public void testIsPassing() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		assertEquals("", c.getActualResultsLog());
		c.addTestResult(true, "Actual Results");
		assertTrue(c.isTestCasePassing());
		c.addTestResult(false, "Actual Results");
		assertFalse(c.isTestCasePassing());
	}
	
	/**
	 * Tests getStatus() method.
	 */
	@Test
	public void testGetStatus() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		assertEquals("", c.getActualResultsLog());
		c.addTestResult(true, "Actual Results");
		assertEquals("PASS", c.getStatus());
		c.addTestResult(false, "Actual Results");
		assertEquals("FAIL", c.getStatus());
	}
	
	/**
	 * Tests getActualResultsLog() method.
	 */
	@Test
	public void testGetActualResultsLog() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		assertEquals("", c.getActualResultsLog());
		c.addTestResult(true, "Actual Results");
		assertEquals("- PASS: Actual Results\n", c.getActualResultsLog());
		c.addTestResult(false, "Actual Results");
		assertEquals("- PASS: Actual Results\n- FAIL: Actual Results\n", c.getActualResultsLog());
	}
	
	/**
	 * Tests setTestPlan() method.
	 */
	@Test
	public void testSetTestPlan() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.setTestPlan(new TestPlan("Test Plan"));
		assertEquals("Test Plan", c.getTestPlan().getTestPlanName());
		c.setTestPlan(new TestPlan("Test Plan2"));
		assertEquals("Test Plan2", c.getTestPlan().getTestPlanName());
		c.setTestPlan(new TestPlan("Test Plan3"));
		assertEquals("Test Plan3", c.getTestPlan().getTestPlanName());
		
	}
	
	/**
	 * Tests getTestPlan() method.
	 */
	@Test
	public void testGetTestPlan() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.setTestPlan(new TestPlan("Test Plan"));
		assertEquals("Test Plan", c.getTestPlan().getTestPlanName());
		c.setTestPlan(new TestPlan("Test Plan2"));
		assertEquals("Test Plan2", c.getTestPlan().getTestPlanName());
		c.setTestPlan(new TestPlan("Test Plan3"));
		assertEquals("Test Plan3", c.getTestPlan().getTestPlanName());
	}
	
	/**
	 * Tests toString() method.
	 */
	@Test
	public void testToString() {
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(true, "Actual Results #1");
		c.addTestResult(false, "Actual Results #2");
		assertEquals("# ID,Type\n* Description\n* Expected Results\n- PASS: Actual Results #1\n- FAIL: Actual Results #2\n", c.toString());
	}
}
