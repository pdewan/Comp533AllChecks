package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AMapSerializer extends AnAbstractSerializer implements ValueSerializer {
	
	
	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		try {
			int size = anInputBuffer.getInt();
			Map map = (Map) aClass.newInstance();
			retrievedObjects.add(map);
			for (int i = 0; i < size; i++)  {
				Object key = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				Object value = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				map.put(key, value);
			}			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StreamCorruptedException(e.getMessage());
		}
	}

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
		visitedObjects.add(anObject);
		Map map = (Map) anObject;
		int size = map.size();
		anOutputBuffer.putInt(size);
		Set keySet = map.keySet();
		for (Object key:keySet) {
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, key, visitedObjects);
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, map.get(key), visitedObjects);			
		}		
	}
	
	

	

	

}
