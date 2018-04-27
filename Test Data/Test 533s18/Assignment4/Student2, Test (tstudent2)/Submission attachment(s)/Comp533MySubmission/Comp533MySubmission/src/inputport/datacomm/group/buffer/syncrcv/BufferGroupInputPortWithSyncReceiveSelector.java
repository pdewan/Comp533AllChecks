package inputport.datacomm.group.buffer.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.group.syncrcv.GroupInputPortWithSyncReceiveFactory;
import inputport.datacomm.group.syncrcv.GroupServerInputPortWithSyncReceive;

import java.nio.ByteBuffer;



public class BufferGroupInputPortWithSyncReceiveSelector  {
	static GroupInputPortWithSyncReceiveFactory<ByteBuffer> inputPortFactory = new ABufferGroupInputPortWithSyncReceiveFactory();
	public static void setGroupInputPortWithSyncReceiveFactory(GroupInputPortWithSyncReceiveFactory<ByteBuffer> theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static GroupServerInputPortWithSyncReceive<ByteBuffer> createGroupServerInputPortWithSyncReceive(String theServerId, String theServerName) {
		return inputPortFactory.createGroupServerInputPortWithSyncReceive(theServerId, theServerName);
	}
	public static DuplexClientInputPortWithSyncReceive createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		return inputPortFactory.createDuplexClientInputPortWithSyncReceive(theHost, theServerId, aServerName, theClientName);
	}
}
