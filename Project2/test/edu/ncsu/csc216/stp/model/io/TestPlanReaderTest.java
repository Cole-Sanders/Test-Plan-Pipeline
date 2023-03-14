/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Tests the TestPlanReader class
 * @author Cole Sanders
 *
 */
public class TestPlanReaderTest {
	
	/**
	 * Tests readTestPlansFile() method.
	 */
	@Test
	public void testReadTestPlansFile() {
		File file = new File("test-files/test-plans1.txt");
		ISortedList<TestPlan> list = TestPlanReader.readTestPlansFile(file);
		assertEquals(2, list.size());
		assertEquals("PackScheduler", list.get(0).getTestPlanName());
		assertFalse(list.get(1).getTestCase(2).isTestCasePassing());
		assertEquals("T24 Add new course", list.get(0).getTestCase(0).getTestCaseId());
		assertEquals("- PASS: course added (Lab 5)\n- FAIL: course not added b/c of invalid course name (Lab 6)\n", list.get(0).getTestCase(0).getActualResultsLog());
	}
}
