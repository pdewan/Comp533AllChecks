package gradingTools.comp533s18.assignment4.testcases.explicit_receive;

import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class AMultipleReceiveQueueChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		toPrefixedRegex("I\\*\\*\\*" , "ReceivedMessageQueueCreated"),
		toPrefixedRegex("I\\*\\*\\*" , "ReceivedMessageQueueCreated")	

//		toPrefixedRegex("I\\*\\*\\*.*" + "ReceivedMessageQueueCreated.*",
		
		
	};
	public AMultipleReceiveQueueChecker() {
		super(MY_SUBSTRINGS);
	}
/*
 .*ReceivedMessageQueueCreated.*Server0<-->Client1 (Opened).*ReceivedMessageQueueCreated.*Server0<-->Client2 (Opened).*
 */
}
