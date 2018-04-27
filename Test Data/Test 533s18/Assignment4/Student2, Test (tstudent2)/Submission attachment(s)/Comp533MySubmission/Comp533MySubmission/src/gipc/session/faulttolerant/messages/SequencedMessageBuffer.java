package gipc.session.faulttolerant.messages;

import java.util.List;

public interface SequencedMessageBuffer {

	public abstract int insertMessage(SequencedMessage aMessage);

	public abstract int indexOf(SequencedMessage aMessage);

	public abstract int indexOf(int aSequenceNumber);

	public abstract List<SequencedMessage> removeMessagesFrom(
			int aSequenceNumber);

}