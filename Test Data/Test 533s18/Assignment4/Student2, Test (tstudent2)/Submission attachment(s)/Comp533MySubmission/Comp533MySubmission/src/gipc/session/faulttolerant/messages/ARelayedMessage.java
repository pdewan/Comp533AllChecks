package gipc.session.faulttolerant.messages;

public class ARelayedMessage extends ASequencedMessage implements RelayedMessage{
	Object sourceBroadcast;
	public Object getBroadcastObject() {
		return sourceBroadcast;
	}

	public void setBroadcastObject(Object sourceBroadcast) {
		this.sourceBroadcast = sourceBroadcast;
	}

	public ARelayedMessage() {
	}

	public ARelayedMessage(Object message, String identifier, int sequenceNunber, Object aSourceBroadcast) {
		super(message, identifier, sequenceNunber);
		sourceBroadcast = aSourceBroadcast;
	}

}
