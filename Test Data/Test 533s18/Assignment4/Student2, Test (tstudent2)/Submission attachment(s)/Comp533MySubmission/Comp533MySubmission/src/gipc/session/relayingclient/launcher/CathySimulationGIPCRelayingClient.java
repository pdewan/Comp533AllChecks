package gipc.session.relayingclient.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.CathyCommandProcessorFactory;
import common.controller.CathyControllerUI;
import common.controller.ServerControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.CathyUI;
import gipc.session.relayingclient.AGIPCSimulationRelayingClientLauncher;
import port.sessionserver.SessionServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.CathySessionPort;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class CathySimulationGIPCRelayingClient extends AGIPCSimulationRelayingClientLauncher implements CathyUI, CathySessionPort {

public CathySimulationGIPCRelayingClient(String aClientName,
			String aServerHost, String aServerId,
			String aServerName, HalloweenCommandProcessor aCommandProcessor,
			boolean aBroadcastData) {
		super(aClientName,  aServerHost, aServerId, aServerName,
				aCommandProcessor, aBroadcastData);
		// TODO Auto-generated constructor stub
	}
@Override
protected String getClientId() {
	return CATHY_ID;
	
}

public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(false);
	OEFrame oeFrame = CathyControllerUI.createUI();
	OEFrame relayerFrame = ServerControllerUI.createUI();
	relayerFrame.setLocation((int) oeFrame.getLocation().getX(), (int) oeFrame.getLocation().getY());
	relayerFrame.setTitle(NAME + " Relayer");
	HalloweenCommandProcessor aCommandProcessor = CathyCommandProcessorFactory.getOrCreateSingleton();
	(new CathySimulationGIPCRelayingClient(NAME, "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME, 
			aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}

