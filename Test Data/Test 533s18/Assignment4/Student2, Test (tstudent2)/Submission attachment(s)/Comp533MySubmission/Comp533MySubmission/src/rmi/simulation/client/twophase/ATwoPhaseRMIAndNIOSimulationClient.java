package rmi.simulation.client.twophase;

import rmi.simulation.client.AnRMIAndNIOSimulationClient;
import rmi.simulation.client.AnRMISimulationInCoupler;
import rmi.simulation.common.RMISimulationInCoupler;

public class ATwoPhaseRMIAndNIOSimulationClient extends AnRMIAndNIOSimulationClient{
	@Override
	protected RMISimulationInCoupler createRMIInCoupler() {
		return new ATwoPhaseRMISimulationInCoupler(commandProcessor);
	}

}
