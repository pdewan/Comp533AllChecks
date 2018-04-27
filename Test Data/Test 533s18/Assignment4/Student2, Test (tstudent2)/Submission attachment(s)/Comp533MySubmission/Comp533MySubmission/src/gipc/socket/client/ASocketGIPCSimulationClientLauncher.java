package gipc.socket.client;


import gipc.simulation.client.AGIPCSimulationClientLauncher;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.buffer.socket.ASocketDuplexBufferInputPortFactory;
import stringProcessors.HalloweenCommandProcessor;





public class ASocketGIPCSimulationClientLauncher extends AGIPCSimulationClientLauncher  {	

	public ASocketGIPCSimulationClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName, HalloweenCommandProcessor aCommandProcessor,
			 boolean aBroadcastData) {
		super(aClientName, aServerHost, aServerId, aServerName, aCommandProcessor, aBroadcastData);
		
	}
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(new ASocketDuplexBufferInputPortFactory());
	}		

}
