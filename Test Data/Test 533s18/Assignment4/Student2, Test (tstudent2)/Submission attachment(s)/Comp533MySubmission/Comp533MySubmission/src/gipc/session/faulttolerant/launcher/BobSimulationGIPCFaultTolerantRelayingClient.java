package gipc.session.faulttolerant.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.BobCommandProcessorFactory;
import common.controller.BobControllerUI;
import common.controller.ServerControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.BobUI;
import gipc.session.faulttolerant.relayingclient.AGIPCSimulationFaultTolerantRelayingClientLauncher;
import port.sessionserver.SessionServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.BobSessionPort;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class BobSimulationGIPCFaultTolerantRelayingClient extends AGIPCSimulationFaultTolerantRelayingClientLauncher implements BobUI, BobSessionPort {

public BobSimulationGIPCFaultTolerantRelayingClient(String aClientName,
			String aServerHost, String aServerId,
			String aServerName, HalloweenCommandProcessor aCommandProcessor,
			boolean aBroadcastData) {
		super(aClientName,  aServerHost, aServerId, aServerName,
				aCommandProcessor, aBroadcastData);
		// TODO Auto-generated constructor stub
	}
@Override
protected String getClientId() {
	return BOB_ID;
	
}

public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(true);
//	OEFrame oeFrame = BobControllerUI.createUI();
	OEFrame relayerFrame = ServerControllerUI.createUI();
//	relayerFrame.setLocation((int) oeFrame.getLocation().getX(), (int) oeFrame.getLocation().getY());
	relayerFrame.setLocation(BobControllerUI.CONTROLLER_X, BobControllerUI.CONTROLLER_Y);

	relayerFrame.setTitle(NAME + " Relayer");
	HalloweenCommandProcessor aCommandProcessor = BobCommandProcessorFactory.getOrCreateSingleton();
	(new BobSimulationGIPCFaultTolerantRelayingClient(NAME, "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME, 
			aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}
