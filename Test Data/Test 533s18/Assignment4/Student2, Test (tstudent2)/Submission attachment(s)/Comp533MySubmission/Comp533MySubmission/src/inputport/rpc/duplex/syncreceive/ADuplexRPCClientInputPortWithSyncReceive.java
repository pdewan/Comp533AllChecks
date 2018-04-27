package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import inputport.rpc.duplex.RPCReturnValue;

public class ADuplexRPCClientInputPortWithSyncReceive  extends ADuplexRPCClientInputPort  
									implements DuplexRPCClientInputPortWithSyncReceive {
	DuplexClientInputPortWithSyncReceive<Object> duplexClientInputPortWithSyncReceive;
	public ADuplexRPCClientInputPortWithSyncReceive(DuplexClientInputPortWithSyncReceive<Object> aDuplexObjectClientInputPort) {
		super(aDuplexObjectClientInputPort);
		duplexClientInputPortWithSyncReceive = aDuplexObjectClientInputPort;;
	}
	@Override
	public ReceiveReturnMessage<Object> receive() {
		return duplexClientInputPortWithSyncReceive.receive();
	}
	@Override
	public ReceiveReturnMessage<Object> receive(String source) {
		return  duplexClientInputPortWithSyncReceive.receive();
	}	

	/**
	 * RPC return values are no longer sent to the default GIPC code
	 */
	@Override
	public void messageReceived(String remoteClientName, Object message) {
		if (!(message instanceof RPCReturnValue)) {
//			rpcReturnValueReceiver.messageReceived(remoteClientName, (RPCReturnValue) message);
//		} else
			super.messageReceived(remoteClientName, message);
		}
			
	}
	

}
