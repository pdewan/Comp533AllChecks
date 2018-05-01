package gradingTools.comp533s18.assignment5.testcases.all.binary;

import gradingTools.comp533s18.assignment5.testcases.all.AnAllSerializerTest;
import util.annotations.Comp533Tags;

public class AnAllBinarySerializerTest extends AnAllSerializerTest {
	public static final String[] BINARY_SERIALIZER = {Comp533Tags.LOGICAL_BINARY_SERIALIZER_FACTORY};
	@Override
	protected String[] proxyClassTags() {
		return BINARY_SERIALIZER;
	}
	

}
