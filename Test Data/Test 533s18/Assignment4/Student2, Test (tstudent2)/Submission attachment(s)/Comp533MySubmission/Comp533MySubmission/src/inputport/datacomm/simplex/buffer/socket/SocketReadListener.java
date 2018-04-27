package inputport.datacomm.simplex.buffer.socket;

import java.net.Socket;
import java.nio.ByteBuffer;


public interface SocketReadListener {
	void socketRead(Socket aSocket, ByteBuffer aMessage);
	
}
