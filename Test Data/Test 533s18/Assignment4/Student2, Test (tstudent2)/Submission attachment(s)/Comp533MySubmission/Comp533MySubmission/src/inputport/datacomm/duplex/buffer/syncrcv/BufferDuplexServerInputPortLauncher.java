package inputport.datacomm.duplex.buffer.syncrcv;

import inputport.datacomm.duplex.DuplexServerInputPort;
import port.old.PrintingReplyingReceiveListener;
import util.trace.Tracer;



public class BufferDuplexServerInputPortLauncher {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		DuplexServerInputPort serverInputPort = BufferDuplexInputPortWithSyncReceiveSelector.createBufferDuplexServerInputPortWithSyncReceive("9090", "test server");
		PrintingReplyingReceiveListener echoingReceiveListener = new PrintingReplyingReceiveListener(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.connect();
	}

}
