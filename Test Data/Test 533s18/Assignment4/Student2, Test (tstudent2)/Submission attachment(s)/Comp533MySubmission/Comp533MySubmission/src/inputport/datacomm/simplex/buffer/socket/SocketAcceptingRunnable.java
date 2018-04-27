package inputport.datacomm.simplex.buffer.socket;

import java.net.Socket;

public interface SocketAcceptingRunnable extends Runnable {
	Thread getReadingThread(Socket socket);
}
