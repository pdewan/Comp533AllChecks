package gradingTools.comp533s18.assignment4.testcases;

public interface SubstringChecker {
	public abstract boolean check(StringBuffer aStringBuffer);
	public abstract boolean check(String aStringBuffer);

	public abstract double getMyWeight();
}
