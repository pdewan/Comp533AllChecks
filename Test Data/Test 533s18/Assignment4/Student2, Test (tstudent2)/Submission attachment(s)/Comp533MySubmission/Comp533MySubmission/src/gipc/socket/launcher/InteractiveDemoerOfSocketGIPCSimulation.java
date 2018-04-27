package gipc.socket.launcher;

import gipc.socket.server.ASocketGIPCSimulationServerLauncher;

public class InteractiveDemoerOfSocketGIPCSimulation {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				ASocketGIPCSimulationServerLauncher.class,
				AliceSimulationSocketGIPCClient.class

				
		};
//		MainClassLaunchingUtility.interactiveLaunch(classes);
		bus.uigen.pipe.MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
