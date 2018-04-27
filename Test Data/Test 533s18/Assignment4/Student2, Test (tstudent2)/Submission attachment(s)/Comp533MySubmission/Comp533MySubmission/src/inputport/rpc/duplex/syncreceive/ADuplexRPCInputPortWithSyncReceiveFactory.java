package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexObjectInputPortWithSyncReceiveSelector;
import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexRPCServerInputPort;

// just has sync receive, not provedure syncing
public class ADuplexRPCInputPortWithSyncReceiveFactory implements DuplexRPCInputPortWithSyncReceiveFactory, DuplexRPCInputPortFactory {
    
	@Override
	public DuplexRPCClientInputPortWithSyncReceive createDuplexRPCClientInputPortWithSyncReceive(String theServerHost, String theServerId,
			String theServerName, String theClientName) {
		// TODO Auto-generated method stub
		DuplexClientInputPortWithSyncReceive<Object> typedClientInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexClientInputPortWithSyncReceive (theServerHost, theServerId, theServerName, theClientName);
		DuplexRPCClientInputPortWithSyncReceive rpcClientInputPort =  new ADuplexRPCClientInputPortWithSyncReceive(typedClientInputPort);
		return rpcClientInputPort;
	}

	@Override
	public DuplexRPCServerInputPortWithSyncReceive createDuplexRPCServerInputPortWithSyncReceive(String theServerId,
			String theServerName) {
		DuplexServerInputPortWithSyncReceive<Object> typedServerInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexServerInputPortWithSyncReceive(theServerId, theServerName);
		DuplexRPCServerInputPortWithSyncReceive rpcServerInputPort = new ADuplexRPCServerInputPortWithSyncReceive(typedServerInputPort);
		return rpcServerInputPort;
	}

	@Override
	public DuplexRPCServerInputPort createDuplexRPCServerInputPort(
			String theServerId, String theServerName) {
		return createDuplexRPCServerInputPortWithSyncReceive(theServerId, theServerName);
	}

	@Override
	public DuplexRPCClientInputPort createDuplexRPCClientInputPort(
			String theServerHost, String theServerId, String theServerName,
			String theClientName) {
		return createDuplexRPCClientInputPortWithSyncReceive(theServerHost, theServerId, theServerName, theClientName);
	}

	@Override
	public DuplexRPCClientInputPort createDuplexRPCClientInputPort(
			DuplexClientInputPort<Object> typedClientInputPort) {
		return new ADuplexRPCClientInputPortWithSyncReceive((DuplexClientInputPortWithSyncReceive<Object>) typedClientInputPort);
	}

	@Override
	public DuplexRPCServerInputPort createDuplexRPCServerInputPort(
			DuplexServerInputPort<Object> typedServerInputPort) {
		return new ADuplexRPCServerInputPortWithSyncReceive((DuplexServerInputPortWithSyncReceive<Object>) typedServerInputPort);
	}

}
