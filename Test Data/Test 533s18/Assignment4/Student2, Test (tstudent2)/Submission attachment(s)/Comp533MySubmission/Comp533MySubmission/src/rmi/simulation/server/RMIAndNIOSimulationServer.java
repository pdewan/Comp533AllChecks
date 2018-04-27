package rmi.simulation.server;

import rmi.simulation.common.RMISimulationServerLauncher;
import nio.manager.simulation.server.NIOManagerSimulationServer;

public interface RMIAndNIOSimulationServer extends NIOManagerSimulationServer, RMISimulationServerLauncher {
	void initialize(int aServerPort, String aRegistryHost, int aRegistryPort);

}
