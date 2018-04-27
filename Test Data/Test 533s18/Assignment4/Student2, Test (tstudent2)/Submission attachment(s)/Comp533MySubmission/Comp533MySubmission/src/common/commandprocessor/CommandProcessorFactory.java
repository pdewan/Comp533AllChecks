package common.commandprocessor;

import stringProcessors.HalloweenCommandProcessor;

public class CommandProcessorFactory {
	static HalloweenCommandProcessor commandProcessor;

	public static HalloweenCommandProcessor getCommandProcessor() {
		return commandProcessor;
	}

	public static void setCommandProcessor(
			HalloweenCommandProcessor commandProcessor) {
		CommandProcessorFactory.commandProcessor = commandProcessor;
	}
	

}
