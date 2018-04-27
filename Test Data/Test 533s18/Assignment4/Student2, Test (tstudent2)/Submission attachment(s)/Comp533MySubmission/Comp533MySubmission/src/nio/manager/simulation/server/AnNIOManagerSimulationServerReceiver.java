package nio.manager.simulation.server;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class AnNIOManagerSimulationServerReceiver implements NIOManagerSimulationServerReceiver{
	NIOManagerSimulationServerMulticaster multicaster;
	public AnNIOManagerSimulationServerReceiver(NIOManagerSimulationServerMulticaster aMulticaster) {
		multicaster = aMulticaster;
	}
	@Override
	public void socketChannelRead(SocketChannel aSocketChannel,
			ByteBuffer aMessage, int aLength) {
		multicaster.multicast(aSocketChannel, aMessage);
		
	}

}
