package rmi.simulation.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.AliceCommandProcessorFactory;
import common.controller.AliceControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.AliceUI;
import rmi.simulation.client.AnRMISimulationClientLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class AliceSimulationRMIClient implements AliceUI {
	public static String SERVER_HOST = "localhost";

public static void main(String[] args) {
	Tracer.showWarnings(false);
	OEFrame oeFrame = AliceControllerUI.createUI();
	HalloweenCommandProcessor aCommandProcessor = AliceCommandProcessorFactory.getOrCreateSingleton();
	AnRMISimulationClientLauncher.launchClient( NAME, aCommandProcessor, true);
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();
  }
}

