package inputport.rpc.duplex.syncreceive;


import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.ADuplexReceivedCallInvoker;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

import java.io.Serializable;


public class AProcedureSyncingDuplexReceivedCallInvoker extends ADuplexReceivedCallInvoker  {
	
	public AProcedureSyncingDuplexReceivedCallInvoker(LocalRemoteReferenceTranslator aRemoteHandler, DuplexInputPort<Object> aReplier, RPCRegistry theRPCRegistry) {
		super(aRemoteHandler, aReplier, theRPCRegistry);

	}
	@Override
	protected void handleProcedureReturn(String sender, Exception e) {
		replyPossiblyTransformedMethodReturnValue(sender, null, Object.class, e);

	}
	
	
}
