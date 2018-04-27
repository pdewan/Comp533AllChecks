package rmi.simulation.client.twophase;

import java.rmi.RemoteException;

import nio.manager.simulation.client.AnNIOManagerSimulationClient;
import assignments.util.MiscAssignmentUtils;
import rmi.simulation.client.AnRMISimulationInCoupler;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationInCoupler;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.IPCMechanism;
import util.trace.port.consensus.ProposalAcceptRequestReceived;
import util.trace.port.consensus.ProposalAcceptedNotificationSent;
import util.trace.port.consensus.communication.CommunicationStateNames;

public class ATwoPhaseRMISimulationInCoupler extends AnRMISimulationInCoupler
		implements TwoPhaseRMISimulationInCoupler {

	public ATwoPhaseRMISimulationInCoupler(
			HalloweenCommandProcessor aCommandProcessor) {
		super(aCommandProcessor);
	}

	@Override
	public boolean acceptRemoteBroadcastMode(boolean newValue)
			throws RemoteException {
		ProposalAcceptRequestReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		boolean retVal = !AnNIOManagerSimulationClient.getNIOManagerSimulationClient().isRejectMetaStateChange();
		ProposalAcceptedNotificationSent.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue, MiscAssignmentUtils.toProposalFeedbackKind(retVal));
		return retVal;
	}

	@Override
	public boolean acceptRemoteIPCMechanism(IPCMechanism newValue)
			throws RemoteException {
		ProposalAcceptRequestReceived.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		boolean retVal = !AnNIOManagerSimulationClient.getNIOManagerSimulationClient().isRejectMetaStateChange();
		ProposalAcceptedNotificationSent.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue, MiscAssignmentUtils.toProposalFeedbackKind(retVal));

		return retVal;
	}

	@Override
	public boolean acceptNewCommand(String newValue) throws RemoteException {
		ProposalAcceptRequestReceived.newCase(this, CommunicationStateNames.COMMAND, -1, newValue);
		boolean retVal = !AnNIOManagerSimulationClient.getNIOManagerSimulationClient().isRejectMetaStateChange();
		ProposalAcceptedNotificationSent.newCase(this, CommunicationStateNames.COMMAND, -1, newValue, MiscAssignmentUtils.toProposalFeedbackKind(retVal));
		return retVal;
	}

}
