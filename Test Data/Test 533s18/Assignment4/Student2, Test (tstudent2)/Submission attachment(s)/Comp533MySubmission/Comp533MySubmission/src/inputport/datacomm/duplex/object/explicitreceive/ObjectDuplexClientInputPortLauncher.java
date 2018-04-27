package inputport.datacomm.duplex.object.explicitreceive;



import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;

import java.util.Scanner;

import port.old.PrintingTypedReceiveListener;
import util.trace.Tracer;



public class ObjectDuplexClientInputPortLauncher {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		DuplexClientInputPortWithSyncReceive<Object> clientInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexClientInputPortWithSyncReceive("localhost", "9090", "test server", "test client");
//		PrintingReceiveListener echoingConnectionListener = new PrintingReceiveListener();
//		clientInputPort.addConnectListener(echoingConnectionListener);
//		clientInputPort.addReceiveListener(echoingConnectionListener);
//		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
		PrintingTypedReceiveListener echoingConnectionListener = new PrintingTypedReceiveListener(clientInputPort);
		clientInputPort.addConnectionListener(echoingConnectionListener);
		clientInputPort.addReceiveListener(echoingConnectionListener);
		clientInputPort.connect();
		String stringMessage = "hello server";
//		ByteBuffer message = ByteBuffer.wrap(stringMessage.getBytes());
		Scanner in = new Scanner(System.in);	
		while (true) {
			clientInputPort.send(stringMessage);
			ReceiveReturnMessage<Object> received = clientInputPort.receive();
			System.out.println("Sync Received message: " + received + " from "  + received.getSource());
		    stringMessage  = in.nextLine();
			System.out.println("sending read input:" + stringMessage);
//		    message =  ByteBuffer.wrap(stringMessage.getBytes());	
		}
	}
	

}
