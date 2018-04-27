package nio.launcher;


public class InteractiveDemoerOfNIOTrickOrTreat {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				TrickOrTreatNioRelayingServerLauncher.class,
				AliceTrickOrTreatNioClient.class,
				BobTrickOrTreatNioClient.class,
				CathyTrickOrTreatNioClient.class

				
		};
//		MainClassLaunchingUtility.interactiveLaunch(classes);
		bus.uigen.pipe.MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
