package sessionport.datacomm.group.syncrcv;

import sessionport.datacomm.group.GroupSessionPort;
import sessionport.duplex.syncrcv.DuplexSessionPortWithSyncReceive;

public interface GroupSessionPortWithSyncReceive<MessageType> extends  GroupSessionPort<MessageType>, 
DuplexSessionPortWithSyncReceive<MessageType> { 

}
