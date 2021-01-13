package nio.manager.simulation.server;

import assignments.util.mainArgs.ServerPort;
import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;


public interface NIOManagerSimulationServer extends ServerPort, SocketChannelAcceptListener, WriteBoundedBufferListener {
	void initialize(int aServerPort);
}
