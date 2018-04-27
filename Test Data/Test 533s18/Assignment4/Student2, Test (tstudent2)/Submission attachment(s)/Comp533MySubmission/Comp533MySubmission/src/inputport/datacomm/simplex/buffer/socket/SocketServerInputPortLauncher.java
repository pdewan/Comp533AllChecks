package inputport.datacomm.simplex.buffer.socket;

import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;

import java.nio.ByteBuffer;

import extraip.BufferServerInputPortLauncher;


public class SocketServerInputPortLauncher {
	public static void main (String[] args) {
		SimplexInputPortFactory<ByteBuffer> untypedInputPortFactory = new ASocketInputPortFactory();
		SimplexBufferInputPortSelector.setSimplexBufferInputPortFactory(untypedInputPortFactory);
		BufferServerInputPortLauncher.launch(args);
	}

}
