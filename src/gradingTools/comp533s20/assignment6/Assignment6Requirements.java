package gradingTools.comp533s20.assignment6;

import grader.basics.execution.BasicProjectExecution;
import grader.config.ExecutionSpecificationSelector;
import grader.junit.AJUnitProjectRequirements;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWrite;
import gradingTools.comp533s19.assignment1.testcases.OneClientCorrectThreadsTestCase;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioTestCase;
import gradingTools.comp533s19.assignment1.testcases.ReadWriteUpdateOrderTestCase;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTaggedTestCase;
import gradingTools.comp533s19.assignment1.testcases.StaticArguments;
import gradingTools.comp533s19.assignment1.testcases.TwoClientConnection;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWrite;
import trace.grader.basics.GraderBasicsTraceUtility;
import util.trace.Tracer;
import gradingTools.comp533s19.assignment1.testcases.TwoClientCorrectThreadsTestCase;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioTestCase;

public class Assignment6Requirements extends AJUnitProjectRequirements {
	public Assignment6Requirements() {
		
//		BasicProjectExecution.setReRunInfiniteProcesses(true);
//		GraderBasicsTraceUtility.setBufferTracedMessages(false);
//		Tracer.setMaxTraces(4000);
		

		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		addDueDate("04/10/2020 01:00:00", 1.05); // early extra
		addDueDate("04/15/2020 01:00:00", 1.03); // other early extra
		addDueDate("04/17/2020 01:00:00", 1.0); // regular
		addDueDate("04/25/2020 01:00:00", 0.90); // one week late
		//addDueDate("04/20/2020 01:00:00", 0.75); // two weeks late
		
		addJUnitTestSuite(Assignment6Suite.class);
        addManualFeature("Test Cases", 10, false);
        addManualFeature("Experimental Input", 10, false);
	}
}
