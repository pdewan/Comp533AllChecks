package gipc.session.faulttolerant.messages;

public interface SequencedMessage extends IdentifiableMessage{

	public abstract int getSequenceNumber();

	public abstract void setSequenceNumber(int sequenceNunber);

}