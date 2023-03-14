/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import java.util.Objects;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * An abstract class that represents a generic form of test plan. It is inherited by two 
 * subclasses TestPlan and FailingTestList which use the non-abstracted methods from this
 * class in common, and override the other methods. An AbstractTestPlan stores the data 
 * for its name and a SwapList of test cases. It has the functionality to add and remove
 * test cases, get the number of failing test cases, and results to a test case, and get
 * test cases in the form of a 2D array.
 * @author Cole Sanders
 *
 */
public abstract class AbstractTestPlan {
	
	/** The name of the test plan */
	private String testPlanName;
	/** A list containing the test cases in a test plan with special functionality to allow shifting */
	private ISwapList<TestCase> testCases;
	
	/**
	 * Constructs an AbstractTestPlan and initializes the name field to the parameter value.
	 * An empty swap list is constructed to store test cases.
	 * @param testPlanName the name of the test plan being set
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public AbstractTestPlan(String testPlanName) {
		setTestPlanName(testPlanName);
		testCases = new SwapList<TestCase>();
	}

	/**
	 * Gets the name of the test plan
	 * @return the testPlanName
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * Sets the test plan name to the given parameter value.
	 * @param testPlanName the name of the test plan being set
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setTestPlanName(String testPlanName) {
		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.testPlanName = testPlanName;
	}

	/**
	 * Gets the list of test cases
	 * @return the list of test cases
	 */
	public ISwapList<TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Adds a test case to the end of the list of test cases.
	 * @param testCase the test case being added.
	 * @throws NullPointerException if the passed in test case is null
	 * @throws IllegalArgumentException if the test case cannot be added 
	 */
	public void addTestCase(TestCase testCase) {
		testCases.add(testCase);
	}
	
	/**
	 * Removes a test case from the given index of the list of test cases.
	 * @param index the test case will be removed from
	 * @return the removed test case
	 * @throws IndexOutOfBoundsException if the index is out of the scope of the list
	 */
	public TestCase removeTestCase(int index) {
		return testCases.remove(index);
	}
	
	/**
	 * Gets a test case from the given index in the list.
	 * @param index the index of the fetched test case
	 * @return the test case at the given index
	 * @throws IndexOutOfBoundsException if the index is out of the scope of the list
	 */
	public TestCase getTestCase(int index) {
		return testCases.get(index);
	}
	
	/**
	 * Gets the number of test cases in a test plan that are failing.
	 * @return the number of failing test cases
	 */
	public int getNumberOfFailingTests() {
		if (testCases.size() > 0) {
			int sum = 0;
			for (int i = 0; i < testCases.size(); ++i) {
				if (!testCases.get(i).isTestCasePassing()) {
					++sum;
				}
			}
			return sum;
		} else {
			return 0;
		}
	}
	
	/**
	 * Adds test results to the test case at the given index
	 * @param index the index of the test case in question
	 * @param passing a boolean that is true if the test case passes, false if it
	 * fails.
	 * @param actualResults a String description of the actual results of a test case
	 * @throws IllegalArgumentException if the passed in actual results string is
	 * null or empty.
	 */
	public void addTestResult(int index, boolean passing, String actualResults) {
		testCases.get(index).addTestResult(passing, actualResults);
	}
	
	/**
	 * An abstract method that delegates to the subclasses to get their
	 * tests cases in the form of a 2D array.
	 * @return a 2D array of the test cases
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Generates the hash code based on solely the test plan's
	 * name.
	 */
	@Override
	public int hashCode() {
		String ignoreCase = testPlanName.toLowerCase();
		return Objects.hash(ignoreCase);
	}

	/**
	 * Determines if the passed in object is equal to the current instance of
	 * AbstractTestPlan based on the test plans name ignoring case.
	 * @param obj the object being compared
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		return testPlanName.equalsIgnoreCase(other.testPlanName);
	}
	
	
	
}
