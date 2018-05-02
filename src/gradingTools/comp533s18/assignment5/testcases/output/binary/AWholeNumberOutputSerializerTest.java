package gradingTools.comp533s18.assignment5.testcases.output.binary;

import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.all.checks.AWholeNumberChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.MaxValue;

@MaxValue(10)
public class AWholeNumberOutputSerializerTest extends AnOutputBinarySerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		return new AWholeNumberChecker();
	}
//	@Override
//	protected boolean doTest() {
//		String anOutput = getOutput();
//		boolean aRetVal = checker.check(anOutput);
//		Assert.assertTrue(checker.getRegex() + " not matched in output of TestSerialization", aRetVal);
//		return true;
//	}
	

}
