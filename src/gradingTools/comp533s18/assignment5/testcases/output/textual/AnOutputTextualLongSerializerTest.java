package gradingTools.comp533s18.assignment5.testcases.output.textual;

import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.output.AnOutputTextualSerializerTest;
import gradingTools.comp533s18.assignment5.testcases.output.checks.ASerializationTraceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(3)
public class AnOutputTextualLongSerializerTest extends AnOutputTextualSerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), "5", "5" );
	}
//	@Override
//	protected boolean doTest() {
//		String anOutput = getOutput();
//		boolean aRetVal = checker.check(anOutput);
//		Assert.assertTrue(checker.getRegex() + " not matched in output of TestSerialization", aRetVal);
//		return true;
//	}

	@Override
	protected String classTag() {
		// TODO Auto-generated method stub
		return Comp533Tags.LONG_SERIALIZER;
	}
	

}