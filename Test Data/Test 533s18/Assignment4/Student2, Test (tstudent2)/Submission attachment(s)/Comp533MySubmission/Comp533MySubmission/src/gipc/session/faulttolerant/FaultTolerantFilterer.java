package gipc.session.faulttolerant;

public interface FaultTolerantFilterer {

	public abstract String[] getBroadcastHeaders();

	public abstract void setBroadcastHeaders(String[] newVal);

	public abstract boolean isBroadcastCall(String aDestination, Object message);

	String[] getRelayedHeaders();

	void setRelayedHeaders(String[] newVal);

	boolean isRelayCall(String aDestination, Object message);

}