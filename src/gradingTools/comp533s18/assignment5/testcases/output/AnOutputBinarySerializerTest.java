package gradingTools.comp533s18.assignment5.testcases.output;

import gradingTools.comp533s21.assignment9.testcases.ASerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.AnOutputSerializerTest;
import util.annotations.Comp533Tags;

public abstract class AnOutputBinarySerializerTest extends AnOutputSerializerTest {
	public static final String BYTE_BUFFER_PATTERN = "HeapByteBuffer";
	public static final String[] BINARY_SERIALIZER = {Comp533Tags.LOGICAL_BINARY_SERIALIZER_FACTORY};
	protected String[] proxyClassTags() {
		return BINARY_SERIALIZER;
	}
	

}
