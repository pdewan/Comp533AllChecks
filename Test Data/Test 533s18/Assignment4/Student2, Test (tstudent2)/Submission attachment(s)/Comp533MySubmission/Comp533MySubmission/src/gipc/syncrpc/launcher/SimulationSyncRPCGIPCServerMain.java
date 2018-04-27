package gipc.syncrpc.launcher;

import common.controller.ServerControllerUI;
import gipc.simulation.server.AGIPCSimulationServerLauncher;
import gipc.syncrpc.server.ASyncRPCGIPCSimulationServerLauncher;



public class SimulationSyncRPCGIPCServerMain extends AGIPCSimulationServerLauncher   {
	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		ServerControllerUI.createUI();
		(new ASyncRPCGIPCSimulationServerLauncher(SESSION_SERVER, SESSION_SERVER_ID)).launch();
	}

}
