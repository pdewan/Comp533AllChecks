package gipc.customserializer.launcher;

import common.controller.ServerControllerUI;
import gipc.customserializer.server.ACustomSerializerGIPCSimulationServerLauncher;
import gipc.simulation.server.AGIPCSimulationServerLauncher;



public class SimulationCustomSerializerGIPCServerMain extends AGIPCSimulationServerLauncher   {
	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		ServerControllerUI.createUI();

		(new ACustomSerializerGIPCSimulationServerLauncher(SESSION_SERVER, SESSION_SERVER_ID)).launch();
	}

}
