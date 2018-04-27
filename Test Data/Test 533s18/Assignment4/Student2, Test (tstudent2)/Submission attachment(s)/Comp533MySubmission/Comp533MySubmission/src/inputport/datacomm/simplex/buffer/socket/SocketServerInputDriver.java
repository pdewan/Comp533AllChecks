package inputport.datacomm.simplex.buffer.socket;

import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;

import java.net.ServerSocket;
import java.net.Socket;


public interface SocketServerInputDriver extends SimplexBufferServerInputPortDriver<ServerSocket, Socket>, SocketReadListener, SocketCloseListener, 
SocketAcceptListener{

}
