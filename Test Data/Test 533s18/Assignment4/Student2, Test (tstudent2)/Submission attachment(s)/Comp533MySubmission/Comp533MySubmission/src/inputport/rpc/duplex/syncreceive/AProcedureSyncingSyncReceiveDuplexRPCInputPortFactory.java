package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.DuplexObjectInputPortWithSyncReceiveSelector;
import inputport.rpc.duplex.ADuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
// add procedure sync receive
public class AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory extends ADuplexRPCInputPortFactory implements DuplexRPCInputPortFactory {
	public AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory() {
		super();
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
				new AProcedureSyncingSyncReceiveSentCallCompleterCallFactory());
//		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new AProcedureSyncingDuplexReceivedCallInvokerFactory());

		
	}
	protected DuplexServerInputPort<Object> createDuplexServerInputPort(String theServerId,
			String theServerName) {
		return DuplexObjectInputPortWithSyncReceiveSelector.createDuplexServerInputPortWithSyncReceive(theServerId, theServerName);
		
	}
	protected DuplexClientInputPort<Object> createDuplexClientInputPort(String theServerHost, String theServerId,
			String theServerName, String theClientName)  {
		return DuplexObjectInputPortWithSyncReceiveSelector.createDuplexClientInputPortWithSyncReceive(theServerHost, theServerId, theServerName, theClientName);		
	}
	


}
