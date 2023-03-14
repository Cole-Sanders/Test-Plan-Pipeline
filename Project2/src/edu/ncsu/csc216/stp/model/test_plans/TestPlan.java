package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * A class that represents a test plan. It extends the AbstractTestPlan interface to provide specific functionality
 * not shared with the FailingTestList class. Its specific functionality includes getting its test cases as an 2D string
 * array of information, adding test cases, and implementing the Comparable interface for sorting functionality used in 
 * the SortedList class.
 * @author Cole Sanders
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	/**
	 * Constructs a TestPlan and sets its name equal to the parameter value.
	 * @param name the name of the test plan
	 * @throws IllegalArgumentException if the name parameter is the name as the 
	 * name of the failing tests list
	 */
	public TestPlan(String name) {
		super(name);
		if (FailingTestList.FAILING_TEST_LIST_NAME.equalsIgnoreCase(name)) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}
	
	/**
	 * Gets the test cases in the current TestPlan in 2D array form where each
	 * row contains a test case with information about its ID, type, and passing 
	 * or failing status.
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		String[][] cases = new String[getTestCases().size()][3];
		for (int i = 0; i < getTestCases().size(); ++i) {
			cases[i][0] = getTestCases().get(i).getTestCaseId();
			cases[i][1] = getTestCases().get(i).getTestType();
			cases[i][2] = getTestCases().get(i).getStatus();
		}
		return cases;
	}
	
	/**
	 * Adds the passed in test case to the test plan and sets that test cases
	 * TestPlan to the test plan it was just added to.
	 */
	@Override
	public void addTestCase(TestCase testCase) {
		super.addTestCase(testCase);
		testCase.setTestPlan(this);
	}
	
	/**
	 * Compares the two names of the current test plan and the parameter passed in 
	 * test plan. This comparison is based on their names and is case insensitive.
	 * @return -1 if the current instance of TestPlan is less than the passed in 
	 * instance, 0 if they are equal, and 1 if the current instance is greater.
	 */
	@Override
	public int compareTo(TestPlan testPlan) {
		String current = getTestPlanName();
		current = current.toLowerCase();
		String compare = testPlan.getTestPlanName();
		compare = compare.toLowerCase();
		int difference = current.compareTo(compare);
		if (difference > 0) {
			return 1;
		} else if (difference < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
