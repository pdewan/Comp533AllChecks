package gradingTools.comp533s18.assignment5.testcases.output.binary;

import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.output.checks.ASerializationTraceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(20)
public class AnOutputBinaryArraySerializerTest extends AnOutputBinarySerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), "HeapByteBuffer", "Hello World, Goodbye World, GREEN" );
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
		return Comp533Tags.ARRAY_SERIALIZER;
	}
	

}