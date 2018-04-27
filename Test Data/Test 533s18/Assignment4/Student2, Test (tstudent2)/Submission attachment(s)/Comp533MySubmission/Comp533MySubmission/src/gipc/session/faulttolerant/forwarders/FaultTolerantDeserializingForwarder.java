package gipc.session.faulttolerant.forwarders;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveTrapper;

public interface FaultTolerantDeserializingForwarder extends ReceiveTrapper<ByteBuffer, Object> {

}
