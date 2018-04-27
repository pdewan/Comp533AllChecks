package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import util.trace.Tracer;

public abstract class AnAbstractSerializer implements ValueSerializer {

	void objectToBuffer (ByteBuffer anOutputBuffer, Object object, String typeName, List visitedObjects) throws NotSerializableException {		
		stringToBuffer(anOutputBuffer, typeName);

		representationToBuffer(anOutputBuffer, object, visitedObjects);	
		
	}
	
	void objectToBuffer (ByteBuffer anOutputBuffer, Object object, Class aClass, List visitedObjects) throws NotSerializableException {
		objectToBuffer(anOutputBuffer, object, object.getClass().getName(), visitedObjects);
	}
	
//	void objectToBuffer (ByteBuffer anOutputBuffer, String aTypeName, String aRepresentation) {
//		stringToBuffer(anOutputBuffer, aTypeName);
//		stringToBuffer(anOutputBuffer, aRepresentation);		
//	}
//	
	abstract void representationToBuffer(ByteBuffer anOutputBuffer, 
			Object aValue, List visitedObjects) throws NotSerializableException;
	
	public static void stringToBuffer (ByteBuffer anOutputBuffer, String aString) {
		Tracer.info(AnAbstractSerializer.class, "will put string:"  + aString + " in buffer:" + anOutputBuffer);

		byte[] aStringBytes = aString.getBytes();
		anOutputBuffer.putInt(aStringBytes.length);
		anOutputBuffer.put(aStringBytes);
	}
	
	public static String stringFromBuffer(ByteBuffer anInputBuffer) throws StreamCorruptedException, NotSerializableException {
		try {
			int length = anInputBuffer.getInt();
//			Message.info("Creating byte array of length " + length);
			byte[] stringBytes = new byte[length];
			anInputBuffer.get(stringBytes);
			String retVal = new String(stringBytes);
			Tracer.info(AnAbstractSerializer.class, "Deserialized string:" + retVal + " from buffer:" + anInputBuffer);
			return retVal;
//			return new String(stringBytes);
		} catch (Exception e) {
			e.printStackTrace();
			StreamCorruptedException streamCorruptedException = new StreamCorruptedException( e.getMessage());
			throw streamCorruptedException;			
		}		
	}
	
	@Override
	public void objectToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
//		if (anObject == null) {
//			objectToBuffer(anOutputBuffer, NUll_TYPE_NAME, NULL_REPRESENTATION, visitedObjects);
//		} else
			objectToBuffer(anOutputBuffer, anObject, anObject.getClass(), visitedObjects);
	}
	

	
//	String representationFromBuffer(ByteBuffer anInputBuffer) throws StreamCorruptedException {
//		try {
//			String typeString = stringFromBuffer(anInputBuffer);
//			Class typeClass = ReflectionUtility.forName(typeString);
//			String representationString = stringFromBuffer(anInputBuffer);
//			return representationString;
//		} catch (Exception e) {
//			StreamCorruptedException streamCorruptedException = new StreamCorruptedException("Leaf Representation from Buffer Falied. Cause: " + e.getMessage());
//			throw streamCorruptedException;		
//		}		
//			
//	}
//	@Override
//	public Object objectFromBuffer(ByteBuffer anInputBuffer) throws StreamCorruptedException {
//		try {
//			String typeName = stringFromBuffer(anInputBuffer);
//			String objectRepresentation = stringFromBuffer(anInputBuffer);
//			
//			
//			
//		} catch (Exception e) {
//			StreamCorruptedException streamCorruptedException = new StreamCorruptedException("Leaf Representation from Buffer Falied. Cause: " + e.getMessage());
//			throw streamCorruptedException;	
//		}		
//	}
	
//	public Object objectFromBuffer(ByteBuffer anInputBuffer, List retrievedObjects) throws StreamCorruptedException, NotSerializableException {
//		try {
//			String typeName = stringFromBuffer(anInputBuffer);
////			List visitedObjects = new ArrayList();
//			Class type;
//			if (typeName.equals(NUll_TYPE_NAME)) {
//				String nullValue = stringFromBuffer(anInputBuffer);
//				if (nullValue.equals(NULL_REPRESENTATION))
//					return null;
//				else
//					throw new StreamCorruptedException("Illegal null representation:" + nullValue);
//				
//			} else {
//				type = ReflectionUtility.forName(typeName);				
//				return objectFromBuffer(anInputBuffer, type, retrievedObjects);
//			}
//			
//			
//		} catch (Exception e) {
//			StreamCorruptedException streamCorruptedException = new StreamCorruptedException( e.getMessage());
//			throw streamCorruptedException;	
//		}		
//	}
	
//	abstract Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass)  throws StreamCorruptedException;

}
