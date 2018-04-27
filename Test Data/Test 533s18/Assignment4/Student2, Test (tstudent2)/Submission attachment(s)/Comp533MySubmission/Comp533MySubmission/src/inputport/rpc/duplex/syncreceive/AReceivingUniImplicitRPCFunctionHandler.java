package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceive;
import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;
import extraip.ReturnerOfFunctionCall;

public class AReceivingUniImplicitRPCFunctionHandler 
	extends AReceivingReturnValueProcessor 
	implements ReturnerOfFunctionCall	{
	ExplicitReceive<Object> receiver;
	LocalRemoteReferenceTranslator remoteHandler;
	
	public AReceivingUniImplicitRPCFunctionHandler(ExplicitReceive<Object> aReceiver, LocalRemoteReferenceTranslator aRemoteHandler) {
		receiver = aReceiver;
		remoteHandler = aRemoteHandler;
	}
		
	@Override
	public Object returnValueOfRemoteMethodCall(String aRemoteEndPoint, RemoteCall aCall) {
		try {
			while (true) {
			ReceiveReturnMessage messageWithSource = receiver.receive();
			Object message = messageWithSource.getMessage();
			if (message instanceof RPCReturnValue ) {
				return remoteHandler.transformReceivedReference(((RPCReturnValue) message).getReturnValue());
			} 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	

}
