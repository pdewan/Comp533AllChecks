package rmi.simulation.launcher;

import rmi.simulation.server.AnRMIAndNIOSimulationServer;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.misc.ThreadDelayed;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;
public class RMIAndNIOServerLauncher {
	public static final String NIO_SERVER_PORT = "2090";
	public static void main (String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		ThreadDelayed.enablePrint();
		
		String[] myArgs = {NIO_SERVER_PORT, "localhost", RMIRegistryLauncher.RMI_PORT};
		AnRMIAndNIOSimulationServer.launch(myArgs);
	}

}
