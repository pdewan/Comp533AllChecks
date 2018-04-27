package inputport.datacomm.simplex.buffer.socket;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;

import java.net.Socket;


public interface SocketClientInputDriver extends SimplexBufferClientInputPortDriver<Socket>, SocketCloseListener {

}
