package gradingTools.comp533s18.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp533s18.assignment1.testcases.OneClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment1.testcases.OneClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment1.testcases.OneClientCorrectThreadsTestCase;
import gradingTools.comp533s18.assignment1.testcases.OneClientCorrectValuesTestCase;
import gradingTools.comp533s18.assignment1.testcases.OneClientMessageRatioTestCase;
import gradingTools.comp533s18.assignment1.testcases.ReadWriteUpdateOrderTestCase;
import gradingTools.comp533s18.assignment1.testcases.StaticArgumentsTestCase;
import gradingTools.comp533s18.assignment1.testcases.TwoClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment1.testcases.TwoClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment1.testcases.TwoClientCorrectThreadsTestCase;
import gradingTools.comp533s18.assignment1.testcases.TwoClientMessageRatioTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);
		
		// takes 0.5 points from each read, write, and behaviors test, behavior exclusive testing
		addFeature("Static arguments work properly", 2, new StaticArgumentsTestCase());
		addFeature("One client correct connection", 2, new OneClientCorrectConnectionTestCase());
		// read/write correct and client+server behaviors
		addFeature("One client correct read, write, and behaviors - Atomic", 3.5, new OneClientCorrectReadWriteTestCase(true));
		addFeature("One client correct read, write, and behaviors - Non-atomic", 3.5, new OneClientCorrectReadWriteTestCase(false));
//		  threads exist
		addFeature("One client correct threads - Atomic", 0.5, new OneClientCorrectThreadsTestCase(true));
		addFeature("One client correct threads - Non-atomic", 0.5, new OneClientCorrectThreadsTestCase(false));
		addFeature("One client correct message ratios - Atomic", 1, new OneClientMessageRatioTestCase(true));
		addFeature("One client correct message ratios - Non-atomic", 1, new OneClientMessageRatioTestCase(false));
//		addFeature("One client correct values - Atomic", 10, new OneClientCorrectValuesTestCase(true));
//		addFeature("One client correct values - Non-atomic", 10, new OneClientCorrectValuesTestCase(false));
		addFeature("Two client correct connection", 2, new TwoClientCorrectConnectionTestCase());
		// read/write correct and client+server behaviors
		addFeature("Two client correct read, write, and behaviors - Atomic", 3.5, new TwoClientCorrectReadWriteTestCase(true));
		addFeature("Two client correct read, write, and behaviors - Non-atomic", 3.5, new TwoClientCorrectReadWriteTestCase(false));
		//  threads exist
		addFeature("Two client correct threads - Atomic", 0.5, new TwoClientCorrectThreadsTestCase(true));
		addFeature("Two client correct threads - Non-atomic", 0.5, new TwoClientCorrectThreadsTestCase(false));
		addFeature("Two client correct message ratios - Atomic", 1, new TwoClientMessageRatioTestCase(true));
		addFeature("Two client correct message ratios - Non-atomic", 1, new TwoClientMessageRatioTestCase(false));
		// update ordering
		addFeature("Read-Write-Update ordering - Atomic", 1, new ReadWriteUpdateOrderTestCase(true));
		addFeature("Read-Write-Update ordering - Non-atomic", 1, new ReadWriteUpdateOrderTestCase(false));
	}
}
