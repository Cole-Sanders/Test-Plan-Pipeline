/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests the TestPlanWriter class.
 * @author Cole Sanders
 *
 */
public class TestPlanWriterTest {
	/**
	 * Tests writeTestPlanFile() method.
	 */
	@Test
	public void test() {
		TestPlan plan1 = new TestPlan("TestPlan1");
		TestCase case1 = new TestCase("ID 0", "type 0", "description 0", "expected results 0");
		case1.addTestResult(false, "actual results 0-1");
		plan1.addTestCase(case1);
		TestCase case2 = new TestCase("ID 1", "type 1", "description 1", "expected results 1");
		case2.addTestResult(true, "actual results 1-1");
		case2.addTestResult(false, "actual results 1-2");
		plan1.addTestCase(case2);
		TestCase case3 = new TestCase("ID 2", "type 2", "description 2", "expected results 2");
		case3.addTestResult(true, "actual results 2-1");
		case3.addTestResult(true, "actual results 2-2");
		plan1.addTestCase(case3);
		TestPlan plan2 = new TestPlan("TestPlan2");
		ISortedList<TestPlan> list = new SortedList<TestPlan>();
		list.add(plan1);
		list.add(plan2);
		File file = new File("test-files/actual_out.txt");
		try {
			TestPlanWriter.writeTestPlanFile(file, list);
			File actFile = new File("test-files/actual_out.txt");
			File expectFile = new File("test-files/expected_out.txt");
			compareFiles(actFile, expectFile);
		} catch(Exception e) {
			fail();
		}
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
