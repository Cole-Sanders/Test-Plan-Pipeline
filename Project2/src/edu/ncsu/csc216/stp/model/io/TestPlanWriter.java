/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Writes a ISortedList of test plans to an output file.
 * @author Cole Sanders
 *
 */
public class TestPlanWriter {
	
	/**
	 * Writes a list of test plans to a file.
	 * @param file the file the test plans will be written to.
	 * @param testPlans a list of test plans to be written to file.
	 * @throws IllegalArgumentException if the file is unable to be saved.
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> testPlans) {
		try {
			PrintStream fileWriter = new PrintStream(file);
			for (int i = 0; i < testPlans.size(); ++i) {
				TestPlan plan = testPlans.get(i);
				fileWriter.println("! " + plan.getTestPlanName());
				ISwapList<TestCase> list = plan.getTestCases();
				for (int j = 0; j < list.size(); ++j) {
					if (j == list.size() - 1 && i == testPlans.size() - 1) {
						fileWriter.print(list.get(j).toString().substring(0, list.get(j).toString().length() - 1));
					} else {
						fileWriter.print(list.get(j).toString());
					}
				}
			}
			fileWriter.close();
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
