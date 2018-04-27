package common.commandprocessor;

import common.ui.BobUI;
import main.BeauAndersonFinalProject;
import stringProcessors.HalloweenCommandProcessor;
import util.remote.ADistributedProcessController;

public class BobCommandProcessorFactory extends ADistributedProcessController implements BobUI{

	static HalloweenCommandProcessor singleton;
	public static HalloweenCommandProcessor getOrCreateSingleton() {
		if (singleton == null) {
			singleton = BeauAndersonFinalProject.createSimulation(
					SIMULATION_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
		CommandProcessorFactory.setCommandProcessor(singleton);
		}
		return singleton;
			
	}

}
