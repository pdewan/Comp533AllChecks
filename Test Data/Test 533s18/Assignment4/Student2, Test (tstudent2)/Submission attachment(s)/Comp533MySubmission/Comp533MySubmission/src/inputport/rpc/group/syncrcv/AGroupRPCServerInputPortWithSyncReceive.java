package inputport.rpc.group.syncrcv;

import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.group.syncrcv.GroupServerInputPortWithSyncReceive;
import inputport.rpc.group.AGroupRPCServerInputPort;
import port.old.PrintingReplyingObjectReceiver;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import util.trace.Tracer;
import extraip.AGroupAdder;
import extraip.GroupAdder;





public class AGroupRPCServerInputPortWithSyncReceive extends AGroupRPCServerInputPort implements GroupRPCServerInputPortWithSyncReceive {
	GroupServerInputPortWithSyncReceive<Object> groupServerInputPortWithSyncReceive;
	public AGroupRPCServerInputPortWithSyncReceive(GroupServerInputPortWithSyncReceive<Object> aTypedGroupServerInputPort) {
		super(aTypedGroupServerInputPort);
		groupServerInputPortWithSyncReceive = aTypedGroupServerInputPort;
	}
//	@Override
//	protected GroupReturnerOfValueOfRemoteFunctionCall createWaitingGroupRPCFunctionHandler(LocalRemoteReferenceTranslator aRemoteHandler) {
//		return new AReceivingGroupRPCFunctionHandler(this, aRemoteHandler);
//	}	
	@Override
	public ReceiveReturnMessage<Object> receive(String source) {
		return groupServerInputPortWithSyncReceive.receive(source);
	}
	@Override
	public ReceiveReturnMessage<Object> receive() {
		return  groupServerInputPortWithSyncReceive.receive();
	}
	
	public static void main (String[] args) {
		Tracer.showInfo(true);
//		GroupRPCServerInputPort serverInputPort = new AGroupRPCServerInputPort("9090", "test server");
		GroupRPCServerInputPortWithSyncReceive serverInputPort = GroupRPCWithSyncReceiveInputPortSelector.createGroupRPCServerInputPortWithSyncReceive("9090", "test server");

		serverInputPort.connect();
		PrintingReplyingObjectReceiver messageReceiver = new PrintingReplyingObjectReceiver(serverInputPort);
//		RPCReceiver rpcServer = new RPCReceiver(serverInputPort);
		serverInputPort.addConnectionListener(messageReceiver);
//		serverInputPort.addDisconnectListener(messageReceiver);

//		serverInputPort.register(TypedReceiveListener.class, messageReceiver);
//		serverInputPort.register(RPCReceiver.class, rpcServer);
		Adder adder = new AnAdder();
		serverInputPort.register(Adder.class, adder);
		AGroupAdder groupAdder = new AGroupAdder(serverInputPort);
		serverInputPort.register(GroupAdder.class, groupAdder);
		//serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.addSendListener(messageReceiver);
	}
	
	
}
