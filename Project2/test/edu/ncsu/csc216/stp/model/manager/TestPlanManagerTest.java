/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test the TestPlanManager class.
 * @author Cole Sanders
 *
 */
public class TestPlanManagerTest {
	/**
	 * Tests constructor
	 */
	@Test
	public void testTestPlanManager() {
		TestPlanManager tm = new TestPlanManager();
		assertFalse(tm.isChanged());
		assertEquals("Failing Tests", tm.getCurrentTestPlan().getTestPlanName());
	}
	
	/**
	 * Tests loadTestPlans() and saveTestPlans() methods.
	 */
	@Test
	public void testLoadTestPlans() {
		TestPlanManager tm = new TestPlanManager();
		File file = new File("test-files/test-plans1.txt");
		tm.loadTestPlans(file);
		assertEquals(3, tm.getTestPlanNames().length);
		assertEquals("PackScheduler", tm.getTestPlanNames()[1]);
		assertEquals("PackScheduler", tm.getTestPlanNames()[1]);
		File outFile = new File("test-files/actual-test-plans1.txt");
		File expOutFile = new File("test-files/expected-test-plans1.txt");
		tm.saveTestPlans(outFile);
		compareFiles(outFile, expOutFile);
	}
	
	/**
	 * Tests isChanged() method.
	 */
	@Test
	public void testIsChanged() {
		TestPlanManager tm = new TestPlanManager();
		assertFalse(tm.isChanged());
		File file = new File("test-files/test-plans1.txt");
		tm.loadTestPlans(file);
		assertTrue(tm.isChanged());
	}
	
	/**
	 * Tests addTestPlan() method.
	 */
	@Test
	public void testAddTestPlan() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getCurrentTestPlan().getTestPlanName());
		tm.addTestPlan("TestPlan2");
		assertEquals("TestPlan2", tm.getCurrentTestPlan().getTestPlanName());
		tm.addTestPlan("TestPlan3");
		assertEquals("TestPlan3", tm.getCurrentTestPlan().getTestPlanName());
	}
	
	/**
	 * Tests getTestPlanNames() method.
	 */
	@Test
	public void testGetTestPlanNames() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getTestPlanNames()[1]);
		tm.addTestPlan("TestPlan2");
		assertEquals("TestPlan2", tm.getTestPlanNames()[2]);
		tm.addTestPlan("TestPlan3");
		assertEquals("TestPlan3", tm.getTestPlanNames()[3]);
	}
	
	/**
	 * Tests setCurrentTestPlan() method.
	 */
	@Test
	public void testSetCurrentTestPlan() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getCurrentTestPlan().getTestPlanName());
		tm.setCurrentTestPlan("");
		assertEquals("Failing Tests", tm.getCurrentTestPlan().getTestPlanName());
		tm.setCurrentTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getCurrentTestPlan().getTestPlanName());
		tm.clearTestPlans();
		File file = new File("test-files/test-plans0.txt");
		tm.loadTestPlans(file);
		assertEquals(3, tm.getTestPlanNames().length);
		tm.setCurrentTestPlan("PackScheduler");
		assertEquals("PackScheduler", tm.getCurrentTestPlan().getTestPlanName());
	}
	
	/**
	 * Tests editTestPlan() method.
	 */
	@Test
	public void testEditTestPlan() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getCurrentTestPlan().getTestPlanName());
		tm.editTestPlan("TestPlanEdited");
		assertEquals("TestPlanEdited", tm.getCurrentTestPlan().getTestPlanName());
	}
	
	/**
	 * Tests removeTestPlan() method.
	 */
	@Test
	public void testRemoveTestPlan() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getTestPlanNames()[1]);
		tm.removeTestPlan();
		assertEquals(1, tm.getTestPlanNames().length);
		assertEquals("Failing Tests", tm.getCurrentTestPlan().getTestPlanName());
		
	}
	
	/**
	 * Tests addTestCase() method.
	 */
	@Test
	public void testAddTestCase() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getCurrentTestPlan().getTestPlanName());
		TestCase testCase = new TestCase("Id", "type", "description", "expResults");
		tm.addTestCase(testCase);
		assertEquals(1, tm.getCurrentTestPlan().getTestCases().size());
		assertEquals("Id", tm.getCurrentTestPlan().getTestCase(0).getTestCaseId());
	}
	
	/**
	 * Tests addTestResult() method.
	 */
	@Test
	public void testAddTestResult() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		TestCase testCase = new TestCase("Id", "type", "description", "expResults");
		tm.addTestCase(testCase);
		tm.addTestResult(0, false, "actResults");
		assertFalse(tm.getCurrentTestPlan().getTestCase(0).isTestCasePassing());
		TestCase testCase2 = new TestCase("Id", "type", "description", "expResults");
		tm.addTestCase(testCase2);
		tm.addTestResult(1, true, "actResults");
		assertTrue(tm.getCurrentTestPlan().getTestCase(1).isTestCasePassing());
	}
	
	/**
	 * Tests clearTestPlans() method.
	 */
	@Test
	public void testClearTestPlans() {
		TestPlanManager tm = new TestPlanManager();
		tm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tm.getCurrentTestPlan().getTestPlanName());
		assertTrue(tm.isChanged());
		tm.clearTestPlans();
		assertEquals("Failing Tests", tm.getCurrentTestPlan().getTestPlanName());
		assertFalse(tm.isChanged());
	}
	
	/**
	 * Helper method to compare two files
	 * @param actFile the actual file output from TestPlanWriter
	 * @param expectFile the expected file output from TestPlanWriter
	 */
	private void compareFiles(File actFile, File expectFile) {
		try {
			Scanner actScan = new Scanner(actFile);
			Scanner expectScan = new Scanner(expectFile);
			while(expectScan.hasNextLine()) {
				assertEquals(actScan.nextLine(), expectScan.nextLine());
			}
			actScan.close();
			expectScan.close();
		} catch (Exception e) {
			fail();
		}
	}
}
