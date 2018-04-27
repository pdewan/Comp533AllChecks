package nio.launcher;

import java.nio.channels.SocketChannel;

import bus.uigen.OEFrame;
import common.commandprocessor.AliceCommandProcessorFactory;
import common.controller.AliceControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.AliceUI;
import nio.client.TrickOrTreatNioClient;
import nio.couplers.AnNIOSimulationInCoupler;
import nio.couplers.AnNIOSimulationOutCoupler;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.Tracer;



public class AliceTrickOrTreatNioClient implements AliceUI {
	// The host:port combination to connect to
//	public static final String SIMULATION1_PREFIX = "1:";
//
//	public static final String ALICE_NAME = "Alice:";
//	public static final String SIMULATION2_PREFIX = "2:";
//	public static final int NUM_MOVES = 250;
//	public static  final int COUPLED_SIMULATION_X_OFFSET = 250;
//	public static int SIMULATION_COMMAND_Y_OFFSET = 0;
//	public static int SIMULATION_WIDTH = 400;
//	public static int SIMULATION_HEIGHT = 765;
	SocketChannel socket; 



//	public static void main(String[] args) {
////		commandProcessor = BeauAndersonFinalProject.createSimulation(
////				"SIMULATION1_PREFIX", 0, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//		
//	    try {
////	    	String currentDir = System.getProperty("user.dir");
////	        System.out.println("Current dir using System:" +currentDir);
//	    	
//	      TrickOrTreatNioClient client = new TrickOrTreatNioClient(InetAddress.getByName("localhost"), 9090);
//	      Thread t = new Thread(client);
//	      t.setDaemon(true);
//	      t.start();
//	      HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//	    		  SIMULATION2_PREFIX, 0, TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//	      client.createUI(aCommandProcessor);
////	      client.initiateConnection();
////	      RspHandler handler = new RspHandler();
////	      client.send("Hello World".getBytes(), handler);
////	      handler.waitForResponse();
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	  
//	  }
//}
public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(true);
	Tracer.setDisplayThreadName(true);
	TraceableInfo.setPrintTraceable(true);
	Traceable.setPrintThread(true);
	TraceableInfo.setPrintTime(true);
	Tracer.setKeywordPrintStatus(TrickOrTreatNioClient.class, true);
	Tracer.setKeywordPrintStatus(AnNIOSimulationOutCoupler.class, true);
	Tracer.setKeywordPrintStatus(AnNIOSimulationInCoupler.class, true);
	

//	HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//			SIMULATION2_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
//	HalloweenCommandProcessor 
	OEFrame oeFrame = AliceControllerUI.createUI();

	HalloweenCommandProcessor aCommandProcessor = AliceCommandProcessorFactory.getOrCreateSingleton();
	TrickOrTreatNioClient.launchClient(aCommandProcessor, true);
//	ClearanceManager clearanceManager = ClearanceManagerFactory.getOrCreateClearanceManager();
//	
//	OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
//	
//	clearanceFrame.setLocation(700, 500);
//	OEFrame settingsFrame = ObjectEditor.edit(SendReceiveSettingsFactory.getOrCreateSettings());
//	settingsFrame.setLocation(900,  500);
//	TrickOrTreatClientController aController = TrickOrTreatClientControllerFactory.getOrCreateSingleton();
//	OEFrame controllerFrame = ObjectEditor.edit(aController);
//	controllerFrame.setLocation(700, 500);	
//	Tracer.showWarnings(false);
//	Tracer.showInfo(true);
//	Tracer.setKeywordPrintStatus(AnNIOSimulationInCoupler.class, true);
//	Tracer.setKeywordPrintStatus(AnNIOSimulationOutCoupler.class, true);
//	Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);

	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop();

//	ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
	
	
//	long startTime = System.currentTimeMillis();
////	System.out.println("start time" + System.currentTimeMillis());
////	aCommandProcessor.processCommand("move 50 -50");
////	System.out.println("end time after one command" + System.currentTimeMillis());
//	for (int i=0; i < NUM_MOVES; i++) {
////		aCommandProcessor.processCommand("move 1 -1");
//		aCommandProcessor.setInputString("move 1 0");
//
//
//	}
//	for (int i=0; i < NUM_MOVES; i++) {
////		aCommandProcessor.processCommand("move 1 -1");
//		aCommandProcessor.setInputString("move 0 -1");
//
//
//	}
//	long endTime = System.currentTimeMillis();
//
//	System.out.println("Loop execution time:" + (endTime - startTime));



  }
}

