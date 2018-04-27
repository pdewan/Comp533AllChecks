package inputport.datacomm.group.syncrcv;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ASyncReceiveListener;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceiveListener;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AGroupServerInputPortWithSyncReceive<MessageType>  implements 
	GroupServerInputPortWithSyncReceive<MessageType> {
	GroupServerInputPort<MessageType> groupServerInputPort;
	public void addConnectionListener(ConnectionListener portConnectListener) {
		groupServerInputPort.addConnectionListener(portConnectListener);
	}
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		groupServerInputPort.addReceiveListener(portReceiveListener);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		groupServerInputPort.addSendListener(portSendListener);
	}
	public void connect() {
		groupServerInputPort.connect();
	}
	public void disconnect() {
		groupServerInputPort.disconnect();
	}
	public String getSender() {
		return groupServerInputPort.getSender();
	}
	public String getLocalName() {
		return groupServerInputPort.getLocalName();
	}
	public Set<String> getConnections() {
		return groupServerInputPort.getConnections();
	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		groupServerInputPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		groupServerInputPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		groupServerInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		groupServerInputPort.notifyPortReceive(remoteEnd, message);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		groupServerInputPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		groupServerInputPort.removeConnectionListener(portConnectListener);
	}
	public void removeReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		groupServerInputPort.removeReceiveListener(portReceiveListener);
	}
	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		return groupServerInputPort.getReceiveListeners();
	}
	
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		groupServerInputPort.removeSendListener(portSendListener);
	}
	public void reply(MessageType message) {
		groupServerInputPort.reply(message);
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
	}
	public void send(MessageType message) {
		groupServerInputPort.send(message);
	}
	public void send(Collection<String> clientNames, MessageType message) {
		groupServerInputPort.send(clientNames, message);
	}
	public void send(String remoteName, MessageType message) {
		groupServerInputPort.send(remoteName, message);
	}
	public void sendAll(MessageType message) {
		groupServerInputPort.sendAll(message);
	}
	public void sendOthers(MessageType message) {
		groupServerInputPort.sendOthers(message);
	}
	public void setSender(String newVal) {
		groupServerInputPort.setSender(newVal);
	}
	Map<String, ExplicitReceiveListener<MessageType>> nameToSyncReceiveListener = new HashMap();
	
	public AGroupServerInputPortWithSyncReceive(GroupServerInputPort<MessageType> aGroupServerInputPort) {
		groupServerInputPort = aGroupServerInputPort;
	
	}
	protected ExplicitReceiveListener<MessageType>  createSyncReceiveListener(String aClientName) {
		return new ASyncReceiveListener(aClientName);
	}
	protected ExplicitReceiveListener<MessageType> getAndMaybeCreateSyncReceiveListener(String clientName) {
		ExplicitReceiveListener<MessageType> syncReceiveListener = nameToSyncReceiveListener.get(clientName);
		if (syncReceiveListener == null) {
			syncReceiveListener = createSyncReceiveListener(clientName);
			nameToSyncReceiveListener.put(clientName, syncReceiveListener);
			groupServerInputPort.addReceiveListener(syncReceiveListener);	
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
	@Override
	public String getServerId() {
		return groupServerInputPort.getServerId();
	}
	@Override
	public GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper() {
		return groupServerInputPort.getGroupSendTrapper();
	}
	@Override
	public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() {
		return groupServerInputPort.getGroupToUniSendTrapper();
	}
	@Override
	public void setGroupSendTrapper(
			GroupSendTrapper<MessageType, MessageType> groupSendTrapper) {
		groupServerInputPort.setGroupSendTrapper(groupSendTrapper);
	}
	@Override
	public void setGroupToUniSendTrapper(
			GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) {
		groupServerInputPort.setGroupToUniSendTrapper(groupToUniSendTrapper);
	}
	@Override
	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
		return groupServerInputPort.getReceiveTrapper();
	}
	@Override
	public void setReceiveTrapper(
			ReceiveTrapper<MessageType, MessageType> newVal) {
		groupServerInputPort.setReceiveTrapper(newVal);
	}
	@Override
	public SendTrapper<MessageType, MessageType> getSendTrapper() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return groupServerInputPort.isConnected(remoteName);
	}
	
	@Override
	public String getLogicalRemoteEndPoint() {
		return groupServerInputPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return groupServerInputPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		groupServerInputPort.setLogicalRemoteEndPoint(newVal);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		groupServerInputPort.setPhysicalRemoteEndPoint(newVal);
	}
	

}
