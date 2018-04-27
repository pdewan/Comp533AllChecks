package rmi.simulation.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import nio.manager.simulation.client.AnNIOManagerSimulationClient;
import common.controller.TrickOrTreatClientControllerFactory;
import rmi.simulation.common.RMISimulationRelayer;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.IPCMechanism;
import util.trace.port.consensus.ActionWhileEnablingProposalIsPending;
import util.trace.port.consensus.RemoteProposeRequestSent;
import util.trace.port.consensus.communication.CommunicationStateNames;

public class AnRMISimulationOutCoupler implements PropertyChangeListener {
	HalloweenCommandProcessor observingSimulation;
	RMISimulationRelayer sessionServer;
	IPCMechanism myIPCMechanism;
	

	String myName;
	
	public AnRMISimulationOutCoupler (HalloweenCommandProcessor anObservedSimulaton, RMISimulationRelayer aSessionServer, String aName, IPCMechanism aMyIPCMechanism) {
		anObservedSimulaton.addPropertyChangeListener(this);
		sessionServer = aSessionServer;
		myName = aName;
		myIPCMechanism = aMyIPCMechanism;
	}

	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (!anEvent.getPropertyName().equals("InputString")) return;
		if (AnRMIAndNIOSimulationClient.getRMIAndNIOSimulationClient().isAtomicBroadcast() == null) {
			ActionWhileEnablingProposalIsPending.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, anEvent);
			return;
		}
		if (AnRMIAndNIOSimulationClient.getRMIAndNIOSimulationClient().getIPCMechanism() == null) {
			ActionWhileEnablingProposalIsPending.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, anEvent);
			return;
		}
		if (AnRMIAndNIOSimulationClient.getRMIAndNIOSimulationClient().isLocalProcessingOnly() || 
				AnRMIAndNIOSimulationClient.getNIOManagerSimulationClient().getIPCMechanism() != myIPCMechanism) {
			return;
		}
		String newCommand = (String) anEvent.getNewValue();
		try {
//			if (Settingsfactory.getOrCreateSettings().isWaitForSend()) {
//				ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
//			} else if (Settingsfactory.getOrCreateSettings().isDelaySend()) {
//				ThreadSupport.sleep((Settingsfactory.getOrCreateSettings().getSendDelay()));
//			}
//			TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelaySend();
			RemoteProposeRequestSent.newCase(this, CommunicationStateNames.COMMAND, -1, newCommand);
			getSessionServer().broadcast(myName, newCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Received command:" + newCommand);
//		observingSimulation.processCommand(newCommand);
	}

	
	public RMISimulationRelayer getSessionServer() {
		return sessionServer;
	}

	public void setSessionServer(RMISimulationRelayer sessionServer) {
		this.sessionServer = sessionServer;
	}
}
