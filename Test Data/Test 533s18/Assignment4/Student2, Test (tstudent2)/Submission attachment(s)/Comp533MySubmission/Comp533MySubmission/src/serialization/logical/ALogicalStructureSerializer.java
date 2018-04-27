package serialization.logical;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import examples.serialization.AStringHistory;
import examples.serialization.AnotherStringHistory;
import serialization.Serializer;
import util.annotations.Tags;
import util.trace.Tracer;

@Tags("Type-Serializer Selector")
public class ALogicalStructureSerializer  implements Serializer {
	public static int OUTPUT_BUFFER_SIZE = 40960;
	static Map<Class, ValueSerializer> classToLeafSerializer = new HashMap();
	static Map<Class, Class> classToDeserializedClass = new HashMap();
	static ValueSerializer enumSerializer = new AnEnumSerializer();
	static DispatchingSerializer topSerializer = new ADispatchingSerializer();
	static ValueSerializer arraySerializer = new AnArraySerializer() ;
	static ValueSerializer beanSerializer = new ABeanSerializer();
	static ValueSerializer listPatternSerializer = new AListPatternSerializer();
//	public static int BYTES_IN_CHAR = Character.SIZE/Byte.SIZE;
	ByteBuffer outputBuffer = ByteBuffer.allocate(OUTPUT_BUFFER_SIZE);
//	ByteBuffer inputBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	
	public static void registerValueSerializer(Class aClass, ValueSerializer anExternalSerializer) {
		classToLeafSerializer.put(aClass, anExternalSerializer);
	}
	public static void registerDeserializingClass(Class aClass, Class aDeserializedClass) {
		classToDeserializedClass.put(aClass, aDeserializedClass);
	}
	public static Class getDeserializingClass(Class aClass) {
		Class alternateClass = classToDeserializedClass.get(aClass);
		if (alternateClass == null) return aClass;
		return alternateClass;
	}
	public static ValueSerializer getValueSerializer(Class aClass) {
		return classToLeafSerializer.get(aClass);
	}
//	
//	public static boolean isLeaforPrimitive(Class aClass) {
//		return classToLeafSerializer.values().contains(aClass);
//	}
	
	public static DispatchingSerializer getDispatchingSerializer() {
		return topSerializer;
	}
	
	public static void  registerDispatchingSerializer(DispatchingSerializer newVal) {
		 topSerializer = newVal;
	}
	public static ValueSerializer getEnumSerializer() {
		return enumSerializer;
	}
	
	public static void  registerEnumSerializer(ValueSerializer newVal) {
		 enumSerializer = newVal;
	}
	
	public static ValueSerializer getArraySerializer() {
		return arraySerializer;
	}
	
	public static void  registerArraySerializer(ValueSerializer newVal) {
		 arraySerializer = newVal;
	}
	
	public static ValueSerializer getBeanSerializer() {
		return beanSerializer;
	}
	
	public static void  registerBeanSerializer(ValueSerializer newVal) {
		 beanSerializer = newVal;
	}
	
	public static ValueSerializer getListPatternSerializer() {
		return listPatternSerializer;
	}
	
	public static void  registerListPatternSerializer(ValueSerializer newVal) {
		listPatternSerializer = newVal;
	}

	@Override
	public Object objectFromInputBuffer(ByteBuffer anInputBuffer) {
		try {
			Tracer.info(this, "De serializing inputBuffer " + anInputBuffer);
			List retrievedObjects = new ArrayList();
			DispatchingSerializer objectSerializer = getDispatchingSerializer();
			return objectSerializer.objectFromBuffer(anInputBuffer, retrievedObjects);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ByteBuffer outputBufferFromObject(Object object) {
		try {
		Tracer.info(this, "Serializing object " + object);
		DispatchingSerializer objectSerializer = getDispatchingSerializer();
		outputBuffer.position(0);
		outputBuffer.limit(outputBuffer.capacity());
		List visitedObjects = new ArrayList();
		objectSerializer.objectToBuffer(outputBuffer, object, visitedObjects);
		outputBuffer.flip();
		Tracer.info(this, "Finished Serializing object " + object + " outputBuffer:" + outputBuffer);

		return outputBuffer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	

	static {
		ALogicalStructureSerializer.registerValueSerializer(Integer.class, new AnIntegerSerializer());
		ALogicalStructureSerializer.registerValueSerializer(Boolean.class, new ABooleanSerializer());
		ALogicalStructureSerializer.registerValueSerializer(Double.class, new ADoubleSerializer());
		ALogicalStructureSerializer.registerValueSerializer(String.class, new AStringSerializer());
		ALogicalStructureSerializer.registerValueSerializer(HashSet.class, new ACollectionSerializer());
		ALogicalStructureSerializer.registerValueSerializer(ArrayList.class, new ACollectionSerializer());
		ALogicalStructureSerializer.registerValueSerializer(Vector.class, new ACollectionSerializer());
		ALogicalStructureSerializer.registerValueSerializer(HashMap.class, new AMapSerializer());
		ALogicalStructureSerializer.registerValueSerializer(Hashtable.class, new AMapSerializer());
		ALogicalStructureSerializer.registerArraySerializer(new AnArraySerializer());
		ALogicalStructureSerializer.registerBeanSerializer(new ABeanSerializer());
		ALogicalStructureSerializer.registerListPatternSerializer(new AListPatternSerializer());
		ALogicalStructureSerializer.registerDeserializingClass(ArrayList.class, Vector.class);
//		ACustomSerializer.registerDeserializingClass(ABMISpreadsheet.class, AnotherBMISpreadsheet.class);
//		ACustomSerializer.registerDeserializingClass(AnotherBMISpreadsheet.class, ABMISpreadsheet.class);
		ALogicalStructureSerializer.registerDeserializingClass(AStringHistory.class, AnotherStringHistory.class);
	}
	
}
