package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.DuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.AnAsynchronousSingleThreadDuplexReceivedCallInvoker;
import inputport.rpc.duplex.DuplexReceivedCallInvoker;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class AnAsynchronousProcedureSyncingDuplexReceivedCallInvokerFactory
    extends AProcedureSyncingDuplexReceivedCallInvokerFactory
    implements DuplexReceivedCallInvokerFactory{

	@Override
	public DuplexReceivedCallInvoker createDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aRemoteHandler,
			DuplexInputPort<Object> aReplier, RPCRegistry anRPCRegistry) {
//		return new AProcedureSyncingDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry);
		return new 
				AnAsynchronousSingleThreadDuplexReceivedCallInvoker(super.createDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry));
	}

}
