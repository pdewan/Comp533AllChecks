package inputport.datacomm.group.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexServerInputPortWithSyncReceive;
import inputport.datacomm.group.GroupServerInputPort;

public interface GroupServerInputPortWithSyncReceive<MessageType> extends  GroupServerInputPort<MessageType>, 
	DuplexServerInputPortWithSyncReceive<MessageType>{

}
