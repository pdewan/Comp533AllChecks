package rmi.simulation.client;


import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION1_PREFIX;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_COMMAND_Y_OFFSET;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_HEIGHT;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_WIDTH;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import assignments.util.MiscAssignmentUtils;
import assignments.util.mainArgs.ClientArgsProcessor;
import nio.manager.simulation.client.AnNIOManagerSimulationClient;
import nio.manager.simulation.client.NIOManagerSimulationClient;
import common.client.ClientParameters;
import main.BeauAndersonFinalProject;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationServerLauncher;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.server.RMIAndNIOSimulationServer;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.IPCMechanism;
import util.interactiveMethodInvocation.SimulationParametersControllerFactory;
import util.remote.RelayerClientControllerFactory;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.consensus.RemoteProposeRequestSent;
import util.trace.port.consensus.communication.CommunicationStateNames;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.rmi.RMIObjectLookedUp;
import util.trace.port.rpc.rmi.RMIRegistryLocated;



public class AnRMIAndNIOSimulationClient 
	extends AnNIOManagerSimulationClient
	implements RMIAndNIOSimulationClient{
	protected RMISimulationRelayer rmiServerProxy;
	public AnRMIAndNIOSimulationClient() {
	}
	protected RMISimulationInCoupler createRMIInCoupler() {
		return new AnRMISimulationInCoupler(commandProcessor);
	}
	@Override
	public void initialize(String aServerHost, int aServerPort,
			String aClientName,
			String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset, 
			String anRMIRegistryHost, 
			int anRMIRegistryPort) {
		super.initialize(aServerHost, aServerPort, aClientName, aPrefix, anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
		RMISimulationInCoupler inCoupler = createRMIInCoupler();

		Registry anRMIRegistry;

		try {
		
		
			// this is where we block if necessary
//			rmiRegistry = LocateRegistry.getRegistry();
			anRMIRegistry = LocateRegistry.getRegistry(anRMIRegistryHost, anRMIRegistryPort);
			RMIRegistryLocated.newCase(this, anRMIRegistryHost, anRMIRegistryPort, anRMIRegistry);

			UnicastRemoteObject.exportObject(inCoupler, 0);
			
			rmiServerProxy = (RMISimulationRelayer) anRMIRegistry.lookup(RMISimulationServerLauncher.SESSION_SERVER);
		   
			RMIObjectLookedUp.newCase(this, rmiServerProxy, RMISimulationServerLauncher.SESSION_SERVER, anRMIRegistry);
			rmiServerProxy.join(aClientName, inCoupler);
			
			new AnRMISimulationOutCoupler(commandProcessor, rmiServerProxy, aClientName, IPCMechanism.RMI);
			 
//				ClientControllerFactory.getOrCreateSingleton().setServerId(RMISimulationServerLauncher.SESSION_SERVER);
			

				   
		   } catch (Exception e) {
			
	      e.printStackTrace();
	    }
	  
	  }
	
	public void ipcMechanism(IPCMechanism newValue) {
		if (!isBroadcastBroadcastMode()) {
		     super.ipcMechanism(newValue);
		} else {
			try {
//				if (!isAtomicBroadcast()) {
//					super.ipcMechanism(newValue);
//				}
				RemoteProposeRequestSent.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
				rmiServerProxy.broadcastIPCMechanism(clientName, newValue);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void atomicBroadcast(boolean newValue) {
		if (!isBroadcastBroadcastMode()) {
		     super.atomicBroadcast(newValue);
		} else {
			try {
//				if (!isAtomicBroadcast()) {
//					super.atomicBroadcast(newValue);
//				}
				RemoteProposeRequestSent.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
				rmiServerProxy.broadcastBroadcastMode(clientName, newValue);
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected static RMIAndNIOSimulationClient rmiAndNIOClient;
	public static RMIAndNIOSimulationClient getRMIAndNIOSimulationClient() {
		return rmiAndNIOClient;
	}
	public static void launchClient(String aServerHost, int aServerPort,
			String aClientName, String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset,
			String anRMIHost,
			int anRMIPort) {
		RMIAndNIOSimulationClient aClient = new AnRMIAndNIOSimulationClient();
		rmiAndNIOClient = aClient;
		nioManagerClient = aClient;
		aClient.initialize(aServerHost, aServerPort, aClientName, aPrefix,
				anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset,
				aCommandWindowYOffset, anRMIHost, anRMIPort);
		aClient.processCommands();

	}
	public static void main(String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		MiscAssignmentUtils.setHeadless(ClientArgsProcessor.getHeadless(args));
		AnRMIAndNIOSimulationClient.launchClient(
				ClientArgsProcessor.getServerHost(args), 
				ClientArgsProcessor.getServerPort(args), 
				ClientArgsProcessor.getClientName(args), 
				SIMULATION1_PREFIX, 
				0, SIMULATION_COMMAND_Y_OFFSET,
				SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0,
				ClientArgsProcessor.getRegistryHost(args),
				ClientArgsProcessor.getRegistryPort(args));
	}	

	
}
