package gipc.syncrpc.launcher;

public class InteractiveDemoerOfSyncRPCGIPCSimulation {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				SimulationSyncRPCGIPCServerMain.class,
				AliceSimulationSyncRPCGIPCClient.class,
				BobSimulationSyncRPCGIPCClient.class,
				CathySimulationSyncRPCGIPCClient.class				
		};
//		MainClassLaunchingUtility.interactiveLaunch(classes);
		bus.uigen.pipe.MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
