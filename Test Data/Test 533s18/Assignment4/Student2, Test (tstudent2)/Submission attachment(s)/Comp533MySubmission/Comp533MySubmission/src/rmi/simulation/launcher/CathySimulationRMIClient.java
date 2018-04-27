package rmi.simulation.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.CathyCommandProcessorFactory;
import common.controller.CathyControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.CathyUI;
import rmi.simulation.client.AnRMISimulationClientLauncher;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class CathySimulationRMIClient implements CathyUI {

	
public static void main(String[] args) {
	Tracer.showWarnings(false);
	OEFrame oeFrame = CathyControllerUI.createUI();
	HalloweenCommandProcessor aCommandProcessor =CathyCommandProcessorFactory.getOrCreateSingleton();
	AnRMISimulationClientLauncher.launchClient(NAME, aCommandProcessor, true);
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();
  }
}

