package nio.manager.simulation.client;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import nio.manager.simulation.common.AChannelByteBuffer;
import nio.manager.simulation.common.ChannelByteBuffer;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.port.nio.RemoteCommandExecuted;

public class AnNIOManagerSimulationClientInCoupler implements NIOManagerSimulationInCoupler{
	HalloweenCommandProcessor commandProcessor;
	int numReceivedCommands = 0;
	public static int MAX_QUEUE_SIZE = 2000;
	BlockingQueue<ChannelByteBuffer> messageQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
	public AnNIOManagerSimulationClientInCoupler(HalloweenCommandProcessor aCommandProcessor) {
		commandProcessor = aCommandProcessor;
	}


	@Override
	public void socketChannelRead(SocketChannel aSocketChannel,
			ByteBuffer aMessage, int aLength) {
		if (messageQueue.size() >= MAX_QUEUE_SIZE) {
			System.err.println("Message queue full, ignoring message");
			return;
		}
		messageQueue.add(new AChannelByteBuffer(aSocketChannel, aMessage));
//		doRead(aSocketChannel, aMessage);
//		 String aCommand = new String(aMessage.array(), aMessage.position(), aLength);
//		 RemoteCommandExecuted.newCase(this, aCommand);
//		 commandProcessor.processCommand(aCommand); // make this is a thread
	}
	
	protected void doRead(SocketChannel aSocketChannel,
			ByteBuffer aMessage) {
		String aCommand = new String(aMessage.array(), aMessage.position(), aMessage.remaining());
		 RemoteCommandExecuted.newCase(this, aCommand);
		 commandProcessor.processCommand(aCommand); // make this is a thread
	}
	
	@Override
	public void run() {
		while (true) {
			try {
			ChannelByteBuffer aMessage = messageQueue.take();
			doRead(aMessage.getSocketChannel(), aMessage.getByteBuffer());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
