package inputport.datacomm.duplex.buffer.socket;

import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.datacomm.simplex.buffer.socket.SocketServerInputDriver;

import java.net.ServerSocket;
import java.net.Socket;

public interface SocketDuplexServerInputPortDriver extends SocketServerInputDriver, DuplexServerInputPortDriver<ServerSocket, Socket> {
//	 void send(Socket aSocket, ByteBuffer message);
}
