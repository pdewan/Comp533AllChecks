package gipc.session.faulttolerant.messages;

public class AnIdentifiableMessage implements IdentifiableMessage {
	Object message;
	String identifier;
	
	public AnIdentifiableMessage(Object message, String identifier) {
		super();
		this.message = message;
		this.identifier = identifier;
	}
	public AnIdentifiableMessage() {
		
	}
	@Override
	public Object getMessage() {
		return message;
	}
	@Override
	public void setMessage(Object message) {
		this.message = message;
	}
	@Override
	public String getIdentifier() {
		return identifier;
	}
	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String toString() {
		return identifier + ":" + message;
	}
	
	

}
