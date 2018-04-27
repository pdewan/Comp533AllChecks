package serialization.logical;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.List;

public interface TopSerializer {

	void objectToBuffer (ByteBuffer anOutputBuffer,  Object anObject, List visitedObjects) throws NotSerializableException;
	Object objectFromBuffer(ByteBuffer anInputBuffer, List retrievedObjects) throws StreamCorruptedException, NotSerializableException;



}
