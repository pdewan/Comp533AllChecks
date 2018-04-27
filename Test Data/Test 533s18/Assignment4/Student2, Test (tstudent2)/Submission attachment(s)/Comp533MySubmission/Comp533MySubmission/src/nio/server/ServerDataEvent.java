package nio.server;

import java.nio.channels.SocketChannel;

public class ServerDataEvent {
	public SimulationNioRelayer server;
	public SocketChannel socket;
	public byte[] data;
	
	public ServerDataEvent(SimulationNioRelayer server, SocketChannel socket, byte[] data) {
		this.server = server;
		this.socket = socket;
		this.data = data;
	}
}
