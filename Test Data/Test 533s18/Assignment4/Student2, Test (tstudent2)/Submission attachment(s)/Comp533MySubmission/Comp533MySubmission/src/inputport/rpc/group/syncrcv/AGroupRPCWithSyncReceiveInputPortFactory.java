package inputport.rpc.group.syncrcv;

import inputport.datacomm.group.syncrcv.GroupServerInputPortWithSyncReceive;
import inputport.rpc.duplex.syncreceive.ADuplexRPCInputPortWithSyncReceiveFactory;
import sessionport.datacomm.group.object.syncrcv.ObjectGroupInputPortWithSyncReceiveSelector;

public class AGroupRPCWithSyncReceiveInputPortFactory  
      extends ADuplexRPCInputPortWithSyncReceiveFactory implements GroupRPCWithSyncReceiveInputPortFactory {



	@Override
	public GroupRPCServerInputPortWithSyncReceive createGroupRPCServerInputPortWithSyncReceive(String theServerId,
			String theServerName) {
		GroupServerInputPortWithSyncReceive<Object> typedServerInputPort = ObjectGroupInputPortWithSyncReceiveSelector.createGroupServerInputPortWithSyncReceive(theServerId, theServerName);
		GroupRPCServerInputPortWithSyncReceive rpcServerInputPort = new AGroupRPCServerInputPortWithSyncReceive(typedServerInputPort);
		return rpcServerInputPort;
	}
	

}
