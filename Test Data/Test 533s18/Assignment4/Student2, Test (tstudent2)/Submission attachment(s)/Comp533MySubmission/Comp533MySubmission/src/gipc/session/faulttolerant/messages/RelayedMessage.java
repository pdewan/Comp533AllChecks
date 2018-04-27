package gipc.session.faulttolerant.messages;

public interface RelayedMessage extends SequencedMessage {
	 Object getBroadcastObject() ;

	 void setBroadcastObject(Object sourceBroadcast);

}
