package sessionport.datacomm.duplex.buffer.syncrcv;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;

import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;
import sessionport.duplex.syncrcv.ADuplexSessionPortWithSyncReceiveFactory;


public class ABufferDuplexSessionPortWithSyncReceiveFactory extends ADuplexSessionPortWithSyncReceiveFactory<ByteBuffer> {

	@Override
	public DuplexSessionPort<ByteBuffer> createDuplexSessionPort(
			String sessionsServerHost, String sessionsServerId,
			String sessionsServerName, String sessionName, String anId,
			String name, ParticipantChoice aJoinChoice) {
		return BufferDuplexSessionPortSelector.createBufferDuplexSessionPort(sessionsServerHost, sessionsServerId, sessionsServerName, sessionName, anId, name, aJoinChoice);
	}
//
//	@Override
//	public DuplexSessionPort<ByteBuffer> createDuplexSessionPort(
//			SessionsServer aSessionsServer, String aSessionName, String anId,
//			String aName) {
//		return BufferDuplexSessionPortSelector.createDuplexSessionPort(aSessionsServer, aSessionName, anId, aName);
//	}

	@Override
	public DuplexSessionPort<ByteBuffer> createDuplexSessionPort(
			DuplexRPCClientInputPort aSessionsServerClienPort,
			String aSessionName, String anId, String aName, ParticipantChoice aChoice) {
		return BufferDuplexSessionPortSelector.createDuplexSessionPort(aSessionsServerClienPort, aSessionName, anId, aName, aChoice);
	}

}
