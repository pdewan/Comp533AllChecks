package inputport.datacomm.duplex.buffer.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;

import java.nio.ByteBuffer;
import java.util.Scanner;

import util.trace.Tracer;
import extraip.APrintingReceiveAndSendListener;

public class BufferDuplexClientInputPortLauncher {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		DuplexClientInputPortWithSyncReceive<ByteBuffer> clientInputPort = BufferDuplexInputPortWithSyncReceiveSelector.createBufferDuplexClientInputPortWithSyncReceive("localhost", "9090", "test server", "test client");
//		PrintingReceiveListener echoingConnectionListener = new PrintingReceiveListener();
//		clientInputPort.addConnectListener(echoingConnectionListener);
//		clientInputPort.addReceiveListener(echoingConnectionListener);
//		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
		APrintingReceiveAndSendListener echoingConnectionListener = new APrintingReceiveAndSendListener(clientInputPort);
		clientInputPort.addConnectionListener(echoingConnectionListener);
		clientInputPort.addReceiveListener(echoingConnectionListener);
		clientInputPort.connect();
		String stringMessage = "hello server";
		ByteBuffer message = ByteBuffer.wrap(stringMessage.getBytes());
		Scanner in = new Scanner(System.in);	
		while (true) {
			clientInputPort.send(message);
			ReceiveReturnMessage<ByteBuffer> received = clientInputPort.receive();
			System.out.println("Sync Received message: " + new String (received.getMessage().array()) + " from "  + received.getSource());
		    stringMessage  = in.nextLine();
			System.out.println("sending read input:" + stringMessage);
		    message =  ByteBuffer.wrap(stringMessage.getBytes());		
		}
	}
	

}
