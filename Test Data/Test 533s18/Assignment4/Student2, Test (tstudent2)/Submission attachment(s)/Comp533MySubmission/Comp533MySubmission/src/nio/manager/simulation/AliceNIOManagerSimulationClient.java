package nio.manager.simulation;

import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION1_PREFIX;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_COMMAND_Y_OFFSET;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_HEIGHT;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_WIDTH;
import assignments.util.MiscAssignmentUtils;
import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import nio.manager.simulation.client.AnNIOManagerSimulationClient;
import util.trace.port.nio.NIOTraceUtility;

public class AliceNIOManagerSimulationClient  {
	public static void main(String[] args) {
		NIOTraceUtility.setTracing();
//		MiscAssignmentUtils.setHeadless(true);
		AnNIOManagerSimulationClient.launchClient(ClientArgsProcessor.getServerHost(args), ServerPort.SERVER_PORT,
				"Alice", SIMULATION1_PREFIX, 0, SIMULATION_COMMAND_Y_OFFSET,
				SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0 );
	}

}
