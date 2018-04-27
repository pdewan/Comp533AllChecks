package inputport.datacomm.duplex.buffer.socket;

import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexClientInputPortSkeleton;
import inputport.datacomm.simplex.buffer.socket.ASocketClientInputDriver;
import inputport.datacomm.simplex.buffer.socket.ASocketReadingRunnable;

import java.net.Socket;
import java.nio.ByteBuffer;

import port.old.BufferDuplexClientInputPortLauncher;
import util.misc.StoppableRunnable;



public class ASocketDuplexClientInputPortDriver extends ASocketClientInputDriver implements SocketDuplexClientInputPortDriver{
	DuplexClientInputPortSkeleton<Socket> duplexSkeleton;
	public ASocketDuplexClientInputPortDriver(
			String theRemoteHostName, String theRemotePort, String aServerName) {
		super(theRemoteHostName, theRemotePort, aServerName);		
	}
	@Override
	protected void connected(Socket aSocket) {
		StoppableRunnable socketReadingRunnable = new ASocketReadingRunnable(socket, this, this);
		Thread thread = new Thread(socketReadingRunnable);
		thread.setName("Socket Reading thread for " + aSocket);
		thread.start();
		super.connected(aSocket);
		
	}
	@Override
	public void socketRead(Socket aSocket,
			ByteBuffer theMessage) {
		duplexSkeleton.messageReceived(serverName, theMessage);		
	}

	
	public static void main (String[] args) {
		DuplexInputPortFactory inputPortFactory = new ASocketDuplexBufferInputPortFactory();
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(inputPortFactory);
		BufferDuplexClientInputPortLauncher.launch(args);
//		DuplexClientInputPort clientInputPort = BufferDuplexInputPortSelector.createBufferDuplexClientInputPort("localhost", "9090", "test server", "test client");
//		PrintingReceiveListener echoingConnectionListener = new PrintingReceiveListener();
//		clientInputPort.addConnectListener(echoingConnectionListener);
//		clientInputPort.addReceiveListener(echoingConnectionListener);
//		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
////		byte[] padding = new byte[10];
////		message.position(message.limit());
////		message.put(padding);
////		message.flip();
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
	}
//	@Override
//	public void reply(ByteBuffer message) {
////		return send(message);
//		send(message);
//	}
//	@Override
//	public void send(String remoteName, ByteBuffer message) {
//		send(message);
//		
//	}
	@Override
	public void setSkeleton(DuplexBufferGenericClientInputPort<Socket> theSkeleton) {
		super.setSkeleton(theSkeleton);
		duplexSkeleton = theSkeleton;
	}

}
