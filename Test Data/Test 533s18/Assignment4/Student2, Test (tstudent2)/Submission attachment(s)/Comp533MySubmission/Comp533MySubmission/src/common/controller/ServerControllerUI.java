package common.controller;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import common.ui.ServerUI;
import util.remote.RelayerController;
import util.remote.RelayerControllerFactory;
import util.trace.Tracer;

public class ServerControllerUI implements ServerUI {
	static final int Y_OFFSET_FROM_BOB = 15;
	public static OEFrame createUI() {
		Tracer.showWarnings(false);
		RelayerController aController = RelayerControllerFactory.getOrCreateSingleton();
		OEFrame controllerFrame = ObjectEditor.edit(aController);
		controllerFrame.setLocation(CONTROLLER_X, CONTROLLER_Y - Y_OFFSET_FROM_BOB);
		controllerFrame.setSize(CONTROLLER_WIDTH, CONTROLLER_HEIGHT);
		Tracer.showWarnings(false);
		Tracer.showInfo(false);
		return controllerFrame;
		
	}

}
