package gipc.session.relayingclient;

import java.util.List;

public interface RelayerElector {

	//	Map<String, Object>  remoteEndToRelayer = new HashMap();
	//	public ARetargetableRelayerManager(DuplexRPCSessionPort aSessionPort) {
	//		sessionPort = aSessionPort;
	//	}
	public abstract String getCurrentRelayerName();

	//	public Object processPeerConnection(String aPeerName, Object aRelayer) {
	//		remoteEndToRelayer.put(aPeerName, aRelayer);
	//		return processElectRelayer();
	//		
	//	}
	//	
	//	public Object processPeerDisconnection(String aPeerName, Object aRelayer) {
	//		remoteEndToRelayer.remove(aPeerName);
	//		return processElectRelayer();		
	//	}

	public abstract boolean processConnectionChange(List<String> relayerNames);

	void processJoin(String aRelayerName);

	void processLeave(String aRelayerName);

}