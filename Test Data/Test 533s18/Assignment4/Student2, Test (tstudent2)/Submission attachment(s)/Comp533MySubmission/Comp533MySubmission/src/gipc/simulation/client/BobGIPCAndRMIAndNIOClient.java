package gipc.simulation.client;

import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import assignments.util.MiscAssignmentUtils;
import assignments.util.mainArgs.ClientArgsProcessor;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION2_PREFIX;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_2_X_OFFSET;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_COMMAND_Y_OFFSET;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_HEIGHT;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_WIDTH;
public class BobGIPCAndRMIAndNIOClient {
	public static void launchBob(String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		MiscAssignmentUtils.setHeadless(ClientArgsProcessor.getHeadless(args));
		AGIPCAndRMIAndNIOSimulationClient.launchClient(
				ClientArgsProcessor.getServerHost(args), 
				ClientArgsProcessor.getServerPort(args), 
				ClientArgsProcessor.getClientName(args), 
				SIMULATION2_PREFIX, 
				SIMULATION_2_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET,
				SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_2_X_OFFSET, 0,
				ClientArgsProcessor.getRegistryHost(args),
				ClientArgsProcessor.getRegistryPort(args),
				ClientArgsProcessor.getGIPCPort(args));
	}

}
