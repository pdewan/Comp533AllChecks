package gradingTools.comp533s18.assignment5.testcases.output.binary;

import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.output.AnOutputBinarySerializerTest;
import gradingTools.comp533s18.assignment5.testcases.output.checks.ASerializationTraceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(75)
public class AnOutputBinaryBeanSerializerTest extends AnOutputBinarySerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), 
				"HeapByteBuffer", 
				"examples.serialization.AnotherBMISpreadsheet.*\\(2.0,75.0,18.75,true\\)" 
				);
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
		return Comp533Tags.BEAN_SERIALIZER;
	}
	

}
