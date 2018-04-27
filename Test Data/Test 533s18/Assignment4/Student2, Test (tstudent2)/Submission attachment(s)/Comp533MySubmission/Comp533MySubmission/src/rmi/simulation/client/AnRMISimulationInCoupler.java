package rmi.simulation.client;

import java.rmi.RemoteException;

import nio.manager.simulation.client.AnNIOManagerSimulationClient;
import nio.manager.simulation.server.AnNIOManagerSimulationServer;
import common.controller.TrickOrTreatClientControllerFactory;
import rmi.simulation.common.RMISimulationInCoupler;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.IPCMechanism;
import util.trace.port.consensus.ProposalLearnedNotificationReceived;
import util.trace.port.consensus.communication.CommunicationStateNames;

public class AnRMISimulationInCoupler implements RMISimulationInCoupler {
	HalloweenCommandProcessor commandProcessor;
	public AnRMISimulationInCoupler(HalloweenCommandProcessor aCommandProcessor) {
		commandProcessor = aCommandProcessor;
	}

	@Override
	public void processCommand(String aCommand)  {
		ProposalLearnedNotificationReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, aCommand);
		TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelayReceive();
		commandProcessor.processCommand(aCommand);		
	}

	@Override
	public void newRemoteBroadcastMode(boolean newValue) throws RemoteException {
		ProposalLearnedNotificationReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		AnNIOManagerSimulationClient.getNIOManagerSimulationClient().setAtomicBroadcast(newValue);

	}

	@Override
	public void newRemoteIPCMechanism(IPCMechanism newValue) throws RemoteException {
		ProposalLearnedNotificationReceived.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		AnNIOManagerSimulationClient.getNIOManagerSimulationClient().setIPCMechanism(newValue);

		
	}

}
