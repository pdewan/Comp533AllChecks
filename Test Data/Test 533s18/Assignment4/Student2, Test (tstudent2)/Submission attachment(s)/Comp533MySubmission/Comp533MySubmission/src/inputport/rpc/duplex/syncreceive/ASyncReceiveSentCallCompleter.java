package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;
import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;

public class ASyncReceiveSentCallCompleter 
	extends ADuplexSentCallCompleter
	implements 
	DuplexSentCallCompleter{
	protected ExplicitSourceReceive<Object>  receiver;
//	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	
	public ASyncReceiveSentCallCompleter(DuplexRPCInputPort aPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		super(aPort, aRemoteHandler);
		receiver = 
		(ExplicitSourceReceive<Object>)aPort.getDuplexInputPort();
//		localRemoteReferenceTranslator = aRemoteHandler;
	}
	/**
	 * Keep processing messages until a return value is received. 
	 * Other messages will also be received, focus only on received RPC
	 * values
	 * 
	 */
	protected Object receiveReturnValue(String aSource) {
		while (true) {
			ReceiveReturnMessage messageWithSource = receiver.receive(aSource);
			Object message = messageWithSource.getMessage();
			if (message instanceof RPCReturnValue) {
				return ((RPCReturnValue) message).getReturnValue();
			}
		}		
	}
	/**
	 * Do nothing when return value received in receiving thread
	 */
	@Override
	protected void returnValueReceived(String source, Object message) {
//		return  message instanceof RPCReturnValue;
	}
		
	/**
	 * Called by sending thread to receive return value
	 */
	@Override
	public Object getReturnValueOfRemoteFunctionCall(String aRemoteEndPoint, Object aMessage) {
//		if (isProcedure(aMessage))
//			return super.returnValueOfRemoteMethodCall(aRemoteEndPoint, aMessage);
		try {

			return localRemoteReferenceTranslator.transformReceivedReference(receiveReturnValue(aRemoteEndPoint));			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
