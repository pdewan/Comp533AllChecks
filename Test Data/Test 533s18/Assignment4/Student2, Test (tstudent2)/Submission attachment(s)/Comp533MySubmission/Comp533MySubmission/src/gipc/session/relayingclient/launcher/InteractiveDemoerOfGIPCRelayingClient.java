package gipc.session.relayingclient.launcher;

import bus.uigen.pipe.MainClassLaunchingUtility;
import gipc.session.sessionmanager.ACustomSerializerSessionServerLauncher;

public class InteractiveDemoerOfGIPCRelayingClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
//				ACustomSerializerGIPCSimulationServerLauncher.class,
				ACustomSerializerSessionServerLauncher.class,
				AliceSimulationGIPCRelayingClient.class,
				BobSimulationGIPCRelayingClient.class,
				CathySimulationGIPCRelayingClient.class,
//				ABobDuplexRPCSessionPort.class,
//				AModularCathyDuplexRPCSessionPort.class,
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
