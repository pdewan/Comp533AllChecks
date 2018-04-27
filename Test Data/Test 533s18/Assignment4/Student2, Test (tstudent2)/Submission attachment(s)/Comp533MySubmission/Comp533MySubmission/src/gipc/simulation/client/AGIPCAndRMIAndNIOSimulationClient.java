package gipc.simulation.client;

import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.simulation.client.AnRMIAndNIOSimulationClient;
import rmi.simulation.client.AnRMISimulationInCoupler;
import rmi.simulation.client.AnRMISimulationOutCoupler;
import rmi.simulation.client.RMIAndNIOSimulationClient;
import rmi.simulation.client.twophase.ATwoPhaseRMIAndNIOSimulationClient;
import rmi.simulation.client.twophase.ATwoPhaseRMISimulationInCoupler;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationServerLauncher;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationInCoupler;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationRelayer;
import util.annotations.Tags;
import util.interactiveMethodInvocation.IPCMechanism;
import util.tags.DistributedTags;
@Tags({DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.GIPC, DistributedTags.NIO})
public class AGIPCAndRMIAndNIOSimulationClient 
		extends ATwoPhaseRMIAndNIOSimulationClient  
		implements GIPCAndRMIAndNIOSimulationClient{
	protected TwoPhaseRMISimulationRelayer gipcServerProxy;

	@Override
	public void initialize(String aServerHost, int aServerPort,
			String aClientName, String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset, String anRMIRegistryHost,
			int anRMIRegistryPort, int aGIPCServerPort) {
		super.initialize(aServerHost, aServerPort, aClientName, aPrefix, anXOffset,
				aYOffset, aWidth, aHeight, aCommandWindowXOffset, aCommandWindowYOffset, 
				anRMIRegistryHost, anRMIRegistryPort);
		TwoPhaseRMISimulationInCoupler inCoupler = new ATwoPhaseRMISimulationInCoupler(commandProcessor);

		GIPCRegistry aGIPCRegistry;

		try {
		
		
			// this is where we block if necessary
//			rmiRegistry = LocateRegistry.getRegistry();
			aGIPCRegistry = GIPCLocateRegistry.getRegistry(aServerHost, aGIPCServerPort, aClientName);
			
			gipcServerProxy = (TwoPhaseRMISimulationRelayer) aGIPCRegistry.lookup(TwoPhaseRMISimulationRelayer.class, RMISimulationServerLauncher.SESSION_SERVER);
		   
			gipcServerProxy.join(aClientName, inCoupler);
			
			new AnRMISimulationOutCoupler(commandProcessor, gipcServerProxy, aClientName, IPCMechanism.GIPC);
			 
//				ClientControllerFactory.getOrCreateSingleton().setServerId(RMISimulationServerLauncher.SESSION_SERVER);
			

				   
		   } catch (Exception e) {
			
	      e.printStackTrace();
	    }
		
	}
	public static void launchClient(String aServerHost, int aServerPort,
			String aClientName, String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset,
			String anRMIHost,
			int anRMIPort,
			int aGIPCPort) {
		GIPCAndRMIAndNIOSimulationClient aClient = new AGIPCAndRMIAndNIOSimulationClient();
		rmiAndNIOClient = aClient;
		nioManagerClient = aClient;
		aClient.initialize(aServerHost, aServerPort, aClientName, aPrefix,
				anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset,
				aCommandWindowYOffset, anRMIHost, anRMIPort, aGIPCPort);
		aClient.processCommands();

	}

}
