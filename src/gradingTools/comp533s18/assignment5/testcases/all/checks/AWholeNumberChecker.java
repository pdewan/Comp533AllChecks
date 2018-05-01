package gradingTools.comp533s18.assignment5.testcases.all.checks;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class AWholeNumberChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
			"Serializing 5",
			"Deserialized 5",
			"Serializing 5",
			"Deserialized 5",
			"Serializing 5",
			"Deserialized 5"
	};
	public AWholeNumberChecker() {
		init( MY_SUBSTRINGS, 0.0);
	}
	

}
