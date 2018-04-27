package rmi.simulation.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.BobCommandProcessorFactory;
import common.controller.BobControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.BobUI;
import rmi.simulation.client.AnRMISimulationClientLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class BobSimulationRMIClient implements BobUI {


	
public static void main(String[] args) {
	Tracer.showWarnings(false);
	OEFrame oeFrame = BobControllerUI.createUI();
	HalloweenCommandProcessor aCommandProcessor = BobCommandProcessorFactory.getOrCreateSingleton();
	AnRMISimulationClientLauncher.launchClient(NAME, aCommandProcessor, true);
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();



  }
}

