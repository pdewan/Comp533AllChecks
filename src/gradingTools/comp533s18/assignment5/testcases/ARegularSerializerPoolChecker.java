package gradingTools.comp533s18.assignment5.testcases;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;

public class ARegularSerializerPoolChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		"I\\*\\*\\*." + "SerializerTakenFromPool.*" +
		"serialization.simple.ASimpleSerializer.*"		
		
	};
	public ARegularSerializerPoolChecker() {
		super(MY_SUBSTRINGS);
	}
/*
 .*ReceivedMessageQueueCreated.*Server0<-->Client1 (Opened).*ReceivedMessageQueueCreated.*Server0<-->Client2 (Opened).*
 */
}
