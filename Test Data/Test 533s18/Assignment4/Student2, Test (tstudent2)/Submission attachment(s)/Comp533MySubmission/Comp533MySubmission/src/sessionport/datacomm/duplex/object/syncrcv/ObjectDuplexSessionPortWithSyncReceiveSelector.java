package sessionport.datacomm.duplex.object.syncrcv;

import port.ParticipantChoice;
import sessionport.duplex.syncrcv.DuplexSessionPortWithSyncReceive;
import sessionport.duplex.syncrcv.DuplexSessionPortWithSyncReceiveFactory;


public class ObjectDuplexSessionPortWithSyncReceiveSelector  {
	static DuplexSessionPortWithSyncReceiveFactory<Object> factory = new AnObjectDuplexSessionPortWithSyncReceiveFactory();
	public static void setObjectDuplexSessionPortWithSyncReceiveFactory(DuplexSessionPortWithSyncReceiveFactory<Object> aFactory) {
		factory = aFactory;
	}
	
	public static DuplexSessionPortWithSyncReceive<Object> 
	     createObjectDuplexSessionPortWithSyncReceive (
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aJoinChoice){
		return factory.createDuplexSessionPortWithSyncReceive(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);
	}	
}
