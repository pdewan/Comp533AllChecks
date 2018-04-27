package common.controller;

import util.remote.RelayerClientControllerFactory;

public class TrickOrTreatClientControllerFactory {
	static TrickOrTreatClientController singleton;
	public static void setSingleton(TrickOrTreatClientController newVal) {
		singleton = newVal;
	}
	public static TrickOrTreatClientController getOrCreateSingleton() {
		if (singleton == null) {
			singleton = new ATrickOrTreatClientController();
			RelayerClientControllerFactory.setSingleton( singleton);
		}
		return singleton;
			
	}

}
