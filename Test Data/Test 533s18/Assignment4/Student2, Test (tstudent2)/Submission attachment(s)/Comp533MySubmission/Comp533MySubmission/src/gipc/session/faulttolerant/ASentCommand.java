package gipc.session.faulttolerant;

public class ASentCommand implements SentCommand {
	String source;
	String command;
	int id;
	public ASentCommand(String aSource, String aCommand) {
		this.source = aSource;
		this.command = aCommand;
		id = this.hashCode();
	}
	@Override
	public int getId() {
		return id;
	}
	@Override
	public String getSource() {
		return source;
	}
//	@Override
//	public void setaSource(String aSource) {
//		this.aSource = aSource;
//	}
	@Override
	public String getCommand() {
		return command;
	}
//	@Override
//	public void setaCommand(String aCommand) {
//		this.aCommand = aCommand;
//	}

}
