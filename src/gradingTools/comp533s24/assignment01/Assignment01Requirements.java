package gradingTools.comp533s24.assignment01;

import grader.junit.AJUnitProjectRequirements;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.sharedTestCase.DocumentEnclosedTestCase;

public class Assignment01Requirements extends AJUnitProjectRequirements {
	public Assignment01Requirements() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		addDueDate("01/30/2024 14:05:00", 1.0);
		addDueDate("02/01/2024 12:35:00", 0.7);
        addFeature("Screenshots enclosed", 3, new DocumentEnclosedTestCase());
		addJUnitTestSuite(S24Assignment0_1SuiteNoHints.class);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_533_A0.xml");
		
//		addFeature("Server tagged", 5, new SingleClassTaggedTestCase("Server"));
//		addFeature("Client tagged", 5, new SingleClassTaggedTestCase("Client"));
//		
////		addJUnitTestSuite(Assignment3Suite.class);
//		
//		// takes 0.5 points from each read, write, and behaviors test, behavior exclusive testing
//		addFeature("Static arguments work properly", 20, new StaticArguments());
//		addFeature("One client correct connection", 20, new OneClientConnection());
//		// read/write correct and client+server behaviors
//		addFeature("One client correct read, write, and behaviors - Atomic", 35, new OneClientCorrectReadWriteTestCase(true));
//		addFeature("One client correct read, write, and behaviors - Non-atomic", 35, new OneClientCorrectReadWriteTestCase(false));
////		  threads exist
//		addFeature("One client correct threads - Atomic", 5, new OneClientCorrectThreadsTestCase(true));
//		addFeature("One client correct threads - Non-atomic", 5, new OneClientCorrectThreadsTestCase(false));
//		addFeature("One client correct message ratios - Atomic", 10, new OneClientMessageRatioTestCase(true));
//		addFeature("One client correct message ratios - Non-atomic", 10, new OneClientMessageRatioTestCase(false));
////		addFeature("One client correct values - Atomic", 10, new OneClientCorrectValuesTestCase(true));
////		addFeature("One client correct values - Non-atomic", 10, new OneClientCorrectValuesTestCase(false));
//		addFeature("Two client correct connection", 20, new TwoClientConnection());
//		// read/write correct and client+server behaviors
//		addFeature("Two client correct read, write, and behaviors - Atomic", 35, new TwoClientReadWrite(true));
//		addFeature("Two client correct read, write, and behaviors - Non-atomic", 35, new TwoClientReadWrite(false));
//		//  threads exist
//		addFeature("Two client correct threads - Atomic", 5, new TwoClientCorrectThreadsTestCase(true));
//		addFeature("Two client correct threads - Non-atomic", 5, new TwoClientCorrectThreadsTestCase(false));
//		addFeature("Two client correct message ratios - Atomic", 10, new TwoClientMessageRatioTestCase(true));
//		addFeature("Two client correct message ratios - Non-atomic", 10, new TwoClientMessageRatioTestCase(false));
//		// update ordering
//		addFeature("Read-Write-Update ordering - Atomic", 10, new ReadWriteUpdateOrderTestCase(true));
//		addFeature("Read-Write-Update ordering - Non-atomic", 10, new ReadWriteUpdateOrderTestCase(false));
	}
}
