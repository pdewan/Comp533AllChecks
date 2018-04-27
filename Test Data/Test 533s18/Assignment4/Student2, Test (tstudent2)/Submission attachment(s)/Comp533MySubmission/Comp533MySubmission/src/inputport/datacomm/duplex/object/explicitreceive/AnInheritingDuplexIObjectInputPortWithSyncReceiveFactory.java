package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortFactory;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;



public class AnInheritingDuplexIObjectInputPortWithSyncReceiveFactory  extends ADuplexObjectInputPortFactory 
	implements DuplexInputPortWithSyncReceiveFactory<Object>{

	@Override
	public DuplexClientInputPortWithSyncReceive<Object> createDuplexClientInputPortWithSyncReceive(
			String theHost, String theServerId, String aServerName,
			String theClientName) {
		DuplexClientInputPort  duplexClientInputPort = super.createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
		return new ADuplexClientInputPortWithSyncReceive(duplexClientInputPort);
	}

	@Override
	public DuplexServerInputPortWithSyncReceive<Object> createDuplexServerInputPortWithSyncReceive(
			String aServerId, String aServerName) {
		DuplexServerInputPort  duplexServerInputPort = super.createDuplexServerInputPort(aServerId, aServerName);
		return new ADuplexServerInputPortWithSyncReceive(duplexServerInputPort);
		
	}

	@Override
	public DuplexServerInputPort<Object> createDuplexServerInputPort(
			String aServerId, String aServerName) {
		return createDuplexServerInputPortWithSyncReceive(aServerId, aServerName);
		
	}

	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theHost,
			String theServerId, String aServerName, String theClientName) {
		
		return createDuplexClientInputPortWithSyncReceive(theHost, theServerId, aServerName, theClientName);


	}	

}
