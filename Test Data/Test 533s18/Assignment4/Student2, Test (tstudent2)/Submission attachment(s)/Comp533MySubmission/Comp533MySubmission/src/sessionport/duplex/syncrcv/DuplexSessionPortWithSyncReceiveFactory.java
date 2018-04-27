package sessionport.duplex.syncrcv;

import port.ParticipantChoice;

public interface DuplexSessionPortWithSyncReceiveFactory<MessageType> {
	DuplexSessionPortWithSyncReceive<MessageType> createDuplexSessionPortWithSyncReceive(
										String aSessionsServerHost, 
										String aSessionsServerId, 
										String aSessionsServerName, 
										String aSessionName, 
										String anId,
										String aName, ParticipantChoice aJoinChoice);	
}
