package port.old;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.datacomm.group.buffer.syncrcv.ABufferGroupServerInputPortWithSyncReceive;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.syncrcv.GroupSessionPortWithSyncReceive;


public class ABufferGroupSessionPortWithSyncReceive extends ABufferGroupServerInputPortWithSyncReceive implements GroupSessionPortWithSyncReceive<ByteBuffer>{

	public ABufferGroupSessionPortWithSyncReceive(
			DuplexServerInputPortWithSyncReceive<ByteBuffer> duplexServerInputPort) {
		super(duplexServerInputPort);
	}
	String physicalRemoteEndPoint;
	String logicalRemoteEndPoint;
	@Override
	public String getLogicalRemoteEndPoint() {
		return logicalRemoteEndPoint;
	}

	@Override
	public String getPhysicalRemoteEndPoint() {
		return physicalRemoteEndPoint;
	}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		physicalRemoteEndPoint = newVal;
	}

	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		logicalRemoteEndPoint = newVal;
	}

	

	@Override
	public List<SessionParticipantDescription> getServers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionParticipantDescription> getMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionParticipantDescription> getClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionParticipantDescription getServer(String aName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionParticipantDescription getClient(String aName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionParticipantDescription getMember(String aName) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Set<String> getClientConnections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getServerConnections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getMemberConnections() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void sendOtherClients(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOtherServers(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOtherMembers(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAllClients(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAllServers(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAllMembers(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public ParticipantChoice getParticipantChoice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendAllRemoteMembers(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void sendOtherClients(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void sendOtherServers(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void sendOtherMembers(Object message) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void sendAllClients(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void sendAllServers(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void sendAllMembers(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public Set<String> getClientConnections() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set<String> getServerConnections() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set<String> getMemberConnections() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
