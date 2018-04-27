package gipc.simulation.client;

import rmi.simulation.client.RMIAndNIOSimulationClient;

public interface GIPCAndRMIAndNIOSimulationClient extends RMIAndNIOSimulationClient{

	void initialize(String aServerHost, int aServerPort, String aClientName,
			String aPrefix, int anXOffset, int aYOffset, int aWidth,
			int aHeight, int aCommandWindowXOffset, int aCommandWindowYOffset,
			String anRMIRegistryHost, int anRMIRegistryPort, int aGIPCServerPort);
}
