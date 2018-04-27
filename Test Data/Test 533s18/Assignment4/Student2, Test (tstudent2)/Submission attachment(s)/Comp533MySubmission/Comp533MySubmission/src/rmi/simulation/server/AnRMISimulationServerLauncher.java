package rmi.simulation.server;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.controller.ServerControllerUI;
import rmi.simulation.common.RMISimulationServerLauncher;
import rmi.simulation.common.RMISimulationRelayer;



public class AnRMISimulationServerLauncher implements RMISimulationServerLauncher {	
	public static void main (String[] args) {
		try {
			ServerControllerUI.createUI();
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RMISimulationRelayer session = new AnRMISimulationRelayer();
			UnicastRemoteObject.exportObject(session, 0);
//			UnicastRemoteObject.exportObject(counterRepository, 0);
			rmiRegistry.rebind(SESSION_SERVER, session);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
