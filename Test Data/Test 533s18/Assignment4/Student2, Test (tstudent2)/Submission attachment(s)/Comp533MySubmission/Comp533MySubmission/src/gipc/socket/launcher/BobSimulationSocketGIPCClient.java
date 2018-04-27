package gipc.socket.launcher;

import common.ui.BobUI;
import gipc.simulation.server.GIPCSimulationServerLauncher;
import gipc.socket.client.ASocketGIPCSimulationClientLauncher;

//import niotut.RspHandler;

import main.BeauAndersonFinalProject;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class BobSimulationSocketGIPCClient implements BobUI {

	public static final String SIMULATION2_PREFIX = "2:";

public static void main(String[] args) {
	Tracer.showWarnings(false);

	HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject.createSimulation(
			SIMULATION2_PREFIX, SIMULATION_COMMAND_X_OFFSET, SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH, SIMULATION_HEIGHT, SIMULATION_COMMAND_X_OFFSET, 0);
	(new ASocketGIPCSimulationClientLauncher(NAME, "localhost", GIPCSimulationServerLauncher.SESSION_SERVER_ID, GIPCSimulationServerLauncher.SESSION_SERVER, aCommandProcessor, true )).launch();


  }
}

