package gipc.session.faulttolerant;


public class FaultTolerantFiltererFactory {
	static FaultTolerantFilterer singleton;
	public static void setSingleton(FaultTolerantFilterer newVal) {
		singleton = newVal;
	}
	public static FaultTolerantFilterer getOrCreateSingleton() {
		if (singleton == null) {
			singleton = new AFaultToleranRPCFilterer();
		}
		return singleton;
			
	}
	
}
