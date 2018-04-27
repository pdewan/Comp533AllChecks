package nio.couplers;

public interface NIOSimulationInCoupler {
	String COMMAND_SEPARATOR = ",";
	public  void handleResponse(byte[] rsp);

}
