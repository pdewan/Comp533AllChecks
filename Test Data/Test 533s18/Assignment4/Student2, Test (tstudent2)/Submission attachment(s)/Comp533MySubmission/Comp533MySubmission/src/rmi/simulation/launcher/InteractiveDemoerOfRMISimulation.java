package rmi.simulation.launcher;

import rmi.simulation.registry.ADistributedRMIRegistryStarter;
import rmi.simulation.server.AnRMISimulationServerLauncher;

public class InteractiveDemoerOfRMISimulation {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				ADistributedRMIRegistryStarter.class,
				AnRMISimulationServerLauncher.class,
				AliceSimulationRMIClient.class,
				BobSimulationRMIClient.class,
				CathySimulationRMIClient.class

				
		};
//		MainClassLaunchingUtility.interactiveLaunch(classes);
		bus.uigen.pipe.MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
