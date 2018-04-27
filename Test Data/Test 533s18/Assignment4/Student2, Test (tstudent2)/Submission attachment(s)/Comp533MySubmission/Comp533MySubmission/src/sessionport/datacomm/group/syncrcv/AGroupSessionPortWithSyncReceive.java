package sessionport.datacomm.group.syncrcv;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ASyncReceiveListener;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceiveListener;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;


public class AGroupSessionPortWithSyncReceive<MessageType>  implements GroupSessionPortWithSyncReceive<MessageType>{
	GroupSessionPort<MessageType> groupSessionPort;
	Map<String, ExplicitReceiveListener<MessageType>> nameToSyncReceiveListener = new HashMap();

	public AGroupSessionPortWithSyncReceive (GroupSessionPort<MessageType> aGroupSessionPort) {
		groupSessionPort = aGroupSessionPort;
	}
	protected ExplicitReceiveListener<MessageType>  createSyncReceiveListener(String aClientName) {
		return new ASyncReceiveListener(aClientName);
	}
	protected ExplicitReceiveListener<MessageType> getAndMaybeCreateSyncReceiveListener(String clientName) {
		ExplicitReceiveListener<MessageType> syncReceiveListener = nameToSyncReceiveListener.get(clientName);
		if (syncReceiveListener == null) {
			syncReceiveListener = createSyncReceiveListener(clientName);
			nameToSyncReceiveListener.put(clientName, syncReceiveListener);
			groupSessionPort.addReceiveListener(syncReceiveListener);	
		}
		return syncReceiveListener;
	}

	@Override
	public ReceiveReturnMessage<MessageType> receive() {
		return receive(getSender());		
	}	
	@Override
	public ReceiveReturnMessage<MessageType> receive(String aSource) {
		return getAndMaybeCreateSyncReceiveListener(aSource).getReceivedMessage();		
	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		groupSessionPort.addConnectionListener(portConnectListener);
	}
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		groupSessionPort.addReceiveListener(portReceiveListener);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		groupSessionPort.addSendListener(portSendListener);
	}
	public void connect() {
		groupSessionPort.connect();
	}
	public void disconnect() {
		groupSessionPort.disconnect();
	}
	public String getSender() {
		return groupSessionPort.getSender();
	}
	public String getLocalName() {
		return groupSessionPort.getLocalName();
	}
	public Set<String> getConnections() {
		return groupSessionPort.getConnections();
	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		groupSessionPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		groupSessionPort.notifyConnectFailure(remoteEnd, message, aConnectionType);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		groupSessionPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		groupSessionPort.notifyPortReceive(remoteEnd, message);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		groupSessionPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		groupSessionPort.removeConnectionListener(portConnectListener);
	}
	public void removeReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		groupSessionPort.removeReceiveListener(portReceiveListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		groupSessionPort.removeSendListener(portSendListener);
	}
	public void reply(MessageType message) {
		groupSessionPort.reply(message);
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
	}
	public void send(MessageType message) {
		groupSessionPort.send(message);
	}
	public void send(Collection<String> clientNames, MessageType message) {
		groupSessionPort.send(clientNames, message);
	}
	public void send(String remoteName, MessageType message) {
		groupSessionPort.send(remoteName, message);
	}
	public void sendAll(MessageType message) {
		groupSessionPort.sendAll(message);
	}
	public void sendOthers(MessageType message) {
		groupSessionPort.sendOthers(message);
	}
	public void setSender(String newVal) {
		groupSessionPort.setSender(newVal);
	}
	@Override
	public String getServerId() {
		return groupSessionPort.getServerId();
	}
	@Override
	public GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper() {
		return groupSessionPort.getGroupSendTrapper();
	}
	@Override
	public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() {
		return groupSessionPort.getGroupToUniSendTrapper();
	}
	@Override
	public void setGroupSendTrapper(
			GroupSendTrapper<MessageType, MessageType> groupSendTrapper) {
		groupSessionPort.setGroupSendTrapper(groupSendTrapper);
	}
	@Override
	public void setGroupToUniSendTrapper(
			GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) {
		groupSessionPort.setGroupToUniSendTrapper(groupToUniSendTrapper);		
	}
	@Override
	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
		return groupSessionPort.getReceiveTrapper();
	}
	@Override
	public void setReceiveTrapper(
			ReceiveTrapper<MessageType, MessageType> newVal) {
		groupSessionPort.setReceiveTrapper(newVal);
		
	}
	@Override
	public SendTrapper<MessageType, MessageType> getSendTrapper() {
		return groupSessionPort.getSendTrapper();
	}
	@Override
	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
		groupSessionPort.setSendTrapper(newVal);
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return groupSessionPort.isConnected(remoteName);
	}
	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		// TODO Auto-generated method stub
		return groupSessionPort.getReceiveListeners();
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
	public Set<String> getClientConnections() {
		return groupSessionPort.getClientConnections();
	}
	@Override
	public Set<String> getServerConnections() {
		// TODO Auto-generated method stub
		return  groupSessionPort.getServerConnections();
	}
	@Override
	public Set<String> getMemberConnections() {
		return groupSessionPort.getMemberConnections();
	}
	@Override
	public List<SessionParticipantDescription> getServers() {
		return groupSessionPort.getServers();
	}
	@Override
	public List<SessionParticipantDescription> getMembers() {
		return groupSessionPort.getServers();
	}
	@Override
	public List<SessionParticipantDescription> getClients() {
		return groupSessionPort.getClients();
	}
	@Override
	public SessionParticipantDescription getServer(String aName) {
		return groupSessionPort.getServer(aName);
	}
	@Override
	public SessionParticipantDescription getClient(String aName) {
		return groupSessionPort.getClient(aName);
	}
	@Override
	public SessionParticipantDescription getMember(String aName) {
		return groupSessionPort.getMember(aName);
	}
	@Override
	public void sendOtherClients(MessageType message) {
		groupSessionPort.sendOtherClients(message);
	}
	@Override
	public void sendOtherServers(MessageType message) {
		groupSessionPort.sendOtherServers(message);
		
	}
	@Override
	public void sendOtherMembers(MessageType message) {
		groupSessionPort.sendOtherMembers(message);
	}
	@Override
	public void sendAllClients(MessageType message) {
		groupSessionPort.sendAllClients(message);
	}
	@Override
	public void sendAllServers(MessageType message) {
		groupSessionPort.sendAllServers(message);
	}
	@Override
	public void sendAllMembers(MessageType message) {
		groupSessionPort.sendAllMembers(message);
	}
	@Override
	public ParticipantChoice getParticipantChoice() {
		// TODO Auto-generated method stub
		return groupSessionPort.getParticipantChoice();
	}
	@Override
	public void sendAllRemoteMembers(MessageType message) {
		groupSessionPort.sendAllRemoteMembers(message);
		
	}

}
