package inputport.datacomm.duplex.object.explicitreceive.test;

import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.trace.port.objects.ObjectTraceUtility;
import examples.gipc.counter.customization.ACustomCounterServer;
import examples.gipc.counter.customization.ATracingFactorySetter;
import examples.gipc.counter.customization.FactorySetterFactory;
@Tags({Comp533Tags.EXPLICIT_RECEIVE_SERVER})
public class ASyncReceiveServerLauncher {
	public static void main (String[] args) {
		ObjectTraceUtility.setTracing();
		FactorySetterFactory.setSingleton(new ASyncReceiveFactorySetter());
		ACustomCounterServer.launch();
	}

}
