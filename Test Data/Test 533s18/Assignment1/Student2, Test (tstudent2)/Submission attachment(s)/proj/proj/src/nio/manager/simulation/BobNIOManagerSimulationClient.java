package nio.manager.simulation;

import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION2_PREFIX;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_2_X_OFFSET;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_COMMAND_Y_OFFSET;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_HEIGHT;
import static nio.manager.simulation.client.AnNIOManagerSimulationClient.SIMULATION_WIDTH;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import nio.manager.simulation.client.AnNIOManagerSimulationClient;
import util.trace.port.nio.NIOTraceUtility;

public class BobNIOManagerSimulationClient  {
	public static void main(String[] args) {
		NIOTraceUtility.setTracing();
		AnNIOManagerSimulationClient.launchClient(ClientArgsProcessor.getServerHost(args), ServerPort.SERVER_PORT,
				"Bob", SIMULATION2_PREFIX, SIMULATION_2_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET,
				SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_2_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET );
	}

}
