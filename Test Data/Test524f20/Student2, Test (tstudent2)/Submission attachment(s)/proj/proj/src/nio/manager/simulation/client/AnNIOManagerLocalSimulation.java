package nio.manager.simulation.client;

import main.BeauAndersonFinalProject;
import stringProcessors.HalloweenCommandProcessor;

public class AnNIOManagerLocalSimulation implements NIOManagerSimulationProcessor{
	public static final String SIMULATION1_PREFIX = "1:";
//	public static final String SIMULATION2_PREFIX = "2:";
//	public static final int SIMULATION_2_X_OFFSET = 250;
//	public static final int SIMULATION_3_X_OFFSET = 500;

	public static int SIMULATION_COMMAND_Y_OFFSET = 0;
	public static int SIMULATION_WIDTH = 400;
	public static int SIMULATION_HEIGHT = 765;
	HalloweenCommandProcessor commandProcessor;
//	protected NIOSimulationInCoupler nioManagerSimulationInCoupler;
	@Override
	public void run() {
		
		createUI();
	}
	public void createUI(HalloweenCommandProcessor aCommandProcessor) {
		try {
			commandProcessor = aCommandProcessor;
//			nioManagerSimulationInCoupler = new AnNIOManagerSimulationInCoupler(commandProcessor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createUI(String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset) {
		HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject
				.createSimulation(aPrefix, anXOffset, aYOffset, aWidth,
						aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
		createUI(aCommandProcessor);
	
	}
	public void createUI(){
		
	}
	

}
