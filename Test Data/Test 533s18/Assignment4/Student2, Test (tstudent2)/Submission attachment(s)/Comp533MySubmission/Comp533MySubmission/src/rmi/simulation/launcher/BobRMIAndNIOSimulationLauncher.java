package rmi.simulation.launcher;

import rmi.simulation.client.AliceRMIAndNIOClient;
import rmi.simulation.client.BobRMIAndNIOClient;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.misc.ThreadDelayed;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;

public class BobRMIAndNIOSimulationLauncher {
	public static void main (String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		ThreadDelayed.enablePrint();
		
		String[] myArgs = {
				"localhost", 
				RMIAndNIOServerLauncher.NIO_SERVER_PORT,
				"Bob",
				"false",
				"localhost",
				RMIRegistryLauncher.RMI_PORT};
		
		BobRMIAndNIOClient.launchBob(myArgs);
		
	}

}
