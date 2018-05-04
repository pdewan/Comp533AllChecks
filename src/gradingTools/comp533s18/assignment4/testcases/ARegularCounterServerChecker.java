package gradingTools.comp533s18.assignment4.testcases;

public class ARegularCounterServerChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		"Incrementing counter in response to message.*",
		"Incrementing counter in response to message.*",
		"Incrementing counter in response to message.*",
		"Incrementing counter in response to message.*",
		 "getValue returns:12.*"
		
	};
//	public ARegularCounterServerChecker(double aFraction) {
//		super(MY_SUBSTRINGS);
//	}
	public ARegularCounterServerChecker() {
		super(MY_SUBSTRINGS);
	}

}
