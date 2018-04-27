package inputport.datacomm.duplex.object.explicitreceive;

import inputport.datacomm.duplex.DuplexServerInputPort;
import port.old.PrintingReplyingObjectReceiver;
import util.trace.Tracer;



public class ObjectDuplexServerInputPortLauncher {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		DuplexServerInputPort serverInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexServerInputPortWithSyncReceive("9090", "test server");
		PrintingReplyingObjectReceiver echoingReceiveListener = new PrintingReplyingObjectReceiver(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.connect();
	}

}
