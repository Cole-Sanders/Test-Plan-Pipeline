package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * A class that manages a set of system test plans. Its data includes a boolean value that determines if the current 
 * instance recently underwent alterations, a sorted list of test plans, a list of the failing tests from all of those
 * test plans, and a current test plan. It provides the functionality to load and save test plans from file, add a test
 * plan to the list, remove a test plan from the list, edit a test plan, get the current test plan, set the current
 * test plan, add test cases to a test plan, add results to a test case, clear its fields, and get the names of its
 * test plans.
 * @author Cole Sanders
 *
 */
public class TestPlanManager {
	/** A boolean value that keeps track of whether the most recent method call changed the fields of TestPlanManager */
	private boolean isChanged;
	
	/** A sorted list of test plans */
	private ISortedList<TestPlan> testPlans;
	
	/** A list of the failing test cases from the list of test plans */
	private FailingTestList failingTestList;
	
	/** The current test plan being edited */
	private AbstractTestPlan currentTestPlan;
	
	/**
	 * Constructs a TestPlanManager and initializes the testPlans field to an empty list, failingTestList to an empty FailingTestList, 
	 * the currentTestPlan to failingTestList, and is changed to false.
	 */
	public TestPlanManager() {
		clearTestPlans();
	}
	
	/**
	 * Loads test plans in a sorted list from the file passed in as a parameter. 
	 * @param file the file containing the test plans
	 * @throws IllegalArgumentException if the file cannot be read.
	 */
	public void loadTestPlans(File file) {
		ISortedList<TestPlan> potentialPlans = TestPlanReader.readTestPlansFile(file);
		for (int i = 0; i < potentialPlans.size(); ++i) {
			try {
				testPlans.add(potentialPlans.get(i));
			} catch (Exception e) {
				//Do nothing
			}
		}
		isChanged = true;
		setCurrentTestPlan("");
	}
	
	/**
	 * Writes the list of current test plans to a file.
	 * @param file the file the test plans will be written to.
	 * @throws IllegalArgumentException if they are unable to be written to file.
	 */
	public void saveTestPlans(File file) {
		TestPlanWriter.writeTestPlanFile(file, testPlans);
		isChanged = false;
	}
	
	/**
	 * Gets isChanged.
	 * @return the value of the field isChanged
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Creates a test plan with the given name and adds it to the list in sorted order.
	 * @param testPlanName the name of the test plan to be created and added.
	 * @throws IllegalArgumentException if the name is the same as the name of the failing tests list.
	 * @throws IllegalArgumentException if the name is already the name of a test plan in the list.
	 */
	public void addTestPlan(String testPlanName) {
		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < testPlans.size(); ++i) {
			if (testPlans.get(i).getTestPlanName().equalsIgnoreCase(testPlanName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		testPlans.add(new TestPlan(testPlanName));
		setCurrentTestPlan(testPlanName);
		isChanged = true;
	}
	
	/**
	 * Gets an array list of the test plan names starting with the failing tests list
	 * @return an array of test plan names
	 */
	public String[] getTestPlanNames() {
		String[] name = new String[testPlans.size() + 1];
		name[0] = FailingTestList.FAILING_TEST_LIST_NAME;
		for (int i = 1; i <= testPlans.size(); ++i) {
			name[i] = testPlans.get(i - 1).getTestPlanName();
		}
		return name;
	}
	
	/**
	 * Helper method that updates the failing tests list to account to account for changes in the test
	 * plans list.
	 */
	private void getFailingTests() {
		failingTestList.clearTests();
		for (int i = 0; i < testPlans.size(); ++i) {
			ISwapList<TestCase> cases = testPlans.get(i).getTestCases();
			for (int j = 0; j < cases.size(); ++j) {
				if (!cases.get(j).isTestCasePassing()) {
					failingTestList.addTestCase(cases.get(j));
				}
			}
		}
	}
	
	/**
	 * Sets the current test plan to the test plan with the same name as the parameter
	 * value. If no such test plan exists in the list the current test plan is set to
	 * the failing tests list.
	 * @param name the name of the current test plan to be set
	 */
	public void setCurrentTestPlan(String name) {
		getFailingTests();
		for (int i = 0; i < testPlans.size(); ++i) {
			if (name.equalsIgnoreCase(testPlans.get(i).getTestPlanName())) {
				currentTestPlan = testPlans.get(i);
				break;
			} else {
				currentTestPlan = failingTestList;
			}
		}
	}
	
	/**
	 * Gets the current test plan.
	 * @return the current test plan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}
	
	/**
	 * Sets the name of the current test plan to the passed in string parameter.
	 * @param name the name being set to the current test plan.
	 * @throws IllegalArgumentException if the current test plan being edited is the failing tests list
	 * @throws IllegalArgumentException if the name parameter is set to the name of the failing tests list
	 * @throws IllegalArgumentException if another test plan in the list already uses the passed in name
	 */
	public void editTestPlan(String name) {
		if (currentTestPlan.equals(failingTestList)) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		if (name.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		int index = 0;
		for (int i = 0; i < testPlans.size(); ++i) {
			if (name.equalsIgnoreCase(testPlans.get(i).getTestPlanName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
			if (currentTestPlan.equals(testPlans.get(i))) {
				index = i;
			}
		}
		TestPlan plan = testPlans.remove(index);
		plan.setTestPlanName(name);
		testPlans.add(plan);
		isChanged = true;
	}
	
	/**
	 * Removes the current test plan if it is not the failing tests list.
	 * @throws IllegalArgumentException if the current test plan is the failing tests list.
	 */
	public void removeTestPlan() {
		if (currentTestPlan.equals(failingTestList)) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		for (int i = 0; i < testPlans.size(); ++i) {
			if (testPlans.get(i).equals(currentTestPlan)) {
				testPlans.remove(i);
			}
		}
		getFailingTests();
		currentTestPlan = failingTestList;
		isChanged = true;
	}
	
	/**
	 * Adds a test case to the current test plan if it is an instance of TestPlan.
	 * The failing test list is updated if the test is failing.
	 * @param testCase the test case being added to the current test plan.
	 */
	public void addTestCase(TestCase testCase) {
		if (currentTestPlan instanceof TestPlan) {
			currentTestPlan.addTestCase(testCase);
			getFailingTests();
			isChanged = true;
		}
	}
	
	/**
	 * Adds a test result to the test case at the passed in index of the current test plan. Updates
	 * the failing tests list.
	 * @param index the index of test case being updated.
	 * @param passing a boolean that represents whether or not a test case passed.
	 * @param actualResults the actual results description of a test case.
	 */
	public void addTestResult(int index, boolean passing, String actualResults) {
		currentTestPlan.addTestResult(index, passing, actualResults);
		getFailingTests();
	}
	
	/**
	 * Sets the testPlans field to an empty list, failingTestList to an empty FailingTestList, 
	 * the currentTestPlan to failingTestList, and is changed to false.
	 */
	public void clearTestPlans() {
		isChanged = false;
		testPlans = new SortedList<TestPlan>();
		failingTestList = new FailingTestList();
		currentTestPlan = failingTestList;
	}
}
