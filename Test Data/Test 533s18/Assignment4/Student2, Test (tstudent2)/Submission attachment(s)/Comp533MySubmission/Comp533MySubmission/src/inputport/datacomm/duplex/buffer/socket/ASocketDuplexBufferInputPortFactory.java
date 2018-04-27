package inputport.datacomm.duplex.buffer.socket;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.AGenericDuplexBufferClientInputPort;
import inputport.datacomm.duplex.buffer.AGenericDuplexBufferServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexClientInputPortDriver;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.nio.manager.factories.SelectionManagerFactory;

import java.net.ServerSocket;
import java.net.Socket;

public class ASocketDuplexBufferInputPortFactory extends SelectionManagerFactory implements DuplexInputPortFactory {

	@Override
	public DuplexServerInputPort createDuplexServerInputPort(String theServerId,
			String theServerName) {
		DuplexBufferGenericServerInputPort<ServerSocket, Socket> skeleton = new AGenericDuplexBufferServerInputPort(theServerName, theServerId);
		DuplexServerInputPortDriver<ServerSocket, Socket> driver = new ASocketDuplexServerInputPortDriver(theServerId);
		skeleton.setDriver(driver);
		driver.setSkeleton(skeleton);
		return skeleton;
	}
	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		DuplexBufferGenericClientInputPort skeleton = new AGenericDuplexBufferClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		DuplexClientInputPortDriver implementation = new ASocketDuplexClientInputPortDriver(theServerHost, theServerId, aServerName);
		skeleton.setDriver(implementation);
		implementation.setSkeleton(skeleton);
		return skeleton;
	}
}
