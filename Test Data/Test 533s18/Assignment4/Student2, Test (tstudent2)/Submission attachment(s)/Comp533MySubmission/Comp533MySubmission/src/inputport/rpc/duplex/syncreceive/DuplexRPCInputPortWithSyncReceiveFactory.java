package inputport.rpc.duplex.syncreceive;

public interface DuplexRPCInputPortWithSyncReceiveFactory {
	public DuplexRPCServerInputPortWithSyncReceive createDuplexRPCServerInputPortWithSyncReceive(String theServerId, String theServerName);
	public DuplexRPCClientInputPortWithSyncReceive createDuplexRPCClientInputPortWithSyncReceive (String theServerHost, String theServerId, String theServerName, String theClientName);

}
