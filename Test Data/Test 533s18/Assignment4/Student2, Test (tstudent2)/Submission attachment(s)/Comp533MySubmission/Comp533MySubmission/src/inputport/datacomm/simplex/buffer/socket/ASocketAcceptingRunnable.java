package inputport.datacomm.simplex.buffer.socket;

import java.net.ServerSocket;
import java.net.Socket;

import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.misc.StoppableRunnable;


public class ASocketAcceptingRunnable implements SocketAcceptingRunnable {
	ServerSocket serverSocket;
	SocketServerInputDriver driver;
	IdentityMap<Socket, Thread> socketToReadingThread = new HashIdentityMap();
	public  ASocketAcceptingRunnable(SocketServerInputDriver aSocketServerInputDriver,  ServerSocket aServerSocket) {
		driver = aSocketServerInputDriver;
		serverSocket = aServerSocket;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				StoppableRunnable socketReadingRunnable = new ASocketReadingRunnable(socket, driver, driver);
				driver.socketAccepted(serverSocket, socket);
				Thread thread = new Thread(socketReadingRunnable);
				thread.setName("Socket reading thread for " + socket);
				socketToReadingThread.put(socket, thread);
				thread.start();
				
			} catch (Exception e) {
				e.printStackTrace();
				driver.socketNotAccepted(serverSocket, e);
			}
		}
		
	}

	@Override
	public Thread getReadingThread(Socket socket) {
		return socketToReadingThread.get(socket);
	}

}
