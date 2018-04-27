package sync.server;
import bus.uigen.widgets.VirtualToolkit;
import bus.uigen.widgets.swing.SwingToolkit;
import edu.unc.sync.server.SyncServer;
public class StartSimationSyncServer {
	public static final String SERVER_NAME = "A";
	public static void main(String[] args) {
		//String[] myArgs = {"--ui", "--trace",  "--server_id", "A"};
//		String[] myArgs = {"--ui",  "--server_id", "A"};	
		String[] myArgs = {"--ui",  "--server_id", SERVER_NAME};	

		VirtualToolkit.setDefaultToolkit(new SwingToolkit());
		SyncServer.instantiate(myArgs);
	}
}
