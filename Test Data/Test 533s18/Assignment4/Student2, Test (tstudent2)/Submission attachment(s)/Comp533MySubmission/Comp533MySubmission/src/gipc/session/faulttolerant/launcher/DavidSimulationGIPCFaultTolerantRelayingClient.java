package gipc.session.faulttolerant.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.DavidCommandProcessorFactory;
import common.controller.DavidControllerUI;
import common.controller.ServerControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.DavidUI;
import gipc.session.faulttolerant.relayingclient.AGIPCSimulationFaultTolerantRelayingClientLauncher;
import port.sessionserver.SessionServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.DavidSessionPort;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class DavidSimulationGIPCFaultTolerantRelayingClient extends AGIPCSimulationFaultTolerantRelayingClientLauncher implements DavidUI, DavidSessionPort {

public DavidSimulationGIPCFaultTolerantRelayingClient(String aClientName,
			String aServerHost, String aServerId,
			String aServerName, HalloweenCommandProcessor aCommandProcessor,
			boolean aBroadcastData) {
		super(aClientName,  aServerHost, aServerId, aServerName,
				aCommandProcessor, aBroadcastData);
		// TODO Auto-generated constructor stub
	}
@Override
protected String getClientId() {
	return DAVID_ID;
	
}

public static void main(String[] args) {
	Tracer.showWarnings(true);
	Tracer.showInfo(true);
//	OEFrame oeFrame = CathyControllerUI.createUI();
	OEFrame relayerFrame = ServerControllerUI.createUI();
//	relayerFrame.setLocation((int) oeFrame.getLocation().getX(), (int) oeFrame.getLocation().getY());
	relayerFrame.setLocation(DavidControllerUI.CONTROLLER_X, DavidControllerUI.CONTROLLER_Y);
	relayerFrame.setTitle(NAME + " Relayer");
	HalloweenCommandProcessor aCommandProcessor = DavidCommandProcessorFactory.getOrCreateSingleton();
	(new DavidSimulationGIPCFaultTolerantRelayingClient(NAME, "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME, 
			aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}

