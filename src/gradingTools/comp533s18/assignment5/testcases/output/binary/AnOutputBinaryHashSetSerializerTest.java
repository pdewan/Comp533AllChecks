package gradingTools.comp533s18.assignment5.testcases.output.binary;

import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.comp533s21.assignment9.testcases.output.AnOutputBinarySerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.checks.ASerializationTraceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(5)
public class AnOutputBinaryHashSetSerializerTest extends AnOutputBinarySerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), BYTE_BUFFER_PATTERN, ".*? world, .*? world" );
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
		return Comp533Tags.COLLECTION_SERIALIZER;
	}
	

}
