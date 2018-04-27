package inputport.rpc.group.syncrcv;

import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.syncreceive.DuplexRPCClientInputPortWithSyncReceive;
import inputport.rpc.duplex.syncreceive.DuplexRPCInputPortWithSyncReceiveSelector;
import port.old.PrintingTypedReceiveListener;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import util.trace.Tracer;
import extraip.GroupAdder;






public class AnRPCClientLauncher {
	public static void launchRPCClient(String clientName) {	
		Tracer.showInfo(true);
		DuplexRPCClientInputPortWithSyncReceive clientInputPort = DuplexRPCInputPortWithSyncReceiveSelector.createDuplexRPCClientInputPortWithSyncReceive("localhost", "9090", "Test Server", clientName);
		PrintingTypedReceiveListener messageReceiver = new PrintingTypedReceiveListener(clientInputPort);
		clientInputPort.addConnectionListener(messageReceiver);
//		clientInputPort.addDisconnectListener(messageReceiver);

		clientInputPort.register(messageReceiver.getClass(), messageReceiver);
		
		clientInputPort.addReceiveListener(messageReceiver);		
		clientInputPort.addSendListener(messageReceiver);
//		clientInputPort.register(TypedReceiveListener.class, messageReceiver);
		Adder adder = new AnAdder();
		clientInputPort.register(Adder.class, adder);

		clientInputPort.connect();
//		clientInputPort.send("hello server");
//		MessageWithSource receivedMessage = (MessageWithSource) clientInputPort.receive();
//		System.out.println(receivedMessage.getMessage());
		
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
//		clientInputPort.send("hello from" + clientName);
		try {
//		Class[] parameterTypes = {String.class, Serializable.class};
//		Method typedMethod = TypedReceiveListener.class.getMethod("messageReceived", parameterTypes);
//		Method rpcMethod = RPCReceiver.class.getMethod("messageReceived", parameterTypes);
//		Serializable[] args = {clientName, "hello from " + clientName};
//		clientInputPort.call(typedMethod, args);
//		clientInputPort.call(RPCReceiver.class, rpcMethod, args);
//		Method groupAddMethod = GroupAdder.class.getMethod("messageReceived", parameterTypes);
//		clientInputPort.call(GroupAdder.class, groupAddMethod, args);
//		Integer[] args2 = { 5, 6};
//		Class[] parameterTypes2 = {Integer.class, Integer.class};
//		Method intAddMethod = Adder.class.getMethod("add", parameterTypes2);
////		Object retVal = clientInputPort.call(Adder.class, intAddMethod, args2);
//		Integer[] args3 = { 5, 6};
//		Class[] parameterTypes3 = {Integer.TYPE, Integer.TYPE};
//		Method intAddMethod2 = Adder.class.getMethod("add", parameterTypes2);
//		Object retVal2 = clientInputPort.call(Adder.class, intAddMethod, args2);
//		System.out.println(retVal2);
		GroupAdder groupAdderProxy = (GroupAdder) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null, GroupAdder.class, null);
		groupAdderProxy.groupSum(5, 6);
		Adder adderProxy = (Adder) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null, Adder.class, null);
		Object result = adderProxy.sum(5, 6);
		System.out.println("Result of adding:" + result);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public static void main (String[] args) {
		launchRPCClient("test client");
	}

}
