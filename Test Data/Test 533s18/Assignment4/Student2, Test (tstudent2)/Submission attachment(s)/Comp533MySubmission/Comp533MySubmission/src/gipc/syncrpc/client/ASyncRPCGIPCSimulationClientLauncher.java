package gipc.syncrpc.client;


import gipc.customserializer.client.ACustomSerializerGIPCSimulationClientLauncher;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.SynchronousDuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingDuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingSyncReceiveSentCallCompleterCallFactory;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;





public class ASyncRPCGIPCSimulationClientLauncher extends ACustomSerializerGIPCSimulationClientLauncher  {	

	public ASyncRPCGIPCSimulationClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName, HalloweenCommandProcessor aCommandProcessor,
			 boolean aBroadcastData) {
		super(aClientName, aServerHost, aServerId, aServerName, aCommandProcessor, aBroadcastData);
		
	}
	
	
	
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
		Tracer.setKeywordPrintStatus(AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory.class, true);
//		Tracer.setKeywordPrintStatus(ACustomSerializer.class, true);
//		(AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory.class, true);
//		Tracer.showInfo(true);
//		SerializerSelector.setSerializerFactory(new ACustomSerializerFactory());

		DuplexRPCInputPortSelector.setInputPortFactory(new AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory());
		
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new AProcedureSyncingSyncReceiveSentCallCompleterCallFactory());
		SynchronousDuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new AProcedureSyncingDuplexReceivedCallInvokerFactory());

		
	}

	
//	public  InputPort getRPCClientInputPort(PortAccessKind aPortAccessKind) {
//		Tracer.showInfo(true);
//
//		return DuplexRPCInputPortWithSyncReceiveSelector.createDuplexRPCClientInputPortWithSyncReceive(serverHost, serverId, serverName, clientName);
//	}
}
