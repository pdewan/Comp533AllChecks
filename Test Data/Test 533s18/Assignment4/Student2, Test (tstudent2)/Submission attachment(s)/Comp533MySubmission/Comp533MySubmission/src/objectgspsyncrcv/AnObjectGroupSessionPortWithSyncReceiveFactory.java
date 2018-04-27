package objectgspsyncrcv;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.syncrcv.AGroupSessionPortWithSyncReceiveFactory;

public class AnObjectGroupSessionPortWithSyncReceiveFactory extends AGroupSessionPortWithSyncReceiveFactory<Object>  {

	@Override
	protected GroupSessionPort<Object> createGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aChoice) {
		return ObjectGroupSessionPortSelector.createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);
	}

	

}
