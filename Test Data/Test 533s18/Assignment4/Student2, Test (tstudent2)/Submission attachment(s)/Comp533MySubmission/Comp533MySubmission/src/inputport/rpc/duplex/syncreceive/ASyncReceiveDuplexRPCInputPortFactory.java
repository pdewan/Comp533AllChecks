package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.DuplexObjectInputPortWithSyncReceiveSelector;
import inputport.rpc.duplex.ADuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;

public class ASyncReceiveDuplexRPCInputPortFactory extends ADuplexRPCInputPortFactory implements DuplexRPCInputPortFactory {
	public ASyncReceiveDuplexRPCInputPortFactory() {
		super();
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
				new ASyncReceiveSentCallCompleterCallFactory());
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
