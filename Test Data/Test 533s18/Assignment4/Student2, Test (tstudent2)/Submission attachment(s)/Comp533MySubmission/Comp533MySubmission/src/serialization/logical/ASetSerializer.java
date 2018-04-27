package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

public class ASetSerializer extends AnAbstractSerializer implements ValueSerializer {
	
	
	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		try {
			int size = anInputBuffer.getInt();
			Set list = (Set) aClass.newInstance();
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
		Set list = (Set) anObject;
		int size = list.size();
		anOutputBuffer.putInt(size);
		for (Object element:list) {
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, element, visitedObjects);
		}		
	}
	
	

	

	

}
