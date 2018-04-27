package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;



public class DuplexObjectInputPortWithSyncReceiveSelector  {
//	static DuplexInputPortFactory<ByteBuffer> factory = new AnNIODuplexInputPortFactory();
	static DuplexInputPortWithSyncReceiveFactory<Object> factory = new ADuplexObjectInputPortWithSyncReceiveFactory();
	public static void setObjectDuplexInputPortWithSyncReceiveFactory(DuplexInputPortWithSyncReceiveFactory<Object> aFactory) {
		factory = aFactory;
	}
	public static DuplexServerInputPortWithSyncReceive<Object> createDuplexServerInputPortWithSyncReceive(String theServerId, String theServerName) {
		return factory.createDuplexServerInputPortWithSyncReceive(theServerId, theServerName);
	}
	public static DuplexClientInputPortWithSyncReceive<Object> createDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName){
		return factory.createDuplexClientInputPortWithSyncReceive(theHost, theServerId, aServerName, theClientName);
	}
	
}
