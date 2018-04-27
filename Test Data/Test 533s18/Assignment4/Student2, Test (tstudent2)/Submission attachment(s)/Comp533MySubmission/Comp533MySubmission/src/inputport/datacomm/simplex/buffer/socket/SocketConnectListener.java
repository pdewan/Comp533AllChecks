package inputport.datacomm.simplex.buffer.socket;

import java.net.Socket;

public interface SocketConnectListener {
	public void connected(Socket aSocket);	
	public void notConnected(Socket aSocket, Exception e);
}
