package inputport.rpc.duplex.syncreceive.syncrpc.test;

import examples.gipc.counter.customization.ACustomDuplexReceivedCallInvokerFactory;
import examples.gipc.counter.customization.ACustomSentCallCompleterFactory;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.AnInheritingDuplexIObjectInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.test.ASyncReceiveFactorySetter;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingDuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingSyncReceiveSentCallCompleterCallFactory;
import inputport.rpc.duplex.syncreceive.ASyncReceiveSentCallCompleterCallFactory;
import inputport.rpc.duplex.syncreceive.AnAsynchronousProcedureSyncingDuplexReceivedCallInvokerFactory;

public class ASyncRPCSyncReceiveFactorySetter extends ASyncReceiveFactorySetter{
	@Override
	public void setFactories() {
		 super.setFactories();
			

		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
					new AProcedureSyncingSyncReceiveSentCallCompleterCallFactory());
		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
				new AnAsynchronousProcedureSyncingDuplexReceivedCallInvokerFactory());
		
	}

}
