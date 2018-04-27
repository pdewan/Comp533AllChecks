package inputport.datacomm.duplex.object.explicitreceive;


import inputport.datacomm.duplex.object.explicitreceive.AReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitReceiveListener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import util.trace.Tracer;
import util.trace.port.objects.ReceivedMessageDequeued;
import util.trace.port.objects.ReceivedMessageQueueCreated;
import util.trace.port.objects.ReceivedMessageQueued;

/**
 * This should really tke client name as an argument and receive messages
 * from it only
 * 
 */

public class ASyncReceiveListener<MessageType> implements ExplicitReceiveListener<MessageType> {
	BlockingQueueWithBlockedThreadCount<ReceiveReturnMessage<MessageType>> receivedValueQueue = new ABlockingQueueWithBlockedThreadCount<>();
	String remoteEnd;	
	public ASyncReceiveListener(String aRemoteEnd) {
		ReceivedMessageQueueCreated.newCase(this, receivedValueQueue);
		remoteEnd = aRemoteEnd;
		
	}
	public boolean hasBlockedThread() {
		return receivedValueQueue.getNumBlockedThreads() > 0;
	}

	@Override
	public  void  messageReceived(String aRemoteEnd, MessageType aMessage) {
		try {
			if (remoteEnd != null && !aRemoteEnd.equals(aRemoteEnd)) {
				return; // will not queue the message
			}
			Tracer.info("Sync message received");
			receivedValueQueue.put(new AReceiveReturnMessage(aRemoteEnd, aMessage));
			ReceivedMessageQueued.newCase(this, receivedValueQueue, aMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public ReceiveReturnMessage<MessageType> getReceivedMessage() {
		try {
			Tracer.info("waiting for received message");
			ReceiveReturnMessage<MessageType> retVal = receivedValueQueue.take();
			ReceivedMessageDequeued.newCase(this, receivedValueQueue, retVal);
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}



}
