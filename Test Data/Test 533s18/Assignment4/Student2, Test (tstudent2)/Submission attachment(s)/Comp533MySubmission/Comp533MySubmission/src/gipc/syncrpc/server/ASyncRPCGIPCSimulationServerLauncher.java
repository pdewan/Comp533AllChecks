package gipc.syncrpc.server;

import gipc.customserializer.server.ACustomSerializerGIPCSimulationServerLauncher;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.SynchronousDuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingDuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingSyncReceiveSentCallCompleterCallFactory;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;



public class ASyncRPCGIPCSimulationServerLauncher extends ACustomSerializerGIPCSimulationServerLauncher   {
	public ASyncRPCGIPCSimulationServerLauncher(String aServerName,
			String aServerId) {
		super (aServerName, aServerId);
	}	
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
//		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
		Tracer.setImplicitDisplayKeywordKind(ImplicitKeywordKind.OBJECT_PACKAGE_NAME);
		Tracer.setKeywordPrintStatus(AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory.class, true);
//		Tracer.setKeywordPrintStatus(ACustomSerializer.class, true);
//		SerializerSelector.setSerializerFactory(new ACustomSerializerFactory());
		DuplexRPCInputPortSelector.setInputPortFactory(new AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory());
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new AProcedureSyncingSyncReceiveSentCallCompleterCallFactory());
		SynchronousDuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new AProcedureSyncingDuplexReceivedCallInvokerFactory());
		
	}
		
//	public  InputPort getRPCServerInputPort(PortAccessKind aPortAccessKind) {
//		
//		return DuplexRPCInputPortWithSyncReceiveSelector.createDuplexRPCServerInputPortWithSyncReceive(serverId, serverName);
//	}
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		(new ASyncRPCGIPCSimulationServerLauncher(SESSION_SERVER, SESSION_SERVER_ID)).launch();
	}

}
