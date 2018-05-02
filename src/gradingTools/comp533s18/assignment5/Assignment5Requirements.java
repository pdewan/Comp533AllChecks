package gradingTools.comp533s18.assignment5;

import grader.basics.execution.BasicRunningProject;
import grader.basics.project.BasicProjectIntrospection;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.comp533s18.assignment4.Assignment4Requirements;
import gradingTools.comp533s18.assignment5.testcases.ARegularSerializerPoolChecker;
import gradingTools.comp533s18.assignment5.testcases.ASerializerPoolChecker;
import gradingTools.comp533s18.assignment5.testcases.output.ABasicSerializerOutputTestSuite;
import util.annotations.Comp533Tags;

public class Assignment5Requirements extends Assignment4Requirements  {
//	public static String[] SIMULATION_CLIENT_TAGS = {DistributedTags.CLIENT, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
//	public static String[] SIMULATION_SERVER_TAGS = {DistributedTags.SERVER, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
//	public static String[] SIMULATION_REGISTRY_TAGS = {DistributedTags.REGISTRY, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};

//	public DistributedCounterProgramRunningTestCase createServerProgrammingRunTestCase(
//			SubstringSequenceChecker aChecker,
//			SingleClassTagListTestCase aServerTaggedTestCase, 
//			SingleClassTagListTestCase aClient1TaggedTestCase,
//			SingleClassTagListTestCase aClient2TaggedTestCase) {
//		return new DistributedCounterProgramRunningTestCase(
//				new ARegularCounterServerChecker(),
//				aServerTaggedTestCase,
//				aClient1TaggedTestCase,
//				aClient2TaggedTestCase);
//		
//	}
	@Override
	public void init() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		BasicRunningProject.setEchoOutput(false);
    	addJUnitTestSuite(ABasicSerializerOutputTestSuite.class);

		
		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);
		
		addGroupedCounterExperimentFetaures(
				"Counter: Custom Serialization", 				
				Comp533Tags.CUSTOM_SERIALIZER_SERVER, 
				Comp533Tags.CUSTOM_SERIALIZER_CLIENT1,
				Comp533Tags.CUSTOM_SERIALIZER_CLIENT2,
				new ASerializerPoolChecker(),
				new ASerializerPoolChecker(),
				new ARegularSerializerPoolChecker(),
				new ARegularSerializerPoolChecker()
				);
		
	}
}
