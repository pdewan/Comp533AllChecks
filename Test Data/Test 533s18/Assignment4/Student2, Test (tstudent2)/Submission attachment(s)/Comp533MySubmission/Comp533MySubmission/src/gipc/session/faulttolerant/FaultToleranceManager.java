package gipc.session.faulttolerant;

import java.util.List;

import gipc.session.relayingclient.RelayerElector;
import inputport.datacomm.simplex.object.DeserializingForwarder;
import inputport.datacomm.simplex.object.SerializingForwarder;

public interface FaultToleranceManager extends RelayerElector{

//	public abstract int getNextGeneratedMessageNumber();
//
//	public abstract void setNextGeneratedMessageNumber(int nextMessageNumber);
//
//	public abstract int getLastSentNumber();
//
//	public abstract void setLastSentNumber(int lastSentNumber);
//
//	public abstract int getLastRelayedNumber();
//
//	public abstract void setLastRelayedNumber(int lastRelayedNumber);
//
//	public abstract int getLastReceivedNumber();
//
//	public abstract void setLastReceivedNumber(int lastReceivedNumber);
//
//	public abstract IdentifiableMessage getLastBroadcast();
//
//	public abstract void setLastBroadcast(IdentifiableMessage lastMessageSent);
//
//    String getId(Object aMessage);
//
//	void setLastRelayedMessageReceived(SequencedMessage aMessage);
//
//
//	void setLastBroadcastMessageReceived(IdentifiableMessage aMessage);
//
//	IdentifiableMessage getIdentifiableMessage(Object aMessage);
//
//	IdentifiableMessage getLastMessageReceived();
//
//	void setLastMessageReceived(IdentifiableMessage lastMessageReceived);
//
//	SequencedMessage getSequencedMessage(Object aMessage);

//	List<Object> processSentBroadcastMessage(Object aMessage);
//
//	List<Object> processSentRelayedMessage(Object aMessage);

	List<Object> processSentMessage(SerializingForwarder aSourceTrapper, String remoteName, Object aMessage);

	List<Object> processReceivedMessage(DeserializingForwarder aSourceTrapper, String remoteEnd, Object serializable);}