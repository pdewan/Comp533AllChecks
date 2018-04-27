package gipc.session.faulttolerant.launcher;

import bus.uigen.OEFrame;
import common.commandprocessor.AliceCommandProcessorFactory;
import common.controller.AliceControllerUI;
import common.controller.ServerControllerUI;
import common.controller.TrickOrTreatClientControllerFactory;
import common.ui.AliceUI;
import gipc.session.faulttolerant.relayingclient.AGIPCSimulationFaultTolerantRelayingClientLauncher;
import port.sessionserver.SessionServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.AliceSessionPort;
import stringProcessors.HalloweenCommandProcessor;
import util.trace.Tracer;



public class AliceSimulationGIPCFaultTolerantRelayingClient extends AGIPCSimulationFaultTolerantRelayingClientLauncher implements AliceUI, AliceSessionPort {

public AliceSimulationGIPCFaultTolerantRelayingClient(String aClientName,
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
	Tracer.showInfo(true);
//	OEFrame oeFrame = AliceControllerUI.createUI();
	OEFrame relayerFrame = ServerControllerUI.createUI();
//	relayerFrame.setLocation((int) oeFrame.getLocation().getX(), (int) oeFrame.getLocation().getY());
	relayerFrame.setLocation(AliceControllerUI.CONTROLLER_X, AliceControllerUI.CONTROLLER_Y);
	relayerFrame.setTitle(NAME + " Relayer");
	HalloweenCommandProcessor aCommandProcessor = AliceCommandProcessorFactory.getOrCreateSingleton();
	(new AliceSimulationGIPCFaultTolerantRelayingClient(NAME, "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME, 
			aCommandProcessor, true )).launch();
	TrickOrTreatClientControllerFactory.getOrCreateSingleton().executeCommandLoop(); 
  }
}

