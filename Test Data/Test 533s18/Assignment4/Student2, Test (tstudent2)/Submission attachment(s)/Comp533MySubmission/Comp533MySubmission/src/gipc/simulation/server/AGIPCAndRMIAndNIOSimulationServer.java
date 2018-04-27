package gipc.simulation.server;

import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import assignments.util.mainArgs.ServerArgsProcessor;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationRelayer;
import rmi.simulation.server.AnRMIAndNIOSimulationServer;
import rmi.simulation.server.AnRMISimulationRelayer;
import rmi.simulation.server.RMIAndNIOSimulationServer;
import rmi.simulation.server.twophase.ATwoPhaseRMIAndNIOSimulationServer;
import util.annotations.Tags;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;
import util.interactiveMethodInvocation.SimulationParametersControllerFactory;
import util.tags.DistributedTags;
@Tags({DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.GIPC, DistributedTags.NIO})

public class AGIPCAndRMIAndNIOSimulationServer extends ATwoPhaseRMIAndNIOSimulationServer 
	implements GIPCAndRMIAndNIOSimulationServer{
	@Override
	public void initialize(int aServerPort, String aRegistryHost,
			int aRegistryPort, int aGIPCPort) {
		super.initialize(aServerPort, aRegistryHost, aRegistryPort);
		TwoPhaseRMISimulationRelayer aRelayer = createRMIRelayer();
		GIPCRegistry gipcRegistry = GIPCLocateRegistry
				.createRegistry(aGIPCPort);
		gipcRegistry.rebind(SESSION_SERVER, aRelayer);
	}
	public static void launch(String[] args) {
		args = ServerArgsProcessor.removeEmpty(args);
		GIPCAndRMIAndNIOSimulationServer aServer = 
				new AGIPCAndRMIAndNIOSimulationServer();
		nioManagerSimulationServer = aServer;
		aServer.initialize(
				ServerArgsProcessor.getServerPort(args),
				ServerArgsProcessor.getRegistryHost(args),
				ServerArgsProcessor.getRegistryPort(args),
				ServerArgsProcessor.getGIPCServerPort(args));
		SimulationParametersControllerFactory.getSingleton().processCommands();
	}
	

}
