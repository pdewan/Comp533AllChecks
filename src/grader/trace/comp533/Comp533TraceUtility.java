package grader.trace.comp533;

import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;
import gradingTools.comp533s18.assignment1.testcases.OneClientCorrectConnection;
import gradingTools.comp533s18.assignment1.testcases.StaticArguments;

public class Comp533TraceUtility {
	static boolean turnOn = true;
	public static boolean isTurnOn() {
		return turnOn;
	}
	public static void setTurnOn(boolean turnOn) {
		Comp533TraceUtility.turnOn = turnOn;
	}
	
	public static void setTracing() {
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_PACKAGE_NAME);	
		if (isTurnOn()) {
			Tracer.setKeywordPrintStatus(StaticArguments.class, true);
			Tracer.setKeywordPrintStatus(OneClientCorrectConnection.class, true);
			Tracer.setKeywordPrintStatus(gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase.class, true);
		}

	}

}
