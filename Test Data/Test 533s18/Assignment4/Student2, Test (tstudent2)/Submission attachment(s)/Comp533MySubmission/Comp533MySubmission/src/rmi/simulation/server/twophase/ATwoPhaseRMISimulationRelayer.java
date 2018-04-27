package rmi.simulation.server.twophase;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nio.manager.simulation.server.AnNIOManagerSimulationServer;
import common.controller.TrickOrTreatClientControllerFactory;
import consensus.ProposalFeedbackKind;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationInCoupler;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationRelayer;
import rmi.simulation.server.AnRMISimulationRelayer;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;
import util.misc.ClearanceManagerFactory;
import util.remote.RelayerController;
import util.remote.RelayerControllerFactory;
import util.trace.port.consensus.ProposalAcceptRequestReceived;
import util.trace.port.consensus.ProposalAcceptRequestSent;
import util.trace.port.consensus.ProposalAcceptedNotificationReceived;
import util.trace.port.consensus.ProposalAcceptedNotificationSent;
import util.trace.port.consensus.ProposalLearnedNotificationSent;
import util.trace.port.consensus.RemoteProposeRequestReceived;
import util.trace.port.consensus.communication.CommunicationStateNames;

public class ATwoPhaseRMISimulationRelayer extends AnRMISimulationRelayer implements  TwoPhaseRMISimulationRelayer  {
//	Map<String, RMISimulationInCoupler> clients = new HashMap();
//	List<String> clientNames = new ArrayList();

	@Override
	public void join(String aName, TwoPhaseRMISimulationInCoupler aClient)
			throws RemoteException {
		super.join(aName, aClient);

		
	}
	@Override
	public void broadcast(String aSource, String aCommand) throws RemoteException {
		if (!AnNIOManagerSimulationServer.getNIOManagerSimulationServer().isAtomicBroadcast() ||
				AnNIOManagerSimulationServer.getNIOManagerSimulationServer().getConsensusAlgorithm() == ConsensusAlgorithm.CENTRALIZED_ASYNCHRONOUS) {
			super.broadcast(aSource, aCommand);
			return;
		}
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand);
		Set<String> clientNames = clients.keySet();
		List<String> sortedClientNames = new ArrayList(clientNames);
		Collections.sort(sortedClientNames);
		boolean anAllAccept = true;
		for (String clientName:sortedClientNames) {
			ProposalAcceptRequestSent.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand);
			boolean anAccept = ((TwoPhaseRMISimulationInCoupler) (clients.get(clientName))).acceptNewCommand(aCommand);
			ProposalFeedbackKind aProposalFeedbackKind = anAccept?ProposalFeedbackKind.SUCCESS:ProposalFeedbackKind.SERVICE_DENIAL;
			ProposalAcceptedNotificationReceived.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand, aProposalFeedbackKind);
			anAllAccept = anAccept && anAllAccept;
		}
		if (!anAllAccept) {
			return;
		}
		super.doBroadcast(aSource, aCommand);
	}
	@Override
	public void broadcastBroadcastMode(String aSource, boolean newValue) throws RemoteException {
		if (AnNIOManagerSimulationServer.getNIOManagerSimulationServer().getConsensusAlgorithm() == ConsensusAlgorithm.CENTRALIZED_ASYNCHRONOUS) {
			super.broadcastBroadcastMode(aSource, newValue);
			return;
		}
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		Set<String> clientNames = clients.keySet();
		List<String> sortedClientNames = new ArrayList(clientNames);
		Collections.sort(sortedClientNames);
		boolean anAllAccept = true;
		for (String clientName:sortedClientNames) {
			ProposalAcceptRequestSent.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
			boolean anAccept = ((TwoPhaseRMISimulationInCoupler) (clients.get(clientName))).acceptRemoteBroadcastMode(newValue);
			ProposalFeedbackKind aProposalFeedbackKind = anAccept?ProposalFeedbackKind.SUCCESS:ProposalFeedbackKind.SERVICE_DENIAL;
			ProposalAcceptedNotificationReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue, aProposalFeedbackKind);
			anAllAccept = anAccept;
		}	
		if (!anAllAccept) {
			return;
		}
		super.doBroadcastBroadcastMode(aSource, newValue);		
	}
	@Override
	public void broadcastIPCMechanism(String aSource, IPCMechanism newValue) throws RemoteException {
		if (AnNIOManagerSimulationServer.getNIOManagerSimulationServer().getConsensusAlgorithm() == ConsensusAlgorithm.CENTRALIZED_ASYNCHRONOUS) {
			super.broadcastIPCMechanism(aSource, newValue);
			return;
		}
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		Set<String> clientNames = clients.keySet();
		List<String> sortedClientNames = new ArrayList(clientNames);
		Collections.sort(sortedClientNames);
		boolean anAllAccept = true;
		for (String clientName:sortedClientNames) {
			ProposalAcceptRequestSent.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
			boolean anAccept = ((TwoPhaseRMISimulationInCoupler) (clients.get(clientName))).acceptRemoteIPCMechanism(newValue);
			ProposalFeedbackKind aProposalFeedbackKind = anAccept?ProposalFeedbackKind.SUCCESS:ProposalFeedbackKind.SERVICE_DENIAL;
			ProposalAcceptedNotificationReceived.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue, aProposalFeedbackKind);
			anAllAccept = anAccept;
		}	
		if (!anAllAccept) {
			return;
		}
		super.doBroadcastIPCMechanism(aSource, newValue);	
	}
	

}
