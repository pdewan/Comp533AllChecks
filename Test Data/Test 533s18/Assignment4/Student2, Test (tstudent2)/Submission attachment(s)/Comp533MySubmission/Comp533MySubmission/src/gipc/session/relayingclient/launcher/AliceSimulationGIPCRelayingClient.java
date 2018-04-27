package gipc.session.relayingclient.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.AliceCommandProcessorFactory;
import common.controller.AliceControllerUI;
import common.controller.ServerControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.AliceUI;
import gipc.session.relayingclient.AGIPCSimulationRelayingClientLauncher;
import port.sessionserver.SessionServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.AliceSessionPort;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class AliceSimulationGIPCRelayingClient extends AGIPCSimulationRelayingClientLauncher implements AliceUI, AliceSessionPort {

public AliceSimulationGIPCRelayingClient(String aClientName,
			String aServerHost, String aServerId,
			String aServerName, HalloweenCommandProcessor aCommandProcessor,
			boolean aBroadcastData) {
		super(aClientName,  aServerHost, aServerId, aServerName,
				aCommandProcessor, aBroadcastData);
		// TODO Auto-generated constructor stub
	}
@Override
protected String getClientId() {
	return ALICE_ID;
	
}

public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(false);
	OEFrame oeFrame = AliceControllerUI.createUI();
	OEFrame relayerFrame = ServerControllerUI.createUI();
	relayerFrame.setLocation((int) oeFrame.getLocation().getX(), (int) oeFrame.getLocation().getY());
	relayerFrame.setTitle(NAME + " Relayer");
	HalloweenCommandProcessor aCommandProcessor = AliceCommandProcessorFactory.getOrCreateSingleton();
	(new AliceSimulationGIPCRelayingClient(NAME, "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME, 
			aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}

