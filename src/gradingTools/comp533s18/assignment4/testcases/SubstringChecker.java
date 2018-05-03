package gradingTools.comp533s18.assignment4.testcases;

import gradingTools.comp533s18.assignment5.LinesMatchKind;
import gradingTools.comp533s18.assignment5.LinesMatcher;

public interface SubstringChecker {
	public abstract boolean check(StringBuffer aStringBuffer);
	public abstract boolean check(String aStringBuffer);
	boolean check(LinesMatcher aLinesMatcher, LinesMatchKind aMatchKind,
			int aFlags);
}
