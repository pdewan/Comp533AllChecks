package inputport.datacomm.duplex.object.explicitreceive;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceiveListener;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;


public class ADuplexClientInputPortWithSyncReceive<MessageType>  implements 
	DuplexClientInputPortWithSyncReceive<MessageType>, ConnectionListener {
	DuplexClientInputPort<MessageType> duplexClientInputPort;
	ExplicitReceiveListener<MessageType> syncReceiveListener;
//	ReceiveRegistrarAndNotifier<MessageType> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<MessageType>();
	/**
	 * Do nothing except listen to received messages
	 */
	public ADuplexClientInputPortWithSyncReceive(DuplexClientInputPort<MessageType> aDuplexClientInputPort) {
		duplexClientInputPort = aDuplexClientInputPort;
		duplexClientInputPort.addConnectionListener(this);
//		syncReceiveListener = new ASyncReceiveListener<MessageType>(null);
//		duplexClientInputPort.addReceiveListener(syncReceiveListener);	
	}
	
	/**
	 * Parameterless receive implementation
	 */
	@Override
	public ReceiveReturnMessage<MessageType> receive() {
//		return syncReceiveListener.getReceivedMessage();	
		return receive(getSender());		

	}
	/**
	 * Get message queued by receiving thread
	 */
	@Override
	public ReceiveReturnMessage<MessageType> receive(String clientName) {
		return syncReceiveListener.getReceivedMessage();		
	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		syncReceiveListener = new ASyncReceiveListener<MessageType>(aRemoteEndName);
		duplexClientInputPort.addReceiveListener(syncReceiveListener);			
	}
	
	/*
	 * Delegation methods below 
	 */
	public void addConnectionListener (ConnectionListener portConnectListener) {
		duplexClientInputPort.addConnectionListener(portConnectListener);
	}
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		duplexClientInputPort.addReceiveListener(portReceiveListener);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		duplexClientInputPort.addSendListener(portSendListener);
	}
	public void connect() {
		duplexClientInputPort.connect();
	}
	public void disconnect() {
		duplexClientInputPort.disconnect();
	}
	public String getLocalName() {
		return duplexClientInputPort.getLocalName();
	}
	public String getLogicalRemoteEndPoint() {
		return duplexClientInputPort.getLogicalRemoteEndPoint();
	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		duplexClientInputPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		duplexClientInputPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		duplexClientInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		duplexClientInputPort.notifyPortReceive(remoteEnd, message);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexClientInputPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		duplexClientInputPort.removeConnectionListener(portConnectListener);
	}
	public void removeReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		duplexClientInputPort.removeReceiveListener(portReceiveListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		duplexClientInputPort.removeSendListener(portSendListener);
	}
	public void reply(MessageType message) {
		duplexClientInputPort.reply(message);
	}
	public void send(MessageType message) {
		duplexClientInputPort.send(message);
	}
	public void send(String remoteName, MessageType message) {
		duplexClientInputPort.send(remoteName, message);
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
	}


	@Override
	public SendTrapper<MessageType, MessageType> getSendTrapper() {
		return duplexClientInputPort.getSendTrapper();
	}


	@Override
	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
		duplexClientInputPort.setSendTrapper(newVal);
	}


	@Override
	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
		return duplexClientInputPort.getReceiveTrapper();
	}


	@Override
	public void setReceiveTrapper(
			ReceiveTrapper<MessageType, MessageType> newVal) {
		duplexClientInputPort.setReceiveTrapper(newVal);
	}


	@Override
	public Set<String> getConnections() {
		return duplexClientInputPort.getConnections();
	}


	@Override
	public String getSender() {
		return duplexClientInputPort.getSender();
	}


	@Override
	public void setSender(String newVal) {
		duplexClientInputPort.setSender(newVal);

	}


	@Override
	public String getPhysicalRemoteEndPoint() {
		return duplexClientInputPort.getPhysicalRemoteEndPoint();
	}


	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		duplexClientInputPort.setPhysicalRemoteEndPoint(newVal);
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		duplexClientInputPort.setLogicalRemoteEndPoint(newVal);

		
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return duplexClientInputPort.isConnected(remoteName);
	}


	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		// TODO Auto-generated method stub
		return duplexClientInputPort.getReceiveListeners();
	}

	

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	

}
