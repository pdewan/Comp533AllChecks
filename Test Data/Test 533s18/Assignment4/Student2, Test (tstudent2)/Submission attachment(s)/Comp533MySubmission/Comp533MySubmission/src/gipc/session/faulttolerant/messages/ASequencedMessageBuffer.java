package gipc.session.faulttolerant.messages;

import java.util.ArrayList;
import java.util.List;

public class ASequencedMessageBuffer implements SequencedMessageBuffer {
	List<SequencedMessage> buffer = new ArrayList();
	@Override
	public int insertMessage(SequencedMessage aMessage) {
		int anIndex = indexOf(aMessage);
		if (anIndex == -1)
			anIndex = buffer.size();
		buffer.add(anIndex, aMessage);
		return anIndex;
		
	}
	@Override
	public int indexOf(SequencedMessage aMessage) {
		return indexOf(aMessage.getSequenceNumber());
	}
	
	@Override
	public int indexOf(int aSequenceNumber) {
		int retVal = buffer.size();
		for (int i = 0; i < buffer.size(); i++) {
			if (buffer.get(i).getSequenceNumber() > aSequenceNumber)
				return i;
		}
		return -1;
	}
	
	@Override
	public List<SequencedMessage> removeMessagesFrom (int aSequenceNumber) {
		List<SequencedMessage> result = new ArrayList();
		int anIndex = indexOf(aSequenceNumber);
		if (anIndex == -1)
			return result;
		for (int i = anIndex; i < buffer.size(); i++ ) {
			result.add(buffer.get(i));
			
		}
		
		for (int i = anIndex; i < buffer.size(); i++ ) {
			buffer.remove(anIndex);
			
		}
		return result;
	}

}
