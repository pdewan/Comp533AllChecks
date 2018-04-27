package gipc.session.relayingclient;

import rmi.simulation.common.RMISimulationInCoupler;
import sessionport.rpc.duplex.DuplexRPCSessionPort;


public class RelayerConnectorFactory {
	static RelayerConnector singleton;
	public static void setSingleton(RelayerConnector newVal) {
		singleton = newVal;
	}
	public static RelayerConnector createSingleton(DuplexRPCSessionPort aSessionPort, RMISimulationInCoupler anInCoupler) {
		if (singleton == null) {
			singleton = new ARelayerConnector(aSessionPort, anInCoupler);
		}
		return singleton;
			
	}
	public static RelayerConnector getSingleton() {
		return singleton;
	}
}
