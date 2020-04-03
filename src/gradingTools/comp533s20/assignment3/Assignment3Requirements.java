package gradingTools.comp533s20.assignment3;

import grader.basics.execution.BasicProjectExecution;
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

public class Assignment3Requirements extends AJUnitProjectRequirements {
	public Assignment3Requirements() {
		
//		BasicProjectExecution.setReRunInfiniteProcesses(true);
//		GraderBasicsTraceUtility.setBufferTracedMessages(false);
//		Tracer.setMaxTraces(4000);
		
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		addDueDate("02/19/2020 01:00:00", 1.05); // early extra
		addDueDate("02/21/2020 01:00:00", 1.03); // other early extra
		addDueDate("02/26/2020 01:00:00", 1.0); // regular
		addDueDate("03/4/2020 01:00:00", 0.90); // one week late
		addDueDate("03/18/2020 01:00:00", 0.75); // two weeks late
		
		addJUnitTestSuite(Assignment3Suite.class);
        addManualFeature("Test Cases", 20, false);
		

	}
}
