package inputport.datacomm.duplex.buffer.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;

import java.nio.ByteBuffer;



public class BufferDuplexInputPortWithSyncReceiveSelector  {
//	static DuplexInputPortFactory<ByteBuffer> factory = new AnNIODuplexInputPortFactory();
	static DuplexInputPortWithSyncReceiveFactory<ByteBuffer> factory = new ABufferDuplexInputPortWithSyncReceiveFactory();
	public static void setBufferDuplexInputPortWithSyncReceiveFactory(DuplexInputPortWithSyncReceiveFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	public static DuplexServerInputPortWithSyncReceive<ByteBuffer> createBufferDuplexServerInputPortWithSyncReceive(String theServerId, String theServerName) {
		return factory.createDuplexServerInputPortWithSyncReceive(theServerId, theServerName);
	}
	public static DuplexClientInputPortWithSyncReceive<ByteBuffer> createBufferDuplexClientInputPortWithSyncReceive(String theHost, String theServerId, String aServerName, String theClientName){
		return factory.createDuplexClientInputPortWithSyncReceive(theHost, theServerId, aServerName, theClientName);
	}
	
}
