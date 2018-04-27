package gipc.simulation.server;

import common.controller.ServerControllerUI;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import port.ATracingConnectionListener;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.server.AnRMISimulationRelayer;
import util.trace.Tracer;



public class AGIPCSimulationServerLauncher extends AnAbstractDuplexRPCServerPortLauncher implements GIPCSimulationServerLauncher   {
	public AGIPCSimulationServerLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public AGIPCSimulationServerLauncher() {
	}

	protected RMISimulationRelayer getSessionServer() {
		return new AnRMISimulationRelayer();
	}
	
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}

	protected void registerRemoteObjects() {
		DuplexRPCServerInputPort anRPCServerInputPort = (DuplexRPCServerInputPort) mainPort;
		RMISimulationRelayer sessionServer = getSessionServer();
		anRPCServerInputPort.register(sessionServer);
	}
	
	public static void main (String[] args) {
		Tracer.showWarnings(false);
		ServerControllerUI.createUI();
		Tracer.showInfo(true);


		(new AGIPCSimulationServerLauncher(SESSION_SERVER, SESSION_SERVER_ID)).launch();
	}	
}
