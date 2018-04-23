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
//
		addFeature("Broadcast meta-state - Enabled, set in Client", 5, new MetaStateBroadcastTestCase(true, true, false, false, true));
		addFeature("Broadcast meta-state - Enabled, set in Server", 0, new MetaStateBroadcastTestCase(true, false, false, false, true));
		addFeature("Broadcast meta-state - Disabled, set in Client", 5, new MetaStateBroadcastTestCase(false, true, false, false, true));
		addFeature("Broadcast meta-state - Disabled, set in Server", 5, new MetaStateBroadcastTestCase(false, false, false, false, true));
	}
}
