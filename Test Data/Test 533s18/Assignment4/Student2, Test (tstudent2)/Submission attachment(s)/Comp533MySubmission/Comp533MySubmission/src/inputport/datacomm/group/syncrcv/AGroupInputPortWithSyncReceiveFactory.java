package inputport.datacomm.group.syncrcv;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ADuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.group.GroupServerInputPort;

public  abstract class AGroupInputPortWithSyncReceiveFactory<MessageType>  implements 
GroupInputPortWithSyncReceiveFactory<MessageType>{
	
	abstract protected GroupServerInputPort<MessageType> createGroupServerInputPort(String theServerId, String theServerName);
	abstract protected DuplexClientInputPort<MessageType> createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName);
	
	@Override
	public DuplexClientInputPortWithSyncReceive<MessageType> createDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName) {
		DuplexClientInputPort<MessageType>  duplexClientInputPort = createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
		return new ADuplexClientInputPortWithSyncReceive<MessageType>(duplexClientInputPort);
		
	}
	
	@Override
	public GroupServerInputPortWithSyncReceive<MessageType> createGroupServerInputPortWithSyncReceive(String aServerId, String aServerName) {
		GroupServerInputPort<MessageType> groupServerInputPort = createGroupServerInputPort(aServerId, aServerName);
		return new AGroupServerInputPortWithSyncReceive<MessageType>(groupServerInputPort);
		
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
