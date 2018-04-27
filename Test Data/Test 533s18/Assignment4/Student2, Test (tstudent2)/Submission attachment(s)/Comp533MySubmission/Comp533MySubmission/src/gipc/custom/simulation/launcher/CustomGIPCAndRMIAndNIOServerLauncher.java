package gipc.custom.simulation.launcher;

import inputport.rpc.duplex.syncreceive.syncrpc.test.ASyncRPCSyncReceiveFactorySetter;
import gipc.simulation.server.AGIPCAndRMIAndNIOSimulationServer;
import rmi.simulation.launcher.RMIAndNIOServerLauncher;
import rmi.simulation.launcher.RMIRegistryLauncher;
import rmi.simulation.registry.ADistributedRMIRegistryStarter;
import rmi.simulation.server.AnRMIAndNIOSimulationServer;
import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import util.trace.port.rpc.gipc.GIPCRPCTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;
@Tags({DistributedTags.SERVER, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC})
public class CustomGIPCAndRMIAndNIOServerLauncher extends RMIAndNIOServerLauncher{
	public static final String GIPC_SERVER_PORT = "3090";
	public static void main (String[] args) {
		(new ASyncRPCSyncReceiveFactorySetter()).setFactories();
//		NIOTraceUtility.setTracing();
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		GIPCRPCTraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		String[] myArgs = {NIO_SERVER_PORT, "localhost", RMIRegistryLauncher.RMI_PORT, GIPC_SERVER_PORT };
		AGIPCAndRMIAndNIOSimulationServer.launch(args);
	}

}
