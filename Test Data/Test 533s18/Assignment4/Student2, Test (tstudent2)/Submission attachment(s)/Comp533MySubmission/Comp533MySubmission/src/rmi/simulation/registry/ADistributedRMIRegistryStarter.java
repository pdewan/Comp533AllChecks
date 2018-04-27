package rmi.simulation.registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import util.annotations.DisplayToString;
import util.annotations.Tags;
import util.tags.DistributedTags;
import assignments.util.mainArgs.RegistryArgsProcessor;
import assignments.util.mainArgs.ServerArgsProcessor;

@Tags({DistributedTags.REGISTRY, DistributedTags.RMI})
public class ADistributedRMIRegistryStarter {
	public static void launch (String[] args) {
		try {
			Registry aRegistry = LocateRegistry.createRegistry(RegistryArgsProcessor.getRegistryPort(args));
			// Keep server alive, wait indefinitely			
			synchronized(aRegistry) {
				aRegistry.wait();				
			}
//			Scanner scanner = new Scanner(System.in);
//			scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
