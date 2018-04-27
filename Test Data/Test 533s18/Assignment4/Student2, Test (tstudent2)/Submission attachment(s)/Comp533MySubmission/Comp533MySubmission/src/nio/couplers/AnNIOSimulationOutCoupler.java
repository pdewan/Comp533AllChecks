package nio.couplers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import common.controller.TrickOrTreatClientControllerFactory;
import nio.client.TrickOrTreatNioClient;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;
import util.trace.port.nio.LocalCommandObserved;

public class AnNIOSimulationOutCoupler implements PropertyChangeListener {
	HalloweenCommandProcessor observingSimulation;
	TrickOrTreatNioClient client;
	int numSentCommands = 0;
	
	public AnNIOSimulationOutCoupler (HalloweenCommandProcessor anObservedSimulaton, TrickOrTreatNioClient aClient) {
		anObservedSimulaton.addPropertyChangeListener(this);
		client = aClient;
	}

	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (!anEvent.getPropertyName().equals("InputString")) return;
		String newCommand = (String) anEvent.getNewValue();
		LocalCommandObserved.newCase(this, newCommand);

		try {
			Tracer.info(this, numSentCommands + " Sending command:" + newCommand);
			String markedCommand = newCommand + NIOSimulationInCoupler.COMMAND_SEPARATOR;
			if (TrickOrTreatClientControllerFactory.getOrCreateSingleton().isMarkCommands())
			client.send(markedCommand.getBytes());
			else client.send(newCommand.getBytes());
			numSentCommands++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Received command:" + newCommand);
//		observingSimulation.processCommand(newCommand);
	}

	

}
