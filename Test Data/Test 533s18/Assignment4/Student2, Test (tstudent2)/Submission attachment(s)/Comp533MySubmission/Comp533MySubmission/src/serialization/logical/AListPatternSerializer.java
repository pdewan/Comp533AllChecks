package serialization.logical;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import util.misc.RemoteReflectionUtility;


public class AListPatternSerializer extends AnAbstractSerializer implements ValueSerializer {
	
	
	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		try {
			if (!(Serializable.class.isAssignableFrom(aClass)))
				throw new NotSerializableException(aClass + " is not Serializable");
			int size = anInputBuffer.getInt();
			Class deserializedClass = ALogicalStructureSerializer.getDeserializingClass(aClass);
			Object list = deserializedClass.newInstance();
			retrievedObjects.add(list);
			for (int i = 0; i < size; i++)  {
				Object element = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				RemoteReflectionUtility.listAdd(list, element);
//				list.add(element);
			}			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StreamCorruptedException(e.getMessage());
		}
	}

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
		if (!(anObject instanceof Serializable))
			throw new NotSerializableException(anObject + " is not Serializable");
		visitedObjects.add(anObject);
//		List list = (List) anObject;
//		int size = list.size();
		int size = RemoteReflectionUtility.listSize(anObject);
		anOutputBuffer.putInt(size);
		for (int i = 0; i <  size; i++) {
//			ABufferCustomSerializationSupport.getTopSerializer().objectToBuffer(anOutputBuffer, list.get(i), visitedObjects);
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, RemoteReflectionUtility.listGet(anObject, i), visitedObjects);
		}		
	}
	
	

	

	

}
