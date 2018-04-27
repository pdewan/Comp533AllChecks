package gipc.simulation.launcher;

import gipc.simulation.server.AGIPCAndRMIAndNIOSimulationServer;
import rmi.simulation.launcher.RMIAndNIOServerLauncher;
import rmi.simulation.launcher.RMIRegistryLauncher;
import rmi.simulation.registry.ADistributedRMIRegistryStarter;
import rmi.simulation.server.AnRMIAndNIOSimulationServer;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.misc.ThreadDelayed;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.gipc.GIPCRPCTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;
@Tags({DistributedTags.SERVER, DistributedTags.GIPC})
public class GIPCAndRMIAndNIOServerLauncher extends RMIAndNIOServerLauncher{
	public static final String GIPC_SERVER_PORT = "3090";
	public static void main (String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		ThreadDelayed.enablePrint();
		GIPCRPCTraceUtility.setTracing();
		
		String[] myArgs = {NIO_SERVER_PORT, "localhost", RMIRegistryLauncher.RMI_PORT, GIPC_SERVER_PORT };
//		AGIPCAndRMIAndNIOSimulationServer.launch(myArgs);
		AGIPCAndRMIAndNIOSimulationServer.launch(args);
	}

}
