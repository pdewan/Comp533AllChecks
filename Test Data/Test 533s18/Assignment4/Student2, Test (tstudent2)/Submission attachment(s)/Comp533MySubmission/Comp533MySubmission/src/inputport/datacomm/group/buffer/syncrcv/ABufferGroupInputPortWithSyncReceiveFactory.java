package inputport.datacomm.group.buffer.syncrcv;

import inputport.datacomm.duplex.buffer.syncrcv.BufferDuplexInputPortWithSyncReceiveSelector;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.datacomm.group.syncrcv.GroupInputPortWithSyncReceiveFactory;
import inputport.datacomm.group.syncrcv.GroupServerInputPortWithSyncReceive;

import java.nio.ByteBuffer;


public class ABufferGroupInputPortWithSyncReceiveFactory implements GroupInputPortWithSyncReceiveFactory<ByteBuffer>  {
	@Override
	public GroupServerInputPortWithSyncReceive<ByteBuffer> createGroupServerInputPortWithSyncReceive(String theServerId,
			String theServerName) {
		DuplexServerInputPortWithSyncReceive<ByteBuffer> duplexPort =  	BufferDuplexInputPortWithSyncReceiveSelector.createBufferDuplexServerInputPortWithSyncReceive(theServerId, theServerName);
//		GroupNamingSender<ByteBuffer> namingSender = new AnUntypedGroupNamingSender(duplexPort);
		return new ABufferGroupServerInputPortWithSyncReceive (duplexPort);	
	}
	@Override
	public DuplexClientInputPortWithSyncReceive<ByteBuffer> createDuplexClientInputPortWithSyncReceive(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		return BufferDuplexInputPortWithSyncReceiveSelector.createBufferDuplexClientInputPortWithSyncReceive(theServerHost, theServerId, aServerName, theClientName);
		
	}
}
