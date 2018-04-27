package gipc.session.relayingclient;

public class RelayerElectorFactory {
	static RelayerElector singleton;
	public static void setSingleton(RelayerElector newVal) {
		singleton = newVal;
	}
	public static RelayerElector getSingleton() {
		if (singleton == null) {
			singleton = new ARelayerElector();
		}
		return singleton;
			
	}
	
}
