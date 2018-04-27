package gipc.syncrpc.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.CathyCommandProcessorFactory;
import common.controller.CathyControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.CathyUI;
import gipc.simulation.server.GIPCSimulationServerLauncher;
import gipc.syncrpc.client.ASyncRPCGIPCSimulationClientLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class CathySimulationSyncRPCGIPCClient implements CathyUI {

	public static final String SIMULATION2_PREFIX = "2:";

//public static void main(String[] args) {
//	Tracer.showWarnings(false);
//
//	HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//			SIMULATION2_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
//	(new ASyncRPCGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
//
//
//  }
	public static void main(String[] args) {
		Tracer.showWarnings(false);
		Tracer.showInfo(false);
		OEFrame oeFrame = CathyControllerUI.createUI();
		HalloweenCommandProcessor aCommandProcessor = CathyCommandProcessorFactory.getOrCreateSingleton();
		(new ASyncRPCGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
		TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();  
	  }
}

