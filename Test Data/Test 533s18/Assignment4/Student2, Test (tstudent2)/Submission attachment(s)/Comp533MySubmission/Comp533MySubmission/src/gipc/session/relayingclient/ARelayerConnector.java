package gipc.session.relayingclient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationRelayer;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public class ARelayerConnector implements RelayerConnector{
	DuplexRPCSessionPort sessionPort;
	Map<String, RMISimulationRelayer>  remoteEndToRelayer = new HashMap();
	

//	String currentRelayerName;
	RelayerElector relayerElector;
	RMISimulationInCoupler inCoupler;
	public ARelayerConnector(DuplexRPCSessionPort aSessionPort, RMISimulationInCoupler anInCoupler) {
		sessionPort = aSessionPort;
		inCoupler = anInCoupler;
		relayerElector = RelayerElectorFactory.getSingleton();
	}
	
	

	

	@Override
	public synchronized void connected(String remoteEnd, ConnectionType aConnectionType) {
		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION ) {
//		System.out.print("Connected  " + remoteEnd);
		RMISimulationRelayer aRelayerProxy = (RMISimulationRelayer) DirectedRPCProxyGenerator.generateRPCProxy(sessionPort, remoteEnd, RMISimulationRelayer.class, null);
		remoteEndToRelayer.put(remoteEnd, aRelayerProxy);	
		maybeRetargetRelayer();
		}
		
	}

	@Override
	public synchronized void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		remoteEndToRelayer.remove(remoteEndName);
		maybeRetargetRelayer();
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}
	public   void  connectToRelayer() {
		try {
			
			RMISimulationRelayer aRelayer = getCurrentRelayer();
			aRelayer.join(sessionPort.getLocalName(), inCoupler);
		   
			
				   
		   } catch (Exception e) {
			
	      e.printStackTrace();
	    }
	  
	  }
//	public String getCurrentRelayerName() {
//		return currentRelayerName;
//	}
	public RMISimulationRelayer getCurrentRelayer() {
		if (relayerElector.getCurrentRelayerName() != null) {
			return remoteEndToRelayer.get(relayerElector.getCurrentRelayerName());
		} else
			return null;
	}
	protected void maybeRetargetRelayer() {
		if (remoteEndToRelayer.size() == 0)
			return; // should never be executed
//		String oldRelayerName = currentRelayerName;
		String oldRelayerName = relayerElector.getCurrentRelayerName();
		RMISimulationRelayer oldRelayer = getCurrentRelayer();
		List<String> relayerNames = new ArrayList(remoteEndToRelayer.keySet());
//		System.out.println("Current relayer names:" + relayerNames);
		if (!relayerElector.processConnectionChange(relayerNames)) 
			return;
//		Collections.sort(relayerNames);
//		currentRelayerName = relayerNames.get(0);
		String newRelayerName = relayerElector.getCurrentRelayerName();
		RMISimulationRelayer newRelayer = getCurrentRelayer();
//		if (newRelayer == oldRelayer)
//			return;
		try {
		if (oldRelayer != null) {
			
			oldRelayer.leave(sessionPort.getLocalName());
			relayerElector.processLeave(oldRelayerName);
			
		}
		newRelayer.join(sessionPort.getLocalName(), inCoupler);		
		relayerElector.processJoin(newRelayerName);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void setCurrentRelayerName(String currentRelayerName) {
//		this.currentRelayerName = currentRelayerName;
//	}
	public Map<String, RMISimulationRelayer> getRemoteEndToRelayer() {
		return remoteEndToRelayer;
	}

	public void setRemoteEndToRelayer(
			Map<String, RMISimulationRelayer> remoteEndToRelayer) {
		this.remoteEndToRelayer = remoteEndToRelayer;
	}
}
