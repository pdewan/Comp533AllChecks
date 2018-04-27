package gipc.custom.simulation.launcher;

import gipc.simulation.server.AGIPCSimulationServerLauncher;

public class InteractiveDemoerOfGIPCSimulation {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				AGIPCSimulationServerLauncher.class,
				AliceSimulationGIPCClient.class,
				BobSimulationGIPCClient.class,
				CathySimulationGIPCClient.class
				
		};
		bus.uigen.pipe.MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
