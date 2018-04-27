package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;

public  abstract class ADuplexInputPortWithSyncReceiveFactory<MessageType> implements 
DuplexInputPortWithSyncReceiveFactory<MessageType>, DuplexInputPortFactory<MessageType> {
	
	
	
	@Override
	public DuplexClientInputPortWithSyncReceive<MessageType> createDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName) {
		DuplexClientInputPort<MessageType>  duplexClientInputPort = createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
		return new ADuplexClientInputPortWithSyncReceive<MessageType>(duplexClientInputPort);
		
	}
	
	@Override
	public DuplexServerInputPortWithSyncReceive<MessageType> createDuplexServerInputPortWithSyncReceive(String aServerId, String aServerName) {
		DuplexServerInputPort<MessageType>  duplexServerInputPort = createDuplexServerInputPort(aServerId, aServerName);
		return new ADuplexServerInputPortWithSyncReceive<MessageType>(duplexServerInputPort);
		
	}

//	@Override
//	public DuplexServerInputPort<MessageType> createDuplexServerInputPort(
//			String theServerId, String theServerName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public DuplexClientInputPort createDuplexClientInputPort(String theHost,
//			String theServerId, String serverName, String theClientName) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	
	
	

}
