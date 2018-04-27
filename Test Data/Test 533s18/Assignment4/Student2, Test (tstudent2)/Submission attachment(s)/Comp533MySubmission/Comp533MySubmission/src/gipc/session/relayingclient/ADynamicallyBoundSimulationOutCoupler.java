package gipc.session.relayingclient;

import rmi.simulation.client.AnRMISimulationOutCoupler;
import rmi.simulation.common.RMISimulationRelayer;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.IPCMechanism;

public class ADynamicallyBoundSimulationOutCoupler extends AnRMISimulationOutCoupler{

	public ADynamicallyBoundSimulationOutCoupler(
			HalloweenCommandProcessor anObservedSimulaton,
			 String aName) {
		super(anObservedSimulaton, null, aName, IPCMechanism.RMI);
		// TODO Auto-generated constructor stub
	}
	public RMISimulationRelayer getSessionServer() {
		RMISimulationRelayer result = super.getSessionServer();
		if (result == null)
			result = RelayerConnectorFactory.getSingleton().getCurrentRelayer();
		return result;
	}

}
