package gradingTools.comp533s18.assignment4;

import util.annotations.Comp533Tags;
import util.tags.DistributedTags;
import framework.grading.testing.BasicTestCase;
import grader.basics.execution.BasicRunningProject;
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
import gradingTools.comp533s18.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s18.assignment4.testcases.ARegularCounterServerChecker;
import gradingTools.comp533s18.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s18.assignment4.testcases.CustomIPCTwoClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment4.testcases.ExplicitReceiveClientTestCase;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.TwoClientCorrectConnectionTestCase;
import gradingTools.comp533s18.assignment4.testcases.blocking_rpc.ABlockingRPCClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.custom_rpc.ACustomRPCClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.custom_rpc.ARegularRPCClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.explicit_receive.AMultipleReceiveQueueChecker;
import gradingTools.comp533s18.assignment4.testcases.explicit_receive.AnExplicitReceiveClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.explicit_receive.AnExplicitReceiveServerReceivesChecker;

public class Assignment4Requirements extends AJUnitProjectRequirements {
	public static String[] SIMULATION_CLIENT_TAGS = {DistributedTags.CLIENT, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_SERVER_TAGS = {DistributedTags.SERVER, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_REGISTRY_TAGS = {DistributedTags.REGISTRY, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};

	public DistributedCounterProgramRunningTestCase addCounterExperimentFetaures(
			String anExperimentName,
			String aServerTag, 
			String aClient1Tag,
			String aClient2Tag,
			SubstringSequenceChecker aServerChecker,
			SubstringSequenceChecker aClientChecker

			) {
		SingleClassTagListTestCase anExplicitReceiveServerTagged = 
				new SingleClassTagListTestCase(aServerTag);
		SingleClassTagListTestCase anExplicitReceiveClient1Tagged = 
				new SingleClassTagListTestCase(aClient1Tag);
		SingleClassTagListTestCase anExplicitReceiveClient2Tagged = 
				new SingleClassTagListTestCase(aClient2Tag);
		addFeature(anExperimentName + " Server Tagged", 2, anExplicitReceiveServerTagged);
		addFeature(anExperimentName +  " Client1 Tagged", 2, anExplicitReceiveClient1Tagged);
		addFeature(anExperimentName + " Client2 Tagged", 2, anExplicitReceiveClient2Tagged);
		
//		addFeature("Explicit Receive Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_SERVER));
//		addFeature("Explicit Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1));
//		addFeature("Explicit Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
		DistributedCounterProgramRunningTestCase anExplicitReceiveProgramRunningTestCase = 
				new DistributedCounterProgramRunningTestCase(
						new ARegularCounterServerChecker(),
						anExplicitReceiveServerTagged,
						anExplicitReceiveClient1Tagged,
						anExplicitReceiveClient2Tagged);
		addFeature(anExperimentName + " Regular Server Output", 5, anExplicitReceiveProgramRunningTestCase);
//		addFeature("Server Creates Two Receive Queues", 5, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.SERVER_NAME,
//						new ARegularCounterServerChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
		if (aServerChecker != null) {
		addFeature(anExperimentName + " Special Server Output", 10, 
				new AStringCheckBasedDependentTestCase(
						"Special Server Output",
						DistributedCounterProgramRunningTestCase.SERVER_NAME,
						aServerChecker,
						anExplicitReceiveProgramRunningTestCase )
				);
		}
		addFeature(anExperimentName + " Regular Client 1 Output", 5, 
				new AStringCheckBasedDependentTestCase(
						" Regular Client 1 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
						new ARegularCounterClientChecker(),
						anExplicitReceiveProgramRunningTestCase )
				);
		if (aClientChecker != null) {
		addFeature(anExperimentName + " Speical Client 1 Output", 10, 
				new AStringCheckBasedDependentTestCase(
						" Special Client 1 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
						aClientChecker,
						anExplicitReceiveProgramRunningTestCase )
				);
		};
		addFeature(anExperimentName + " Regular Client 2 Output", 5, 
				new AStringCheckBasedDependentTestCase(
						"Regular Client 2 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
						new ARegularCounterClientChecker(),
						anExplicitReceiveProgramRunningTestCase )
				);
		if (aClientChecker != null) {
		addFeature(anExperimentName + " Special Client 2 Output", 10, 
				new AStringCheckBasedDependentTestCase(
						" Special Client 2 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
						aClientChecker,
						anExplicitReceiveProgramRunningTestCase )
				);
		}
		return anExplicitReceiveProgramRunningTestCase;
	}
	public DistributedCounterProgramRunningTestCase addGroupedCounterExperimentFetaures(
			String anExperimentName,
			String aServerTag, 
			String aClient1Tag,
			String aClient2Tag,
			SubstringSequenceChecker aServerChecker,
			SubstringSequenceChecker aClientChecker

			) {
		SingleClassTagListTestCase anExplicitReceiveServerTagged = 
				new SingleClassTagListTestCase(aServerTag);
		SingleClassTagListTestCase anExplicitReceiveClient1Tagged = 
				new SingleClassTagListTestCase(aClient1Tag);
		SingleClassTagListTestCase anExplicitReceiveClient2Tagged = 
				new SingleClassTagListTestCase(aClient2Tag);
		addFeature(anExperimentName + " Tags (S, C1, C2)", 6, anExplicitReceiveServerTagged,  
				anExplicitReceiveClient1Tagged, 
				anExplicitReceiveClient2Tagged);
		
//		addFeature("Explicit Receive Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_SERVER));
//		addFeature("Explicit Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1));
//		addFeature("Explicit Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
		DistributedCounterProgramRunningTestCase anExplicitReceiveProgramRunningTestCase = 
				new DistributedCounterProgramRunningTestCase(
						new ARegularCounterServerChecker(),
						anExplicitReceiveServerTagged,
						anExplicitReceiveClient1Tagged,
						anExplicitReceiveClient2Tagged);
		addFeature(anExperimentName + " Regular Output (S, C1, C2)", 15, 
				anExplicitReceiveProgramRunningTestCase,
				new AStringCheckBasedDependentTestCase(
						"Regular Client 1 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
						new ARegularCounterClientChecker(),
						anExplicitReceiveProgramRunningTestCase ),
				new AStringCheckBasedDependentTestCase(
						"Regular Client 2 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
						new ARegularCounterClientChecker(),
						anExplicitReceiveProgramRunningTestCase )						
						
				);
		BasicTestCase aClient1TestCase = new  AStringCheckBasedDependentTestCase(
				"Special Client 1 Output",
				DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
				aClientChecker,
				anExplicitReceiveProgramRunningTestCase );
		
		BasicTestCase aClient2TestCase = new  AStringCheckBasedDependentTestCase(
				"Special Client 2 Output",
				DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
				aClientChecker,
				anExplicitReceiveProgramRunningTestCase );
		BasicTestCase aServerTestCase = new  AStringCheckBasedDependentTestCase(
				"Special Server Output",
				DistributedCounterProgramRunningTestCase.SERVER_NAME,
				aServerChecker,
				anExplicitReceiveProgramRunningTestCase );
		
		if (aClientChecker != null && aServerChecker != null) {		
			
			
			addFeature(anExperimentName + " Special Output (S, C1, C2)", 30,
					aServerTestCase,
					aClient1TestCase,
					aClient2TestCase);
		} else if (aServerChecker != null) {
			addFeature(anExperimentName + " Special Output (S)", 15,
					aServerTestCase,
					aClient1TestCase,
					aClient2TestCase);
		} else if (aClientChecker != null) {
			addFeature(anExperimentName + " Special Output (C1, C2)", 30,
					aClient1TestCase,
					aClient2TestCase);
		}
//		addFeature("Server Creates Two Receive Queues", 5, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.SERVER_NAME,
//						new ARegularCounterServerChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
		
		return anExplicitReceiveProgramRunningTestCase;
	}
	public Assignment4Requirements() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		BasicRunningProject.setEchoOutput(false);
		
		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);
		DistributedCounterProgramRunningTestCase aDistributedCounterProgramRunningTestCase;
		
		 aDistributedCounterProgramRunningTestCase =
//				addCounterExperimentFetaures(	
			addGroupedCounterExperimentFetaures(
				"Explicit Receives (Part 1)", 				
				Comp533Tags.EXPLICIT_RECEIVE_SERVER, 
				Comp533Tags.EXPLICIT_RECEIVE_CLIENT1,
				Comp533Tags.EXPLICIT_RECEIVE_CLIENT2,
				new AnExplicitReceiveServerReceivesChecker(),
				new AnExplicitReceiveClientReceivesChecker());
		addFeature("Explicit Receives Server Creates Two Receive Queues", 5, 
				new AStringCheckBasedDependentTestCase(
						"Explicit Receives Server Creates Two Receive Queues",
						DistributedCounterProgramRunningTestCase.SERVER_NAME,
						new AMultipleReceiveQueueChecker(),
						aDistributedCounterProgramRunningTestCase)
				);
		 aDistributedCounterProgramRunningTestCase =
			addGroupedCounterExperimentFetaures(				
				"Custom RPC (Part 2)", 				
				Comp533Tags.CUSTOM_RPC_SERVER, 
				Comp533Tags.CUSTOM_RPC_CLIENT1,
				Comp533Tags.CUSTOM_RPC_CLIENT2,
				null,
				new ACustomRPCClientReceivesChecker());
		 addFeature("Blocking RPC client does not use regular queue for return values", 5, 
					new AStringCheckBasedDependentTestCase(
							"Blocking RPC client does not use regular queue for return values",
							DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
							new ARegularRPCClientReceivesChecker(),
							false,
							aDistributedCounterProgramRunningTestCase)
					);
		 aDistributedCounterProgramRunningTestCase =
				addGroupedCounterExperimentFetaures(				
					"Blocking RPC (Part 3)", 				
					Comp533Tags.BLOCKING_RPC_SERVER, 
					Comp533Tags.BLOCKING_RPC_CLIENT1,
					Comp533Tags.BLOCKING_RPC_CLIENT2,
					null,
					new ABlockingRPCClientReceivesChecker());
		
	
		
//		SingleClassTagListTestCase anExplicitReceiveServerTagged = new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_SERVER);
//		SingleClassTagListTestCase anExplicitReceiveClient1Tagged = new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1);
//		SingleClassTagListTestCase anExplicitReceiveClient2Tagged = new SingleClassTagListTestCase(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2);
//		addFeature("Explicit Receive Server Tagged", 2, anExplicitReceiveServerTagged);
//		addFeature("Explicit Receive Client1 Tagged", 2, anExplicitReceiveClient1Tagged);
//		addFeature("Explicit Receive Client2 Tagged", 2, anExplicitReceiveClient2Tagged);
//		
//
//		DistributedCounterProgramRunningTestCase anExplicitReceiveProgramRunningTestCase = 
//				new DistributedCounterProgramRunningTestCase(
//						new ARegularCounterServerChecker(),
//						anExplicitReceiveServerTagged,
//						anExplicitReceiveClient1Tagged,
//						anExplicitReceiveClient2Tagged);
//		addFeature("Explicit Receive Regular Server Output", 5, anExplicitReceiveProgramRunningTestCase);
//		addFeature("Server Creates Two Receive Queues", 5, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.SERVER_NAME,
//						new ARegularCounterServerChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
//		addFeature("Explicit Receive Server Receives", 10, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
//						new ARegularCounterClientChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
//		addFeature("Explicit Receive Regular Client 1 Output", 5, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
//						new ARegularCounterClientChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
//		addFeature("Explicit Receive Client 1 Receives", 10, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
//						new AnExplicitReceiveClientReceivesChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
//		;
//		addFeature("Explicit Receive Regular Client 2 Output", 5, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
//						new ARegularCounterClientChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
//		addFeature("Explicit Receive Client 2 Receives", 10, 
//				new AStringCheckBasedDependentTestCase(
//						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
//						new AnExplicitReceiveClientReceivesChecker(),
//						anExplicitReceiveProgramRunningTestCase )
//				);
				
		
	

//		addFeature("Custom RPC Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.CUSTOM_RPC_SERVER));
//		addFeature("Blocking Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.CUSTOM_RPC_CLIENT1));
//		addFeature("Blocking Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.CUSTOM_RPC_CLIENT2));
//		
//		addFeature("Blocking RPC Server Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.BLOCKING_RPC_SERVER));
//		addFeature("Blocking Receive Client1 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.BLOCKING_RPC_CLIENT1));
//		addFeature("Blocking Receive Client2 Tagged", 5, new SingleClassTagListTestCase(Comp533Tags.BLOCKING_RPC_CLIENT2));

		addFeature("Simulation Registry Tagged", 2, new SingleClassTagListTestCase(false, SIMULATION_REGISTRY_TAGS));
		addFeature("Simulation Server Tagged", 2, new SingleClassTagListTestCase(SIMULATION_SERVER_TAGS));
		addFeature("Simulation Client Tagged", 2, new SingleClassTagListTestCase(SIMULATION_CLIENT_TAGS));

//		addFeature("Registry tagged", 5, new SingleClassTagListTestCase("Registry"));
		
//		addJUnitTestSuite(Assignment3Suite.class);
		
		// takes 0.5 points from each read, write, and behaviors test, behavior exclusive testing
//		addFeature("Static arguments work properly", 20, new StaticArgumentsTestCase(true, true, true));
//		
//		addFeature("Two client correct connection", 20, new CustomIPCTwoClientCorrectConnectionTestCase(false, false, true));
		
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
