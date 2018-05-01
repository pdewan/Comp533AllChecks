package gradingTools.comp533s18.assignment4.testcases;

import java.util.regex.Pattern;

public class ASubstringSequenceChecker implements SubstringSequenceChecker {
  protected String[] substrings;
  protected double myWeight;
  protected String regex;
  protected Pattern pattern;
  protected boolean success;
  

public ASubstringSequenceChecker(String[] aSubstrings, double aMyWeight ) {
	 init(aSubstrings, aMyWeight);
	  
  }
	public ASubstringSequenceChecker() {
	  
 }
   protected void init(String[] aSubstrings, double aMyWeight) {
		  substrings = aSubstrings;
		  myWeight = aMyWeight;
		  regex = toRegex(substrings);
		  pattern = toPattern(regex);
   }
  public static Pattern toPattern(String aRegex) {
	  return Pattern.compile(aRegex, Pattern.DOTALL);
  }
  public static  String toRegex(String[] aSubstrings) {
	  StringBuffer aRetVal = new StringBuffer(aSubstrings.length * 20);
	  aRetVal.append(".*");
	  for (String aSubstring:aSubstrings) {
//		  aRetVal.append("." + aSubstring + ".*\n");
		  aRetVal.append(aSubstring + ".*");

		  
	  }
	  return aRetVal.toString();
  }
 
  /* (non-Javadoc)
 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#check(java.lang.StringBuffer)
 */
@Override
public boolean check(String aString) {
		
	  success = aString != null && pattern.matcher(aString).matches();
	  return success;	  
	  
  }
@Override
public boolean check(StringBuffer aStringBuffer) {
	 success = aStringBuffer != null && pattern.matcher(aStringBuffer).matches();
	  return success;
}
  /* (non-Javadoc)
 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#getSubstrings()
 */
@Override
public String[] getSubstrings() {
		return substrings;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#setSubstrings(java.lang.String[])
	 */
	@Override
	public void setSubstrings(String[] substrings) {
		this.substrings = substrings;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#getMyWeight()
	 */
	@Override
	public double getMyWeight() {
		return myWeight;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#setMyWeight(double)
	 */
	@Override
	public void setMyWeight(double myWeight) {
		this.myWeight = myWeight;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#getRegex()
	 */
	@Override
	public String getRegex() {
		return regex;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#setRegex(java.lang.String)
	 */
	@Override
	public void setRegex(String regex) {
		this.regex = regex;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#getPattern()
	 */
	@Override
	public Pattern getPattern() {
		return pattern;
	}
	/* (non-Javadoc)
	 * @see gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker#setPattern(java.util.regex.Pattern)
	 */
	@Override
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	@Override
	public boolean isSuccess() {
		return success;
	}
	
}
