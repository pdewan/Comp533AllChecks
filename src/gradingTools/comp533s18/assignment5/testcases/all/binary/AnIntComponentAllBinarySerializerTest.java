package gradingTools.comp533s18.assignment5.testcases.all.binary;

import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.all.checks.AWholeNumberChecker;

import org.junit.Assert;
import org.junit.Test;


public class AnIntComponentAllBinarySerializerTest extends AnAllBinarySerializerTest{
	SubstringSequenceChecker checker = new AWholeNumberChecker();
	@Test
	public void test() {
		String anOutput = getOutput();
		boolean aRetVal = checker.check(anOutput);
		Assert.assertTrue(checker.getRegex() + " not matched in output of TestSerialization", aRetVal);
		
	}
	

}
