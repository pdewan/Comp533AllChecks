package sessionport.duplex.syncrcv;

import port.ParticipantChoice;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.DuplexSessionPortFactory;

public abstract class ADuplexSessionPortWithSyncReceiveFactory<MessageType> implements 
	DuplexSessionPortWithSyncReceiveFactory<MessageType>, DuplexSessionPortFactory<MessageType>{

	@Override
	public DuplexSessionPortWithSyncReceive<MessageType> createDuplexSessionPortWithSyncReceive(
			String sessionsServerHost, String sessionsServerId,
			String sessionsServerName, String sessionName, String anId,
			String name, ParticipantChoice aJoinChoice) {
		DuplexSessionPort<MessageType>  duplexSessionPort = createDuplexSessionPort(sessionsServerHost, sessionsServerId, sessionsServerName, sessionName, anId, name, aJoinChoice);
		return new ADuplexSessionPortWithSyncReceive<MessageType>(duplexSessionPort);		
	}

}
