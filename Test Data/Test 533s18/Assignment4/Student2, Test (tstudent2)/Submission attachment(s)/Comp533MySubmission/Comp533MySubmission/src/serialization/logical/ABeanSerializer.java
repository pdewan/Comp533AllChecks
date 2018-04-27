package serialization.logical;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import util.misc.RemoteReflectionUtility;
import util.misc.Transient;
import util.trace.Tracer;


public class ABeanSerializer extends AnAbstractSerializer implements ValueSerializer {
	Object[] nullArgs = {};
	protected final String[] ignoreProperties = {"Class", "class"};
	protected final List<String> ignorePropertiesList = Arrays.asList(ignoreProperties);

	@Override
	void representationToBuffer(ByteBuffer anOutputBuffer, Object anObject, List visitedObjects) throws NotSerializableException{
		Class aClass = anObject.getClass();	
		if (!(anObject instanceof Serializable))
			throw new NotSerializableException(anObject + " is not Serializable");

		visitedObjects.add(anObject);
		Tracer.info(this, "Added  serialized object " + anObject + " at index: " + (visitedObjects.size() - 1));

		
		try  {
			BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				PropertyDescriptor property = properties[i];
				if (ignoreProperty(property))
					continue;
				if (property.getReadMethod() == null || RemoteReflectionUtility.isTransient(property.getReadMethod()) ||
						property.getWriteMethod() == null) {
					Tracer.warning("Unserializable property " + property.getName() + " of " + anObject + " as it does not have both getter and setter");
					continue;
				}
				Object component = property.getReadMethod().invoke(anObject, nullArgs);
				ALogicalStructureSerializer.getDispatchingSerializer().objectToBuffer(anOutputBuffer, component, visitedObjects);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotSerializableException(e.getMessage());
		}		

	}
//	@Override
//	void objectToBuffer (ByteBuffer anOutputBuffer, Object anObject, Class aClass, List visitedObjects)  {
//		try {
//			List<Object> visitedObjects = new ArrayList();
//			objectToBuffer(anOutputBuffer, anObject, aClass, visitedObjects);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	}
//	@Override
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
//				Serializer serializer;
//				if (type.isArray()) {
//					
//				}
//				return objectFromBuffer(anInputBuffer, type, retrievedObjects);
//			}
//			
//			
//		} catch (Exception e) {
//			StreamCorruptedException streamCorruptedException = new StreamCorruptedException( e.getMessage());
//			throw streamCorruptedException;	
//		}		
//	}
//	@Override
//	void objectToBuffer (ByteBuffer anOutputBuffer, Object anObject, Class aClass, List aVisitedObjects) throws NotSerializableException {
//		Serializer serializer = ABufferCustomSerializationSupport.getLeafSerializer(aClass);
//		if (serializer != null)
//			serializer.objectToBuffer(anOutputBuffer, anObject, aVisitedObjects);
//		else if (aClass.isArray()) {
//			serializer = ABufferCustomSerializationSupport.getArraySerializer();
//			serializer.objectToBuffer(anOutputBuffer, anObject, aVisitedObjects);
//		} else {
//			beanToBuffer(anOutputBuffer, anObject, aClass, aVisitedObjects);
//		}
//		
//	}
//	void beanToBuffer(ByteBuffer anOutputBuffer, Object anObject, Class aClass, 
//			List visitedObjects) throws NotSerializableException {
//		if (visitedObjects.contains(visitedObjects)) {
//			throw new NotSerializableException(anObject + " has multiple parents");
//		}
//		if (!(Serializable.class.isAssignableFrom(aClass)))
//				throw new NotSerializableException(aClass + " is not Serializable");
//		try  {
//			BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
//			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
//			for (int i = 0; i < properties.length; i++) {
//				PropertyDescriptor property = properties[i];
//				if (property.getReadMethod() == null || 
//						property.getWriteMethod() == null)
//					continue;
//				Object component = property.getReadMethod().invoke(anObject, nullArgs);
////				stringToBuffer(anOutputBuffer, property.getName());
//				ABufferCustomSerializationSupport.getTopSerializer().objectToBuffer(anOutputBuffer, component, visitedObjects);
//			}
//		} catch (Exception e) {
//			throw new NotSerializableException(e.getMessage());
//		}		
//	}
	
//	@Override
//	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass) throws StreamCorruptedException {
//		try {
//			Serializer serializer = ABufferCustomSerializationSupport.getLeafSerializer(aClass);
//			if (serializer != null) {
//				return serializer.objectFromBuffer(anInputBuffer, aClass);
//			} else if (aClass.isArray()) {
//				serializer = ABufferCustomSerializationSupport.getArraySerializer();
//				return serializer.objectFromBuffer(anInputBuffer, aClass);				
//			} else {
//				return beanFromBuffer(anInputBuffer, aClass);
//			}		
//			
//		} catch (Exception e) {
//			StreamCorruptedException streamCorruptedException = new StreamCorruptedException("Leaf Representation from Buffer Falied. Cause: " + e.getMessage());
//			throw streamCorruptedException;	
//		}		
//	}
	
	static PropertyDescriptor getProperty(PropertyDescriptor[] properties, String name) {
		for (int i = 0; i < properties.length; i++) {
			PropertyDescriptor property = properties[i];
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
	
	protected boolean ignoreProperty(PropertyDescriptor aProperty) {
		return 
				ignorePropertiesList.contains(aProperty.getName()) ||
				aProperty.getReadMethod() == null ||				
				aProperty.getReadMethod().getAnnotation(Transient.class) != null;
				
				}
	
	public Object objectFromBuffer(ByteBuffer anInputBuffer, Class aClass, List retrievedObjects) throws StreamCorruptedException {
		Object retVal;
		Class serializedClass = ALogicalStructureSerializer.getDeserializingClass(aClass);

		try {

			Tracer.info(this, "Creating new instanceof class:" + aClass);
			 retVal = serializedClass.newInstance();
			 retrievedObjects.add(retVal);
			 Tracer.info(this, "Added deserialized object " + retVal + " at index: " + (retrievedObjects.size()-1));

		} catch (Exception e) {
//			e.printStackTrace();
			throw new StreamCorruptedException("Cannot deserialize class " + aClass + " as it has  no null constructor or an erroneous one or an invalid toString");
		}
		try  {
			BeanInfo beanInfo = Introspector.getBeanInfo(serializedClass);
			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
//			String propertyName = stringFromBuffer(anInputBuffer);
			for (int i = 0; i < properties.length; i++) {
				PropertyDescriptor property = properties[i];
				if (ignoreProperty(property))
					continue;
				if (property.getReadMethod() == null || 
						property.getWriteMethod() == null) {
					Tracer.warning("Unserializable property " + property.getName() + " of " + retVal + " as it does not have both getter and setter");
					continue;
				}
				Object component = ALogicalStructureSerializer.getDispatchingSerializer().objectFromBuffer(anInputBuffer, retrievedObjects);
				Object[] args = {component};
				property.getWriteMethod().invoke(retVal, args);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new StreamCorruptedException(e.getMessage());
		}
		RemoteReflectionUtility.invokeInitSerializedObject(retVal);
		return retVal;
		
	}


}
