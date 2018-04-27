package inputport.datacomm.simplex.buffer.socket;

import java.net.ServerSocket;
import java.net.Socket;

public interface SocketAcceptListener {
	public void socketAccepted(ServerSocket aServerSocket, Socket aSocket);	
	public void socketNotAccepted(ServerSocket aSocket, Exception e);

}
