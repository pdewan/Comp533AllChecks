package gipc.simulation.server;

import rmi.simulation.common.RMISimulationServerLauncher;
import rmi.simulation.server.AnRMISimulationRelayer;

public interface GIPCSimulationServerLauncher extends RMISimulationServerLauncher{
//	public static String SESSION_SERVER = "session server";
	public static final Class SESSION_SERVER_CLASS =  AnRMISimulationRelayer.class;
	public static final String SESSION_SERVER_ID = "9190";


}
