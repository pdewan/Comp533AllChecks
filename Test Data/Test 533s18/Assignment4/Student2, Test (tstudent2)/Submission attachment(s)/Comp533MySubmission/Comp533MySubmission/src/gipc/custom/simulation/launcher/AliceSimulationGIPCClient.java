package gipc.custom.simulation.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.AliceCommandProcessorFactory;
import common.controller.AliceControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.AliceUI;
import gipc.simulation.client.AGIPCSimulationClientLauncher;
import gipc.simulation.server.GIPCSimulationServerLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class AliceSimulationGIPCClient implements AliceUI {

public static void main(String[] args) {
	Tracer.showWarnings(false);
	OEFrame oeFrame = AliceControllerUI.createUI();
	HalloweenCommandProcessor aCommandProcessor = AliceCommandProcessorFactory.getOrCreateSingleton();
	(new AGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
	Tracer.showInfo(true);
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}

