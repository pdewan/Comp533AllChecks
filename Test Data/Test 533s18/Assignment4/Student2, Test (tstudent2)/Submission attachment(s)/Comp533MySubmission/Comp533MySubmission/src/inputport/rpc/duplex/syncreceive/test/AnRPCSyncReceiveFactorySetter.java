package inputport.rpc.duplex.syncreceive.test;

import examples.gipc.counter.customization.ACustomDuplexReceivedCallInvokerFactory;
import examples.gipc.counter.customization.ACustomSentCallCompleterFactory;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.AnInheritingDuplexIObjectInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.test.ASyncReceiveFactorySetter;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.syncreceive.AProcedureSyncingDuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.syncreceive.ASyncReceiveSentCallCompleterCallFactory;

public class AnRPCSyncReceiveFactorySetter extends ASyncReceiveFactorySetter{
	@Override
	public void setFactories() {
		 super.setFactories();
			
//		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
//					new AProcedureSyncingDuplexReceivedCallInvokerFactory());	

		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
					new ASyncReceiveSentCallCompleterCallFactory());
	}

}
