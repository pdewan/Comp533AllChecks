package gipc.customserializer.server;

import gipc.simulation.server.AGIPCSimulationServerLauncher;
import serialization.SerializerSelector;
import serialization.logical.ALogicalStructureSerializer;
import serialization.logical.ALogicalStructureSerializerFactory;
import util.trace.Tracer;



public class ACustomSerializerGIPCSimulationServerLauncher extends AGIPCSimulationServerLauncher   {
	public ACustomSerializerGIPCSimulationServerLauncher(String aServerName,
			String aServerId) {
		super (aServerName, aServerId);

	}	
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setImplicitDisplayKeywordKind(ImplicitKeywordKind.OBJECT_PACKAGE_NAME);
//		Tracer.setKeywordPrintStatus(AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory.class, true);
		Tracer.setKeywordPrintStatus(ALogicalStructureSerializer.class, true);
		SerializerSelector.setSerializerFactory(new ALogicalStructureSerializerFactory());
//		DuplexRPCInputPortSelector.setInputPortFactory(new AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory());
//		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new AProcedureSyncingSyncReceiveSentCallCompleterCallFactory());
//		SynchronousDuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new AProcedureSyncingDuplexReceivedCallInvokerFactory());
		
	}
		
//	public  InputPort getRPCServerInputPort(PortAccessKind aPortAccessKind) {
//		
//		return DuplexRPCInputPortWithSyncReceiveSelector.createDuplexRPCServerInputPortWithSyncReceive(serverId, serverName);
//	}
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		(new ACustomSerializerGIPCSimulationServerLauncher(SESSION_SERVER, SESSION_SERVER_ID)).launch();
	}

}
