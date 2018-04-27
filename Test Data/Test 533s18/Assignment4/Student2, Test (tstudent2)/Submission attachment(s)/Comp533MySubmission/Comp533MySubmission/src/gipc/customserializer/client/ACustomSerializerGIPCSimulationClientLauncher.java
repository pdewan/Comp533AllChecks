package gipc.customserializer.client;


import gipc.simulation.client.AGIPCSimulationClientLauncher;
import serialization.SerializerSelector;
import serialization.logical.ALogicalStructureSerializerFactory;
import stringProcessors.HalloweenCommandProcessor;





public class ACustomSerializerGIPCSimulationClientLauncher extends AGIPCSimulationClientLauncher  {	

	public ACustomSerializerGIPCSimulationClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName, HalloweenCommandProcessor aCommandProcessor,
			 boolean aBroadcastData) {
		super(aClientName, aServerHost, aServerId, aServerName, aCommandProcessor, aBroadcastData);
		
	}
	
	
	
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
//		Tracer.showInfo(true);

//		Tracer.setKeywordPrintStatus(ACustomSerializer.class, true);

		SerializerSelector.setSerializerFactory(new ALogicalStructureSerializerFactory());



		
	}

	
//	public  InputPort getRPCClientInputPort(PortAccessKind aPortAccessKind) {
//		Tracer.showInfo(true);
//
//		return DuplexRPCInputPortWithSyncReceiveSelector.createDuplexRPCClientInputPortWithSyncReceive(serverHost, serverId, serverName, clientName);
//	}
}
