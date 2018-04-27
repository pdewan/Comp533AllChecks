package rmi.simulation.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nio.manager.simulation.server.AnNIOManagerSimulationServer;
import common.controller.TrickOrTreatClientControllerFactory;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationRelayer;
import util.interactiveMethodInvocation.IPCMechanism;
import util.misc.ClearanceManagerFactory;
import util.remote.RelayerController;
import util.remote.RelayerControllerFactory;
import util.trace.port.consensus.ProposalLearnedNotificationSent;
import util.trace.port.consensus.RemoteProposeRequestReceived;
import util.trace.port.consensus.communication.CommunicationStateNames;

public class AnRMISimulationRelayer implements RMISimulationRelayer {
	protected Map<String, RMISimulationInCoupler> clients = new HashMap();
//	List<String> clientNames = new ArrayList();

	@Override
	public void join(String aName, RMISimulationInCoupler aClient)
			throws RemoteException {
		clients.put(aName, aClient);
//		clientNames.add(aName);
		RelayerControllerFactory.getOrCreateSingleton().setRelayClients(clients.keySet().toString());

		
	}

	@Override
	public void leave(String aName) throws RemoteException {
		clients.remove(aName);
//		clientNames.remove(aName);
		RelayerControllerFactory.getOrCreateSingleton().setRelayClients(clients.keySet().toString());

		
	}
	
	protected void maybeWaitForRelay() {
		RelayerController aRelayerController =  RelayerControllerFactory.getOrCreateSingleton();
		if (aRelayerController.isWaitForRelay()) {
			ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
		}

	}
	@Override
	public void broadcast(String aSource, String aCommand) throws RemoteException {
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand);
		doBroadcast(aSource, aCommand);
	}

	protected void doBroadcast(String aSource, String aCommand) throws RemoteException {
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand);
		Set<String> clientNames = clients.keySet();
		List<String> sortedClientNames = new ArrayList(clientNames);
		Collections.sort(sortedClientNames);
		for (String clientName:sortedClientNames) {
			if (aSource.equals(clientName) && !AnNIOManagerSimulationServer.getNIOManagerSimulationServer().isAtomicBroadcast())
				continue;
//			if (!TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelaySend()) {
//				maybeWaitForRelay();
//			};
			ProposalLearnedNotificationSent.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand);

			clients.get(clientName).processCommand(aCommand);
		}		
	}
	@Override
	public void broadcastBroadcastMode(String aSource, boolean newValue) throws RemoteException {
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		doBroadcastBroadcastMode(aSource, newValue);

	}
	
	protected void doBroadcastBroadcastMode(String aSource, boolean newValue) throws RemoteException {
//		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		Set<String> clientNames = clients.keySet();
		List<String> sortedClientNames = new ArrayList(clientNames);
		Collections.sort(sortedClientNames);
		for (String clientName:sortedClientNames) {
//			if (aSource.equals(clientName) && !AnNIOManagerSimulationServer.getNIOManagerSimulationServer().isAtomicBroadcast())
//				continue;
////			if (!TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelaySend()) {
////				maybeWaitForRelay();
////			};
			ProposalLearnedNotificationSent.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
			clients.get(clientName).newRemoteBroadcastMode(newValue);
		}	
		AnNIOManagerSimulationServer.getNIOManagerSimulationServer().setAtomicBroadcast(newValue);
		
	}
	@Override
	public void broadcastIPCMechanism(String aSource, IPCMechanism newValue) throws RemoteException {
		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		doBroadcastIPCMechanism(aSource, newValue);
	}
//	@Override
	protected void doBroadcastIPCMechanism(String aSource, IPCMechanism newValue) throws RemoteException {
//		RemoteProposeRequestReceived.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		Set<String> clientNames = clients.keySet();
		List<String> sortedClientNames = new ArrayList(clientNames);
		Collections.sort(sortedClientNames);
		for (String clientName:sortedClientNames) {
//			if (aSource.equals(clientName) && !AnNIOManagerSimulationServer.getNIOManagerSimulationServer().isAtomicBroadcast())
//				continue;
////			if (!TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelaySend()) {
////				maybeWaitForRelay();
////			};
			
			clients.get(clientName).newRemoteIPCMechanism(newValue);
		}		
	}
	

}
