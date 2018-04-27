package inputport.rpc.duplex.syncreceive.syncrpc.test;

import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.customization.ACustomCounterClient;
import examples.gipc.counter.customization.ACustomCounterClient1Launcher;
import examples.gipc.counter.customization.ATracingFactorySetter;
import examples.gipc.counter.customization.FactorySetterFactory;
@Tags({Comp533Tags.BLOCKING_RPC_CLIENT1})
public class ASyncRPCSyncReceiveClient1Launcher extends ACustomCounterClient1Launcher{
	public static void main (String[] args) {
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
		FactorySetterFactory.setSingleton(new ASyncRPCSyncReceiveFactorySetter());
		ACustomCounterClient.launch(CLIENT1_NAME);
	}

}
