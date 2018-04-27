package sessionport.datacomm.duplex.buffer.syncrcv;

import java.nio.ByteBuffer;

import port.ParticipantChoice;

import sessionport.duplex.syncrcv.DuplexSessionPortWithSyncReceive;
import sessionport.duplex.syncrcv.DuplexSessionPortWithSyncReceiveFactory;



public class BufferDuplexSessionPortWithSyncReceiveSelector  {
	static DuplexSessionPortWithSyncReceiveFactory<ByteBuffer> factory = new ABufferDuplexSessionPortWithSyncReceiveFactory();
	public static void setBufferDuplexSessionPortWithSyncReceiveFactory(DuplexSessionPortWithSyncReceiveFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	
	public static DuplexSessionPortWithSyncReceive<ByteBuffer> createBufferDuplexSessionPortWithSyncReceive (
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aJoinChoice){
		return factory.createDuplexSessionPortWithSyncReceive(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);
	}	
}
