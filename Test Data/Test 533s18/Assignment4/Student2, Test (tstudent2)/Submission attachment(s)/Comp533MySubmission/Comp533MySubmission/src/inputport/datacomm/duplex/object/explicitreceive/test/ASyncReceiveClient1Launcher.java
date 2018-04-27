package inputport.datacomm.duplex.object.explicitreceive.test;

import util.annotations.Comp533Tags;
import util.annotations.Tags;
import util.trace.port.objects.ObjectTraceUtility;
import examples.gipc.counter.customization.ACustomCounterClient;
import examples.gipc.counter.customization.ACustomCounterClient1Launcher;
import examples.gipc.counter.customization.ATracingFactorySetter;
import examples.gipc.counter.customization.FactorySetterFactory;
@Tags({Comp533Tags.EXPLICIT_RECEIVE_CLIENT1})
public class ASyncReceiveClient1Launcher extends ACustomCounterClient1Launcher{
	public static void main (String[] args) {
		FactorySetterFactory.setSingleton(new ASyncReceiveFactorySetter());
		ObjectTraceUtility.setTracing();
		ACustomCounterClient.launch(CLIENT1_NAME);
	}

}
