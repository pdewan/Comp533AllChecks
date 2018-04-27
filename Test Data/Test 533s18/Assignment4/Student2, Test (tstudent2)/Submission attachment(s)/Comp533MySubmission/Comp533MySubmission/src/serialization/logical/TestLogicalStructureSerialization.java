package serialization.logical;


import examples.serialization.SerializationTester;
import serialization.SerializerSelector;
import util.trace.Tracer;

public class TestLogicalStructureSerialization {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus( Tracer.ALL_KEYWORDS, false);

		Tracer.setKeywordPrintStatus( SerializerSelector.class, true);
		SerializerSelector.setSerializerFactory(new ALogicalStructureSerializerFactory());
		SerializationTester.testSerialization();
		
	}

}
