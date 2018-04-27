package gipc.syncreceive.echoer;

import inputport.InputPort;
import inputport.ServerInputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.echoer.example.AnAbstractDuplexObjectServerPortLauncher;
import inputport.datacomm.duplex.object.explicitreceive.DuplexObjectInputPortWithSyncReceiveSelector;
import port.PortAccessKind;
import port.old.PrintingReplyingObjectReceiver;
import util.trace.Tracer;



public class AnObjectDuplexSyncReceiveServerInputPortLauncher extends AnAbstractDuplexObjectServerPortLauncher {
	
	public AnObjectDuplexSyncReceiveServerInputPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	@Override
	public  ServerInputPort getObjectServerInputPort(PortAccessKind aPortAccessKind) {
		Tracer.showInfo(true);
		return DuplexObjectInputPortWithSyncReceiveSelector.createDuplexServerInputPortWithSyncReceive(serverId, serverName);


	}
	
	
//	protected void createUI(InputPort anInputPort){
//		String stringMessage = "hello server";
//
//		Scanner in = new Scanner(System.in);	
//		while (true) {
//			myPort().send(stringMessage);
//			MessageWithSourceSyncRcv<Object> received = myPort().receive();
//			System.out.println("Sync Received message: " + received + " from "  + received.getSource());
//		    stringMessage  = in.nextLine();
//			System.out.println("sending read input:" + stringMessage);
////		    message =  ByteBuffer.wrap(stringMessage.getBytes());	
//		}
//		
//	}
	@Override
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
		return new PrintingReplyingObjectReceiver((DuplexServerInputPort<Object>) anInputPort);
	}
	
//	@Override
//	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
//		return new PrintingReplyingObjectReceiver((DuplexServerInputPort<Object>) anInputPort);
//	}
	
//	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		DuplexServerInputPort serverInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexServerInputPortWithSyncReceive("9090", "test server");
//		PrintingReplyingObjectReceiver echoingReceiveListener = new PrintingReplyingObjectReceiver(serverInputPort);
//		serverInputPort.addConnectionListener(echoingReceiveListener);
//		serverInputPort.addReceiveListener(echoingReceiveListener);	
//		serverInputPort.connect();
//	}
	
	public static void main (String[] args) {
		Tracer.showInfo(true);
		(new AnObjectDuplexSyncReceiveServerInputPortLauncher("test serverr", "9090")).launch();
//		DuplexServerInputPort serverInputPort = DuplexObjectInputPortWithSyncReceiveSelector.createDuplexServerInputPortWithSyncReceive("9090", "test server");
//		PrintingReplyingObjectReceiver echoingReceiveListener = new PrintingReplyingObjectReceiver(serverInputPort);
//		serverInputPort.addConnectionListener(echoingReceiveListener);
//		serverInputPort.addReceiveListener(echoingReceiveListener);	
//		serverInputPort.connect();
	}

}
