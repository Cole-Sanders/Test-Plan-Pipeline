/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Reads test plans from a file. Test plans are ordered alphabetically by name.
 * @author Cole Sanders
 *
 */
public class TestPlanReader {
	/**
	 * Reads a file containing test plans into a ISortedList of test plans. Invalid
	 * plans and test cases are ignored.
	 * @param file the file containing test plan information
	 * @throws IllegalArgumentException if the file cannot be read.
	 * @return an IsortedList of test plans
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File file){
		ISortedList<TestPlan> list = new SortedList<TestPlan>();
		Scanner fileScan;
		try {
			fileScan = new Scanner(file);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		if (fileScan.hasNext() && !("!".equals(fileScan.next()))) {
			fileScan.close();
			throw new IllegalArgumentException("Unable to load file.");
		}
		fileScan.useDelimiter("\\r?\\n?[!]");
		while (fileScan.hasNext()) {
			try {
				list.add(processTestPlan(fileScan.next()));
			} catch (Exception e) {
				//do nothing
			}
		}
		fileScan.close();
		return list;
	}
	
	/**
	 * A helper method that reads a valid string into a test plan.
	 * @param string the string containing information about the test plan.
	 * @return a test plan created from the information in the string.
	 */
	private static TestPlan processTestPlan(String string) {
		Scanner testPlanScan = new Scanner(string);
		String name = "";
		if (testPlanScan.hasNext()) {
			name = testPlanScan.next().trim();
		}
		TestPlan plan = new TestPlan(name);
		testPlanScan.useDelimiter("\\r?\\n?[#]");
		while(testPlanScan.hasNext()) {
			try {
				plan.addTestCase(processTest(plan, testPlanScan.next()));
			} catch(Exception e) {
				//Do nothing
			}
		}
		testPlanScan.close();
		return plan;
	}
	
	/**
	 * A helper method that creates a test case given the plan it comes from and 
	 * a string of information about the test case.
	 * @param plan the test plan the test case came from.
	 * @param string a string of information about the test case.
	 * @return a test case constructed from the string of information.
	 */
	private static TestCase processTest(AbstractTestPlan plan, String string) {
		Scanner testCaseScan = new Scanner(string);
		Scanner commaScan = new Scanner(testCaseScan.nextLine());
		commaScan.useDelimiter(",");
		String id = commaScan.next().trim();
		String type = commaScan.next().trim();
		testCaseScan.useDelimiter("\\r?\\n?[-]");
		String descriptionExpected = testCaseScan.next();
		Scanner starScan = new Scanner(descriptionExpected);
		starScan.useDelimiter("\\r?\\n?[*]");
		String description = starScan.next().trim();
		String expectedResults = starScan.next().trim();
		TestCase testCase = new TestCase(id, type, description, expectedResults);
		testCase.setTestPlan((TestPlan) plan);
		starScan.close();
		commaScan.close();
		
		while (testCaseScan.hasNext()) {
			boolean passing = false;
			String actResults = "";
			actResults = testCaseScan.next();
			if ("PASS".equals(actResults.substring(1, 5))) {
				passing = true;
			} else if ("FAIL".equals(actResults.substring(1, 5))) {
				passing = false;
			} else {
				testCaseScan.close();
				throw new IllegalArgumentException();
			}
			actResults = actResults.substring(7);
			actResults = actResults.trim();
			testCase.addTestResult(passing, actResults);
		}
		testCaseScan.close();
		return testCase;
	}
}
