package rmi.simulation.launcher;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import rmi.simulation.registry.ADistributedRMIRegistryStarter;

import assignments.util.mainArgs.ServerArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.misc.ThreadDelayed;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;
public class RMIRegistryLauncher {
	public static final String RMI_PORT = "2099";
	public static void main (String[] args) {
		String[] myArgs = {RMI_PORT};
		
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		ThreadDelayed.enablePrint();

		ADistributedRMIRegistryStarter.launch(args);
	}
}
