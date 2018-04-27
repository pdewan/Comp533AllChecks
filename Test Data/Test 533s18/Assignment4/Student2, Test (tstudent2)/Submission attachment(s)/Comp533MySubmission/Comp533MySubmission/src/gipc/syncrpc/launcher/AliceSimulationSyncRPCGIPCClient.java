package gipc.syncrpc.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.AliceCommandProcessorFactory;
import common.controller.AliceControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.AliceUI;
import gipc.simulation.server.GIPCSimulationServerLauncher;
import gipc.syncrpc.client.ASyncRPCGIPCSimulationClientLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class AliceSimulationSyncRPCGIPCClient implements AliceUI {

	public static final String SIMULATION2_PREFIX = "2:";

//public static void main(String[] args) {
//	Tracer.showWarnings(false);
//	
//
//	HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//			SIMULATION2_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
//	(new ASyncRPCGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
////	new AGIPCSimulationClientLauncher( NAME, GIPCSimulationServerLauncher.SESSION_SERVER_ID,, aCommandProcessor, true);
//	ClearanceManager clearanceManager = new AClearanceManager();
//	OEFrame oeFrame = ObjectEditor.edit(clearanceManager);
//	oeFrame.setLocation(700, 500);
//
//	clearanceManager.waitForClearance();
//	long startTime = System.currentTimeMillis();
//
//	for (int i=0; i < 250; i++) {
////		aCommandProcessor.processCommand("move 1 -1");
//		aCommandProcessor.setInputString("move 1 0");
//
//
//	}
//	for (int i=0; i < 250; i++) {
////		aCommandProcessor.processCommand("move 1 -1");
//		aCommandProcessor.setInputString("move 0 -1");
//
//
//	}
//	long endTime = System.currentTimeMillis();
//
//	System.out.println("Loop execution time:" + (endTime - startTime));
//
//
//	
//
//  
//  }
public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(false);
	OEFrame oeFrame = AliceControllerUI.createUI();
	HalloweenCommandProcessor aCommandProcessor = AliceCommandProcessorFactory.getOrCreateSingleton();
	(new ASyncRPCGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();  
  }
}

