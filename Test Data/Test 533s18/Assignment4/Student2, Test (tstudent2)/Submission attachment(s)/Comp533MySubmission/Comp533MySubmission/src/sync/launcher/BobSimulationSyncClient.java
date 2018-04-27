package sync.launcher;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import common.ui.BobUI;
import stringProcessors.HalloweenCommandProcessor;
import sync.client.ASyncSimulationClientLauncher;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.trace.Tracer;



public class BobSimulationSyncClient implements BobUI {

	public static final String SIMULATION2_PREFIX = "2:";
	public static final boolean BROADCAST = true;


	public static void main(String[] args) {
	Tracer.showWarnings(false);
//	boolean aBroadCastData = false;
	HalloweenCommandProcessor aCommandProcessor = ASyncSimulationClientLauncher.launchClient(NAME, BROADCAST, SIMULATION2_PREFIX, SIMULATION_COMMAND_X_OFFSET,  SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
	aCommandProcessor.setAnimateCars(false);

	
	ClearanceManager clearanceManager = new AClearanceManager();
	OEFrame oeFrame = ObjectEditor.edit(clearanceManager);
	oeFrame.setLocation(700, 500);

//	clearanceManager.waitForClearance();
//	long startTime = System.currentTimeMillis();
////	System.out.println("start time" + System.currentTimeMillis());
////	aCommandProcessor.processCommand("move 50 -50");
////	System.out.println("end time after one command" + System.currentTimeMillis());
//	for (int i=0; i < 250; i++) {
////		aCommandProcessor.processCommand("move 1 -1");
//		aCommandProcessor.setInputString("move 1 0");
//		aCommandProcessor.setInputString("move 0 -1");
//
//
//
//	}
////	for (int i=0; i < 250; i++) {
//////		aCommandProcessor.processCommand("move 1 -1");
////		aCommandProcessor.setInputString("move 0 -1");
////
////
////	}
//	long endTime = System.currentTimeMillis();
//
//	System.out.println("Loop execution time:" + (endTime - startTime));


	

//	TrickOrTreatNioClient.launchClient( SIMULATION2_PREFIX, 0, TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//	commandProcessor = BeauAndersonFinalProject.createSimulation(
////			"SIMULATION1_PREFIX", 0, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//	
//    try {
////    	String currentDir = System.getProperty("user.dir");
////        System.out.println("Current dir using System:" +currentDir);
//    	
//      TrickOrTreatNioClient client = new TrickOrTreatNioClient(InetAddress.getByName("localhost"), 9090);
//      Thread t = new Thread(client);
//      t.setDaemon(true);
//      t.start();
//      HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//    		  SIMULATION2_PREFIX, 0, TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//      client.createUI(aCommandProcessor);
////      client.initiateConnection();
////      RspHandler handler = new RspHandler();
////      client.send("Hello World".getBytes(), handler);
////      handler.waitForResponse();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  
  }
}

