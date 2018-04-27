package nio.launcher;

import java.nio.channels.SocketChannel;

import bus.uigen.OEFrame;
import common.commandprocessor.CathyCommandProcessorFactory;
import common.controller.CathyControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.CathyUI;
import nio.client.TrickOrTreatNioClient;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class CathyTrickOrTreatNioClient  implements CathyUI {
	// The host:port combination to connect to
	public static final String SIMULATION1_PREFIX = "1:";
//
//	public static final String ALICE_PREFIX = "Cathy:";
//	public static final String SIMULATION2_PREFIX = "2:";
//	public static  final int COUPLED_SIMULATION_X_OFFSET = 350;
//	public static int SIMULATION_COMMAND_Y_OFFSET = 0;
//	public static int SIMULATION_WIDTH = 400;
//	public static int SIMULATION_HEIGHT = 765;
	SocketChannel socket; 



	public static void main(String[] args) {
		Tracer.showWarnings(false);
		Tracer.showInfo(true);
		OEFrame oeFrame = CathyControllerUI.createUI();


		HalloweenCommandProcessor aCommandProcessor = CathyCommandProcessorFactory.getOrCreateSingleton();

		TrickOrTreatNioClient.launchClient( aCommandProcessor, true);
		TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();



	  }
}

