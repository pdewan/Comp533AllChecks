package sessionport.datacomm.duplex.object.syncrcv;

import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.duplex.syncrcv.ADuplexSessionPortWithSyncReceiveFactory;


public class AnObjectDuplexSessionPortWithSyncReceiveFactory extends ADuplexSessionPortWithSyncReceiveFactory<Object> {

	@Override
	public DuplexSessionPort<Object> createDuplexSessionPort(
			String sessionsServerHost, String sessionsServerId,
			String sessionsServerName, String sessionName, String anId,
			String name, ParticipantChoice aJoinChoice) {
		return ObjectDuplexSessionPortSelector.createObjectDuplexSessionPort(sessionsServerHost, sessionsServerId, sessionsServerName, sessionName, anId, name, aJoinChoice);
	}

	@Override
	public DuplexSessionPort<Object> createDuplexSessionPort(
			DuplexRPCClientInputPort aSessionsManagerClienPort,
			String aSessionName, String anId, String aName, ParticipantChoice aJoinChoice) {
		return ObjectDuplexSessionPortSelector.createDuplexSessionPort(aSessionsManagerClienPort, aSessionName, anId, aName, aJoinChoice);
	}

}
