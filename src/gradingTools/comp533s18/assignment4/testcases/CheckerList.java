package gradingTools.comp533s18.assignment4.testcases;

import java.util.List;

public interface CheckerList extends SubstringChecker {

	public abstract void add(SubstringSequenceChecker aChecker);

	public abstract List<String[]> getUnmatchedStrings();

}