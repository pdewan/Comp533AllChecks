package sessionport.duplex.syncrcv;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ASyncReceiveListener;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceiveListener;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sessionport.datacomm.duplex.DuplexSessionPort;

public class ADuplexSessionPortWithSyncReceive<MessageType> implements  DuplexSessionPortWithSyncReceive<MessageType> {
	DuplexSessionPort<MessageType> duplexSessionPort;
	Map<String, ExplicitReceiveListener<MessageType>> nameToSyncReceiveListener = new HashMap();
	public ADuplexSessionPortWithSyncReceive(DuplexSessionPort<MessageType> aDuplexSessionPort) {
		duplexSessionPort = aDuplexSessionPort;	
	}
	protected ExplicitReceiveListener<MessageType>  createSyncReceiveListener(String clientName) {
		return new ASyncReceiveListener(clientName);
	}
	protected ExplicitReceiveListener<MessageType> getAndMaybeCreateSyncReceiveListener(String clientName) {
		ExplicitReceiveListener<MessageType> syncReceiveListener = nameToSyncReceiveListener.get(clientName);
		if (syncReceiveListener == null) {
			syncReceiveListener = createSyncReceiveListener(clientName);
			nameToSyncReceiveListener.put(clientName, syncReceiveListener);
			duplexSessionPort.addReceiveListener(syncReceiveListener);	
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
		duplexSessionPort.addConnectionListener(portConnectListener);
	}
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		duplexSessionPort.addReceiveListener(portReceiveListener);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		duplexSessionPort.addSendListener(portSendListener);
	}
	public void connect() {
		duplexSessionPort.connect();
	}
	public void disconnect() {
		duplexSessionPort.disconnect();
	}
	public String getSender() {
		return duplexSessionPort.getSender();
	}
	public String getLocalName() {
		return duplexSessionPort.getLocalName();
	}
	public Set<String> getConnections() {
		return duplexSessionPort.getConnections();
	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		duplexSessionPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		duplexSessionPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		duplexSessionPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		duplexSessionPort.notifyPortReceive(remoteEnd, message);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexSessionPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		duplexSessionPort.removeConnectionListener(portConnectListener);
	}
	public void removeReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		duplexSessionPort.removeReceiveListener(portReceiveListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		duplexSessionPort.removeSendListener(portSendListener);
	}
	public void reply(MessageType message) {
		duplexSessionPort.reply(message);
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
	}
	public void send(MessageType message) {
		duplexSessionPort.send(message);
	}
	public void send(String remoteName, MessageType message) {
		duplexSessionPort.send(remoteName, message);
	}
	public void setSender(String newVal) {
		duplexSessionPort.setSender(newVal);
	}
	@Override
	public String getServerId() {
		return duplexSessionPort.getServerId();
	}
	@Override
	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
		return duplexSessionPort.getReceiveTrapper();
	}
	@Override
	public void setReceiveTrapper(
			ReceiveTrapper<MessageType, MessageType> newVal) {
		duplexSessionPort.setReceiveTrapper(newVal);
	}
	@Override
	public SendTrapper<MessageType, MessageType> getSendTrapper() {
		return duplexSessionPort.getSendTrapper();
	}
	@Override
	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
		duplexSessionPort.setSendTrapper(newVal);
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return duplexSessionPort.isConnected(remoteName);
	}
	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		return duplexSessionPort.getReceiveListeners();
	}
	public String getLogicalRemoteEndPoint() {
		return duplexSessionPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return duplexSessionPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		duplexSessionPort.setLogicalRemoteEndPoint(newVal);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		duplexSessionPort.setPhysicalRemoteEndPoint(newVal);
	}

}
