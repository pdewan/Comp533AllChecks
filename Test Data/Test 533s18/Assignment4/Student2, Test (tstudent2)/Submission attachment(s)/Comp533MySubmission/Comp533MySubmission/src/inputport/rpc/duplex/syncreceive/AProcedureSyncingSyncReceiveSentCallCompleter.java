package inputport.rpc.duplex.syncreceive;

import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class AProcedureSyncingSyncReceiveSentCallCompleter 
	extends ASyncReceiveSentCallCompleter
	implements 	DuplexSentCallCompleter{
	
	public AProcedureSyncingSyncReceiveSentCallCompleter(DuplexRPCInputPort aPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		super(aPort, aRemoteHandler);
		
	}
	@Override
	protected Object returnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage) {
		return getReturnValueOfRemoteFunctionCall(aRemoteEndPoint, aMessage);
	}
	protected Object getReturnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage) {
		return getReturnValueOfRemoteFunctionCall(aRemoteEndPoint, aMessage);
	}
	
}
