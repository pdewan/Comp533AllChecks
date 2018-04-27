package nio.manager.simulation.client;

import util.interactiveMethodInvocation.IPCMechanism;
import assignments.util.inputParameters.SimulationParametersBean;
import assignments.util.inputParameters.SimulationParametersListener;
import assignments.util.mainArgs.ServerPort;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;

public interface NIOManagerSimulationClient extends 
	SocketChannelConnectListener, 
	ServerPort, 
	WriteBoundedBufferListener,
	SimulationParametersBean{
	public static String SERVER_HOST = "localhost";
//	void createModelAndUI();
	void connectToServer(String aServerHost, int aServerPort);
	void createModelAndUI(String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset);
	void initialize(String aServerHost, int aServerPort, String aClientName,
			String aPrefix, int anXOffset, int aYOffset, int aWidth,
			int aHeight, int aCommandWindowXOffset, int aCommandWindowYOffset);
	void processCommands();
//	boolean isLocalProcessingOnly();
//	void setLocalProcessingOnly(boolean localProcessingOnly);
//	IPCMechanism getIPCMechanism();
//	boolean isLocalProcessingOnly();
//	void setIPCMechanism(IPCMechanism newVal);
//	void setAtomicBroadcast(boolean newVal);

}
