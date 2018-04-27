package sessionport.datacomm.group.syncrcv;

import port.ParticipantChoice;

public interface GroupSessionPortWithSyncReceiveFactory<MessageType> {
	GroupSessionPortWithSyncReceive<MessageType> createGroupSessionPortWithSyncReceive(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice);

}
