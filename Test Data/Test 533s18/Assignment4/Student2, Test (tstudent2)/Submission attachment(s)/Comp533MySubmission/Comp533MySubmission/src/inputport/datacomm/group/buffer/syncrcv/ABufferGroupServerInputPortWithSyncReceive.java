package inputport.datacomm.group.buffer.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.group.buffer.ABufferGroupServerInputPort;
import inputport.datacomm.group.syncrcv.GroupServerInputPortWithSyncReceive;

import java.nio.ByteBuffer;


public class ABufferGroupServerInputPortWithSyncReceive extends ABufferGroupServerInputPort 
		implements GroupServerInputPortWithSyncReceive<ByteBuffer>{
	DuplexServerInputPortWithSyncReceive<ByteBuffer> duplexServerInputPortWithSyncReceive;

	public ABufferGroupServerInputPortWithSyncReceive(DuplexServerInputPortWithSyncReceive<ByteBuffer> aDuplexServerInputPort) {
		super(aDuplexServerInputPort);
		duplexServerInputPortWithSyncReceive = aDuplexServerInputPort;
	}

	@Override
	public ReceiveReturnMessage<ByteBuffer> receive() {
		return duplexServerInputPortWithSyncReceive.receive();
	}

	@Override
	public ReceiveReturnMessage<ByteBuffer> receive(String source) {
		return duplexServerInputPortWithSyncReceive.receive(source);
	}
	

}
