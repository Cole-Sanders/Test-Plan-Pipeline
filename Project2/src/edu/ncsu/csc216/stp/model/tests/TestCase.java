/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * Represents a test case. Contains the data for the cases ID, type, description, expected results, and
 * a log of its past passing/failing statuses and actual results. Contains getters and setters for all its
 * data as well as the functionality to add the results of a test and print the information to a string.
 * @author Cole Sanders
 *
 */
public class TestCase {
	/** A test case's ID */
	private String testCaseId;
	/** The type of a test case */
	private String testType;
	/** The description of a test case */
	private String testDescription;
	/** The expected results of a test case */
	private String expectedResults;
	/** The test plan a case belongs to */
	private TestPlan testPlan;
	/** A log of the test results for a given test plan */
	private ILog<TestResult> testResults;

	/**
	 * Constructs a test case and initializes all its fields. The parameters are set to their corresponding fields
	 * testPlan is set to null, and testResults to an empty Log of test results.
	 * @param testCaseId the ID of the test case
	 * @param testType the type of the test case
	 * @param testDescription the description of the test case
	 * @param expectedResults the expected results of a test case
	 * @throws IllegalArgumentException if any parameters are null or empty string.
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		testPlan = null;
		testResults = new Log<TestResult>();
	}
	
	/**
	 * Gets the ID of the test case.
	 * @return test case ID
	 */
	public String getTestCaseId() {
		return testCaseId;
	}
	
	/**
	 * Sets the ID of a test case.
	 * @param id the new test case ID
	 * @throws IllegalArgumentException if parameter is null or an empty string.
	 */
	private void setTestCaseId(String id) {
		if (id == null || "".equals(id)) {	
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testCaseId = id;
	}

	/**
	 * Gets the type of a test case
	 * @return test case type
	 */
	public String getTestType() {
		return testType;
	}
	
	/**
	 * Sets the type of a test case.
	 * @param testType the type of the test case being set.
	 * @throws IllegalArgumentException if parameter is null or an empty string.
	 */
	private void setTestType(String testType) {
		if (testType == null || "".equals(testType)) {	
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testType = testType;
	}
	
	/**
	 * Gets the description of a test case
	 * @return test case description
	 */
	public String getTestDescription() {
		return testDescription;
	}
	
	/**
	 * Sets the description of a test case.
	 * @param testDescription the new description of a test case
	 * @throws IllegalArgumentException if parameter is null or an empty string.
	 */
	private void setTestDescription(String testDescription) {
		if (testDescription == null || "".equals(testDescription)) {	
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testDescription = testDescription;
	}
	
	/**
	 * Gets the expected results of a test case.
	 * @return test case expected results
	 */
	public String getExpectedResults() {
		return expectedResults;
	}
	
	/**
	 * Sets the expected results of a test case.
	 * @param expectedResults the new expected results
	 * @throws IllegalArgumentException if parameter is null or an empty string.
	 */
	private void setExpectedResults(String expectedResults) {
		if (expectedResults == null || "".equals(expectedResults)) {	
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.expectedResults = expectedResults;
	}
	
	/**
	 * Creates a TestResult with the parameters and adds it to the end of 
	 * the testResults log.
	 * @param passing a boolean that is true if a test case is passing, false
	 * if it is failing.
	 * @param actualResults the actual results description of a test case.
	 * @throws IllegalArgumentException if the passed in actual results string is
	 * null or empty.
	 */
	public void addTestResult(boolean passing, String actualResults) {
		TestResult result = new TestResult(passing, actualResults);
		testResults.add(result);
	}
	
	/**
	 * Gets the last test result and returns true if it's passing, false if
	 * otherwise.
	 * @return true if the most recent test result was passing, false if otherwise
	 */
	public boolean isTestCasePassing() {
		if (testResults == null || testResults.size() == 0) {
			return false;
		} else {
			return testResults.get(testResults.size() - 1).isPassing();
		}
	}
	
	/**
	 * Gets the status of a test case.
	 * @return if the test case is passing returns "PASS", if failing
	 * returns "FAIL".
	 */
	public String getStatus() {
		if (isTestCasePassing()) {
			return TestResult.PASS;
		} else {
			return TestResult.FAIL;
		}
	}
	
	/**
	 * Returns a string listing the test results log.
	 * @return a string with the information of all past
	 * test results, whether they were passing or failing and
	 * their actual results.
	 */
	public String getActualResultsLog() {
		String s = "";
		if (testResults != null && testResults.size() > 0) {
			for (int i = 0; i < testResults.size(); ++i) {
				s += "- " + testResults.get(i).toString() + "\n";
			}
		}
		return s;
	}
	
	/**
	 * Sets the testPlan field to the passed in TestPlan.
	 * @param testPlan the test plan being set.
	 * @throws IllegalArgumentException if the parameter is null.
	 */
	public void setTestPlan(TestPlan testPlan) {
		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		this.testPlan = testPlan;
	}
	
	/**
	 * Gets the test plan that the test case belongs to.
	 * @return the test plan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}
	
	/**
	 * Outputs all the fields of the test case in a specified order in a string: test ID then type then
	 * description then expected results and finally the test results if available.
	 * @return a string in the specified format above.
	 */
	@Override
	public String toString() {
		if (testResults.size() == 0) { 
			return "# " + testCaseId + ","  + testType + "\n* " + testDescription + "\n* " + expectedResults + "\n";
		} else {
			return "# " + testCaseId + ","  + testType + "\n* " + testDescription + "\n* " + expectedResults + "\n" + getActualResultsLog();
		}
	}
}
