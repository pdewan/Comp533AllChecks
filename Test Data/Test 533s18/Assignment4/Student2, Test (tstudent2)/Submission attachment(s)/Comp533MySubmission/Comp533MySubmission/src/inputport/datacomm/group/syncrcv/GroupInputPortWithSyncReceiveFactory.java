package inputport.datacomm.group.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;

public interface GroupInputPortWithSyncReceiveFactory<MessageType> {
	 GroupServerInputPortWithSyncReceive<MessageType> createGroupServerInputPortWithSyncReceive(String theServerId, String theServerName);
	 DuplexClientInputPortWithSyncReceive<MessageType> createDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName);



}
