package rmi.simulation.client;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.client.ClientParameters;
import main.BeauAndersonFinalProject;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationServerLauncher;
import rmi.simulation.common.RMISimulationRelayer;
import stringProcessors.HalloweenCommandProcessor;
import util.annotations.Tags;
import util.interactiveMethodInvocation.IPCMechanism;
import util.remote.RelayerClientControllerFactory;
import util.tags.DistributedTags;
import util.trace.port.rpc.rmi.RMIRegistryLocated;


@Tags({DistributedTags.CLIENT, DistributedTags.RMI})
public class AnRMISimulationClientLauncher implements ClientParameters{
	public static void  launchClient(String aMyName, boolean aBroadcastData, String aPrefix, int anXOffset, int aYOffset, int aWidth, int aHeight, int aCommandWindowXOffset, int aCommandWindowYOffset) {
		   try {
		HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
				aPrefix, anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
		launchClient(aMyName, aCommandProcessor, aBroadcastData);
	 
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
	    }
	  
	  }
	public static void  launchClient(String aMyName, HalloweenCommandProcessor aCommandProcessor, boolean aBroadcast) {
		   RMISimulationInCoupler inCoupler = new AnRMISimulationInCoupler(aCommandProcessor);
		   Registry rmiRegistry;
		try {
			String aFullServerId = SERVER_HOST + ":" + RMISimulationServerLauncher.SESSION_SERVER;
			RelayerClientControllerFactory.getOrCreateSingleton().setServerHost(SERVER_HOST);
		
			// this is where we block if necessary
//			rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry = LocateRegistry.getRegistry(RelayerClientControllerFactory.getOrCreateSingleton().getServerHost());
			UnicastRemoteObject.exportObject(inCoupler, 0);

			
			RMISimulationRelayer server = (RMISimulationRelayer) rmiRegistry.lookup(RMISimulationServerLauncher.SESSION_SERVER);
		   
			server.join(aMyName, inCoupler);
			RelayerClientControllerFactory.getOrCreateSingleton().setServerId(RMISimulationServerLauncher.SESSION_SERVER);
			RelayerClientControllerFactory.getOrCreateSingleton().setRelayerId(RMISimulationServerLauncher.SESSION_SERVER);
		   
			   if (aBroadcast) {
				   new AnRMISimulationOutCoupler(aCommandProcessor, server, aMyName, IPCMechanism.RMI);
			   }
//				ClientControllerFactory.getOrCreateSingleton().setServerId(RMISimulationServerLauncher.SESSION_SERVER);
			

				   
		   } catch (Exception e) {
			
	      e.printStackTrace();
	    }
	  
	  }

}
