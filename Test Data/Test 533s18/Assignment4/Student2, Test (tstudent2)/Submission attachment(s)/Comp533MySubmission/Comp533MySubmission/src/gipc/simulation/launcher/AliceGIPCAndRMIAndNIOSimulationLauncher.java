package gipc.simulation.launcher;

import gipc.simulation.client.AliceGIPCAndRMIAndNIOClient;
import rmi.simulation.client.AliceRMIAndNIOClient;
import rmi.simulation.launcher.RMIRegistryLauncher;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.misc.ThreadDelayed;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.gipc.GIPCRPCTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;
public class AliceGIPCAndRMIAndNIOSimulationLauncher {
	public static void main (String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		ThreadDelayed.enablePrint();
		GIPCRPCTraceUtility.setTracing();
		
		String[] myArgs = {
				"localhost", 
				GIPCAndRMIAndNIOServerLauncher.NIO_SERVER_PORT,
				"Alice",
				"true",
				"localhost",
				RMIRegistryLauncher.RMI_PORT,
				GIPCAndRMIAndNIOServerLauncher.GIPC_SERVER_PORT};
		
		AliceGIPCAndRMIAndNIOClient.launchAlice(myArgs);
		
	}

}
