package gradingTools.comp533s18.assignment5.testcases.equals;

import util.annotations.MaxValue;
import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
@MaxValue(20)
public class AnEqualsBinaryProgrammerDefinedCollectionSerializerTest extends AnEqualsBinarySerializerTest{

	@Override
	protected Object createSerializableObject() {
		// TODO Auto-generated method stub
		return ProgrammerDefinedCollection.createFilledInstance();
	}


	

}
