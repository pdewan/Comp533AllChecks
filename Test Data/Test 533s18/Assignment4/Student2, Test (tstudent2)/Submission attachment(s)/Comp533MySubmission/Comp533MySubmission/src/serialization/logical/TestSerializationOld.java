package serialization.logical;

import inputport.rpc.ASerializableCall;
import inputport.rpc.ASerializableMethod;
import inputport.rpc.AnRPCProxyInvocationHandler;
import inputport.rpc.SerializableCall;
import inputport.rpc.SerializableMethod;
import inputport.rpc.duplex.AnRPCReturnValue;
import inputport.rpc.duplex.RPCReturnValue;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.serialization.AStringHistory;
import examples.serialization.AnotherBMISpreadsheet;
import examples.serialization.BMISpreadsheet;
import examples.serialization.StringHistory;
import serialization.Serializer;
import serialization.simple.ASimpleSerializer;
import util.trace.Tracer;


public class TestSerializationOld {
	public static void main(String args[]) {
		Tracer.showInfo(true);
		
		

//		Serializer support = new ACustomSerializer();
		Serializer support = new ASimpleSerializer();
		InvocationHandler invocationHandler = new AnRPCProxyInvocationHandler(null, null, DistributedRMICounter.class, null);
		DistributedRMICounter counter = (DistributedRMICounter)  
        Proxy.newProxyInstance(
           DistributedRMICounter.class.getClassLoader(), 
           new Class [] {DistributedRMICounter.class}, 
           invocationHandler );


		try {
			Method[] methods = List.class.getMethods();
			Method method = methods[0];
			SerializableMethod serializableMethod = new ASerializableMethod(
					method);
			// translate(support, serializableMethod);
			Object[] actualArgs = { "arg1", 65 };
			SerializableCall serializableCall = new ASerializableCall(
					"test object", method, actualArgs);
			translate(support, counter);
			translate(support, method);
			translate(support, serializableCall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Method[] methods = List.class.getMethods();
			SerializableMethod[] serializableMethods = new SerializableMethod[methods.length];
			for (int i = 0; i < methods.length; i++) {
				serializableMethods[i] = new ASerializableMethod(methods[i]);
			}
			translate(support, serializableMethods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class argsClass = args.getClass();
		Class componentClass = args.getClass().getComponentType();
		String[] strings = { "Hello World", "Goodbye World" };
		List<String> list = new ArrayList();
		list.add("Hello world");
		list.add("Goodbye world");
		translate(support, list);
		Map map = new HashMap();
		map.put("greeting", "ni hao");
		map.put("farewell", "sayonara");
		BMISpreadsheet bmi = new AnotherBMISpreadsheet();
		bmi.setHeight(1.77);
		bmi.setWeight(75);
		map.put("myBMI", bmi);
		translate(support, map);
		// translate(support, list);
	
		RPCReturnValue rpcReturnValue = new AnRPCReturnValue(strings);
		translate(support, rpcReturnValue);
		List recursive = new ArrayList();
		recursive.add(null);
		recursive.add(strings);
		recursive.add(recursive);
		recursive.add(list);
		translate(support, recursive);
		StringHistory stringHistory = new AStringHistory();
		stringHistory.add("James Dean");
		stringHistory.add("Joe Doe");
		stringHistory.add("Jane Smith");
		stringHistory.add("John Smith");
		translate(support, stringHistory);
	}

	static void translate(Serializer support, Object object) {
		System.out.println("Serializing " + object);
		ByteBuffer buffer;
		try {
			buffer = support.outputBufferFromObject(object);

			Object readVal;
			try {
				readVal = support.objectFromInputBuffer(buffer);
				System.out.println("Deserialized " + readVal);

			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			}
		} catch (NotSerializableException e1) {
			e1.printStackTrace();
		}
	}

}
