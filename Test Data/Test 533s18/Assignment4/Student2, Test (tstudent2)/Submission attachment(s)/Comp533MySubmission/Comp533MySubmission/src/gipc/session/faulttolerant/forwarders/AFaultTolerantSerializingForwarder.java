package gipc.session.faulttolerant.forwarders;

import java.nio.ByteBuffer;
import java.util.List;

import gipc.session.faulttolerant.FaultToleranceManagerFactory;
import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.object.ASerializingForwarder;
import util.trace.Tracer;

public class AFaultTolerantSerializingForwarder extends ASerializingForwarder {
	

	public AFaultTolerantSerializingForwarder(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
		super(anInputPort, aDestination);
	}
	
	public void send(String remoteName, Object aMessage) {
//		Object anActualSentMessage = aMessage;
		Tracer.info(this, "Forwarding to fault tolerance manager:" + aMessage);

		List<Object> aProcessedMessages = FaultToleranceManagerFactory.getSingleton().processSentMessage(null, remoteName, aMessage);
		for (Object aProcessedMessage: aProcessedMessages) {
			super.send(remoteName, aProcessedMessage);
			Tracer.info(this, "Forwarding to destination:" + aMessage);

		}
//		if (FaultTolerantFiltererFactory.getOrCreateSingleton().isBroadcastMessage(remoteName, aMessage)) {
////		String anId = FaultToleranceManagerFactory.getSingleton().getId(aMessage);
////		IdentifiableMessage aIdentifiableMessage = FaultToleranceManagerFactory.getSingleton().getIdentifiableMessage(aMessage);
////		// record before sending just in case response received before recording
////		FaultToleranceManagerFactory.getSingleton().setLastIdentifiableMessageSent(aIdentifiableMessage);
//		anActualSentMessage = FaultToleranceManagerFactory.getSingleton().processSentBroadcastMessage(aMessage);
//		if (anActualSentMessage == null) // means do not send
//			return; 
//		} else if (FaultTolerantFiltererFactory.getOrCreateSingleton().isRelayedMessage(remoteName, aMessage)) {
////			SequencedMessage aSequencedMesage = FaultToleranceManagerFactory.getSingleton().getSequencedMessage(aMessage);
//			anActualSentMessage = FaultToleranceManagerFactory.getSingleton().processSentRelayedMessage(aMessage);
//		}
	}

}
