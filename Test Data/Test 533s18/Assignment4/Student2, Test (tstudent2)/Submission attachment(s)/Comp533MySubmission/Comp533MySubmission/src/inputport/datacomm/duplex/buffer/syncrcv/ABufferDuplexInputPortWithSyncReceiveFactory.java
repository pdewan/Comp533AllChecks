package inputport.datacomm.duplex.buffer.syncrcv;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ADuplexInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.DuplexInputPortWithSyncReceiveFactory;

import java.nio.ByteBuffer;



public class ABufferDuplexInputPortWithSyncReceiveFactory  extends ADuplexInputPortWithSyncReceiveFactory<ByteBuffer> implements DuplexInputPortWithSyncReceiveFactory<ByteBuffer>{

	@Override
	public DuplexServerInputPort<ByteBuffer> createDuplexServerInputPort(
			String theServerId, String theServerName) {
		return DuplexBufferInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
	}

	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theHost,
			String theServerId, String serverName, String theClientName) {
		return DuplexBufferInputPortSelector.createDuplexClientInputPort(theHost, theServerId, serverName, theClientName);
	}	

}
