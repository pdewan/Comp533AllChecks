package rmi.simulation.server;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import assignments.util.mainArgs.ServerArgsProcessor;
import nio.manager.simulation.server.AnNIOManagerSimulationServer;
import nio.manager.simulation.server.NIOManagerSimulationServer;
import common.controller.ServerControllerUI;
import rmi.simulation.common.RMISimulationServerLauncher;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.launcher.RMIRegistryLauncher;
import util.annotations.DisplayToString;
import util.annotations.Tags;
import util.interactiveMethodInvocation.IPCMechanism;
import util.interactiveMethodInvocation.SimulationParametersControllerFactory;
import util.tags.DistributedTags;
import util.trace.Tracer;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.rmi.RMIObjectRegistered;
import util.trace.port.rpc.rmi.RMIRegistryLocated;

@Tags({DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO})
public class AnRMIAndNIOSimulationServer 
		extends AnNIOManagerSimulationServer 
		implements RMIAndNIOSimulationServer
{
	protected RMISimulationRelayer createRMIRelayer() {
		return new AnRMISimulationRelayer();
	}
	protected void registerRelayer(int aServerPort, String aRegistryHost, int aRegistryPort) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry(aRegistryHost, aRegistryPort);
			RMIRegistryLocated.newCase(this, aRegistryHost, aRegistryPort, rmiRegistry);
			RMISimulationRelayer aRelayer = createRMIRelayer();
			UnicastRemoteObject.exportObject(aRelayer, 0);
			rmiRegistry.rebind(SESSION_SERVER, aRelayer);
			RMIObjectRegistered.newCase(this, SESSION_SERVER, aRelayer, rmiRegistry);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void initialize(int aServerPort, String aRegistryHost, int aRegistryPort) {
		super.initialize(aServerPort);
		registerRelayer(aServerPort, aRegistryHost, aRegistryPort);
//		try {
//			Registry rmiRegistry = LocateRegistry.getRegistry(aRegistryHost, aRegistryPort);
//			RMIRegistryLocated.newCase(this, aRegistryHost, aRegistryPort, rmiRegistry);
//			RMISimulationSessionServer aRelayer = new AnRMISimulationRelayer();
//			UnicastRemoteObject.exportObject(aRelayer, 0);
//			rmiRegistry.rebind(SESSION_SERVER, aRelayer);
//			RMIObjectRegistered.newCase(this, SESSION_SERVER, aRelayer, rmiRegistry);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
	}
	
	public static void launch(String[] args) {
		args = ServerArgsProcessor.removeEmpty(args);
		RMIAndNIOSimulationServer aServer = 
				new AnRMIAndNIOSimulationServer();
		nioManagerSimulationServer = aServer;
		aServer.initialize(
				ServerArgsProcessor.getServerPort(args),
				ServerArgsProcessor.getRegistryHost(args),
				ServerArgsProcessor.getRegistryPort(args));
		SimulationParametersControllerFactory.getSingleton().processCommands();
	}
	
	

	
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		AnRMIAndNIOSimulationServer.launch(args);
	}
}
