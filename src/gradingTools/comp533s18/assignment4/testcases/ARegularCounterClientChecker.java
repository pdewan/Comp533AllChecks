package gradingTools.comp533s18.assignment4.testcases;

public class ARegularCounterClientChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		"Sending counter increment message:2",
		"Making RPC call with increment:1",
		"Making RPC call to get counter value",
		"Returned remote value"
		
	};
	public ARegularCounterClientChecker() {
		super(MY_SUBSTRINGS, 0);
	}

}