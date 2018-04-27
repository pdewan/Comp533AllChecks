package gipc.session.faulttolerant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import gipc.session.faulttolerant.messages.ABroadcastMessage;
import gipc.session.faulttolerant.messages.AFinishedSynchronizingMessage;
import gipc.session.faulttolerant.messages.ALastReceivedSequenceNumber;
import gipc.session.faulttolerant.messages.AMostRecentRelay;
import gipc.session.faulttolerant.messages.ARelayedMessage;
import gipc.session.faulttolerant.messages.BroadcastMessage;
import gipc.session.faulttolerant.messages.FinishedSynchronizingMessage;
import gipc.session.faulttolerant.messages.MostRecentRelay;
import gipc.session.faulttolerant.messages.RelayedMessage;
import gipc.session.faulttolerant.messages.SynchronizingMessage;
import gipc.session.relayingclient.ARelayerElector;
import inputport.datacomm.simplex.object.DeserializingForwarder;
import inputport.datacomm.simplex.object.SerializingForwarder;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import util.trace.Tracer;

public class AFaultToleranceManager extends ARelayerElector implements FaultToleranceManager {
	int nextGeneratedMessageNumber = -1; // will always be incremented by received broadcast message before being used
	int nextRelayedMessageNumber = 0; 
//	boolean pendingRelayOfBroadcast;
	
//	int lastSentNumber;
//	int lastRelayedNumber;
//	int lastReceivedNumber = -1;
	BlockingQueue<BroadcastMessage> pendingSentBroadcasts = new LinkedBlockingDeque<>(); // by client
	RelayedMessage lastAckedBroadcast;
//	boolean lastBroadcastAcked;
	BroadcastMessage lastReceivedBroadcast; // by server
//	IdentifiableMessage lastIdentifiableMessageReceived;
//	SequencedMessage lastSequencedMessageG;
//	BroadcastMessage mostRecentEcho; // last received message was my echo
//	int mostRecentEchoSequenceNumber;
	SynchronizingMessage  possibleSynchronizingMessage = new ALastReceivedSequenceNumber(nextRelayedMessageNumber - 1);
//	RelayedMessage lastReceivedRelayedMessage;
	protected boolean synchronizing;
	protected int numberOfPendingSynchronizingMessages;
	protected int numPendingRelays; // do not want to change sequence number until all relays have happened
	// do not need buffer now, as relayer will syncyronize, but will keepi it anyway
//	SequencedMessageBuffer buffer = new ASequencedMessageBuffer();
	protected Map<String, SynchronizingMessage> nameToSynchronizingMessage = new HashMap<>();
//	protected Set<String> clientsNeedingSynchronizing = new HashSet();
	
	DuplexRPCSessionPort sessionPort;
	public AFaultToleranceManager(DuplexRPCSessionPort aSessionPort) {
		sessionPort = aSessionPort;		
	}
	
//	public int getNextGeneratedMessageNumber() {
//		return nextGeneratedMessageNumber;
//	}
//	public void setNextGeneratedMessageNumber(int nextMessageNumber) {
//		this.nextGeneratedMessageNumber = nextMessageNumber;
//	}
//	public int getNextRelayedMessageNumber() {
//		return nextRelayedMessageNumber;
//	}
//	public void setNextRelayedMessageNumber(int nextRelayedMessageNumber) {
//		this.nextRelayedMessageNumber = nextRelayedMessageNumber;
//	}
	
//	public int getLastSentNumber() {
//		return lastSentNumber;
//	}
//	public void setLastSentNumber(int lastSentNumber) {
//		this.lastSentNumber = lastSentNumber;
//	}
//	
//	public int getLastRelayedNumber() {
//		return lastRelayedNumber;
//	}
//	public void setLastRelayedNumber(int lastRelayedNumber) {
//		this.lastRelayedNumber = lastRelayedNumber;
//	}
//	public int getLastReceivedNumber() {
//		return lastReceivedNumber;
//	}
//	public void setLastReceivedNumber(int lastReceivedNumber) {
//		this.lastReceivedNumber = lastReceivedNumber;
//	}
//	public BroadcastMessage getLastBroadcast() {
//		return lastBroadcast;
//	}
//	public void setLastBroadcast(BroadcastMessage lastMessageSent) {
//		this.lastBroadcast = lastMessageSent;	
//		setLastBroadcastAcked(false);
//	}
//	protected boolean isLastBroadcastAcked() {
//		return pendingBroadcasts.size() == 0;;
//	}
//	protected void setLastBroadcastAcked(boolean lastBroadcastAcked) {
//		this.lastBroadcastAcked = lastBroadcastAcked;
//	
//	}
//	// all messages relayed after this will have this number
//	public void processBroadcastMessageReceived(BroadcastMessage aMessage) {
//		lastReceivedBroadcast = aMessage;
//		nextGeneratedMessageNumber++;
////		nextMessageNumber++;		
//	}
//	public SequencedMessage setLastSequencedMessageReceived() {
//		return lastSequencedMessageG;
//	}
//	public void setLastSequencedMessageGenerated(SequencedMessage lastMessageSent) {
//		setNextGeneratedMessageNumber(lastMessageSent.getSequenceNunber() + 1);
////		this.lastSequencedMessageG = lastMessageSent;		
//	}
	
	
//	public void setLastRelayedMessageReceived(SequencedMessage aMessage) {
//		setNextRelayedMessageNumber(aMessage.getSequenceNunber() + 1);		
//	}
	public String getId(Object aMessage) {
		return sessionPort.getLocalName() + "." + System.identityHashCode(aMessage);
	}
	public BroadcastMessage getBroadcastMessage (Object aMessage) {
		return new ABroadcastMessage(aMessage, getId(aMessage));
	}
	
//	public IdentifiableMessage getLastMessageReceived() {
//		return lastIdentifiableMessageReceived;
//	}
//	public void setLastMessageReceived(IdentifiableMessage lastMessageReceived) {
//		this.lastIdentifiableMessageReceived = lastMessageReceived;
//	}
	
	protected List generateRelayRequest(BroadcastMessage aMessage) {
		lastReceivedBroadcast = aMessage;
		nextGeneratedMessageNumber++;
		numPendingRelays = getRelayerNames().size();
//		Tracer.info(this, Thread.currentThread() + "forwarding relay request  " + aMessage.getIdentifier() + " incremented next generated numer to:" + nextGeneratedMessageNumber);
		Tracer.info(this, "processed relay request incrementing counter to:" + nextGeneratedMessageNumber);
		return Arrays.asList(new Object[]{aMessage.getMessage()});
	}
	/*
	 * This can be the sending thread on the same machine such as the main thread, otherwise it is the
	 * selection thread
	 */
	boolean waiting;
	public synchronized List<Object> processReceivedBroadcastMessage(BroadcastMessage aMessage) {
		while (numPendingRelays != 0)
			try {
				Tracer.info(this, "Relay request waits as previous request has pending relays:" + numPendingRelays );
//				System.out.println ("Waiting broadcast thread:" + Thread.currentThread() + " numPendngrelays:" + numPendingRelays);
				waiting = true;
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		waiting = false;
		return generateRelayRequest(aMessage);
//		lastReceivedBroadcast = aMessage;
//		nextGeneratedMessageNumber++;
//		numPendingRelays = getCurrentRelayerNames().size();
//		Tracer.info(this, Thread.currentThread() + "forwarding relay request  " + aMessage.getIdentifier() + " incremented next generated numer to:" + nextGeneratedMessageNumber);
//
//		return Arrays.asList(new Object[]{aMessage.getMessage()});
	}
	static List<Object> emptyList = new ArrayList();
	protected  void generateSynchronizationMessage(RelayedMessage aReceivedBroadcast) {
		possibleSynchronizingMessage = new  AMostRecentRelay
				(aReceivedBroadcast.getBroadcastObject(), aReceivedBroadcast.getIdentifier(), aReceivedBroadcast.getSequenceNumber());
////		if (pendingSentBroadcasts.size() == 0)
////			return;
//		BroadcastMessage firstPendingBroadcast = pendingSentBroadcasts.peek();
//		if (firstPendingBroadcast != null && firstPendingBroadcast.getIdentifier().equals(firstPendingBroadcast.getIdentifier())) {
//			try {
//				pendingSentBroadcasts.take(); // we know this will not blok
//				possibleSynchronizingMessage = new AMostRecentRelay
//						(firstPendingBroadcast.getMessage(), firstPendingBroadcast.getIdentifier(), aReceivedBroadcast.getSequenceNumber());
////				mostRecentEcho = firstPendingBroadcast;
////				mostRecentEchoSequenceNumber = aReceivedBroadcast.getSequenceNumber();
//				Tracer.info(this, "Received relay of my broadcast, generated most recent echo message as possible synchronizing mesage:" + possibleSynchronizingMessage );
//
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else { // recent message was not mine
//
//			possibleSynchronizingMessage = new ALastReceivedSequenceNumber (aReceivedBroadcast.getSequenceNumber());
//			Tracer.info(this, "Received relay of someone else's broadcast, generated last received sequence number message as possible synchronizing mesage:" + possibleSynchronizingMessage );
//
//		}
	}
	protected  void maybeProcessMyRelay(RelayedMessage aReceivedRelay) {
//		if (pendingSentBroadcasts.size() == 0)
//			return;
		BroadcastMessage firstPendingBroadcast = pendingSentBroadcasts.peek();
		// will the anded check not always return true
//		if (firstPendingBroadcast != null && firstPendingBroadcast.getIdentifier().equals(aReceivedRelay.getIdentifier())) {

		if (firstPendingBroadcast != null && firstPendingBroadcast.getIdentifier().equals(firstPendingBroadcast.getIdentifier())) {
			try {
				pendingSentBroadcasts.take(); // we know this will not blok
				
				Tracer.info(this, "Received relay of my broadcast, generated most recent echo message as possible synchronizing mesage:" + possibleSynchronizingMessage );

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { // recent message was not mine

			possibleSynchronizingMessage = new ALastReceivedSequenceNumber (aReceivedRelay.getSequenceNumber());
			Tracer.info(this, "Received relay of someone else's broadcast, generated last received sequence number message as possible synchronizing mesage:" + possibleSynchronizingMessage );

		}
	}
	
//	public List processBufferedReceivedRelayedMessage(SequencedMessage aReceivedBroadcast) {
//		maybeProcessRelayOfMyBroadcast(aReceivedBroadcast); // my broadcasts will be received in sequence
//		int aSequenceNumber = aReceivedBroadcast.getSequenceNunber();
//		if (getNextRelayedMessageNumber() > aSequenceNumber) {
//			return emptyList; // duplicate
//		}
//		if (getNextRelayedMessageNumber()  == aSequenceNumber) {		// expected	
//			List<SequencedMessage> aNewMessages = buffer.removeMessagesFrom(aReceivedBroadcast.getSequenceNunber() + 1);
//			aNewMessages.add(0, aReceivedBroadcast);
//			setNextRelayedMessageNumber(aNewMessages.get(aNewMessages.size() -1).getSequenceNunber() + 1);
//			return toActualMessages(aNewMessages);
//		} else { // early message
//			buffer.insertMessage(aReceivedBroadcast);
//			return emptyList;
//		}
//	}
	public List processReceivedRelayedMessage(RelayedMessage aReceivedRelay) {
		Tracer.info(this, "Received message from relay server:" + aReceivedRelay);
//		lastReceivedRelayedMessage = aReceivedBroadcast; // this is the last one, from anyone
		maybeProcessMyRelay(aReceivedRelay);
		generateSynchronizationMessage(aReceivedRelay); // my broadcasts will be received in sequence
		int aSequenceNumber = aReceivedRelay.getSequenceNumber();
		if (nextRelayedMessageNumber > aSequenceNumber) {
			Tracer.info(this, "Received duplicate message from relay server, my sequence number:" + nextRelayedMessageNumber);
			return emptyList; // duplicate, my message is re-echoed as it was rebroadcast for others
		}
		if (nextRelayedMessageNumber  == aSequenceNumber) {	
			// expected	
//			List<SequencedMessage> aNewMessages = buffer.removeMessagesFrom(aReceivedBroadcast.getSequenceNunber() + 1);
//			aNewMessages.add(0, aReceivedBroadcast);
//			setNextRelayedMessageNumber(aNewMessages.get(aNewMessages.size() -1).getSequenceNunber() + 1);
			nextRelayedMessageNumber++;
			Tracer.info(this, "Next relay message number:" + nextRelayedMessageNumber);


			return Arrays.asList(new Object[]{aReceivedRelay.getMessage()});
		} else { // early message
			Tracer.error("Received early message, expected number " + nextRelayedMessageNumber + " actual sequence number:" + aSequenceNumber );
//			buffer.insertMessage(aReceivedBroadcast);
			return emptyList;
		}
	}
//	public List toActualMessages(List<SequencedMessage> aSequencedMessages) {
//		List result = new ArrayList();
//		for (SequencedMessage aSequencedMessage:aSequencedMessages) {
//			result.add(aSequencedMessage.getMessage());
//		}
//		return result;
//	}
	
//	public List oldProcessReceivedMessage(String remoteEnd, Object serializable) {
//		if (serializable instanceof IdentifiableMessage) {
//			Object realMessage = ((IdentifiableMessage) serializable).getMessage();
//			if (FaultTolerantFiltererFactory.getOrCreateSingleton().isBroadcastMessage(sessionPort.getLocalName(), realMessage)) {
//				return processReceivedBroadcastMessage((BroadcastMessage) serializable);
//			} else if (serializable instanceof SequencedMessage && FaultTolerantFiltererFactory.getOrCreateSingleton().isRelayedMessage(sessionPort.getLocalName(), realMessage)) {
//				return processReceivedRelayedMessage((SequencedMessage) serializable);
//			} else {
//				System.err.println("Unexpected indentifiable message:" + serializable);
//				return emptyList;
//			}			
//		} else
//			return  Arrays.asList(new Object[] {serializable});
////		if (serializable instanceof SequencedMessage) {
////			return processReceivedRelayedMessage((SequencedMessage) serializable);
////			
////		} 
////		 else if (serializable instanceof IdentifiableMessage) {
////			return processReceivedBroadcastMessage((IdentifiableMessage) serializable);
////		}
////		 else 
////			 return  Arrays.asList(new Object[] {serializable});
//	}
	protected synchronized List processReceivedSynchronizingMessage(String remoteEnd, SynchronizingMessage aSynchronizingMessage) {
		numberOfPendingSynchronizingMessages --;
		nameToSynchronizingMessage.put(remoteEnd, aSynchronizingMessage);
		Tracer.info(this, "Received from " + remoteEnd + " synchronizing message:"  + aSynchronizingMessage + " pending synchronizing messages:" + 0);

		return maybeFinishSynchronizing();
//		return emptyList;
//		int aSequenceNumber = aSynchronizingMessage.getSequenceNumber();
//		if (aSequenceNumber == nextGeneratedMessageNumber + 1) // missed broadcast, assume it just arrived
//			return processReceivedMessage(remoteEnd, aSynchronizingMessage.getMessage());
//		else {
//			return emptyList;
//		}
		
	}
	protected synchronized List processReceivedFinishedSynchronizingMessage(String remoteEnd, FinishedSynchronizingMessage aSynchronizingMessage) {
		Tracer.info(this, "Received finished synchronizing message from relay server");
		sendPendingMessages();
		return emptyList;
	}
	@Override
	public synchronized List processReceivedMessage(DeserializingForwarder aSourceTrapper, String remoteEnd, Object serializable) {
		if (serializable instanceof BroadcastMessage) {
				return processReceivedBroadcastMessage((BroadcastMessage) serializable);
		} else if (serializable instanceof RelayedMessage) {
				return processReceivedRelayedMessage((RelayedMessage) serializable);
		} else if (serializable instanceof SynchronizingMessage) {
				return processReceivedSynchronizingMessage(remoteEnd, (SynchronizingMessage) serializable);
		} else if (serializable instanceof FinishedSynchronizingMessage) {
			 return processReceivedFinishedSynchronizingMessage(remoteEnd, (FinishedSynchronizingMessage) serializable);
		} else
		
		{
			return  Arrays.asList(new Object[] {serializable});
		}
//		if (serializable instanceof SequencedMessage) {
//			return processReceivedRelayedMessage((SequencedMessage) serializable);
//			
//		} 
//		 else if (serializable instanceof IdentifiableMessage) {
//			return processReceivedBroadcastMessage((IdentifiableMessage) serializable);
//		}
//		 else 
//			 return  Arrays.asList(new Object[] {serializable});
	}

//	@Override
//	public synchronized List processSentBroadcastMessage(Object aMessage) {
	/*
	 * Sending thread on local machine
	 */
	public synchronized List processSentBroadcastMessage(Object aMessage) {
//		while (synchronizing) {
//			try {
//				Tracer.info(this, "Message sender waiting for synchronization phase to end");
//				wait();
//				Tracer.info(this, "Message sender unblocked as synchronization phase ended");
//
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		BroadcastMessage aBroadcastMessage = getBroadcastMessage(aMessage);
		Tracer.info(this, "Local client sending broadcast request to server:" + aBroadcastMessage);

		// record before sending just in case response received before recording
		try {
			pendingSentBroadcasts.put(aBroadcastMessage); // ok to block sender if the queue is full
			Tracer.info(this, "Queued broadcast message in pending list to match echoed reply");;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		notify(); // any other thread that is waiting
		if (synchronizing) {
			Tracer.info(this, "Broadcast not sent to relayer as syncronization phase is on");

			return emptyList; // do not send now, send it after all pending messages are sent
		}
		return Arrays.asList(new Object[] {aBroadcastMessage});
	}
	@Override
	public synchronized List processSentMessage (SerializingForwarder aSource, String remoteName, Object aMessage) {
		if (FaultTolerantFiltererFactory.getOrCreateSingleton().isBroadcastCall(remoteName, aMessage)) {
//			String anId = FaultToleranceManagerFactory.getSingleton().getId(aMessage);
//			IdentifiableMessage aIdentifiableMessage = FaultToleranceManagerFactory.getSingleton().getIdentifiableMessage(aMessage);
//			// record before sending just in case response received before recording
//			FaultToleranceManagerFactory.getSingleton().setLastIdentifiableMessageSent(aIdentifiableMessage);
			return processSentBroadcastMessage(aMessage);
			
			} else if (FaultTolerantFiltererFactory.getOrCreateSingleton().isRelayCall(remoteName, aMessage)) {
//				SequencedMessage aSequencedMesage = FaultToleranceManagerFactory.getSingleton().getSequencedMessage(aMessage);
				return processSentRelayedMessage(remoteName, aMessage);
			} else 
				return Arrays.asList(new Object[]{aMessage});
	}
	/*
	 * sender and receiver threads can be different and on same computer,
	 * this is the method invoker thread which received the call and is sending the message
	 * this is the wrapped message sent by relayer to the clients, who will unwrap it
	 * this can be the local sending thread (can it), or is it always the method invoker
	 */
	public synchronized List processSentRelayedMessage(String aRemoteName, Object aMessage) {
//		if (synchronizing && !clientsNeedingSynchronizing.contains(aRemoteName))
//			return emptyList;
//		System.out.println ("Sent relayed:" + Thread.currentThread());
		RelayedMessage aRelayedMessage = getRelayedMessage(aMessage);
		// record before sending just in case response received before recording
		numPendingRelays--;
		Tracer.info(this, "sending to client:" + aRemoteName + " relay result " + aRelayedMessage + " pending relays:" + numPendingRelays);


		if (numPendingRelays <= 0) {
			Tracer.info(this, "All relay results of previous relay request sent, notifying any blocked new sent relay requests");
			if (waiting)
//			System.out.println ("Notifying waitinhg thread:" + Thread.currentThread());
			notifyAll(); // anyone waiting should go
		}
		return Arrays.asList(new Object[] {aRelayedMessage});
	}
	
	public RelayedMessage getRelayedMessage (Object aMessage) {
		if (lastReceivedBroadcast == null) {
			Tracer.error("Sequencing a message not received");
			return null;
		}		
		return new ARelayedMessage(aMessage, lastReceivedBroadcast.getIdentifier(), nextGeneratedMessageNumber, lastReceivedBroadcast.getMessage());
	}
	// what if we are sending our own message when this happened, but that will not fail
	
	// do not need this because of synchronizing messages
//	protected void haveBecomeRelayer() {
//		nextGeneratedMessageNumber = nextRelayedMessageNumber - 1; // they are always off by one
//		
//	}
//	
	
	
//	public boolean processConnectionChange(List<String> relayerNames) {
//		boolean aNewRelayer = super.processConnectionChange(relayerNames);
//		if (!aNewRelayer)
//			return aNewRelayer;
//		if (getCurrentRelayerName().equals(sessionPort.getLocalName())) {
//			haveBecomeRelayer();
//		}
//		return aNewRelayer;
////		return getCurrentRelayer();
//		
//	
//   }
	
	
	
	public  void  sendSynchronizingMessage() {
//		SynchronizingMessage aSynchronizingMessage = null;
//		if (mostRecentEcho == null)
//			aSynchronizingMessage = ANoMostRecentEcho.SINGLETON;
//		else
//			aSynchronizingMessage =  new AMostRecentEcho(
//					mostRecentEcho.getMessage(), mostRecentEcho.getIdentifier(), mostRecentEchoSequenceNumber);
//		SynchronizingMessage message = new ASynchronizingMessage(
//				lastReceivedRelayedMessage.getMessage(), lastReceivedRelayedMessage.getIdentifier(), lastReceivedRelayedMessage.getSequenceNumber());
		Tracer.info(this, "Sending to new  relayer synchronizing message:" + possibleSynchronizingMessage );
		sessionPort.send(getCurrentRelayerName(), possibleSynchronizingMessage);		
	}
//	public   void  sendPendingMessage(BroadcastMessage aPendingMessage) {
//		while (!pendingSentBroadcasts.isEmpty()) {
//			sessionPort.send(getCurrentRelayerName(), aPendingMessage.getMessage());			
//		}
//		synchronizing = false;
//	}
	/*
	 * sycnrhronized so that the queue is stable, do not need to use take
	 * this is the receiving thread (Selector) that received the synchronization message
	 */
//	public synchronized  void  sendPendingMessages() {
//	Tracer.info(this, "Sending pending messages");
	public  synchronized void  sendPendingMessages() {
		while (!pendingSentBroadcasts.isEmpty()) {
				BroadcastMessage aPendingBroadcast = pendingSentBroadcasts.remove();
				Tracer.info(this, "Sending pending broadcast:" + aPendingBroadcast);

				sessionPort.send(aPendingBroadcast);
			
			
		}
		synchronizing = false;
		Tracer.info(this, "Synchronization phase ends");

	}
	// doing a system broadcast, selection thread
    protected List maybeFinishSynchronizing() {
    	if (numberOfPendingSynchronizingMessages == 0) {
    		Tracer.info(this, "Received all pending synchronizing messages");

    		 List retVal = processSynchronizingMessages();
    		FinishedSynchronizingMessage aFinishedSynchronizingMessage = new AFinishedSynchronizingMessage();
    		for (String aRelayerName:getRelayerNames()) {
    			Tracer.info(this, "Sending finsished snchronizing message to:" + aRelayerName);
    			// if same thread does the send then it will block for the pendng relays, and never return value to 
    			// forwarder, the same forwarder will be reentrant
    			// sending thread must not receive in ADuplexServerInputPort
    			sessionPort.send(aRelayerName, aFinishedSynchronizingMessage );
    		}
    		return retVal;
    	}
    	return emptyList;
	}
	protected MostRecentRelay getMaxRecentRelay() {
//		int aMaxSequenceNumber = -1;
		MostRecentRelay result = null;
		for (SynchronizingMessage aSynchronizingMessage:nameToSynchronizingMessage.values()) {
			if (aSynchronizingMessage instanceof MostRecentRelay &&
			(result == null ||  
			((MostRecentRelay) aSynchronizingMessage).getSequenceNumber() > result.getSequenceNumber())) {
				result = (MostRecentRelay) aSynchronizingMessage;
			}			
		}
		return result;
	}
	
	/*
	 * selection thread that sent the synchronizing messae
	 */
    protected List processSynchronizingMessages() {
    	MostRecentRelay aMaxRecentRelay = getMaxRecentRelay();
    	if (aMaxRecentRelay == null) // no one has received anything, nothing to do
    		return emptyList;
    	boolean aSomeClientNeedsSynchronizing = false;
    	
//    	RelayedMessage aRelayBackMesage = new ARelayedMessage(
//    			aMaxSynchronizingMessage.getMessage(), 
//    			aMaxSynchronizingMessage.getIdentifier(), 
//    			aMaxSynchronizingMessage.getSequenceNumber());
    	for (String aDestination:nameToSynchronizingMessage.keySet()) {
    		SynchronizingMessage aSynchronizingMessage = nameToSynchronizingMessage.get(aDestination);
    		// difference shoudl not be less than 1
    		if (aSynchronizingMessage.getSequenceNumber() < aMaxRecentRelay.getSequenceNumber()) {
//    			sessionPort.send(aDestination, aRelayBackMesage); // send back the wrapped message so it does not bounce back
//    			clientsNeedingSynchronizing.add(aDestination);
    			aSomeClientNeedsSynchronizing = true;
    		} 
//    		else {
//    			aSourceOfMaxSynchronizingMessage = aDestination; //take this out
//    		}
    	}
    	if (aSomeClientNeedsSynchronizing) {
    		Tracer.info(this, "Some client needs synchronizing of partially relayed message:" + aMaxRecentRelay);
    		// last sender of synchronizing message will be the source! Should not matter
    		nextGeneratedMessageNumber = aMaxRecentRelay.getSequenceNumber() -1; // this will get incremented by next call

    		return generateRelayRequest(
    				new ABroadcastMessage(aMaxRecentRelay.getMessage(), aMaxRecentRelay.getIdentifier()));
    				
    	
//    		lastReceivedBroadcast = new ABroadcastMessage(aMaxSynchronizingMessage.getMessage(), aMaxSynchronizingMessage.getIdentifier());
//    		// the relayed version of this message will be labelled with next Generated number + 1
//    		nextGeneratedMessageNumber = aMaxSynchronizingMessage.getSequenceNumber(); 
//    		numPendingRelays = getCurrentRelayerNames().size();
//    		Tracer.info(this, "Forwarding to relayer synchronizing message:" + aMaxSynchronizingMessage + "pending relays =" + numPendingRelays);
//    		return Arrays.asList(new Object[]{aMaxSynchronizingMessage.getMessage()});

    		
    	} else {
    		nextGeneratedMessageNumber = aMaxRecentRelay.getSequenceNumber(); // will increment this for the next round
    	}
    	Tracer.info(this, "no client needs synchronization");
    	return emptyList;
//    	nextGeneratedMessageNumber = aMaxSynchronizingMessage.getSequenceNumber();
    	
    	
    	
    }
	
	
	
	
	/*
	 * Synchronized as changing shared variable synchronizing
	 * @see gipc.session.relayingclient.ARelayerElector#processJoin(java.lang.String)
	 */
	protected  synchronized void initJoinData(String aRelayerName) {
		Tracer.info(this, "Synchronizing phase starts, joining relayer:" + aRelayerName);
		synchronizing = true;
		numberOfPendingSynchronizingMessages = getRelayerNames().size();
		nameToSynchronizingMessage.clear();
	}
//	public  void processJoin(String aRelayerName) {	
   // asynchronous call invoker, do not want it to block on sends do we
	@Override
	public synchronized  void  processJoin(String aRelayerName) {	

//	public synchronized  void  processJoin(String aRelayerName) {	
		synchronized (this) {
//		System.out.println("join phase starts" + Thread.currentThread());
		Tracer.info(this, "join phase starts");
		
		if (nextRelayedMessageNumber == 0) {	// startup phase 
			Tracer.info(this, "Startup phase, joining relayer:" + aRelayerName);
			return;
		}
		Tracer.info(this, "Synchronizing phase starts, joining relayer:" + aRelayerName);
		synchronizing = true;
		numberOfPendingSynchronizingMessages = getRelayerNames().size();
		nameToSynchronizingMessage.clear();
		}
//		clientsNeedingSynchronizing.clear();
		sendSynchronizingMessage();
		
		Tracer.info(this, "join phase ends");
//		System.out.println("join phase ends" + Thread.currentThread());


//		SequencedMessage aStatusMessage;
//		if (lastBroadcast != null && lastBroadcastAcked) {
//			aStatusMessage = new ASequencedMessage(lastBroadcast.getMessage(), lastBroadcast.getIdentifier(), lastBroadcastSequenceNumber);
//		} else {
//			aStatusMessage = new ASequencedMessage(null, "", -1);
////			ObjectTranslatingIPTrapperSelector.getTrapperSelector().getSendTrapperFactory(
//		}
		
	}
}
