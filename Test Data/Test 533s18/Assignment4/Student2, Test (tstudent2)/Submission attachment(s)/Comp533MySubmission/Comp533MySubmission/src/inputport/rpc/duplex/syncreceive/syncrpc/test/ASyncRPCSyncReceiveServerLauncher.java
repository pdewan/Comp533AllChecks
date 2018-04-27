package inputport.rpc.duplex.syncreceive.syncrpc.test;

import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.customization.ACustomCounterServer;
import examples.gipc.counter.customization.ATracingFactorySetter;
import examples.gipc.counter.customization.FactorySetterFactory;
@Tags({Comp533Tags.BLOCKING_RPC_SERVER})
public class ASyncRPCSyncReceiveServerLauncher {
	public static void main (String[] args) {
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
		FactorySetterFactory.setSingleton(new ASyncRPCSyncReceiveFactorySetter());
		ACustomCounterServer.launch();
	}

}
