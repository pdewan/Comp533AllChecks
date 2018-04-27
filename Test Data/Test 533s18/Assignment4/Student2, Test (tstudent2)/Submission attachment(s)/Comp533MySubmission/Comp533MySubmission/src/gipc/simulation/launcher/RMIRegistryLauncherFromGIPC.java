package gipc.simulation.launcher;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import rmi.simulation.launcher.RMIRegistryLauncher;
import rmi.simulation.registry.ADistributedRMIRegistryStarter;
import util.annotations.Tags;
import util.tags.DistributedTags;
import assignments.util.mainArgs.ServerArgsProcessor;

@Tags({DistributedTags.REGISTRY})
public class RMIRegistryLauncherFromGIPC {
	public static void main (String[] args) {
		RMIRegistryLauncher.main(args);
	}
	
}
