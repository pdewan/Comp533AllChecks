package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import port.common.DistMisc;
import util.misc.RemoteReflectionUtility;
import util.trace.Tracer;


public class ADispatchingSerializer implements DispatchingSerializer {
	Object[] nullArgs = {};
	static String NUll_TYPE_NAME = "Null Class";
	static String NULL_REPRESENTATION = "null";
	static String REFERENCE_TYPE = "R T";
	@Override
	public void objectToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
		if (anObject == null) {
			AnAbstractSerializer.stringToBuffer(anOutputBuffer, NUll_TYPE_NAME);
			AnAbstractSerializer.stringToBuffer(anOutputBuffer, NULL_REPRESENTATION);
		}   else {
			int index = DistMisc.indexOfReference(visitedObjects, anObject);
			if (index != -1) {
				Tracer.info(this, "Serializing: Object" + anObject + " duplicate of object: " + visitedObjects.get(index) + " at index: " + index);
				AnAbstractSerializer.stringToBuffer(anOutputBuffer, REFERENCE_TYPE);
				Tracer.info(this, "Putting int:" + index + " in buffer:" + anOutputBuffer);
				anOutputBuffer.putInt(index);
			} else {			
				Class aClass = anObject.getClass();
				ValueSerializer serializer = getSerializer(aClass);
				serializer.objectToBuffer(anOutputBuffer, anObject,  visitedObjects);
			}
		}
	}
	ValueSerializer getSerializer(Class type) {
		ValueSerializer serializer;
		if (type.isArray()) {
			return ALogicalStructureSerializer.getArraySerializer();
		} else  {
			serializer = ALogicalStructureSerializer.getValueSerializer(type);
			if (serializer != null) return serializer;
			if (type.isEnum()) 
				return ALogicalStructureSerializer.getEnumSerializer();
			if (RemoteReflectionUtility.isList(type))
					return ALogicalStructureSerializer.getListPatternSerializer();			
			return ALogicalStructureSerializer.getBeanSerializer();			
		}		
	}
	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, List retrievedObjects) throws StreamCorruptedException, NotSerializableException {
		try {
			String typeName = AnAbstractSerializer.stringFromBuffer(anInputBuffer);
//			List visitedObjects = new ArrayList();
			Class type;
			if (typeName.equals(NUll_TYPE_NAME)) {
				String nullValue = AnAbstractSerializer.stringFromBuffer(anInputBuffer);
				if (nullValue.equals(NULL_REPRESENTATION))
					return null;
				else
					throw new StreamCorruptedException("Illegal null representation:" + nullValue);
				
			} else if (typeName.equals(REFERENCE_TYPE)) {
				int index = anInputBuffer.getInt();
				Tracer.info(this, "Deserializing: found reference at index: " + index + " of object "  + retrievedObjects.get(index));
				return retrievedObjects.get(index);
			} else 	{
				type = RemoteReflectionUtility.forName(typeName);
				ValueSerializer serializer = getSerializer(type);
				Object newObject = serializer.objectFromBuffer(anInputBuffer, type, retrievedObjects);
				return newObject;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			StreamCorruptedException streamCorruptedException = new StreamCorruptedException( e.getMessage());
			throw streamCorruptedException;	
		}		
	}

	
	


}
