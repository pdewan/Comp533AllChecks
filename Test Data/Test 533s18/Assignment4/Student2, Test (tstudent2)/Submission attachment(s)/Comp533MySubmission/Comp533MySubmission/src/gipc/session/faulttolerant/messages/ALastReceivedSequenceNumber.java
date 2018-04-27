package gipc.session.faulttolerant.messages;

public class ALastReceivedSequenceNumber   implements SynchronizingMessage{
	int sequenceNumber;
	public ALastReceivedSequenceNumber() {
		
	}
	public ALastReceivedSequenceNumber(int sequenceNumber) {
		super();
		this.sequenceNumber = sequenceNumber;
	}
	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	protected void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String toString() {
		return "Last received sequence number:" + sequenceNumber;
	}
}
