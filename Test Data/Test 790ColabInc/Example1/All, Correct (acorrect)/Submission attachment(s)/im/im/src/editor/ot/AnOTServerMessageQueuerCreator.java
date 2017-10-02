package editor.ot;

import util.session.ServerMessageFilter;
import util.session.ServerMessageFilterCreator;

public class AnOTServerMessageQueuerCreator  implements ServerMessageFilterCreator{
	ServerMessageFilter  serverMessageQueuer = new AnOTServerMessageQueuer();
	@Override
	public ServerMessageFilter getMessageQueuer() {
		//return serverMessageQueuer;
		return new AnOTServerMessageQueuer();
	}

}
