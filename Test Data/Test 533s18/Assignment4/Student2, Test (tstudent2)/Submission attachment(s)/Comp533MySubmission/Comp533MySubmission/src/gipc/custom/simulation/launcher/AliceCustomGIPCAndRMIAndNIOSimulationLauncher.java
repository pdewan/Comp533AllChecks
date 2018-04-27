package gipc.custom.simulation.launcher;

import inputport.rpc.duplex.syncreceive.syncrpc.test.ASyncRPCSyncReceiveFactorySetter;
import gipc.simulation.client.AliceGIPCAndRMIAndNIOClient;
import rmi.simulation.client.AliceRMIAndNIOClient;
import rmi.simulation.launcher.RMIRegistryLauncher;
import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import util.trace.port.rpc.gipc.GIPCRPCTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;
@Tags({DistributedTags.CLIENT_1, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC})

public class AliceCustomGIPCAndRMIAndNIOSimulationLauncher {
	public static void main (String[] args) {
		(new ASyncRPCSyncReceiveFactorySetter()).setFactories();
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
//		NIOTraceUtility.setTracing();
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();	
		RMITraceUtility.setTracing();
		GIPCRPCTraceUtility.setTracing();
		String[] myArgs = {
				"localhost", 
				CustomGIPCAndRMIAndNIOServerLauncher.NIO_SERVER_PORT,
				"Alice",
				"true",
				"localhost",
				RMIRegistryLauncher.RMI_PORT,
				CustomGIPCAndRMIAndNIOServerLauncher.GIPC_SERVER_PORT};
		
//		AliceGIPCAndRMIAndNIOClient.launchAlice(myArgs);
		AliceGIPCAndRMIAndNIOClient.launchAlice(args);

		
	}

}
