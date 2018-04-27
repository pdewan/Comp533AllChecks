package rmi.simulation.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.interactiveMethodInvocation.IPCMechanism;


public interface RMISimulationRelayer extends Remote {
	void join (String aName, RMISimulationInCoupler aClient) throws RemoteException ;
	void leave (String aName) throws RemoteException;
	void broadcast (String aSource, String aCommand) throws RemoteException;
	void broadcastBroadcastMode(String aSource, boolean newValue)
			throws RemoteException;
	void broadcastIPCMechanism(String aSource, IPCMechanism newValue)
			throws RemoteException;

}
