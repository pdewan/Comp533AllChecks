package objectgspsyncrcv;


import port.ParticipantChoice;
import sessionport.datacomm.group.syncrcv.GroupSessionPortWithSyncReceive;
import sessionport.datacomm.group.syncrcv.GroupSessionPortWithSyncReceiveFactory;


public class ObjectGroupSessionPortWithSyncReceiveSelector  {
	static GroupSessionPortWithSyncReceiveFactory<Object> factory = new AnObjectGroupSessionPortWithSyncReceiveFactory();
	 
	public static void setBufferGroupSessionPortWithSyncReceiveFactory(GroupSessionPortWithSyncReceiveFactory<Object> theSessionPortFactory) {
		factory = theSessionPortFactory;
	}
	public static GroupSessionPortWithSyncReceive<Object> createBufferGroupSessionPortWithSyncReceive(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice) {
		return factory.createGroupSessionPortWithSyncReceive(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);
	}
}
