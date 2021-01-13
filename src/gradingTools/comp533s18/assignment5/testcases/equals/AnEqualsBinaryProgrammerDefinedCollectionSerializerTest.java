package gradingTools.comp533s18.assignment5.testcases.equals;

import gradingTools.comp533s21.assignment9.testcases.equals.AnEqualsBinarySerializerTest;
import gradingTools.comp533s21.assignment9.testcases.equals.ProgrammerDefinedCollection;
import util.annotations.MaxValue;
@MaxValue(20)
public class AnEqualsBinaryProgrammerDefinedCollectionSerializerTest extends AnEqualsBinarySerializerTest{

	@Override
	protected Object createSerializableObject() {
		// TODO Auto-generated method stub
		return ProgrammerDefinedCollection.createFilledInstance();
	}


	

}
