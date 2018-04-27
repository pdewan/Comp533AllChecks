package gipc.session.relayingclient;

import java.util.Map;

import inputport.ConnectionListener;
import rmi.simulation.common.RMISimulationRelayer;

public interface RelayerConnector extends ConnectionListener {

//	public String getCurrentRelayerName() ;
//
//	public void setCurrentRelayerName(String currentRelayerName) ;
	public Map<String, RMISimulationRelayer> getRemoteEndToRelayer() ;

	public void setRemoteEndToRelayer(
			Map<String, RMISimulationRelayer> remoteEndToRelayer) ;
	public RMISimulationRelayer getCurrentRelayer();

}
