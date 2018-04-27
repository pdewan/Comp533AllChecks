package sessionport.datacomm.group.syncrcv;
import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;

public abstract class AGroupSessionPortWithSyncReceiveFactory<MessageType> implements GroupSessionPortWithSyncReceiveFactory<MessageType> {

	@Override
	public GroupSessionPortWithSyncReceive<MessageType> createGroupSessionPortWithSyncReceive(
			String sessionsServerHost, String sessionsServerId,
			String sessionsServerName, String sessionName, String anId,
			String name, ParticipantChoice aChoice) {
		GroupSessionPort<MessageType> groupSessionPort = createGroupSessionPort(sessionsServerHost, sessionsServerId, sessionsServerName, sessionName, anId, name, aChoice);
		return new AGroupSessionPortWithSyncReceive<MessageType>(groupSessionPort);
	}	
	abstract protected GroupSessionPort<MessageType> createGroupSessionPort(
			String sessionsServerHost, String sessionsServerId,
			String sessionsServerName, String sessionName, String anId,
			String name, ParticipantChoice aChoice);

}
