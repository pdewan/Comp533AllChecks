package rmi.simulation.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.interactiveMethodInvocation.IPCMechanism;

public interface RMISimulationInCoupler extends Remote {
	public void processCommand(String aCommand) throws RemoteException;
	public void newRemoteBroadcastMode(boolean newValue) throws RemoteException;
	public void newRemoteIPCMechanism(IPCMechanism newValue) throws RemoteException;

}
