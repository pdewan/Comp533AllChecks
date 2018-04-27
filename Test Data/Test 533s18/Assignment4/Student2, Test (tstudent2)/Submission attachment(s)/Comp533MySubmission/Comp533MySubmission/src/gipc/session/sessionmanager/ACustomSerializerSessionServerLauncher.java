package gipc.session.sessionmanager;

import inputport.nio.manager.AScatterGatherSelectionManager;
import port.sessionserver.ASessionServerLauncher;
import serialization.SerializerSelector;
import serialization.logical.ALogicalStructureSerializer;
import serialization.logical.ALogicalStructureSerializerFactory;
import util.trace.Tracer;

public class ACustomSerializerSessionServerLauncher extends ASessionServerLauncher{

	public ACustomSerializerSessionServerLauncher(String aSessionServerId,
			String aSessionServerName, String aLogicalServerName) {
		super(aSessionServerId, aSessionServerName, aLogicalServerName);
	}
	public ACustomSerializerSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeywordPrintStatus(AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory.class, true);
		Tracer.setKeywordPrintStatus(ALogicalStructureSerializer.class, true);
//		(AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory.class, true);
//		Tracer.showInfo(true);
		SerializerSelector.setSerializerFactory(new ALogicalStructureSerializerFactory());

//		DuplexRPCInputPortSelector.setInputPortFactory(new AProcedureSyncingSyncReceiveDuplexRPCInputPortFactory());
		
//		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new AProcedureSyncingSyncReceiveSentCallCompleterCallFactory());
//		SynchronousDuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new AProcedureSyncingDuplexReceivedCallInvokerFactory());

		
	}
	public static void main (String args[]) {	
		AScatterGatherSelectionManager.setMaxOutstandingWrites(1000);

		(new ACustomSerializerSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	

}
