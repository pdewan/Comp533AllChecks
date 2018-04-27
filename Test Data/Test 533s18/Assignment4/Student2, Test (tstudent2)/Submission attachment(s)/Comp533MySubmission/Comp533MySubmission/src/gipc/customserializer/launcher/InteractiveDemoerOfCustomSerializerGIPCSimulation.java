package gipc.customserializer.launcher;

public class InteractiveDemoerOfCustomSerializerGIPCSimulation {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				SimulationCustomSerializerGIPCServerMain.class,
				AliceSimulationCustomSerializerGIPCClient.class,
				BobSimulationCustomSerializerGIPCClient.class,
				CathySimulationCustomSerializerGIPCClient.class				
		};
//		MainClassLaunchingUtility.interactiveLaunch(classes);
		bus.uigen.pipe.MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
