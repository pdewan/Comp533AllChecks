package port.old;

import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.buffer.socket.ASocketDuplexBufferInputPortFactory;

import java.nio.ByteBuffer;

public class SocketDuplexClientInputPortLauncher {
	public static void main(String args[]) {
		DuplexInputPortFactory<ByteBuffer> untypedInputPortFactory = new ASocketDuplexBufferInputPortFactory();
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(untypedInputPortFactory);
		BufferDuplexClientInputPortLauncher.launch(args);
	}

}
