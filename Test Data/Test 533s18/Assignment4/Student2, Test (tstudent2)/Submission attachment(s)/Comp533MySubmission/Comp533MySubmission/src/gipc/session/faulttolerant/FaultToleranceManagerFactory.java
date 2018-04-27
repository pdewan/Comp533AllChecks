package gipc.session.faulttolerant;

import gipc.session.relayingclient.RelayerElectorFactory;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public class FaultToleranceManagerFactory {
	static FaultToleranceManager singleton;
	public static void setSingleton(FaultToleranceManager newVal) {
		singleton = newVal;
	}
	public static FaultToleranceManager createSingleton(DuplexRPCSessionPort aSessionPort) {
		if (singleton == null) {
			singleton = new AFaultToleranceManager(aSessionPort);
			RelayerElectorFactory.setSingleton(singleton);
		}
		return singleton;
			
	}
	public static FaultToleranceManager getSingleton() {
		return singleton;
	}

}
