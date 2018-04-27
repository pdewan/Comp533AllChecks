package gradingTools.comp533s18.assignment4;

import util.annotations.Comp533Tags;
import util.tags.DistributedTags;
import grader.basics.project.BasicProjectIntrospection;
import grader.junit.AJUnitProjectRequirements;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.comp533s18.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s18.assignment1.testcases.SingleClassTaggedTestCase;
import gradingTools.comp533s18.assignment3.testcases.MetaStateBroadcastTestCase;
import gradingTools.comp533s18.assignment3.testcases.OneClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment3.testcases.OneClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase;
import gradingTools.comp533s18.assignment3.testcases.TwoClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment4.testcases.TwoClientCorrectConnectionTestCase;

public class Assignment4Requirements extends AJUnitProjectRequirements {
	public static String[] SIMULATION_CLIENT_TAGS = {DistributedTags.CLIENT, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_SERVER_TAGS = {DistributedTags.SERVER, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_REGISTRY_TAGS = {DistributedTags.REGISTRY, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};

	public Assignment4Requirements() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		
		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);
		
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);
		
		addFeature("Explicit Receive Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_SERVER));
		addFeature("Explicit Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1));
		addFeature("Explicit Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
		
		addFeature("Custom RPC Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.CUSTOM_RPC_SERVER));
		addFeature("Blocking Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.CUSTOM_RPC_CLIENT1));
		addFeature("Blocking Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.CUSTOM_RPC_CLIENT2));
		
		addFeature("Blocking RPC Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.BLOCKING_RPC_SERVER));
		addFeature("Blocking Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.BLOCKING_RPC_CLIENT1));
		addFeature("Blocking Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.BLOCKING_RPC_CLIENT2));

		addFeature("Simulation Registry Tagged", 5, new SingleClassTagListTestCase(false, SIMULATION_REGISTRY_TAGS));
		addFeature("Simulation Server Tagged", 5, new SingleClassTagListTestCase(SIMULATION_SERVER_TAGS));
		addFeature("Simulation Client Client Tagged", 5, new SingleClassTagListTestCase(SIMULATION_CLIENT_TAGS));

//		addFeature("Registry tagged", 5, new SingleClassTagListTestCase("Registry"));
		
//		addJUnitTestSuite(Assignment3Suite.class);
		
		// takes 0.5 points from each read, write, and behaviors test, behavior exclusive testing
//		addFeature("Static arguments work properly", 20, new StaticArgumentsTestCase(true, true, true));
//		
		addFeature("Two client correct connection", 20, new TwoClientCorrectConnectionTestCase(false, false, true));
		
//		addFeature("One client correct read, write, and behaviors - Atomic", 20, new OneClientCorrectReadWriteTestCase(false, false, true, true));
//		addFeature("One client correct read, write, and behaviors - Non-Atomic", 20, new OneClientCorrectReadWriteTestCase(false, false, true, true));

//		addFeature("Two client correct read, write, and behaviors - Atomic", 20, new TwoClientCorrectReadWriteTestCase(false, false, true, true));
//		addFeature("Two client correct read, write, and behaviors - Non-Atomic", 20, new TwoClientCorrectReadWriteTestCase(false, false, true, true));
//
//		addFeature("Broadcast meta-state - Enabled, set in Client", 5, new MetaStateBroadcastTestCase(true, true, false, false, true));
//		addFeature("Broadcast meta-state - Enabled, set in Server", 0, new MetaStateBroadcastTestCase(true, false, false, false, true));
//		addFeature("Broadcast meta-state - Disabled, set in Client", 5, new MetaStateBroadcastTestCase(false, true, false, false, true));
//		addFeature("Broadcast meta-state - Disabled, set in Server", 5, new MetaStateBroadcastTestCase(false, false, false, false, true));
	}
}
