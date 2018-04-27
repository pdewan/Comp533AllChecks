package inputport.rpc.duplex.syncreceive;

import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceive;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;
import inputport.rpc.duplex.DuplexRPCServerInputPort;

public interface DuplexRPCServerInputPortWithSyncReceive extends DuplexRPCServerInputPort, ExplicitReceive<Object>, ExplicitSourceReceive<Object> {

}
