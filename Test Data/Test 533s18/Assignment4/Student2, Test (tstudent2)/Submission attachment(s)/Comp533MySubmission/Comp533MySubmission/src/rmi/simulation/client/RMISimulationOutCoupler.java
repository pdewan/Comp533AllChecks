package rmi.simulation.client;

import java.beans.PropertyChangeListener;

import rmi.simulation.common.RMISimulationRelayer;

public interface RMISimulationOutCoupler extends PropertyChangeListener{
	public RMISimulationRelayer getSessionServer() ;

	public void setSessionServer(RMISimulationRelayer sessionServer);

}
