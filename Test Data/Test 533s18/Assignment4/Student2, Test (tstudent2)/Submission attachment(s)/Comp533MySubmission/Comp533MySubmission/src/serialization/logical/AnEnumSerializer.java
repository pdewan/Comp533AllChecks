package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import util.trace.Tracer;

public class AnEnumSerializer extends AnAbstractSerializer{

	@Override
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects) throws StreamCorruptedException {
		try {
			Object[] anEnumConstants = aClass.getEnumConstants();			
//			String representationString = representationFromBuffer(anInputBuffer);
			Object enumString = stringFromBuffer(anInputBuffer);
			Object retVal = null;
			for (Object anEnumConstant:anEnumConstants) {
				if (anEnumConstant.toString().equals(enumString)) {
					retVal = anEnumConstant;
					break;
				}
			}
			if (retVal == null) {
				Tracer.error("Enum deserialized to null:" + enumString);
			}
			retrievedObjects.add(retVal);
			Tracer.info(this, "Added deserialized object " + retVal + " at index: " + (retrievedObjects.size()-1));
			return retVal;
		} catch (Exception e) {
			StreamCorruptedException streamCorruptedException = new StreamCorruptedException("Enum Representation from Buffer Falied. Cause: " + e.getMessage());
			throw streamCorruptedException;	
		}
		
	}

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object value, List visitedObjects) {
		visitedObjects.add(value);
		Tracer.info(this, "Added  serialized enum " + value + " at index: " + (visitedObjects.size()-1));
		Class enumClass = value.getClass();
		String string = value.toString();
		stringToBuffer(anOutputBuffer, string);		
	}

}
