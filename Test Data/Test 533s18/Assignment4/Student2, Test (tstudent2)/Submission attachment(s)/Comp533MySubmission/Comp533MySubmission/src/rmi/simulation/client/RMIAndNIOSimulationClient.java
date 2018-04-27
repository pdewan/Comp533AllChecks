package rmi.simulation.client;

import nio.manager.simulation.client.NIOManagerSimulationClient;

public interface RMIAndNIOSimulationClient extends NIOManagerSimulationClient{

	void initialize(String aServerHost, int aServerPort, String aClientName,
			String aPrefix, int anXOffset, int aYOffset, int aWidth,
			int aHeight, int aCommandWindowXOffset, int aCommandWindowYOffset,
			String anRMIRegistryHost, int anRMIRegistryPort);
	
}
