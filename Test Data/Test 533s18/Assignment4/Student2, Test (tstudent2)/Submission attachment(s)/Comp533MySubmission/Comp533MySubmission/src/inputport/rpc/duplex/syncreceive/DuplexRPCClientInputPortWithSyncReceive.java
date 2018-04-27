package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceive;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

public interface DuplexRPCClientInputPortWithSyncReceive extends DuplexRPCClientInputPort, ExplicitReceive<Object>, ExplicitSourceReceive<Object> {

}
