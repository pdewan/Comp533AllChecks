package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.DuplexInputPortWithSyncReceiveFactory;



public class ADuplexObjectInputPortWithSyncReceiveFactory  extends ADuplexInputPortWithSyncReceiveFactory<Object> implements DuplexInputPortWithSyncReceiveFactory<Object>{

	@Override
	public DuplexServerInputPort<Object> createDuplexServerInputPort(
			String theServerId, String theServerName) {
		return DuplexObjectInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
	}

	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theHost,
			String theServerId, String serverName, String theClientName) {
		return DuplexObjectInputPortSelector.createDuplexClientInputPort(theHost, theServerId, serverName, theClientName);
	}	

}
