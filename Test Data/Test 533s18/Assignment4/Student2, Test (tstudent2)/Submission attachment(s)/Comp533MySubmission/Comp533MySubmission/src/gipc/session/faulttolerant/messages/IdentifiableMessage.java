package gipc.session.faulttolerant.messages;

import java.io.Serializable;

public interface IdentifiableMessage extends Serializable{

	public abstract Object getMessage();

	public abstract void setMessage(Object message);

	public abstract String getIdentifier();

	public abstract void setIdentifier(String identifier);

}