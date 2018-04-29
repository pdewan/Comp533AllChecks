package gradingTools.comp533s18.assignment4.testcases.blocking_rpc;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class ABlockingRPCClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
		"main..ProxyMethodCalled",
		"main..ReceivedMessageDequeued",
		"AnRPCReturnValue",
		"main..ProxyMethodCalled",
		"main..ReceivedMessageDequeued",
		"AnRPCReturnValue"
		
	};
	public ABlockingRPCClientReceivesChecker() {
		init( MY_SUBSTRINGS, 0.0);
	}
	

}
