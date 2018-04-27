package common.controller;

import common.commandprocessor.ProgrammedCommandExecutor;
import util.remote.RelayerClientController;

public interface TrickOrTreatClientController extends RelayerClientController, ProgrammedCommandExecutor{
	boolean isMarkCommands();

	void setMarkCommands(boolean markCommands);

	int getInterCommandTime();

	void setInterCommandTime(int interCommandTime);

	void setCommandExecutionTime(long commandExecutionTime);

}
