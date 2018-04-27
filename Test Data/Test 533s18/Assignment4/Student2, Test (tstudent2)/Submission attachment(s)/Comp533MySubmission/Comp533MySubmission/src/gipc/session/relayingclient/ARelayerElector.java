package gipc.session.relayingclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ARelayerElector implements RelayerElector {
	List<String> currentRelayerNames = new ArrayList();
	
	protected String currentRelayerName;
//	Map<String, Object>  remoteEndToRelayer = new HashMap();
//	public ARetargetableRelayerManager(DuplexRPCSessionPort aSessionPort) {
//		sessionPort = aSessionPort;
//	}
	@Override
	public String getCurrentRelayerName() {
		return currentRelayerName;
	}
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
	
	@Override
	/**
	 * return true if new relayer;
	 */
	
	public boolean processConnectionChange(List<String> aRelayerNames) {
			
			if (aRelayerNames.size() == 0)
				return currentRelayerName != null ; // should never be executed
//			String oldRelayerName = currentRelayerName;
//			List<String> relayerNames = new ArrayList(remoteEndToRelayer.keySet());
			Collections.sort(aRelayerNames);
//			System.out.println("Sorted relayer names:" + aRelayerNames);

			String oldRelayerName = currentRelayerName;			
//			currentRelayerName = aRelayerNames.get(0);
			currentRelayerName = aRelayerNames.get(aRelayerNames.size() - 1);
			currentRelayerNames = aRelayerNames;
//			System.out.println(Thread.currentThread() + "New relayer name" + currentRelayerName);

			return !currentRelayerName.equals(oldRelayerName); // new relayer
//			return getCurrentRelayer();
			
		
	}
	@Override
	public void processJoin(String aRelayerName) {
		
	}
	@Override
    public void processLeave(String aRelayerName) {
		
	}
	protected List<String> getRelayerNames() {
		return currentRelayerNames;
	}

	protected void setCurrentRelayerNames(List<String> currentRelayerNames) {
		this.currentRelayerNames = currentRelayerNames;
	}

}
