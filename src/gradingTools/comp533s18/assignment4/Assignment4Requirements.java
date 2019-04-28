package gradingTools.comp533s18.assignment4;

import util.annotations.Comp533Tags;
import util.tags.DistributedTags;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.TestCase;
import grader.basics.execution.BasicRunningProject;
import grader.basics.project.BasicProjectIntrospection;
import grader.junit.AJUnitProjectRequirements;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.comp533s18.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase;
import gradingTools.comp533s18.assignment4.testcases.TagbasedTwoClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment4.testcases.TwoClientCorrectReadWriteTestCase;
import gradingTools.comp533s18.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s18.assignment4.testcases.ARegularCounterServerChecker;
import gradingTools.comp533s18.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s18.assignment4.testcases.AStringPositiveAndNegativeCheckBasedDependentTestCase;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.blocking_rpc.ABlockingRPCClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.custom_rpc.ACustomRPCClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.custom_rpc.ARegularRPCClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.explicit_receive.AMultipleReceiveQueueChecker;
import gradingTools.comp533s18.assignment4.testcases.explicit_receive.AnExplicitReceiveClientReceivesChecker;
import gradingTools.comp533s18.assignment4.testcases.explicit_receive.AnExplicitReceiveServerReceivesChecker;

public class Assignment4Requirements extends AJUnitProjectRequirements {
	public static String[] SIMULATION_CLIENT_TAGS = {DistributedTags.CLIENT, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_SERVER_TAGS = {DistributedTags.SERVER, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_REGISTRY_TAGS = {DistributedTags.REGISTRY};

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
		

		DistributedCounterProgramRunningTestCase aProgrammingRunProgramRunningTestCase = 
				new DistributedCounterProgramRunningTestCase(
						new ARegularCounterServerChecker(),
						anExplicitReceiveServerTagged,
						anExplicitReceiveClient1Tagged,
						anExplicitReceiveClient2Tagged);
		addFeature(anExperimentName + " Regular Server Output", 5, aProgrammingRunProgramRunningTestCase);

		if (aServerChecker != null) {
		addFeature(anExperimentName + " Special Server Output", 10, 
				new AStringCheckBasedDependentTestCase(
						"Special Server Output",
						DistributedCounterProgramRunningTestCase.SERVER_NAME,
						aServerChecker,
						aProgrammingRunProgramRunningTestCase )
				);
		}
		addFeature(anExperimentName + " Regular Client 1 Output", 5, 
				new AStringCheckBasedDependentTestCase(
						" Regular Client 1 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
						new ARegularCounterClientChecker(),
						aProgrammingRunProgramRunningTestCase )
				);
		if (aClientChecker != null) {
		addFeature(anExperimentName + " Speical Client 1 Output", 10, 
				new AStringCheckBasedDependentTestCase(
						" Special Client 1 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
						aClientChecker,
						aProgrammingRunProgramRunningTestCase )
				);
		};
		addFeature(anExperimentName + " Regular Client 2 Output", 5, 
				new AStringCheckBasedDependentTestCase(
						"Regular Client 2 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
						new ARegularCounterClientChecker(),
						aProgrammingRunProgramRunningTestCase )
				);
		if (aClientChecker != null) {
		addFeature(anExperimentName + " Special Client 2 Output", 10, 
				new AStringCheckBasedDependentTestCase(
						" Special Client 2 Output",
						DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
						aClientChecker,
						aProgrammingRunProgramRunningTestCase )
				);
		}
		return aProgrammingRunProgramRunningTestCase;
	}
	public DistributedCounterProgramRunningTestCase createServerProgrammingRunTestCase(
			SubstringSequenceChecker aChecker,
			SingleClassTagListTestCase aServerTaggedTestCase, 
			SingleClassTagListTestCase aClient1TaggedTestCase,
			SingleClassTagListTestCase aClient2TaggedTestCase) {
		return new DistributedCounterProgramRunningTestCase(
				new ARegularCounterServerChecker(),
				aServerTaggedTestCase,
				aClient1TaggedTestCase,
				aClient2TaggedTestCase);
		
	}
	protected int regularOutputCredit() {
		return 5;
	}
	protected int specialOutputCredit() {
		return 30;
	}
	public DistributedCounterProgramRunningTestCase addGroupedCounterExperimentFeatures(
			String anExperimentName,
			String aServerTag, 
			String aClient1Tag,
			String aClient2Tag,
			SubstringSequenceChecker aServerChecker,
			SubstringSequenceChecker aClientChecker,
			SubstringSequenceChecker aServerNegativeChecker,
			SubstringSequenceChecker aClientNegativeChecker			

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
		

		DistributedCounterProgramRunningTestCase anExplicitReceiveProgramRunningTestCase = 
				createServerProgrammingRunTestCase(
						new ARegularCounterServerChecker(), 
						anExplicitReceiveServerTagged, 
						anExplicitReceiveClient1Tagged, 
						anExplicitReceiveClient2Tagged);

		addFeature(anExperimentName + " Regular Output (S, C1, C2)", regularOutputCredit(), 
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
		BasicTestCase aClient1TestCase = new  AStringPositiveAndNegativeCheckBasedDependentTestCase(
				"Special Client 1 Output",
				DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
				aClientChecker,
				aClientNegativeChecker,
				anExplicitReceiveProgramRunningTestCase
				);
		
		BasicTestCase aClient2TestCase = new  AStringPositiveAndNegativeCheckBasedDependentTestCase(
				"Special Client 2 Output",
				DistributedCounterProgramRunningTestCase.CLIENT_2_NAME,
				aClientChecker,
				aClientNegativeChecker,
				anExplicitReceiveProgramRunningTestCase );
		BasicTestCase aServerTestCase = new  AStringPositiveAndNegativeCheckBasedDependentTestCase(
				"Special Server Output",
				DistributedCounterProgramRunningTestCase.SERVER_NAME,
				aServerChecker,
				aServerNegativeChecker,
				anExplicitReceiveProgramRunningTestCase );
		
		if (aClientChecker != null && aServerChecker != null) {		
			
			
			addFeature(anExperimentName + " Special Output (S, C1, C2)", specialOutputCredit(),
					aServerTestCase,
					aClient1TestCase,
					aClient2TestCase);
		} else if (aServerChecker != null) {
			addFeature(anExperimentName + " Special Output (S)", specialOutputCredit(),
					aServerTestCase,
					aClient1TestCase,
					aClient2TestCase);
		} else if (aClientChecker != null) {
			addFeature(anExperimentName + " Special Output (C1, C2)", specialOutputCredit(),
					aClient1TestCase,
					aClient2TestCase);
		}

		
		return anExplicitReceiveProgramRunningTestCase;
	}
	public Assignment4Requirements() {
		init();
		
	}
	public void init() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		BasicRunningProject.setEchoOutput(false);
		
		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);
		DistributedCounterProgramRunningTestCase aDistributedCounterProgramRunningTestCase;
		
		 aDistributedCounterProgramRunningTestCase =
//				addCounterExperimentFetaures(	
			addGroupedCounterExperimentFeatures(
				"Explicit Receives (Part 1)", 				
				Comp533Tags.EXPLICIT_RECEIVE_SERVER, 
				Comp533Tags.EXPLICIT_RECEIVE_CLIENT1,
				Comp533Tags.EXPLICIT_RECEIVE_CLIENT2,
				new AnExplicitReceiveServerReceivesChecker(),
				new AnExplicitReceiveClientReceivesChecker(),
				null,
				null);
		addFeature("Explicit Receives Server Creates Two Receive Queues", 5, 
				new AStringCheckBasedDependentTestCase(
						"Explicit Receives Server Creates Two Receive Queues",
						DistributedCounterProgramRunningTestCase.SERVER_NAME,
						new AMultipleReceiveQueueChecker(),
						aDistributedCounterProgramRunningTestCase)
				);
		 aDistributedCounterProgramRunningTestCase =
			addGroupedCounterExperimentFeatures(				
				"Custom RPC (Part 2)", 				
				Comp533Tags.CUSTOM_RPC_SERVER, 
				Comp533Tags.CUSTOM_RPC_CLIENT1,
				Comp533Tags.CUSTOM_RPC_CLIENT2,
				null,
				new ACustomRPCClientReceivesChecker(),
				null,
				null);
		 addFeature("Blocking RPC client does not use regular queue for return values", 5, 
					new AStringCheckBasedDependentTestCase(
							"Blocking RPC client does not use regular queue for return values",
							DistributedCounterProgramRunningTestCase.CLIENT_1_NAME,
							new ARegularRPCClientReceivesChecker(),
							false,
							aDistributedCounterProgramRunningTestCase)
					);
		 aDistributedCounterProgramRunningTestCase =
				addGroupedCounterExperimentFeatures(				
					"Blocking RPC (Part 3)", 				
					Comp533Tags.BLOCKING_RPC_SERVER, 
					Comp533Tags.BLOCKING_RPC_CLIENT1,
					Comp533Tags.BLOCKING_RPC_CLIENT2,
					null,
					new ABlockingRPCClientReceivesChecker(),
					null,
					null);
			
		SingleClassTagListTestCase aRegistryTagTest = new SingleClassTagListTestCase(false, SIMULATION_REGISTRY_TAGS);
		SingleClassTagListTestCase aServerTagTest = new SingleClassTagListTestCase(false, SIMULATION_SERVER_TAGS);
		SingleClassTagListTestCase aClientTagTest = new SingleClassTagListTestCase(false, SIMULATION_CLIENT_TAGS);

//
		addFeature("Simulation Registry Tagged", 2, aRegistryTagTest);
		addFeature("Simulation Server Tagged", 2, aServerTagTest);
		addFeature("Simulation Client Tagged", 2, aClientTagTest);

//		BasicTestCase aSimulationTestCase = new TwoClientCorrectReadWriteTestCase(true, false, true, true);
		BasicTestCase aSimulationTestCase = new TagbasedTwoClientCorrectReadWriteTestCase(true, false, true, true, aRegistryTagTest, aServerTagTest, aClientTagTest);

		addFeature("Two client correct read, write, and behaviors - Atomic", 20, aSimulationTestCase);
		addFeature("Blocking RPC used in Simuilation", 20, 
				new AStringCheckBasedDependentTestCase(
						"Blocking RPC used in Simuilation",
						"Server",
						new ABlockingRPCClientReceivesChecker(),
						true,
						aSimulationTestCase)
				);

	}
}
