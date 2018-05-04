package gradingTools.comp533s18.assignment5.testcases;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;

public class ASerializerPoolChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		"I\\*\\*\\*.*" + "SerializerTakenFromPool.*"	
		
		
	};
	public ASerializerPoolChecker() {
		super(MY_SUBSTRINGS);
	}
/*
 .*ReceivedMessageQueueCreated.*Server0<-->Client1 (Opened).*ReceivedMessageQueueCreated.*Server0<-->Client2 (Opened).*
 */
}
