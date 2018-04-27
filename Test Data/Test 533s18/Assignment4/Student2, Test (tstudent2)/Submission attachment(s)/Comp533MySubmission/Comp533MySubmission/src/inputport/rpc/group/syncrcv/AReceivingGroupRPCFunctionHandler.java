package inputport.rpc.group.syncrcv;


import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.syncreceive.ASyncReceiveSentCallCompleter;
import inputport.rpc.group.GroupArrayReturnerOfValueOfRemoteFunctionCall;

import java.io.Serializable;
import java.util.Set;


public class AReceivingGroupRPCFunctionHandler  extends ASyncReceiveSentCallCompleter
	implements GroupArrayReturnerOfValueOfRemoteFunctionCall {


	public AReceivingGroupRPCFunctionHandler(
			DuplexRPCInputPort  aReceiver, 
			LocalRemoteReferenceTranslator aRemoteHandler) {
		super(aReceiver, aRemoteHandler);
	
	}
	
	public Object[] returnValueOfRemoteFunctionCall(Set<String> clientNames, RemoteCall aSerializableCall) {
		Object[] retVal = new Serializable[clientNames.size()];
		if (clientNames.size() == 0) return  retVal;
		int i = 0;
		for (String clientName: clientNames) {
			try {
//				RPCReturnValue returnValue = receiveReturnValue(clientName);
				retVal[i] =  localRemoteReferenceTranslator.transformReceivedReference(receiveReturnValue(clientName));		
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return retVal;	
	}

}
