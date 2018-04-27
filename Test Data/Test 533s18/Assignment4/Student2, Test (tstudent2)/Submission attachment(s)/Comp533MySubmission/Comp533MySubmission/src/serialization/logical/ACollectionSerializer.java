package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

import util.trace.Tracer;


public class ACollectionSerializer extends AnAbstractSerializer implements ValueSerializer {
	
	
	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		try {
			Tracer.info(this, "Deserializing object from:" + anInputBuffer);			
			int size = anInputBuffer.getInt();
//			int size = anInputBuffer.getInt();
			Class deserializedClass = ALogicalStructureSerializer.getDeserializingClass(aClass);
			Collection collection = (Collection) deserializedClass.newInstance();
			retrievedObjects.add(collection);
			for (int i = 0; i < size; i++)  {
				Object element = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				collection.add(element);
			}			
			return collection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StreamCorruptedException(e.getMessage());
		}
	}

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
		Tracer.info(this, "Serializing object:" + anObject);
		visitedObjects.add(anObject);
		Collection collection = (Collection) anObject;
		int size = collection.size();
		anOutputBuffer.putInt(size);
		for (Object element:collection) {
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, element, visitedObjects);
		}		
	}
	
	

	

	

}
