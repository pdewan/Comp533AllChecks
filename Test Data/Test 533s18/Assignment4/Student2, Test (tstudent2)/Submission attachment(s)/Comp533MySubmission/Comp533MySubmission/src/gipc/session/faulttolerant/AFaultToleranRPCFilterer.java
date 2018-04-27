package gipc.session.faulttolerant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import inputport.rpc.SerializableCall;
import inputport.rpc.SerializableMethod;

public class AFaultToleranRPCFilterer implements FaultTolerantFilterer {
	String[] broadcastHeaders = {};
	Set<String> broadcastHeadersSet = new HashSet();
	String[] relayedHeaders = {};
	

	Set<String> relayedHeadersSet = new HashSet();


	@Override
	public String[] getBroadcastHeaders() {
		return broadcastHeaders;
	}

	@Override
	public void setBroadcastHeaders(String[] newVal) {
		this.broadcastHeaders = newVal;
		broadcastHeadersSet = new HashSet(Arrays.asList(broadcastHeaders));
	}
	@Override
	public String[] getRelayedHeaders() {
		return relayedHeaders;
	}
	@Override
	public void setRelayedHeaders(String[] newVal) {
		this.relayedHeaders = newVal;
		relayedHeadersSet = new HashSet(Arrays.asList(relayedHeaders));
	}
	
	@Override
	public boolean isBroadcastCall (String aDestination, Object message) {
//		if (!(message instanceof SerializableCall))
//			return false;
//		SerializableCall aSerializableCall = (SerializableCall) message;
//		SerializableMethod aSerializableMethod = aSerializableCall.getSerializableMethod(); 
//		if (aSerializableMethod == null)
//			return false;
//		String aHeader = aSerializableMethod.toHeader();
//		return broadcastHeadersSet.contains(aHeader);
		return isCallContanedIn(aDestination, message, broadcastHeadersSet);
	}

	@Override
	public boolean isRelayCall (String aDestination, Object message) {
//		if (!(message instanceof SerializableCall))
//			return false;
//		SerializableCall aSerializableCall = (SerializableCall) message;
//		SerializableMethod aSerializableMethod = aSerializableCall.getSerializableMethod(); 
//		if (aSerializableMethod == null)
//			return false;
//		String aHeader = aSerializableMethod.toHeader();
//		return broadcastHeadersSet.contains(aHeader);
		return isCallContanedIn(aDestination, message, relayedHeadersSet);
	}
	
	protected boolean isCallContanedIn (String aDestination, Object message, Set<String> aSet) {
		if (!(message instanceof SerializableCall))
			return false;
		SerializableCall aSerializableCall = (SerializableCall) message;
		SerializableMethod aSerializableMethod = aSerializableCall.getSerializableMethod(); 
		if (aSerializableMethod == null)
			return false;
		String aHeader = aSerializableMethod.toHeader();
		return aSet.contains(aHeader);
	}
	

}
