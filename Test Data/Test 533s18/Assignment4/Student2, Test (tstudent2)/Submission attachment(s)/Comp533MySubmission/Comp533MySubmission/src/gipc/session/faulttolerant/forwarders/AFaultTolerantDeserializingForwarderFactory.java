package gipc.session.faulttolerant.forwarders;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.object.ADeserializingForwarderFactory;
import inputport.datacomm.simplex.object.DeserializingForwarder;

public class AFaultTolerantDeserializingForwarderFactory extends ADeserializingForwarderFactory {
	
	@Override
	public DeserializingForwarder createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> receiveRegistrarAndNotifier) {
		return new AFaultTolerantDeserializingForwarder (receiveRegistrarAndNotifier);
	}
}
