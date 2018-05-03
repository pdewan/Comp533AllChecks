package gradingTools.comp533s18.assignment5.testcases.output.checks;

import gradingTools.comp533s18.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s18.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class ASerializationTraceChecker extends ASubstringSequenceChecker{
	
	public ASerializationTraceChecker(Class aTaggedClass, String aBinaryOrText, String aStringRepresentation) {
		String aClassName = aTaggedClass.getSimpleName();
		String[] subStrings = {
				
//				"Serializing " + aStringRepresentation ,

//				"ExtensibleValueSerializationInitiated",
				aClassName,
				aBinaryOrText,
//				aStringRepresentation,
//				"ExtensibleValueSerializationFinished",
//				aClassName,
//				"HeapByteBuffer",
//				aStringRepresentation,
//				"ExtensibleBufferDeserializationInitiated",
//				aClassName,
//				"HeapByteBuffer",
//				aStringRepresentation,
//				"ExtensibleBufferDeserializationFinished",
//				aClassName,
//				"HeapByteBuffer",
//				aStringRepresentation,
				"Deserialized",
				aStringRepresentation,
				};
		init (subStrings, 0.0);
	}
//	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
//	public  String[] subStrings = {
//			"Serializing 5",
//			"Deserialized 5",
//			"Serializing 5",
//			"Deserialized 5",
//			"Serializing 5",
//			"Deserialized 5"
//	};
	
	

}
