package gradingTools.comp533s18.assignment4.testcases.explicit_receive;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class AnExplicitReceiveServerReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
		"I\\*\\*\\*." + "main..ReceivedMessageDequeued.*Client1",
		 "Received message:.*Client1",
		 "I\\*\\*\\*." + "main..ReceivedMessageDequeued.*Client2",
		 "Received message:.*Client2"
	};
	public AnExplicitReceiveServerReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
