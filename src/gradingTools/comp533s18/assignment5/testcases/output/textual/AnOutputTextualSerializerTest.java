package gradingTools.comp533s18.assignment5.testcases.output.textual;

import gradingTools.comp533s18.assignment5.testcases.output.AnOutputSerializerTest;
import util.annotations.Comp533Tags;

public abstract class AnOutputTextualSerializerTest extends AnOutputSerializerTest {
	public static final String[] TEXTUAL_SERIALIZER = {Comp533Tags.LOGICAL_TEXTUAL_SERIALIZER_FACTORY};
	@Override
	protected String[] proxyClassTags() {
		return  TEXTUAL_SERIALIZER;
	}
	

}
