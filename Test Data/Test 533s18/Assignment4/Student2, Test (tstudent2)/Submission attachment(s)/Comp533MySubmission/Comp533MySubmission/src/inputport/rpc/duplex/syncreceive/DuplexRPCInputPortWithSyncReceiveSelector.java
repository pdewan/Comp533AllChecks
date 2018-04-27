package inputport.rpc.duplex.syncreceive;

public class DuplexRPCInputPortWithSyncReceiveSelector {
	static DuplexRPCInputPortWithSyncReceiveFactory inputPortFactory = new ADuplexRPCInputPortWithSyncReceiveFactory();
	public static void setInputPortFactory(DuplexRPCInputPortWithSyncReceiveFactory theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static DuplexRPCInputPortWithSyncReceiveFactory getInputPortFactory() {
		return inputPortFactory;
	}	
	public static DuplexRPCServerInputPortWithSyncReceive createDuplexRPCServerInputPortWithSyncReceive(String theServerId, String theServerName) {
		return inputPortFactory.createDuplexRPCServerInputPortWithSyncReceive(theServerId, theServerName);
	}
	public static DuplexRPCClientInputPortWithSyncReceive createDuplexRPCClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName){
		return inputPortFactory.createDuplexRPCClientInputPortWithSyncReceive(theHost, theServerId, aServerName, theClientName);
	}
}
