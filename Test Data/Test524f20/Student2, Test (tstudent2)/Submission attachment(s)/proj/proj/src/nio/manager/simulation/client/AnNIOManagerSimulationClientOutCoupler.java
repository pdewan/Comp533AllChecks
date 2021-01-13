package nio.manager.simulation.client;

import java.beans.PropertyChangeEvent;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.NIOManagerFactory;
import util.trace.Tracer;
import util.trace.port.nio.LocalCommandObserved;

public class AnNIOManagerSimulationClientOutCoupler implements NIOManagerSimulationOutCoupler {
	SocketChannel socketChannel;
	boolean localProcessingOnly = false;
	
//	int numSentCommands = 0;
	
	public AnNIOManagerSimulationClientOutCoupler (SocketChannel aSocketChannel) {
		socketChannel = aSocketChannel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (!anEvent.getPropertyName().equals("InputString")) return;
		if (localProcessingOnly) {
			return;
		}
		String newCommand = (String) anEvent.getNewValue();
		LocalCommandObserved.newCase(this, newCommand);		
		Tracer.info(this, " Sending command:" + newCommand);
		ByteBuffer aByteBuffer = ByteBuffer.wrap(newCommand.getBytes());
		NIOManagerFactory.getSingleton().write(socketChannel, aByteBuffer);		

	}

	@Override
	public void localProcessingOnly(boolean newVal) {
		localProcessingOnly = newVal;		
	}

	

}
