package serialization.logical;

import serialization.Serializer;
import serialization.SerializerFactory;

public class ALogicalStructureSerializerFactory implements SerializerFactory {
	public Serializer createSerializer() {
		return new ALogicalStructureSerializer();
//		return new ANonCopyingBufferSerializationSupport();
	}
}