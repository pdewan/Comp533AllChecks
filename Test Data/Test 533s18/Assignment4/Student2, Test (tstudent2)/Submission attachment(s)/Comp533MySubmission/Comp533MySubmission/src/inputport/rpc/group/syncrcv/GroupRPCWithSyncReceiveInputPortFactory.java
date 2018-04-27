package inputport.rpc.group.syncrcv;

import inputport.rpc.duplex.syncreceive.DuplexRPCClientInputPortWithSyncReceive;

public interface GroupRPCWithSyncReceiveInputPortFactory {
	public GroupRPCServerInputPortWithSyncReceive createGroupRPCServerInputPortWithSyncReceive(String theServerId, String theServerName);
	public DuplexRPCClientInputPortWithSyncReceive createDuplexRPCClientInputPortWithSyncReceive(String theServerHost, String theServerId, String theServerName, String theClientName);

}
