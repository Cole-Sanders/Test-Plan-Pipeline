/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

/**
 * A class that represents the test results of a test case. It contains the data
 * for if a test case passed or failed and the actual results of that test case.
 * It has getters and setters for that data as well as functionality to print that
 * data in string format.
 * @author Cole Sanders
 *
 */
public class TestResult {
	
	/** A string representing that a test case has passed */
	public static final String PASS = "PASS";
	/** A string representing that a test case has failed */
	public static final String FAIL = "FAIL";
	/** A boolean that is true for a passing test case, and false for a failing one */
	private boolean passing;
	/** A string containing the description of a test cases actual results */
	private String actualResults;
	
	/**
	 * Constructs a TestResult and initializes its fields.
	 * @param passing a boolean that is true for a passing test case, and false
	 * for a failing one.
	 * @param actualResults the actual results of a test case.
	 * @throws IllegalArgumentException if the passed in actual results string is
	 * null or empty.
	 */
	public TestResult(boolean passing, String actualResults) {
		setActualResults(actualResults);
		setPassing(passing);
	}
	
	/**
	 * Gets the actual results of a test case.
	 * @return the actual results
	 */
	public String getActualResults() {
		return actualResults;
	}
	
	/**
	 * Sets the actual results field to the value of the string parameter.
	 * @param actualResults the actual results of a test case.
	 * @throws IllegalArgumentException if the passed in string is null or empty.
	 */
	private void setActualResults(String actualResults) {
		if (actualResults == null || "".equals(actualResults)) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		this.actualResults = actualResults;
	}
	
	/**
	 * Determines if a test case is passing or not.
	 * @return a boolean value that is true if the test case is
	 * passing and false if not.
	 */
	public boolean isPassing() {
		return passing;
	}
	
	/**
	 * Sets the passing field to the value of the passed in parameter.
	 * @param passing a boolean value that is true if the test case is
	 * passing and false if not.
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}
	
	/**
	 * Returns a string of the passing status of a test case, followed by a 
	 * comma and a space, followed by the actual results description.
	 * @return a string in the format specified above
	 */
	@Override
	public String toString() {
		if (passing) {
			return PASS + ": " + actualResults;
		} else {
			return FAIL + ": " + actualResults;
		}
	}
}
