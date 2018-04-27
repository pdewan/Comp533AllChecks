package common.controller;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import common.ui.AliceUI;
import util.trace.Tracer;

public class AliceControllerUI implements AliceUI {
	public static OEFrame createUI() {
		TrickOrTreatClientController aController = TrickOrTreatClientControllerFactory.getOrCreateSingleton();
		OEFrame controllerFrame = ObjectEditor.edit(aController);
		controllerFrame.setLocation(CONTROLLER_X, CONTROLLER_Y);
		controllerFrame.setSize(CONTROLLER_WIDTH, CONTROLLER_HEIGHT);
		controllerFrame.setTitle(NAME);
		Tracer.showWarnings(false);
//		Tracer.showInfo(false);
		return controllerFrame;
		
	}

}
