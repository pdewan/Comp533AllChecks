package gipc.syncreceive.echoer;



import java.util.Scanner;

import inputport.InputPort;
import inputport.datacomm.duplex.object.AnAbstractDuplexObjectClientPortLauncher;
import inputport.datacomm.duplex.object.explicitreceive.DuplexClientInputPortWithSyncReceive;
import inputport.datacomm.duplex.object.explicitreceive.DuplexObjectInputPortWithSyncReceiveSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import port.PortAccessKind;
import util.trace.Tracer;



public class AnObjectDuplexSyncReceiveClientInputPortLauncher extends AnAbstractDuplexObjectClientPortLauncher{
	
	public AnObjectDuplexSyncReceiveClientInputPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	
	@Override
	public  InputPort getObjectClientInputPort(PortAccessKind aPortAccessKind) {
		Tracer.showInfo(true);
		return DuplexObjectInputPortWithSyncReceiveSelector.createDuplexClientInputPortWithSyncReceive(serverHost,serverId, serverName, clientName);
//		return DuplexObjectInputPortWithSyncReceiveSelector.createDuplexClientInputPortWithSyncReceive(hostName, "9090", "test server", "test client");



	}
	
	DuplexClientInputPortWithSyncReceive<Object> myPort() {
		return (DuplexClientInputPortWithSyncReceive<Object>) mainPort;
	}
//	@Override
//	protected void initPortLaucherSupports() {
//		super.initPortLaucherSupports();
//		DuplexObjectInputPortSelector.setDuplexInputPortFactory(new AnInheritingDuplexIObjectInputPortWithSyncReceiveFactory());
//	}	
	
	protected void createUI(InputPort anInputPort){
		String stringMessage = "hello server";

		Scanner in = new Scanner(System.in);	
		while (true) {
			myPort().send(stringMessage);
			ReceiveReturnMessage<Object> received = myPort().receive();
			System.out.println("Sync Received message: " + received + " from "  + received.getSource());
		    stringMessage  = in.nextLine();
			System.out.println("sending read input:" + stringMessage);
//		    message =  ByteBuffer.wrap(stringMessage.getBytes());	
		}
		
	}
	
	public static void main (String[] args) {
		(new AnObjectDuplexSyncReceiveClientInputPortLauncher("test client", "localhost", "9090", "test server")).launch();
	}

	
//	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		DuplexClientInputPortWithSyncReceive<Object> clientInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexClientInputPortWithSyncReceive("localhost", "9090", "test server", "test client");
//
//		PrintingTypedReceiveListener echoingConnectionListener = new PrintingTypedReceiveListener(clientInputPort);
//		clientInputPort.addConnectionListener(echoingConnectionListener);
//		clientInputPort.addReceiveListener(echoingConnectionListener);
//		clientInputPort.connect();
//		String stringMessage = "hello server";
//
//		Scanner in = new Scanner(System.in);	
//		while (true) {
//			clientInputPort.send(stringMessage);
//			MessageWithSourceSyncRcv<Object> received = clientInputPort.receive();
//			System.out.println("Sync Received message: " + received + " from "  + received.getSource());
//		    stringMessage  = in.nextLine();
//			System.out.println("sending read input:" + stringMessage);
////		    message =  ByteBuffer.wrap(stringMessage.getBytes());	
//		}
//	}
	

}
