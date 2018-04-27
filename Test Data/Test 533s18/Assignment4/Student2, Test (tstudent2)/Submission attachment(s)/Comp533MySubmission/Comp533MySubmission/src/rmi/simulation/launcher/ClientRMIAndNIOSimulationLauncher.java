package rmi.simulation.launcher;

import common.ui.AliceUI;
import assignments.util.MiscAssignmentUtils;
import assignments.util.mainArgs.ClientArgsProcessor;
import rmi.simulation.client.AliceRMIAndNIOClient;
import rmi.simulation.client.AnRMIAndNIOSimulationClient;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
@Tags({DistributedTags.CLIENT, DistributedTags.RMI})
public class ClientRMIAndNIOSimulationLauncher implements AliceUI   {
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
				"1:", 
				0, SIMULATION_COMMAND_Y_OFFSET,
				SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0,
				ClientArgsProcessor.getRegistryHost(args),
				ClientArgsProcessor.getRegistryPort(args));
	}

}
