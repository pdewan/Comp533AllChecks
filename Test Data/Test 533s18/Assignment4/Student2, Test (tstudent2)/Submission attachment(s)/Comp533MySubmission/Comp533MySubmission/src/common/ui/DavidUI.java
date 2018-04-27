package common.ui;

public interface DavidUI extends UI {

	public static final String NAME = "David";
	public static final int SIMULATION_COMMAND_X_OFFSET = 600;
	public static final String SIMULATION_PREFIX = NAME + ":";
//	int CONTROLLER_X = SIMULATION_COMMAND_X_OFFSET + COMMAND_DISPLACEMENT;
	int CONTROLLER_X = ServerUI.CONTROLLER_X;
}
