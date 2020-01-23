package gradingTools.comp533s20.assignment1;

import grader.junit.AJUnitProjectRequirements;
import grader.trace.GraderTraceUtility;
import grader.trace.comp533.Comp533TraceUtility;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWrite;
import gradingTools.comp533s19.assignment1.testcases.OneClientCorrectThreadsTestCase;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioTestCase;
import gradingTools.comp533s19.assignment1.testcases.ReadWriteUpdateOrderTestCase;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTaggedTestCase;
import gradingTools.comp533s19.assignment1.testcases.StaticArguments;
import gradingTools.comp533s19.assignment1.testcases.TwoClientConnection;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWrite;
import gradingTools.comp533s19.assignment1.testcases.TwoClientCorrectThreadsTestCase;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioTestCase;

public class Assignment1Requirements extends AJUnitProjectRequirements {
	public Assignment1Requirements() {
		Comp533TraceUtility.setTurnOn(true);
		Comp533TraceUtility.setTracing();
		GraderTraceUtility.setTurnOn(true);
		GraderTraceUtility.setTracing();
		addDueDate("01/22/2020 01:00:00", 1.05); // early extra
		addDueDate("01/24/2020 01:00:00", 1.0); // regular
		addDueDate("01/31/2020 01:00:00", 0.90); // one week late
		addDueDate("02/07/2020 01:00:00", 0.5); // two seeks late
		
		addJUnitTestSuite(Assignment1Suite.class);
        addManualFeature("Test Cases", 20, false);
		

	}
}
