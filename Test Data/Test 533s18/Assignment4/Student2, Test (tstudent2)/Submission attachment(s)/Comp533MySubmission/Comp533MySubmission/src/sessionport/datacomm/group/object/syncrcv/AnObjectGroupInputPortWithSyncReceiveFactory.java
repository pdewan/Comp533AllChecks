package sessionport.datacomm.group.object.syncrcv;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.object.GroupObjectInputPortSelector;
import inputport.datacomm.group.syncrcv.AGroupInputPortWithSyncReceiveFactory;
import inputport.datacomm.group.syncrcv.GroupInputPortWithSyncReceiveFactory;

public class AnObjectGroupInputPortWithSyncReceiveFactory 
	extends AGroupInputPortWithSyncReceiveFactory<Object> 
	implements GroupInputPortWithSyncReceiveFactory<Object>{

	@Override
	protected DuplexClientInputPort<Object> createDuplexClientInputPort(
			String theHost, String theServerId, String aServerName,
			String theClientName) {
		return DuplexObjectInputPortSelector.createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
	}

	@Override
	protected GroupServerInputPort<Object> createGroupServerInputPort(
			String theServerId, String theServerName) {
		return GroupObjectInputPortSelector.createGroupServerInputPort(theServerId, theServerName);
	}

}
