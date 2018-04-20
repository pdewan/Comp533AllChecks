package gradingTools.comp533s18.assignment3;

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

public class Assignment3Requirements extends AJUnitProjectRequirements {
	public Assignment3Requirements() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);
		
		addFeature("Server tagged", 5, new SingleClassTaggedTestCase("Server"));
		addFeature("Client tagged", 5, new SingleClassTaggedTestCase("Client"));
		addFeature("Registry tagged", 5, new SingleClassTaggedTestCase("Registry"));
		
//		addJUnitTestSuite(Assignment3Suite.class);
		
		// takes 0.5 points from each read, write, and behaviors test, behavior exclusive testing
		addFeature("Static arguments work properly", 20, new StaticArgumentsTestCase(true, true, true));
//		
		addFeature("One client correct connection", 20, new OneClientCorrectConnectionTestCase(false, true, true));
		addFeature("Two client correct connection", 20, new TwoClientCorrectConnectionTestCase(false, true, true));
		
		addFeature("One client correct read, write, and behaviors - Atomic", 20, new OneClientCorrectReadWriteTestCase(false, true, true, true));
		addFeature("One client correct read, write, and behaviors - Non-Atomic", 20, new OneClientCorrectReadWriteTestCase(false, true, true, true));

		addFeature("Two client correct read, write, and behaviors - Atomic", 20, new TwoClientCorrectReadWriteTestCase(false, true, true, true));
		addFeature("Two client correct read, write, and behaviors - Non-Atomic", 20, new TwoClientCorrectReadWriteTestCase(false, true, true, true));

		addFeature("Broadcast meta state - Atomic from Client", 20, new MetaStateBroadcastTestCase(true, true, true, true, true));
//		addFeature("Broadcast meta state - Atomic from Server", 20, new MetaStateBroadcastTestCase(true, false, true, true, true));
//		addFeature("Broadcast meta state - Non-Atomic from Client", 20, new MetaStateBroadcastTestCase(false, true, true, true, true));
//		addFeature("Broadcast meta state - Non-Atomic from Server", 20, new MetaStateBroadcastTestCase(false, false, true, true, true));

		
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
//		addFeature("Two client correct connection", 20, new TwoClientCorrectConnectionTestCase());
//		// read/write correct and client+server behaviors
//		addFeature("Two client correct read, write, and behaviors - Atomic", 35, new TwoClientCorrectReadWriteTestCase(true));
//		addFeature("Two client correct read, write, and behaviors - Non-atomic", 35, new TwoClientCorrectReadWriteTestCase(false));
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
