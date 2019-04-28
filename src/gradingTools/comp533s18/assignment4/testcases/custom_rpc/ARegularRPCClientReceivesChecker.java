package gradingTools.comp533s18.assignment4.testcases.custom_rpc;

import util.trace.port.rpc.RemoteCallWaitingForReturnValue;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;

public class ARegularRPCClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public ARegularRPCClientReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
