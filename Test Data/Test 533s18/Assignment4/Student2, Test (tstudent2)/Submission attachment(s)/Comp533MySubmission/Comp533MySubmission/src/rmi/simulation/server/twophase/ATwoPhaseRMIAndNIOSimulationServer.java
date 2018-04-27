package rmi.simulation.server.twophase;

import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.common.twophase.TwoPhaseRMISimulationRelayer;
import rmi.simulation.server.AnRMIAndNIOSimulationServer;
import rmi.simulation.server.AnRMISimulationRelayer;

public class ATwoPhaseRMIAndNIOSimulationServer extends AnRMIAndNIOSimulationServer  {
	@Override
	protected TwoPhaseRMISimulationRelayer createRMIRelayer() {
		return new ATwoPhaseRMISimulationRelayer();
	}
}
