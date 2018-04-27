package gipc.session.faulttolerant.launcher;

import bus.uigen.pipe.MainClassLaunchingUtility;
import gipc.session.sessionmanager.ACustomSerializerSessionServerLauncher;

public class InteractiveDemoerOfGIPCFaultTolerantRelayingClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ACustomSerializerSessionServerLauncher.class,
				AliceSimulationGIPCFaultTolerantRelayingClient.class,
				BobSimulationGIPCFaultTolerantRelayingClient.class,
				CathySimulationGIPCFaultTolerantRelayingClient.class,
				DavidSimulationGIPCFaultTolerantRelayingClient.class,

//				ABobDuplexRPCSessionPort.class,
//				AModularCathyDuplexRPCSessionPort.class,
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
