package gipc.session.relayingclient;

import gipc.customserializer.client.ACustomSerializerGIPCSimulationClientLauncher;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import port.PortAccessKind;
import port.PortKind;
import port.SessionChoice;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.server.AnRMISimulationRelayer;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import stringProcessors.HalloweenCommandProcessor;

public class AGIPCSimulationRelayingClientLauncher extends ACustomSerializerGIPCSimulationClientLauncher{
	public AGIPCSimulationRelayingClientLauncher(String aClientName,
			String aServerHost, String aServerId, String aServerName,
			HalloweenCommandProcessor aCommandProcessor, boolean aBroadcastData) {
		super(aClientName, aServerHost, aServerId, aServerName, aCommandProcessor,
				aBroadcastData);
		DirectedRPCProxyGenerator.setDoShortCircuitLocalCallsToRemotes(false);
		
	}
	// cannot use parameterized version as no launcher support exists for duplex session port in registry right now
//		protected  InputPort getPort() {
//
//			return DuplexRPCSessionPortSelector.createDuplexRPCSessionPort("localhost", 
//					"" + serverId, serverName, getSessionName(), clientId, clientName,
//					ParticipantChoice.MEMBER
//					);	
//			
//		}
		protected RMISimulationRelayer getSessionServer() {
			return new AnRMISimulationRelayer();
		}
		protected void registerRemoteObjects() {
			DuplexRPCSessionPort aSessionPort = (DuplexRPCSessionPort) mainPort;
			RMISimulationRelayer sessionServer = getSessionServer();
			aSessionPort.register(sessionServer);
			
//			Adder adder = new AnAdder();
			
		}
		// do nothing do not create any proxies
		protected  void createProxies() {

//			sessionServerProxy = (RMISimulationSessionServer) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) mainPort, registeredSessionServerClass());
//			sessionServerProxy = (RMISimulationSessionServer)  createProxy (registeredSessionServerClass());
		}
		// do nothing
//		public  void  launchClient(String aMyName, HalloweenCommandProcessor aCommandProcessor, boolean aBroadcast) {
//			
//		}
		// do nothing
		@Override
		public  void  connectInCoupler() {
			
		}
		protected void connectOutCoupler() {
			  if (broadcastData) {
				   new ADynamicallyBoundSimulationOutCoupler(commandProcessor,  clientName);
			   }
		}

		protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
			return RelayerConnectorFactory.createSingleton((DuplexRPCSessionPort) mainPort, createRMISimulationInCoupler());
		}
		public PortKind getPortKind() {
			return PortKind.SESSION_PORT;
		}
		public PortAccessKind getPortAccessKind() {
			return PortAccessKind.DUPLEX;
		}	
		protected SessionChoice getSessionChoice() {
			return SessionChoice.P2P;
		}

}
