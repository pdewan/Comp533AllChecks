package inputport.datacomm.duplex.object.explicitreceive;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceiveListener;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ADuplexServerInputPortWithSyncReceive<MessageType>  implements 
	DuplexServerInputPortWithSyncReceive<MessageType>, ConnectionListener {
	DuplexServerInputPort<MessageType> duplexServerInputPort;
	Map<String, ExplicitReceiveListener<MessageType>> nameToSyncReceiveListener = new HashMap();
	/**
	 * Same as in client, attach a receive listener
	 */
	public ADuplexServerInputPortWithSyncReceive(DuplexServerInputPort<MessageType> aDuplexServerInputPort) {
		duplexServerInputPort = aDuplexServerInputPort;
		duplexServerInputPort.addConnectionListener(this);
	
	}
//	/**
//	 * Factory method	 
//	 */
//	protected ExplicitReceiveListener<MessageType>  createSyncReceiveListener() {
//		return new ASyncReceiveListener();
//	}
//	protected SyncReceiveListener<MessageType> getAndMaybeCreateSyncReceiveListener(String clientName) {
//		SyncReceiveListener<MessageType> syncReceiveListener = nameToSyncReceiveListener.get(clientName);
//		if (syncReceiveListener == null) {
//			syncReceiveListener = createSyncReceiveListener();
//			nameToSyncReceiveListener.put(clientName, syncReceiveListener);
//			duplexServerInputPort.addReceiveListener(syncReceiveListener);	
//		}
//		return syncReceiveListener;
//	}
	protected ExplicitReceiveListener<MessageType> getSyncReceiveListener(String clientName) {
		return  nameToSyncReceiveListener.get(clientName);
		
	}
	/*
	 * Add a separate listener for each client, to be queried by sending thread
	 */
	protected ExplicitReceiveListener<MessageType> createSyncReceiveListener(String clientName) {

		ExplicitReceiveListener<MessageType> syncReceiveListener = new ASyncReceiveListener(clientName);
			nameToSyncReceiveListener.put(clientName, syncReceiveListener);
			duplexServerInputPort.addReceiveListener(syncReceiveListener);	
//		}
		return syncReceiveListener;
	}

	@Override
	public ReceiveReturnMessage<MessageType> receive() {
		return receive(getSender());		
	}
	/**
	 * Get the message from the appropriate receive listener
	 */
	@Override
	public ReceiveReturnMessage<MessageType> receive(String aSource) {
		return getSyncReceiveListener(aSource).getReceivedMessage();		
	}
	public void addConnectionListener (ConnectionListener portConnectListener) {
		duplexServerInputPort.addConnectionListener(portConnectListener);
	}
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		duplexServerInputPort.addReceiveListener(portReceiveListener);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		duplexServerInputPort.addSendListener(portSendListener);
	}
	public void connect() {
		duplexServerInputPort.connect();
	}
	public void disconnect() {
		duplexServerInputPort.disconnect();
	}
	public String getLocalName() {
		return duplexServerInputPort.getLocalName();
	}
	
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		duplexServerInputPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		duplexServerInputPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		duplexServerInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		duplexServerInputPort.notifyPortReceive(remoteEnd, message);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexServerInputPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		duplexServerInputPort.removeConnectionListener(portConnectListener);
	}
	public void removeReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		duplexServerInputPort.removeReceiveListener(portReceiveListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		duplexServerInputPort.removeSendListener(portSendListener);
	}
	public void reply(MessageType message) {
		duplexServerInputPort.reply(message);
	}
	public void send(MessageType message) {
		duplexServerInputPort.send(message);
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
	}
	public void send(String remoteName, MessageType message) {
		duplexServerInputPort.send(remoteName, message);
	}


	@Override
	public String getSender() {
		return duplexServerInputPort.getSender();
	}


	@Override
	public Set<String> getConnections() {
		return duplexServerInputPort.getConnections();
	}


	@Override
	public void setSender(String newVal) {
		duplexServerInputPort.setSender(newVal);
	}
	@Override
	public String getServerId() {
		return duplexServerInputPort.getServerId();
	}
	@Override
	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
		return duplexServerInputPort.getReceiveTrapper();
	}
	@Override
	public void setReceiveTrapper(
			ReceiveTrapper<MessageType, MessageType> newVal) {
		duplexServerInputPort.setReceiveTrapper(newVal);
	}
	@Override
	public SendTrapper<MessageType, MessageType> getSendTrapper() {
		return duplexServerInputPort.getSendTrapper();
	}
	@Override
	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
		duplexServerInputPort.setSendTrapper(newVal);
	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		createSyncReceiveListener(aRemoteEndName);
		
	}
	@Override
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return duplexServerInputPort.isConnected(remoteName);
	}
	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		return duplexServerInputPort.getReceiveListeners();
	}
	@Override
	public String getLogicalRemoteEndPoint() {
		return duplexServerInputPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return duplexServerInputPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		duplexServerInputPort.setLogicalRemoteEndPoint(newVal);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		duplexServerInputPort.setPhysicalRemoteEndPoint(newVal);
	}

	

}
