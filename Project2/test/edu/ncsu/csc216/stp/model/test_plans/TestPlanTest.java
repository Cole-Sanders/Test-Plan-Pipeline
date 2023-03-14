/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the TestPlan class.
 * @author Cole Sanders
 *
 */
public class TestPlanTest {
	/**
	 * Tests constructor
	 */
	@Test
	public void testTestPlan() {
		TestPlan p = new TestPlan("Test Plan");
		assertEquals("Test Plan", p.getTestPlanName());
	}
	
	/**
	 * Tests getTestCasesAsArray() method.
	 */
	@Test
	public void testGetTestCasesAsArray() {
		TestPlan p = new TestPlan("Test Plan");
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(true, "Actual Results");
		p.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		p.addTestCase(c2);
		assertEquals("ID", p.getTestCasesAsArray()[0][0]);
		assertEquals("ID2", p.getTestCasesAsArray()[1][0]);
		assertEquals("Type", p.getTestCasesAsArray()[0][1]);
		assertEquals("Type2", p.getTestCasesAsArray()[1][1]);
		assertEquals("PASS", p.getTestCasesAsArray()[0][2]);
		assertEquals("FAIL", p.getTestCasesAsArray()[1][2]);
	}
	
	/**
	 * Tests addTestCase() method.
	 */
	@Test
	public void testAddTestCase() {
		TestPlan p = new TestPlan("Test Plan");
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
	 * Tests compareTo() method.
	 */
	@Test
	public void testCompareTo() {
		TestPlan plan = new TestPlan("Test Plan");
		TestPlan plan2 = new TestPlan("test plan");
		TestPlan plan3 = new TestPlan("test plans");
		TestPlan plan4 = new TestPlan("test a plan");
		TestPlan plan5 = new TestPlan("test z plan");
		assertEquals(0, plan.compareTo(plan2));
		assertEquals(-1, plan2.compareTo(plan3));
		assertEquals(1, plan3.compareTo(plan4));
		assertEquals(-1, plan.compareTo(plan5));
	}
}
