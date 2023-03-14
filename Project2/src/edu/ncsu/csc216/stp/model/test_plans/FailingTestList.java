/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Represents a list of the failing test cases from various test plans. Contains a 
 * name for itself and a SwapList of test cases. It contains the functionality to 
 * add test cases, set its name, get an array of information about its test cases,
 * and clear its list of test cases.
 * @author Cole Sanders
 *
 */
public class FailingTestList extends AbstractTestPlan {
	/** The name of the list of failing test cases */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Constructs a FailingTestList with the name "Failing Tests".
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 * Checks if the passed in test case is failing and adds it to its list of
	 * test cases if it is.
	 * @param testCase the test case being added to the list
	 * @throws IllegalArgumentException if the test case is passing
	 */
	@Override
	public void addTestCase(TestCase testCase) {
		if (testCase.isTestCasePassing()) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		} else {
			super.addTestCase(testCase);
		}
	}
	
	/**
	 * Sets the name of the current instance of FailingTestList to "Failing Tests" if
	 * the parameter value matches it ignoring case.
	 * @param name the name being set
	 * @throws IllegalArgumentException if the passed in name doesn't match "Failing Tests" 
	 */
	@Override
	public void setTestPlanName(String name) {
		if (FAILING_TEST_LIST_NAME.equalsIgnoreCase(name)) {
			super.setTestPlanName(FAILING_TEST_LIST_NAME);
		} else {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
	}
	
	/**
	 * Gets the test cases in the current FailingTestList in 2D array form where each
	 * row contains a test case with information about its ID, type, and the name of  
	 * its test plan.
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		String[][] cases = new String[getTestCases().size()][3];
		for (int i = 0; i < getTestCases().size(); ++i) {
			cases[i][0] = getTestCases().get(i).getTestCaseId();
			cases[i][1] = getTestCases().get(i).getTestType();
			String s = "";
			try {
				s = getTestCases().get(i).getTestPlan().getTestPlanName();
			} catch(Exception e) {
				s = "";
			}
			cases[i][2] = s;
		}
		return cases;
	}
	
	/**
	 * Clears all the test cases in the list
	 */
	public void clearTests() {
		while (getTestCases().size() > 0) {
			removeTestCase(getTestCases().size() - 1);
		}
	}
}
