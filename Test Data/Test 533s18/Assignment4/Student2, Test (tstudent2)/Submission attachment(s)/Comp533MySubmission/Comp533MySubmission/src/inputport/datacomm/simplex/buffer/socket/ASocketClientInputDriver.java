package inputport.datacomm.simplex.buffer.socket;

import inputport.ConnectionType;
import inputport.datacomm.simplex.buffer.SimplexClientInputPortSkeleton;
import inputport.nio.manager.AScatterGatherSelectionManager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import util.trace.Tracer;


public class ASocketClientInputDriver  implements SocketClientInputDriver {
	InetAddress host;
	int port;
	protected String serverName;
	protected Socket socket;
	OutputStream socketOutputStream;
	SimplexClientInputPortSkeleton clientInputPortSkeleton;
	int sendId = 0;
	ByteBuffer 	headerBuffer = ByteBuffer.allocate(AScatterGatherSelectionManager.BYTES_IN_INT);

	public ASocketClientInputDriver(String theRemoteHostName, String theRemotePort, String aServerName) {
		try {
			host = InetAddress.getByName(theRemoteHostName);
			port = Integer.parseInt(theRemotePort);
			serverName = aServerName;			
//			headerBuffer.rewind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setSkeleton(SimplexClientInputPortSkeleton<Socket> theSkeleton) {
		clientInputPortSkeleton = theSkeleton;		
	}
	
	
	@Override
	public SimplexClientInputPortSkeleton<Socket> getSkeleton() {
		return clientInputPortSkeleton;
	}

	@Override
	public void connect() {
		try {
			socket = new Socket(host, port);
			socketOutputStream = socket.getOutputStream();
			connected(socket);
		} catch (Exception e) {
			e.printStackTrace();
			notConnected(socket, e);
//			clientInputPortSkeleton.notifyConnectFailure(serverName, e.getMessage());
		}		
	}	

	@Override
	public void send(String aDestination, ByteBuffer message) {
		try {
			int messageSize = message.limit() - message.position();
			headerBuffer.putInt(messageSize);
			headerBuffer.rewind();
			Tracer.info(this, "Sending messsage: " + headerBuffer + " to " + socket);
			socketOutputStream.write(headerBuffer.array(), headerBuffer.position(), headerBuffer.limit());
			Tracer.info(this, "Sending messsage: " + message + " to " + socket);

			socketOutputStream.write(message.array(), message.position(), message.limit());
			clientInputPortSkeleton.messageSent(aDestination, message, sendId);
			sendId++;
		} catch (Exception e) {
			socketClosed(socket, e);
			e.printStackTrace();
		}	
	}


//	@Override
	protected void connected(Socket aSocket) {
		clientInputPortSkeleton.connected(serverName, ConnectionType.TO_SERVER);
		
	}
//	@Override
	void notConnected(
			Socket aSocket, Exception e) {
		clientInputPortSkeleton.notConnected(serverName, e.getMessage(), null);
		
	}

	@Override
	public void disconnect() {
		if (socket == null) return; //maybe the socket did not get connected
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
//	@Override
//	public void close(Socket channel) {
//		try {
//			channel.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	@Override
	public void socketClosed(Socket socket, Exception e) {
		clientInputPortSkeleton.disconnected(serverName, false, e.getMessage(), null);
		
	}

	
	
}
