package sessionport.datacomm.group.buffer.syncrcv;



import java.nio.ByteBuffer;

import port.ParticipantChoice;

import sessionport.datacomm.group.syncrcv.GroupSessionPortWithSyncReceive;
import sessionport.datacomm.group.syncrcv.GroupSessionPortWithSyncReceiveFactory;


public class BufferGroupSessionPortWithSyncReceiveSelector  {
	static GroupSessionPortWithSyncReceiveFactory<ByteBuffer> factory = new ABufferGroupSessionPortWithSyncReceiveFactory();
	public static GroupSessionPortWithSyncReceiveFactory<ByteBuffer> 
		sessionPortFactory = new ABufferGroupSessionPortWithSyncReceiveFactory();
	public static void setBufferGroupSessionPortWithSyncReceiveFactory(GroupSessionPortWithSyncReceiveFactory<ByteBuffer> theSessionPortFactory) {
		sessionPortFactory = theSessionPortFactory;
	}
	public static GroupSessionPortWithSyncReceive<ByteBuffer> createBufferGroupSessionPortWithSyncReceive(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName) {
		return factory.createGroupSessionPortWithSyncReceive(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.MEMBER);
	}
}
