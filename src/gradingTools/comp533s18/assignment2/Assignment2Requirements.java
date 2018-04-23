package gradingTools.comp533s18.assignment2;

import grader.junit.AJUnitProjectRequirements;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.comp533s18.assignment1.testcases.SingleClassTaggedTestCase;
import gradingTools.comp533s18.assignment3.testcases.MetaStateBroadcastTestCase;
import gradingTools.comp533s18.assignment3.testcases.OneClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment3.testcases.OneClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase;
import gradingTools.comp533s18.assignment3.testcases.TwoClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment3.testcases.TwoClientCorrectReadWriteTestCase;
public class Assignment2Requirements extends AJUnitProjectRequirements {
	public Assignment2Requirements() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);
		
		addFeature("Server tagged", 5, new SingleClassTaggedTestCase("Server"));
		addFeature("Client tagged", 5, new SingleClassTaggedTestCase("Client"));
		addFeature("Registry tagged", 5, new SingleClassTaggedTestCase("Registry"));
		
//		addJUnitTestSuite(Assignment3Suite.class);4
		
//		// takes 0.5 points from each read, write, and behaviors test, behavior exclusive testing
		addFeature("Static arguments work properly", 20, new StaticArgumentsTestCase(true, true, false));
		
		addFeature("One client correct connection", 20, new OneClientCorrectConnectionTestCase(true, true, false));
		addFeature("Two client correct connection", 20, new TwoClientCorrectConnectionTestCase(true, true, false));
		
		addFeature("One client correct read, write, and behaviors - Atomic", 20, new OneClientCorrectReadWriteTestCase(true, true, true, false));
		addFeature("One client correct read, write, and behaviors - Non-Atomic", 20, new OneClientCorrectReadWriteTestCase(false, true, true, false));

		addFeature("Two client correct read, write, and behaviors - Atomic", 20, new TwoClientCorrectReadWriteTestCase(true, true, true, false));
		addFeature("Two client correct read, write, and behaviors - Non-Atomic", 20, new TwoClientCorrectReadWriteTestCase(false, true, true, false));

		addFeature("Broadcast meta-state - Enabled, set in Client", 5, new MetaStateBroadcastTestCase(true, true, false, true, false));
		addFeature("Broadcast meta-state - Enabled, set in Server", 0, new MetaStateBroadcastTestCase(true, false, false, true, false));
		addFeature("Broadcast meta-state - Disabled, set in Client", 5, new MetaStateBroadcastTestCase(false, true, false, true, false));
		addFeature("Broadcast meta-state - Disabled, set in Server", 5, new MetaStateBroadcastTestCase(false, false, false, true, false));

////		  threads exist
//		addFeature("One client correct threads - Atomic", 5, new OneClientCorrectThreadsTestCase(true));
//		addFeature("One client correct threads - Non-atomic", 5, new OneClientCorrectThreadsTestCase(false));
//		addFeature("One client correct message ratios - Atomic", 10, new OneClientMessageRatioTestCase(true));
//		addFeature("One client correct message ratios - Non-atomic", 10, new OneClientMessageRatioTestCase(false));
////		addFeature("One client correct values - Atomic", 10, new OneClientCorrectValuesTestCase(true));
////		addFeature("One client correct values - Non-atomic", 10, new OneClientCorrectValuesTestCase(false));
//		// read/write correct and client+server behaviors
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
