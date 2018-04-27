package sessionport.datacomm.group.object.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.group.syncrcv.GroupInputPortWithSyncReceiveFactory;
import inputport.datacomm.group.syncrcv.GroupServerInputPortWithSyncReceive;

public class ObjectGroupInputPortWithSyncReceiveSelector {
	static GroupInputPortWithSyncReceiveFactory<Object> factory = new AnObjectGroupInputPortWithSyncReceiveFactory();
	public static void setGroupInputPortWithSyncReceiveFactor(GroupInputPortWithSyncReceiveFactory<Object> aFactory) {
		factory = aFactory;
	}
	public static GroupServerInputPortWithSyncReceive<Object> createGroupServerInputPortWithSyncReceive(String theServerId, String theServerName) {
		return factory.createGroupServerInputPortWithSyncReceive(theServerId, theServerName);
	}
	public static DuplexClientInputPortWithSyncReceive<Object> createDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName) {
		 return factory.createDuplexClientInputPortWithSyncReceive(theHost, theServerId, aServerName, theClientName);
		 
	 }


}
