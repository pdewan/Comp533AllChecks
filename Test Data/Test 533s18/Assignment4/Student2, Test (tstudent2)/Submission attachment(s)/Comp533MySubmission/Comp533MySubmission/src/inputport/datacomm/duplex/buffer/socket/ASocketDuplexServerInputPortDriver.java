package inputport.datacomm.duplex.buffer.socket;

import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.SendToUnkonwnRemoteNameException;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortSkeleton;
import inputport.datacomm.simplex.buffer.socket.ASocketServerInputDriver;
import inputport.nio.manager.AScatterGatherSelectionManager;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import port.old.PrintingReplyingReceiveListener;
import util.trace.Tracer;


public class ASocketDuplexServerInputPortDriver extends ASocketServerInputDriver 
             implements SocketDuplexServerInputPortDriver {
	DuplexServerInputPortSkeleton<ServerSocket, Socket> duplexSkeleton;
	ByteBuffer headerBuffer = ByteBuffer.allocate(AScatterGatherSelectionManager.BYTES_IN_INT);
	int sendId;

	public ASocketDuplexServerInputPortDriver(String thePortId) {
		super(thePortId);
	}

	@Override
	public synchronized void send(String aDestination, ByteBuffer message) {
		Socket aChannel = duplexSkeleton.getChannel(aDestination);
		if (aChannel == null) throw new SendToUnkonwnRemoteNameException();
		Socket aSocket = aChannel;
		try {
			Tracer.info(this, "Sending messsage: " + message + " to " + aSocket);
			int messageSize = message.limit() - message.position();
			headerBuffer.putInt(messageSize);
			headerBuffer.rewind();
			OutputStream socketOutputStream = aSocket.getOutputStream();
			socketOutputStream.write(headerBuffer.array(), headerBuffer.position(), headerBuffer.limit());
			socketOutputStream.write(message.array(), message.position(), message.limit());
			duplexSkeleton.messageSent(aDestination, message, sendId);
			sendId++;
		} catch (Exception e) {
//			close(aSocket);
			socketClosed(aSocket, e);
			e.printStackTrace();
		}	
		
	}
		

	public static void main (String[] args) {
		DuplexInputPortFactory inputPortFactory = new ASocketDuplexBufferInputPortFactory();
		DuplexServerInputPort serverInputPort = inputPortFactory.createDuplexServerInputPort("9090", "test server");
		PrintingReplyingReceiveListener echoingReceiveListener = new PrintingReplyingReceiveListener(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.connect();
	}

	

	@Override
	public void setSkeleton(DuplexServerInputPortSkeleton theSkeleton) {
		super.setSkeleton(theSkeleton);
		duplexSkeleton = theSkeleton;
		
	}



	

	

}
