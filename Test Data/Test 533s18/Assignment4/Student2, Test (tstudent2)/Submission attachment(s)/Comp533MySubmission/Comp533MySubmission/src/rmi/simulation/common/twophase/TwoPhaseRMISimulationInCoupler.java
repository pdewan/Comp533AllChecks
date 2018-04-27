package rmi.simulation.common.twophase;

import java.rmi.RemoteException;

import rmi.simulation.common.RMISimulationInCoupler;
import util.interactiveMethodInvocation.IPCMechanism;

public interface TwoPhaseRMISimulationInCoupler extends RMISimulationInCoupler{
	public boolean acceptRemoteBroadcastMode(boolean newValue) throws RemoteException;
	public boolean acceptRemoteIPCMechanism(IPCMechanism newValue) throws RemoteException;
	public boolean acceptNewCommand(String newValue) throws RemoteException;

}
