package inputport.rpc.group.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceive;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;
import inputport.rpc.group.GroupRPCServerInputPort;

public interface GroupRPCServerInputPortWithSyncReceive 
	extends GroupRPCServerInputPort, 
	ExplicitSourceReceive<Object>,
	ExplicitReceive<Object>{

}
