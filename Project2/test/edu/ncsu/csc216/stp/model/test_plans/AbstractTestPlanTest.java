/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the AbstractTestPlan class.
 * @author Cole Sanders
 *
 */
public class AbstractTestPlanTest {
	/**
	 * Tests constructor
	 */
	@Test
	public void testAbstractTestPlan() {
		AbstractTestPlan aplan = new TestPlan("Test Plan");
		assertEquals("Test Plan", aplan.getTestPlanName());
	}
	
	/**
	 * Tests addTestCase() method.
	 */
	@Test
	public void testAddCase() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(true, "Actual Results");
		p.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		p.addTestCase(c2);
		assertEquals(c, p.getTestCase(0));
		assertEquals(c2, p.getTestCase(1));
	}
	
	/**
	 * Tests removeTestCase() method.
	 */
	@Test
	public void testRemoveCase() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(true, "Actual Results");
		p.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		p.addTestCase(c2);
		assertEquals(c, p.removeTestCase(0));
		assertEquals(c2, p.removeTestCase(0));
		assertEquals(0, p.getTestCases().size());
	}
	
	/**
	 * Tests getTestCase() method.
	 */
	@Test
	public void testGetTestCase() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(true, "Actual Results");
		p.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		p.addTestCase(c2);
		assertEquals(c, p.getTestCase(0));
		assertEquals(c2, p.getTestCase(1));
	}
	
	/**
	 * Tests getNumberOfFailingTests() method.
	 */
	@Test
	public void testGetNumFailingTests() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(true, "Actual Results");
		p.addTestCase(c);
		assertEquals(0, p.getNumberOfFailingTests());
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		p.addTestCase(c2);
		assertEquals(1, p.getNumberOfFailingTests());
	}
	
	/**
	 * Tests addTestResult() method.
	 */
	@Test
	public void testAddTestResult() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		p.addTestCase(c);
		p.addTestResult(0, true, "Actual Results");
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		p.addTestCase(c2);
		p.addTestResult(1, false, "Actual Results2");
		assertTrue(p.getTestCase(0).isTestCasePassing());
		assertFalse(p.getTestCase(1).isTestCasePassing());
	}
	
	/**
	 * Tests hashCode().
	 */
	@Test
	public void testHashCode() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		AbstractTestPlan p2 = new TestPlan("test plan");
		AbstractTestPlan p3 = new TestPlan("Test Plan2");
		assertEquals(p.hashCode(), p2.hashCode());
		assertNotEquals(p2.hashCode(), p3.hashCode());
	}
	
	/**
	 * Tests equals().
	 */
	@Test
	public void testEquals() {
		AbstractTestPlan p = new TestPlan("Test Plan");
		AbstractTestPlan p2 = new TestPlan("test plan");
		AbstractTestPlan p3 = new TestPlan("Test Plan2");
		assertTrue(p.equals(p2));
		assertFalse(p2.equals(p3));
	}
	
}
