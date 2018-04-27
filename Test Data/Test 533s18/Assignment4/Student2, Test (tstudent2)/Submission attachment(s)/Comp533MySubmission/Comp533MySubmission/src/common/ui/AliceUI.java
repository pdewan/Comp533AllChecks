package common.ui;

public interface AliceUI extends UI {

	public static final String NAME = "Alice";
	public static final int SIMULATION_COMMAND_X_OFFSET = 0;
	public static final String SIMULATION_PREFIX = NAME + ":";
//	int CONTROLLER_X = SIMULATION_COMMAND_X_OFFSET + COMMAND_DISPLACEMENT;
	int CONTROLLER_X = ServerUI.CONTROLLER_X;
}
