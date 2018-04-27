package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

import util.annotations.Tags;
@Tags({"Type-Specific Value Serializer"})
public interface ValueSerializer {
	void objectToBuffer (ByteBuffer anOutputBuffer,  Object anObject, List visitedObjects) throws NotSerializableException;
	Object objectFromBuffer(ByteBuffer anInputBuffer, 
			Class aClass, List retrievedObjects) throws StreamCorruptedException, NotSerializableException;
}
