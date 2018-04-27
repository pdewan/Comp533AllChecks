package gipc.session.faulttolerant.messages;
/*
 * sent from client to relayer with last relayed message
 */
public class AMostRecentRelay extends ASequencedMessage implements MostRecentRelay {

	public AMostRecentRelay() {
	}

	public AMostRecentRelay(Object message, String identifier,
			int sequenceNunber) {
		super(message, identifier, sequenceNunber);
	}

}
