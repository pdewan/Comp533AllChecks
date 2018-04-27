package sessionport.datacomm.group.buffer.syncrcv;


import java.nio.ByteBuffer;

import port.ParticipantChoice;

import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.buffer.BufferGroupSessionPortSelector;
import sessionport.datacomm.group.syncrcv.AGroupSessionPortWithSyncReceiveFactory;

public class ABufferGroupSessionPortWithSyncReceiveFactory extends AGroupSessionPortWithSyncReceiveFactory<ByteBuffer>  {

	@Override
	protected GroupSessionPort<ByteBuffer> createGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aChoice) {
		return BufferGroupSessionPortSelector.createBufferGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);
	}

	

}
