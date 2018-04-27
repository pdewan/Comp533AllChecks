package inputport.rpc.group.syncrcv;

import inputport.rpc.duplex.syncreceive.DuplexRPCClientInputPortWithSyncReceive;

public class GroupRPCWithSyncReceiveInputPortSelector {
	static GroupRPCWithSyncReceiveInputPortFactory inputPortFactory = new AGroupRPCWithSyncReceiveInputPortFactory();
	public static void setGroupRPCWithSyncReceiveInputPortFactory(GroupRPCWithSyncReceiveInputPortFactory theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	
	public static GroupRPCServerInputPortWithSyncReceive createGroupRPCServerInputPortWithSyncReceive(String theServerId, String theServerName) {
		return inputPortFactory.createGroupRPCServerInputPortWithSyncReceive(theServerId, theServerName);
	}
	public static DuplexRPCClientInputPortWithSyncReceive createDuplexRPCClientInputPortWithSyncReceive(String theServerHost, String theServerId, String theServerName, String theClientName) {
		return inputPortFactory.createDuplexRPCClientInputPortWithSyncReceive(theServerHost, theServerId, theServerName, theClientName);
	}
	
}
