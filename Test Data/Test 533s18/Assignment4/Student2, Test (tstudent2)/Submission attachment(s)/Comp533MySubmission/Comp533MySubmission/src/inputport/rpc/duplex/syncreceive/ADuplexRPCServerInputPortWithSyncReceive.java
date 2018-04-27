package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.ADuplexRPCServerInputPort;
import inputport.rpc.duplex.RPCReturnValue;

public class ADuplexRPCServerInputPortWithSyncReceive extends ADuplexRPCServerInputPort implements DuplexRPCServerInputPortWithSyncReceive {
	DuplexServerInputPortWithSyncReceive<Object> duplexServerInputPortWithSyncReceive;
	public ADuplexRPCServerInputPortWithSyncReceive(DuplexServerInputPortWithSyncReceive<Object> aTypedServerInputPort) {		
		super(aTypedServerInputPort);
		duplexServerInputPortWithSyncReceive = aTypedServerInputPort;		
	}
	@Override
	public ReceiveReturnMessage<Object> receive() {
		return duplexServerInputPortWithSyncReceive.receive();
	}
	@Override
	public ReceiveReturnMessage<Object> receive(String source) {
		return duplexServerInputPortWithSyncReceive.receive(source);
	}
//	@Override
//	protected DuplexSentCallCompleter createReturningUniNamingRPCFunctionHandler(LocalRemoteReferenceTranslator aRemoteHandler) {
//		return new ASyncReceiveSentCallCompleter(this, aRemoteHandler);
//	}

	
	/**
	 * RPC return values are no longer sent to the default GIPC code. Now they are received
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
