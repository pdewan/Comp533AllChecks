package gipc.session.faulttolerant.messages;

public class ABroadcastMessage extends AnIdentifiableMessage implements BroadcastMessage{

	public ABroadcastMessage() {
	}

	public ABroadcastMessage(Object message, String identifier) {
		super(message, identifier);
	}

}
