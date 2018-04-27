package inputport.datacomm.simplex.buffer.socket;
import inputport.datacomm.simplex.buffer.SimplexServerInputPortSkeleton;

import java.io.EOFException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;




public class ASocketServerInputDriver implements SocketServerInputDriver {
	SimplexServerInputPortSkeleton<ServerSocket, Socket> skeleton;
	protected SocketAcceptingRunnable acceptingRunnable;
	int portNumber;
	ServerSocket serverSocket;	
	String serverId;




	//InetAddress localHost;
	public ASocketServerInputDriver (String thePortId) {
		portNumber = Integer.parseInt(thePortId);
	}
	@Override
	public void connect() {
		serverSocket = createSocket();
		if (serverSocket != null)
			startAcceptThread();
	
	}
	
	ServerSocket createSocket() {
		try {			
			//localHost = InetAddress.getLocalHost();
			ServerSocket retVal = new ServerSocket();
			InetSocketAddress isa = new InetSocketAddress(portNumber);
			retVal.bind(isa);
			return retVal;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void disconnect() {
		closeRequestChannel();
	}	
	void startAcceptThread() {
		acceptingRunnable = new ASocketAcceptingRunnable(this, serverSocket);
		Thread thread = new Thread (acceptingRunnable);
		thread.setName("Socket Accepting Thread for  " + serverSocket);
		thread.start();
	}
	@Override
	public void setSkeleton(SimplexServerInputPortSkeleton theSkeleton) {
		skeleton = theSkeleton;
		
	}
	
	@Override
	public void disconnect(Socket aSocket) {
		try {
			aSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void socketRead(Socket socket, ByteBuffer message) {
		skeleton.messageReceived(socket, message);
		
	}
	@Override
	public void socketClosed(Socket socket, Exception theReadException) {
		try {
			Thread readingThread = acceptingRunnable.getReadingThread(socket);
			if (readingThread != null) {
				readingThread.interrupt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		skeleton.disconnected (skeleton.getClientName(socket), theReadException instanceof EOFException, theReadException.getMessage(), null);
		
	}
	@Override
	public void socketAccepted(ServerSocket serverSocket, Socket socket) {
		
	}
	@Override
	public void socketNotAccepted(ServerSocket socket, Exception e) {
		
	}
	
	void closeRequestChannel() {
		try {
			serverSocket.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
