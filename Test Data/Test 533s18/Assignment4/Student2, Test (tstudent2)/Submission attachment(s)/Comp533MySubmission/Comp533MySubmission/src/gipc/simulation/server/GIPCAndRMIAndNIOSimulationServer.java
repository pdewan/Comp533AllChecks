package gipc.simulation.server;

import rmi.simulation.server.RMIAndNIOSimulationServer;

public interface GIPCAndRMIAndNIOSimulationServer extends RMIAndNIOSimulationServer{
	void initialize(int aServerPort, String aRegistryHost, int aRegistryPort, int aGIPCPort);


}
