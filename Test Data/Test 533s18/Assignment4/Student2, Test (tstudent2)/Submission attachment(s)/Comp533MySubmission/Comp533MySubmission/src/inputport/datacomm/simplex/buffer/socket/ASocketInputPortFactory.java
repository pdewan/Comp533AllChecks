package inputport.datacomm.simplex.buffer.socket;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferClientInputPort;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferServerInputPort;
import inputport.datacomm.simplex.buffer.GenericSimplexClientInputPort;
import inputport.datacomm.simplex.buffer.GenericSimplexServerInputPort;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;



public class ASocketInputPortFactory implements SimplexInputPortFactory<ByteBuffer>{
	@Override
	public SimplexServerInputPort<ByteBuffer> createSimplexServerInputPort(String theServerId,
			String theServerName) {
		GenericSimplexServerInputPort<ServerSocket, Socket> skeleton = new AGenericSimplexBufferServerInputPort(theServerName, theServerId);
		SimplexBufferServerInputPortDriver<ServerSocket, Socket> driver = new ASocketServerInputDriver(theServerId);
		skeleton.setDriver(driver);
		driver.setSkeleton(skeleton);
		return skeleton;
	}
	@Override
	public SimplexClientInputPort<ByteBuffer> createSimplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		GenericSimplexClientInputPort skeleton = new AGenericSimplexBufferClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		SimplexBufferClientInputPortDriver<Socket> implementation = new ASocketClientInputDriver(theServerHost, theServerId, aServerName);
		skeleton.setDriver(implementation);
		implementation.setSkeleton(skeleton);
		return skeleton;
	}

}
