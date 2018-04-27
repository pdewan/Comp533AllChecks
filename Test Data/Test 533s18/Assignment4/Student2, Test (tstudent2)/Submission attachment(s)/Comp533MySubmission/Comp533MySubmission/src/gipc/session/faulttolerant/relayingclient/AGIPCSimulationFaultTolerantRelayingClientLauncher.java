package gipc.session.faulttolerant.relayingclient;

import common.controller.TrickOrTreatClientControllerFactory;
import gipc.session.faulttolerant.FaultToleranceManagerFactory;
import gipc.session.faulttolerant.FaultTolerantFiltererFactory;
import gipc.session.faulttolerant.forwarders.AFaultTolerantDeserializingForwarderFactory;
import gipc.session.faulttolerant.forwarders.AFaultTolerantSerializingForwarderFactory;
import gipc.session.relayingclient.AGIPCSimulationRelayingClientLauncher;
import gipc.session.relayingclient.RelayerConnectorFactory;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.simplex.object.ObjectTranslatingIPTrapperSelector;
import port.PortAccessKind;
import port.PortKind;
import port.SessionChoice;
import rmi.simulation.common.RMISimulationRelayer;
import rmi.simulation.server.AnRMISimulationRelayer;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import stringProcessors.HalloweenCommandProcessor;
import util.remote.RelayerControllerFactory;
import util.trace.Tracer;

public class AGIPCSimulationFaultTolerantRelayingClientLauncher extends AGIPCSimulationRelayingClientLauncher{
	public AGIPCSimulationFaultTolerantRelayingClientLauncher(String aClientName,
			String aServerHost, String aServerId, String aServerName,
			HalloweenCommandProcessor aCommandProcessor, boolean aBroadcastData) {
		super(aClientName, aServerHost, aServerId, aServerName, aCommandProcessor,
				aBroadcastData);
		commandProcessor.setConnectedToSimulation(false);
		RelayerControllerFactory.getOrCreateSingleton().setEchoBack(true);
		TrickOrTreatClientControllerFactory.getOrCreateSingleton().setInterCommandTime(300);
		addFaultToleranceFilterers();
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(AFaultTolerantSerializingForwarderFactory.class, true);
//		Tracer.setKeywordPrintStatus(AFaultTolerantDeserializingForwarderFactory.class, true);

//
		Tracer.setDisplayThreadName(true);
//		Tracer.setKeywordPrintStatus(AFaultToleranceManager.class, true);

		
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
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
		ObjectTranslatingIPTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(new AFaultTolerantDeserializingForwarderFactory());
		ObjectTranslatingIPTrapperSelector.getTrapperSelector().setSendTrapperFactory(new AFaultTolerantSerializingForwarderFactory());
		addFaultToleranceFilterers();


	}
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
//		@Override
//		public  void  connectInCoupler() {
//			
//		}
//		protected void connectOutCoupler() {
//			  if (broadcastData) {
//				   new ADynamicallyBoundSimulationOutCoupler(commandProcessor,  clientName);
//			   }
//		}
//		@Override
//		protected void setStateAfterPortButBeforeConnection() {
//			createFaultToleranceManager(mainPort);
//			RelayerControllerFactory.getOrCreateSingleton().setEchoBack(true);
//
//
//		}
		protected void createUI(InputPort anInputPort) {

			super.createUI(anInputPort);
		
		}
		
		protected void createFaultToleranceManager(InputPort anInputPort) {
			FaultToleranceManagerFactory.createSingleton((DuplexRPCSessionPort) anInputPort);
		}
		protected void addFaultToleranceFilterers() {
			FaultTolerantFiltererFactory.getOrCreateSingleton().
				setBroadcastHeaders(new String[] {"broadcast(java.lang.String,java.lang.String)"});
			FaultTolerantFiltererFactory.getOrCreateSingleton().
				setRelayedHeaders(new String[] {"processCommand(java.lang.String)"});

		}

		protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
			createFaultToleranceManager(anInputPort);

			return RelayerConnectorFactory.createSingleton((DuplexRPCSessionPort) anInputPort, createRMISimulationInCoupler());
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
