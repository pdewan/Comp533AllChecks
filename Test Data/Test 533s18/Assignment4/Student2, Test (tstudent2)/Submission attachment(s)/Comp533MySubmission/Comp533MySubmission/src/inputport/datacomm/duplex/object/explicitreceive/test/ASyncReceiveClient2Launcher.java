package inputport.datacomm.duplex.object.explicitreceive.test;

import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.trace.port.objects.ObjectTraceUtility;
import examples.gipc.counter.customization.ACustomCounterClient;
import examples.gipc.counter.customization.ACustomCounterClient2Launcher;
import examples.gipc.counter.customization.FactorySetterFactory;
@Tags({Comp533Tags.EXPLICIT_RECEIVE_CLIENT2})
public class ASyncReceiveClient2Launcher extends ACustomCounterClient2Launcher{
	public static void main (String[] args) {
		ObjectTraceUtility.setTracing();
		FactorySetterFactory.setSingleton(new ASyncReceiveFactorySetter());
		ACustomCounterClient.launch(CLIENT2_NAME);
	}
}
