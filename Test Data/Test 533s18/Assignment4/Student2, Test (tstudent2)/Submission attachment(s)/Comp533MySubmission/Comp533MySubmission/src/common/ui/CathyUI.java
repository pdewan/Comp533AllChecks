package common.ui;

public interface CathyUI extends UI {

	public static final String NAME = "Cathy";
	public static final int SIMULATION_COMMAND_X_OFFSET = 400;
	public static final String SIMULATION_PREFIX = NAME + ":";
//	int CONTROLLER_X = SIMULATION_COMMAND_X_OFFSET + COMMAND_DISPLACEMENT;
	int CONTROLLER_X = ServerUI.CONTROLLER_X;
}
