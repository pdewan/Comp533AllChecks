package inputport.datacomm.simplex.buffer.socket;

import java.net.Socket;


public interface SocketCloseListener {
	void socketClosed(Socket aSocket, Exception theReadException);
	
}
