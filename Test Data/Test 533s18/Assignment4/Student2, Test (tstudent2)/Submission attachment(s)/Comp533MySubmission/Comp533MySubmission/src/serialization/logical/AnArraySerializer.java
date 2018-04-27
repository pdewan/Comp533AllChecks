package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.List;

import util.trace.Tracer;


public class AnArraySerializer extends AnAbstractSerializer implements ValueSerializer {

	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		try {
			String componentClassName = stringFromBuffer(anInputBuffer);
			Class componentClass = Class.forName(componentClassName);
			int size = anInputBuffer.getInt();
			Object array = Array.newInstance(componentClass, size);
			retrievedObjects.add(array);
			Tracer.info(this, "Added deserialized object " + array + " at index: " + (retrievedObjects.size()-1));
			for (int i = 0; i < size; i++)  {
				Object element = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				Array.set(array, i, element);
			}			
			return array;
		} catch (Exception e) {
			throw new StreamCorruptedException(e.getMessage());
		}
	}

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object array, List visitedObjects) throws NotSerializableException{
		visitedObjects.add(array);
		Tracer.info(this, "Added  serialized object " + array + " at index: " + (visitedObjects.size()-1));
		Class componentClass = array.getClass().getComponentType();
		stringToBuffer(anOutputBuffer,  componentClass.getName());
		int size = Array.getLength(array);
		anOutputBuffer.putInt(size);
		for (int i = 0; i <  size; i++) {
			ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, Array.get(array, i), visitedObjects);
		}		
	}
	
	

	

	

}
