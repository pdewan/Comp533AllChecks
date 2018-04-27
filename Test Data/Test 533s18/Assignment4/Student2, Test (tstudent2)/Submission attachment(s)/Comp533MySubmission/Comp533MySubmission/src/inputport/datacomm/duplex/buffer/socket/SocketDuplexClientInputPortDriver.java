package inputport.datacomm.duplex.buffer.socket;

import inputport.datacomm.duplex.buffer.DuplexClientInputPortDriver;
import inputport.datacomm.simplex.buffer.socket.SocketClientInputDriver;
import inputport.datacomm.simplex.buffer.socket.SocketReadListener;

import java.net.Socket;


public interface SocketDuplexClientInputPortDriver 
extends  DuplexClientInputPortDriver<Socket>, SocketClientInputDriver, 
SocketReadListener {
//	void setSkeleton(BufferGenericDuplexClientInputPort<Socket> theSkeleton);	

}
