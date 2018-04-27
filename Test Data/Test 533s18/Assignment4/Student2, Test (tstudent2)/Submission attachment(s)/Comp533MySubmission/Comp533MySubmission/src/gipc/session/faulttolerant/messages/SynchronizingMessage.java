package gipc.session.faulttolerant.messages;

import java.io.Serializable;

public interface SynchronizingMessage extends Serializable{
	int getSequenceNumber();

}
