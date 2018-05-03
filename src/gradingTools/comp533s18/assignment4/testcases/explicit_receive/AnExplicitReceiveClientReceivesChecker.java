package gradingTools.comp533s18.assignment4.testcases.explicit_receive;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class AnExplicitReceiveClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
		"main..ReceivedMessageDequeued.*AnRPCReturnValue",
		"Received message:.*AnRPCReturnValue"
	};
	public AnExplicitReceiveClientReceivesChecker() {
		init( MY_SUBSTRINGS, 0.0);
	}
	

}
