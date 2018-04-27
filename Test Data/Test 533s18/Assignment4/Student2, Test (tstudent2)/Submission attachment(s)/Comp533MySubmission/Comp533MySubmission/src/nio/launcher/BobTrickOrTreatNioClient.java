package nio.launcher;

import java.nio.channels.SocketChannel;

import bus.uigen.OEFrame;
import common.commandprocessor.BobCommandProcessorFactory;
import common.controller.BobControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.BobUI;
import nio.client.TrickOrTreatNioClient;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class BobTrickOrTreatNioClient  implements BobUI{
	// The host:port combination to connect to
//	public static final String SIMULATION1_PREFIX = "1:";

//	public static final String ALICE_PREFIX = "Bob:";
//	public static final String SIMULATION2_PREFIX = "2:";
//	public static  final int COUPLED_SIMULATION_X_OFFSET = 250;
//	public static int SIMULATION_COMMAND_Y_OFFSET = 0;
//	public static int SIMULATION_WIDTH = 400;
//	public static int SIMULATION_HEIGHT = 765;
	SocketChannel socket; 



	public static void main(String[] args) {
		Tracer.showWarnings(false);
		Tracer.showInfo(false);
		OEFrame oeFrame = BobControllerUI.createUI();
		HalloweenCommandProcessor aCommandProcessor = BobCommandProcessorFactory.getOrCreateSingleton();
		TrickOrTreatNioClient.launchClient(aCommandProcessor, true);
		TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();

//		Tracer.setKeywordPrintStatus(AnNIOSimulationInCoupler.class, true);
//		Tracer.setKeywordPrintStatus(AnNIOSimulationOutCoupler.class, true);
//		HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//				SIMULATION1_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
//		TrickOrTreatNioClient.launchClient( SIMULATION1_PREFIX, TrickOrTreatNioClient.SIMULATION_2_X_OFFSET, TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, TrickOrTreatNioClient.SIMULATION_2_X_OFFSET, 0);
//		TrickOrTreatNioClient.launchClient( aCommandProcessor, true);

////		commandProcessor = BeauAndersonFinalProject.createSimulation(
////				"SIMULATION1_PREFIX", 0, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//		
//	    try {
//	    		
//	    	
//	      TrickOrTreatNioClient client = new TrickOrTreatNioClient(InetAddress.getByName("localhost"), 9090);
//	      Thread t = new Thread(client);
//	      t.setDaemon(true);
//	      t.start();
//	      client.createUI(SIMULATION1_PREFIX, TrickOrTreatNioClient.SIMULATION_2_X_OFFSET, TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, TrickOrTreatNioClient.SIMULATION_2_X_OFFSET, 0);
////	      client.initiateConnection();
////	      RspHandler handler = new RspHandler();
////	      client.send("Hello World".getBytes(), handler);
////	      handler.waitForResponse();
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	  
	  }
}

