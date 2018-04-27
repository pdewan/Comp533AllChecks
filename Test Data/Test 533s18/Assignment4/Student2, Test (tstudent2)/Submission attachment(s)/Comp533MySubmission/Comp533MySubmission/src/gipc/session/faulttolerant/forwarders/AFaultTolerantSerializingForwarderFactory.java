package gipc.session.faulttolerant.forwarders;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.object.ASerializingForwarderFactory;
import inputport.datacomm.simplex.object.SerializingForwarder;

public class AFaultTolerantSerializingForwarderFactory extends ASerializingForwarderFactory {
	SerializingForwarder lastSendTrapper;
	@Override
	public SerializingForwarder createSendTrapper(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
//		lastSendTrapper = new  AFaultTolerantSerializingForwarder(anInputPort, aDestination);
		return new AFaultTolerantSerializingForwarder(anInputPort, aDestination);
//		return lastSendTrapper;

	}
//	@Override
//	public SendTrapper<Object, ByteBuffer> getLastSendTrapper() {
//		return lastSendTrapper;
//	}


}
