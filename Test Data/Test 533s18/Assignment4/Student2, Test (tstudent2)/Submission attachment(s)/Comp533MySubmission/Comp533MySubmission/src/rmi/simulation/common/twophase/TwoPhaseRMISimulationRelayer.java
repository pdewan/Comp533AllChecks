package rmi.simulation.common.twophase;

import java.rmi.RemoteException;

import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationRelayer;

public interface TwoPhaseRMISimulationRelayer extends RMISimulationRelayer {
	void join (String aName, TwoPhaseRMISimulationInCoupler aClient) throws RemoteException ;

}
