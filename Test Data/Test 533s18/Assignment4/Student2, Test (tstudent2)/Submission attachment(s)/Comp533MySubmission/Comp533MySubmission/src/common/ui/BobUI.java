package common.ui;

public interface BobUI extends UI {

	public static final String NAME = "Bob";
	public static final int SIMULATION_COMMAND_X_OFFSET = 200;
	public static final String SIMULATION_PREFIX = NAME + ":";
//	int CONTROLLER_X = SIMULATION_COMMAND_X_OFFSET + COMMAND_DISPLACEMENT;
	int CONTROLLER_X = ServerUI.CONTROLLER_X;




}
