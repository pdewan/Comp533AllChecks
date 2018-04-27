package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import util.trace.Tracer;


public class AnIntegerSerializer extends AnAbstractSerializer {

	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects) throws StreamCorruptedException, NotSerializableException {
		try {
			Object retVal = anInputBuffer.getInt();	
			retrievedObjects.add(retVal);
			Tracer.info(this, "Added deserialized object " + retVal + " at index: " + (retrievedObjects.size()-1));
			return retVal;
		} catch (Exception e) {
			StreamCorruptedException streamCorruptedException = new StreamCorruptedException("Leaf Representation from Buffer Falied. Cause: " + e.getMessage());
			throw streamCorruptedException;	
		}
		
	}
	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object value, List visitedObjects) {
		visitedObjects.add(value);
		Tracer.info(this, "Added  serialized object " + value + " at index: " + (visitedObjects.size()-1));
		anOutputBuffer.putInt((Integer) value);
	}

	
	


}
