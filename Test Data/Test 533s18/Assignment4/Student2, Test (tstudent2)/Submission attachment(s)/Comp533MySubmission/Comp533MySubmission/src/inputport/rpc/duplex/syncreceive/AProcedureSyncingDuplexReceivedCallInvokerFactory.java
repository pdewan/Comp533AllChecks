package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.DuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.DuplexReceivedCallInvoker;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class AProcedureSyncingDuplexReceivedCallInvokerFactory implements DuplexReceivedCallInvokerFactory{

	@Override
	public DuplexReceivedCallInvoker createDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aRemoteHandler,
			DuplexInputPort<Object> aReplier, RPCRegistry anRPCRegistry) {
		return new AProcedureSyncingDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry);
	}

}
