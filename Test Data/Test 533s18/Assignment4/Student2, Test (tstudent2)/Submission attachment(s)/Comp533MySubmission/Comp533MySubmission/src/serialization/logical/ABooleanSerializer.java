package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import util.trace.Tracer;


public class ABooleanSerializer extends AnAbstractSerializer {

	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects) throws StreamCorruptedException, NotSerializableException {
		try {
			Integer retVal = anInputBuffer.getInt();	
			boolean boolVal = retVal == 0? false:true;
			retrievedObjects.add(boolVal);
			Tracer.info(this, "Added deserialized boolean " + retVal + " at index: " + (retrievedObjects.size()-1));
			return boolVal;
		} catch (Exception e) {
			StreamCorruptedException streamCorruptedException = new StreamCorruptedException("Leaf Representation from Buffer Falied. Cause: " + e.getMessage());
			throw streamCorruptedException;	
		}
		
	}
	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object value, List visitedObjects) {
		Boolean boolVal = (Boolean) value;	
		int intVal = boolVal?1:0;
		visitedObjects.add(intVal);
		Tracer.info(this, "Added  serialized boolean " + value + " at index: " + (visitedObjects.size()-1));
		anOutputBuffer.putInt(intVal);
	}

	
	


}
