package common.commandprocessor;

public interface ProgrammedCommandExecutor {
	String DO_NAME = "d";
	String UNDO_NAME = "u";
	String EXECUTE_NAME = "e";
	String FORWARD_X = "x";
	String FORWARD_Y = "y";
	void doTimedCommands();
	void undoTimedCommands();
	long getCommandExecutionTime();
	void executeCommandLoop();

}
