package sync.client;



import bus.uigen.ObjectEditor;
import edu.unc.sync.Sync;
import edu.unc.sync.server.ServerProxy;
import edu.unc.sync.server.SyncClient;
import graphics.HalloweenSimulation;
import main.BeauAndersonFinalProject;
import stringProcessors.AHalloweenCommandProcessor;
import stringProcessors.HalloweenCommandProcessor;
import sync.models.AReplicatableHalloweenCommandProcessor;
import sync.server.StartSimationSyncServer;



public class ASyncSimulationClientLauncher {
//	static String SERVER_NAME = "localhost/A";
	static String SERVER_NAME = "localhost/" + StartSimationSyncServer.SERVER_NAME;

	static String MODEL_NAME = "simulation";
//	static String CLIENT_ID = "alice";
//	static Class EDITOR_CLASS = SyncObjectEditor.class;
	//static Class MODEL_CLASS = ConcertExpenseWithAttendanceTable.class;
	static Class MODEL_CLASS = AReplicatableHalloweenCommandProcessor.class;
	public static ServerProxy server;
	public static SyncClient client;
	public static HalloweenCommandProcessor  launchClient(String aMyName, boolean aBroadcastData, String aPrefix, int anXOffset, int aYOffset, int aWidth, int aHeight, int aCommandWindowXOffset, int aCommandWindowYOffset) {
		   try {
			   HalloweenSimulation aSimulation = BeauAndersonFinalProject.createAndDisplaySimulationWithoutCommandProcessor(anXOffset, aYOffset, aWidth, aHeight, aPrefix);
			  return  launchClient(aMyName, aBroadcastData, aSimulation, aPrefix, aCommandWindowXOffset, aCommandWindowYOffset);
			   
   //		HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
//				aPrefix, anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
	 
////	    	String currentDir = System.getProperty("user.dir");
////	        System.out.println("Current dir using System:" +currentDir);
//	    	
//	      TrickOrTreatNioClient client = new TrickOrTreatNioClient(InetAddress.getByName("localhost"), 9090);
//	      Thread t = new Thread(client);
//	      t.setDaemon(true);
//	      t.start();
////	      HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
////	    		  SIMULATION2_PREFIX, 0, TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
//	      client.createUI(aCommandProcessor);
////	      client.initiateConnection();
////	      RspHandler handler = new RspHandler();
////	      client.send("Hello World".getBytes(), handler);
////	      handler.waitForResponse();
	    } catch (Exception e) {
	    	
	      e.printStackTrace();
	      return null;
	    }
	  
	  }
	public static HalloweenCommandProcessor  launchClient(String aMyName, boolean aBroadcast, HalloweenSimulation aSimulation, String aPrefix, int aCommandWindowXOffset, int aCommandWindowYOffset ) {
		HalloweenCommandProcessor aCommandProcessor;
			if (!aBroadcast) {
				aCommandProcessor = new AHalloweenCommandProcessor();
			} else {
				Sync.setReplicate(MODEL_CLASS, "InputString", false);

				 client = Sync.replicate (SERVER_NAME, 
						MODEL_NAME, 
						MODEL_CLASS,
						aMyName);
				
				 server = client.getServerProxy(SERVER_NAME);
				
				//ConcertExpenseWithAttendanceTable model = (ConcertExpenseWithAttendanceTable) server.getModel(MODEL_NAME);
				aCommandProcessor = (HalloweenCommandProcessor) server.getModel(MODEL_NAME);
				ObjectEditor.edit(server);
			}
			BeauAndersonFinalProject.bindAndDisplayCommandProcessor(aSimulation, aCommandProcessor, aPrefix, aCommandWindowXOffset, aCommandWindowYOffset);
		   
			return aCommandProcessor;
	  }
	
}
