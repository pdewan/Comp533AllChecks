package gipc.simulation.client;


import gipc.simulation.server.AGIPCSimulationServerLauncher;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.nio.manager.AScatterGatherSelectionManager;
import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;
import port.ATracingConnectionListener;
import rmi.simulation.client.AnRMISimulationInCoupler;
import rmi.simulation.client.AnRMISimulationOutCoupler;
import rmi.simulation.common.RMISimulationInCoupler;
import rmi.simulation.common.RMISimulationRelayer;
import serialization.logical.ABeanSerializer;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.IPCMechanism;
import util.remote.RelayerClientControllerFactory;
import util.trace.Tracer;





public class AGIPCSimulationClientLauncher extends AnAbstractDuplexRPCClientPortLauncher  implements GIPCSimulationClientLauncher{
	protected boolean broadcastData;

	protected HalloweenCommandProcessor commandProcessor;
	

	public AGIPCSimulationClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName, HalloweenCommandProcessor aCommandProcessor,
			 boolean aBroadcastData) {
		super(aClientName, aServerHost, aServerId, aServerName);
		commandProcessor = aCommandProcessor;
		broadcastData = aBroadcastData;
		RelayerClientControllerFactory.getOrCreateSingleton().setServerHost(aServerHost);
		
		// add in a wait for user here
		String aRealServerHost = RelayerClientControllerFactory.getOrCreateSingleton().getServerHost();
		

		String aFullServerId = aRealServerHost + ":" + aServerId;
		
		RelayerClientControllerFactory.getOrCreateSingleton().setServerId(aFullServerId);
		RelayerClientControllerFactory.getOrCreateSingleton().setRelayerId(aServerId);
		AScatterGatherSelectionManager.setMaxOutstandingWrites(6000);
//		Tracer.setKeywordPrintStatus(ASerializerPool.class, true);
		Tracer.setKeywordPrintStatus(ABeanSerializer.class, true);



		
	}
	

	protected Class registeredSessionServerClass() {
		return AGIPCSimulationServerLauncher.SESSION_SERVER_CLASS;
	}

	
	protected void createUI(InputPort anInputPort) {

		connectCouplers();
	
	}
	
	protected RMISimulationRelayer sessionServerProxy;

	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}
	

	
	protected  void createProxies() {

//		sessionServerProxy = (RMISimulationSessionServer) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) mainPort, registeredSessionServerClass());
		sessionServerProxy = (RMISimulationRelayer)  createProxy (registeredSessionServerClass());
	}	 
	public  void  connectCouplers() {
		connectInCoupler();
		connectOutCoupler();
//		   try {
//
////		launchClient(clientName, commandProcessor, broadcastData);
//			   RMISimulationInCoupler inCoupler = createRMISimulationInCoupler();
//
//				try {
//					
//
//				   
//					sessionServerProxy.join(clientName, inCoupler);
//				   
////					   if (broadcastData) {
////						   new AnRMISimulationOutCoupler(commandProcessor, sessionServerProxy, clientName);
////					   }
//						   
//				   } catch (Exception e) {
//					
//			      e.printStackTrace();
//			    }
//	 
//
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
////		   if (broadcastData) {
////			   new AnRMISimulationOutCoupler(commandProcessor, sessionServerProxy, clientName);
////		   }
//		   connectOutCoupler();
	  
	  }
	protected void connectInCoupler() {
		   RMISimulationInCoupler inCoupler = createRMISimulationInCoupler();
		   try {
				

			   
				sessionServerProxy.join(clientName, inCoupler);
			   
//				   if (broadcastData) {
//					   new AnRMISimulationOutCoupler(commandProcessor, sessionServerProxy, clientName);
//				   }
					   
			   } catch (Exception e) {
				
		      e.printStackTrace();
		    }

		
	}
	protected RMISimulationInCoupler createRMISimulationInCoupler() {
		return  new AnRMISimulationInCoupler(commandProcessor);
	}
	
	protected void connectOutCoupler() {
		  if (broadcastData) {
			   new AnRMISimulationOutCoupler(commandProcessor, sessionServerProxy, clientName,IPCMechanism.GIPC);
		   }
	}
	
//	public  void  launchClient(String aMyName, HalloweenCommandProcessor aCommandProcessor, boolean aBroadcast) {
////		   RMISimulationInCoupler inCoupler = new AnRMISimulationInCoupler(aCommandProcessor);
//		   RMISimulationInCoupler inCoupler = createRMISimulationInCoupler();
//
//		try {
//			
//
//		   
//			sessionServerProxy.join(aMyName, inCoupler);
//		   
//			   if (aBroadcast) {
//				   new AnRMISimulationOutCoupler(aCommandProcessor, sessionServerProxy, aMyName);
//			   }
//				   
//		   } catch (Exception e) {
//			
//	      e.printStackTrace();
//	    }
//	  
//	  }
	
		

}
