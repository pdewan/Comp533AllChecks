package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

public class AListSerializer extends AnAbstractSerializer implements ValueSerializer {
	
	
	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		try {
			int size = anInputBuffer.getInt();
			Class deserializedClass = ALogicalStructureSerializer.getDeserializingClass(aClass);
			List list = (List) deserializedClass.newInstance();
			retrievedObjects.add(list);
			for (int i = 0; i < size; i++)  {
				Object element = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				list.add(element);
			}			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StreamCorruptedException(e.getMessage());
		}
	}

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
		visitedObjects.add(anObject);
		List list = (List) anObject;
		int size = list.size();
		anOutputBuffer.putInt(size);
		for (int i = 0; i <  size; i++) {
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, list.get(i), visitedObjects);
		}		
	}
	
	

	

	

}
