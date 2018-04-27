package gipc.simulation.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.CathyCommandProcessorFactory;
import common.controller.BobControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.CathyUI;
import gipc.simulation.client.AGIPCSimulationClientLauncher;
import gipc.simulation.server.GIPCSimulationServerLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class CathySimulationGIPCClient implements CathyUI {

//	public static final String SIMULATION2_PREFIX = "2:";

public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(false);

//	HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//			SIMULATION2_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
//	(new AGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
	Tracer.showWarnings(false);
	OEFrame oeFrame = BobControllerUI.createUI();
	HalloweenCommandProcessor aCommandProcessor = CathyCommandProcessorFactory.getOrCreateSingleton();
	(new AGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}

