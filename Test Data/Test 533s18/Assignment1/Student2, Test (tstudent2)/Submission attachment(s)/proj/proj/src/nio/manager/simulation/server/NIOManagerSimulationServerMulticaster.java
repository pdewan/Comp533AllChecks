package nio.manager.simulation.server;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import assignments.util.inputParameters.SimulationParametersListener;

public interface NIOManagerSimulationServerMulticaster extends Runnable, SimulationParametersListener{
	void multicast(SocketChannel aWriter, ByteBuffer aByteBuffer);
	void join(SocketChannel aSocketChannel);

}
