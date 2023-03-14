/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the FailingTestList class.
 * @author Cole Sanders
 *
 */
public class FailingTestListTest {
	/**
	 * Tests constructor
	 */
	@Test
	public void testFailingTestList() {
		FailingTestList list = new FailingTestList();
		assertEquals("Failing Tests", list.getTestPlanName());
	}
	
	/**
	 * Tests addTestCase() method.
	 */
	@Test
	public void testAddTestCase() {
		FailingTestList list = new FailingTestList();
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(false, "Actual Results");
		list.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		list.addTestCase(c2);
		assertEquals(c, list.getTestCase(0));
		assertEquals(c2, list.getTestCase(1));
	}
	
	/**
	 * Tests setTestPlanName() method.
	 */
	@Test
	public void testSetTestPlanName() {
		FailingTestList list = new FailingTestList();
		list.setTestPlanName("failing testS");
		assertEquals("Failing Tests", list.getTestPlanName());
	}
	
	/**
	 * Tests getTestCasesAsArray() method.
	 */
	@Test
	public void testGetTestCasesAsArray() {
		FailingTestList list = new FailingTestList();
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(false, "Actual Results");
		TestPlan plan = new TestPlan("Test Plan");
		c.setTestPlan(plan);
		list.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		list.addTestCase(c2);
		assertEquals("ID", list.getTestCasesAsArray()[0][0]);
		assertEquals("ID2", list.getTestCasesAsArray()[1][0]);
		assertEquals("Type", list.getTestCasesAsArray()[0][1]);
		assertEquals("Type2", list.getTestCasesAsArray()[1][1]);
		assertEquals("Test Plan", list.getTestCasesAsArray()[0][2]);
		assertEquals("", list.getTestCasesAsArray()[1][2]);
	}
	
	/**
	 * Tests clearTests() method.
	 */
	@Test
	public void testClearTests() {
		FailingTestList list = new FailingTestList();
		list.clearTests();
		assertEquals(0, list.getTestCases().size());
		TestCase c = new TestCase("ID", "Type", "Description", "Expected Results");
		c.addTestResult(false, "Actual Results");
		TestPlan plan = new TestPlan("Test Plan");
		c.setTestPlan(plan);
		list.addTestCase(c);
		TestCase c2 = new TestCase("ID2", "Type2", "Description2", "Expected Results2");
		c2.addTestResult(false, "Actual Results2");
		list.addTestCase(c2);
		assertEquals(2, list.getTestCases().size());
		list.clearTests();
		assertEquals(0, list.getTestCases().size());
	}
}
