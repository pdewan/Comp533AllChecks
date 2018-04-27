package inputport.rpc.duplex.syncreceive.test;

import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.customization.ACustomCounterClient;
import examples.gipc.counter.customization.ACustomCounterClient2Launcher;
import examples.gipc.counter.customization.FactorySetterFactory;
@Tags({Comp533Tags.CUSTOM_RPC_CLIENT2})
public class AnRPCSyncReceiveClient2Launcher extends ACustomCounterClient2Launcher{
	public static void main (String[] args) {
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
		FactorySetterFactory.setSingleton(new AnRPCSyncReceiveFactorySetter());
		ACustomCounterClient.launch(CLIENT2_NAME);
	}
}
