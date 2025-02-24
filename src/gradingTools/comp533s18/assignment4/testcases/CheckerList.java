package gradingTools.comp533s18.assignment4.testcases;

import java.util.List;

import gradingTools.shared.testcases.SubstringSequenceChecker;

public interface CheckerList extends SubstringChecker {

	public abstract void add(SubstringSequenceChecker aChecker);

	public abstract List<String[]> getUnmatchedStrings();

}