package nio.couplers;

import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;
import util.trace.port.nio.RemoteCommandExecuted;

public class AnNIOSimulationInCoupler implements NIOSimulationInCoupler{
	HalloweenCommandProcessor commandProcessor;
	int numReceivedCommands = 0;
	public AnNIOSimulationInCoupler(HalloweenCommandProcessor aCommandProcessor) {
		commandProcessor = aCommandProcessor;
	}

	public synchronized void handleResponse(byte[] rsp) {
		String aCommand = new String(rsp);
		Tracer.info(this, numReceivedCommands + " received command:" + aCommand);
		String[] aCommands = aCommand.split(COMMAND_SEPARATOR);
		for (String anActualCommand:aCommands) {
//			commandProcessor.processCommand(new String(rsp))			
			commandProcessor.processCommand(anActualCommand);
			RemoteCommandExecuted.newCase(this, anActualCommand);
		}
		numReceivedCommands++;
//		System.out.println(new String(rsp));
		
	}

}
