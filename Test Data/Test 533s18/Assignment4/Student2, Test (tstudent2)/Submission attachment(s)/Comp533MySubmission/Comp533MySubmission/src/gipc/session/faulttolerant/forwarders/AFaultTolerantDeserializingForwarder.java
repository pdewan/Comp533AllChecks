package gipc.session.faulttolerant.forwarders;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import gipc.session.faulttolerant.FaultToleranceManagerFactory;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.object.ADeserializingForwarder;
import port.delay.MessageWithDestination;
import util.trace.Tracer;

public class AFaultTolerantDeserializingForwarder extends ADeserializingForwarder implements Runnable {
	BlockingQueue<MessageWithDestination> receiveQueue = new LinkedBlockingDeque<>();


	public AFaultTolerantDeserializingForwarder(ReceiveNotifier aReceiveNotifier) {
		super(aReceiveNotifier);
//		Thread aThread = new Thread(this);
//		aThread.setName("Fault Tolerant Received");
//		aThread.start();
	}

	public void notifySerializable(String remoteEnd, Object serializable) {
//		if (remoteEnd.equals("Session Server"))
//			doNotifySerializable(remoteEnd, serializable);
//		else
//		receiveQueue.add(new AMessageWithDestination(serializable, remoteEnd));
		doNotifySerializable(remoteEnd, serializable);

//		List<Object> aProcessedMessages = 
//				FaultToleranceManagerFactory.getSingleton().
//				   processReceivedMessage(null, remoteEnd, serializable);
//		for (Object aProcessedMessage:aProcessedMessages) {
//		
//		   super.notifySerializable(remoteEnd, aProcessedMessage);
//		}
	}
	
	public void doNotifySerializable(String remoteEnd, Object serializable) {
		Tracer.info(this, "Forwarding to fault tolerance manager:" + serializable);
		List<Object> aProcessedMessages = 
				FaultToleranceManagerFactory.getSingleton().
				   processReceivedMessage(null, remoteEnd, serializable);

		for (Object aProcessedMessage:aProcessedMessages) {
			Tracer.info(this, "Forwarding to destination:" + serializable);

		   super.notifySerializable(remoteEnd, aProcessedMessage);
		}
	}


	@Override
	public void run() {
		try {
			MessageWithDestination aMesageWithDestination = receiveQueue.take();
			doNotifySerializable(aMesageWithDestination.getDestination(), aMesageWithDestination.getData());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
