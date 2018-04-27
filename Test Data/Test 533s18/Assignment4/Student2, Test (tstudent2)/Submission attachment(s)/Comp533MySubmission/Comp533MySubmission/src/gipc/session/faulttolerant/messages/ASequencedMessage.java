package gipc.session.faulttolerant.messages;

public class ASequencedMessage extends AnIdentifiableMessage implements SequencedMessage{
	int sequenceNumber;

	public ASequencedMessage(Object message, String identifier, int sequenceNunber) {
		super(message, identifier);
		this.sequenceNumber = sequenceNunber;
	}
	public ASequencedMessage() {
		
	}
	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	@Override
	public void setSequenceNumber(int sequenceNunber) {
		this.sequenceNumber = sequenceNunber;
	}
	
	public String toString() {
		return sequenceNumber + "," + super.toString();
	}

}
