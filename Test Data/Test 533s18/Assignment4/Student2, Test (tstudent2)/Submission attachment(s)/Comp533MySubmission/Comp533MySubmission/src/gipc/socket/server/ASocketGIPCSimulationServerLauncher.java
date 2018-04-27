package gipc.socket.server;

import gipc.simulation.server.AGIPCSimulationServerLauncher;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.buffer.socket.ASocketDuplexBufferInputPortFactory;



public class ASocketGIPCSimulationServerLauncher extends AGIPCSimulationServerLauncher   {
	public ASocketGIPCSimulationServerLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	

	public static void main (String[] args) {
		(new ASocketGIPCSimulationServerLauncher(SESSION_SERVER, SESSION_SERVER_ID)).launch();
	}	
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(new ASocketDuplexBufferInputPortFactory());
	}

}
