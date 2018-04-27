package nio.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import port.trace.buffer.nio.SocketChannelConnectFinished;
//import port.trace.buffer.nio.SocketChannelConnectInitiated;
//import port.trace.buffer.nio.SocketChannelInterestOp;
//import port.trace.buffer.nio.SocketChannelRead;
//import port.trace.buffer.nio.SocketChannelRegistered;
//import port.trace.buffer.nio.SocketChannelWritten;
import common.client.ClientParameters;
import common.controller.TrickOrTreatClientControllerFactory;

//import niotut.RspHandler;

import main.BeauAndersonFinalProject;
import nio.common.ChangeRequest;
import nio.couplers.AnNIOSimulationInCoupler;
import nio.couplers.AnNIOSimulationOutCoupler;
import nio.couplers.NIOSimulationInCoupler;
import stringProcessors.HalloweenCommandProcessor;
import util.remote.ClientControllerFactory;
import util.remote.RelayerClientControllerFactory;
import util.trace.port.nio.SocketChannelConnectInitiated;
import util.trace.port.nio.SocketChannelConnected;
import util.trace.port.nio.SocketChannelInterestOp;
import util.trace.port.nio.SocketChannelRead;
import util.trace.port.nio.SocketChannelRegistered;
import util.trace.port.nio.SocketChannelWritten;

public class TrickOrTreatNioClient implements Runnable, ClientParameters {
	// The host:port combination to connect to
	public static final String SIMULATION1_PREFIX = "1:";
	public static final String SIMULATION2_PREFIX = "2:";
	public static final int SIMULATION_2_X_OFFSET = 250;
	public static final int SIMULATION_3_X_OFFSET = 500;

	public static int SIMULATION_COMMAND_Y_OFFSET = 0;
	public static int SIMULATION_WIDTH = 400;
	public static int SIMULATION_HEIGHT = 765;
	SocketChannel socket;
	boolean sendUsingNIO;

//	private InetAddress hostAddress;
	private String host;
	private int port;

	// The selector we'll be monitoring
	private Selector selector;

	// The buffer into which we'll read data when it's available
	private ByteBuffer readBuffer = ByteBuffer.allocate(8192);

	// A list of PendingChange instances
	private List pendingChanges = new LinkedList();

	// Maps a SocketChannel to a list of ByteBuffer instances
	private Map pendingData = new HashMap();

	// Maps a SocketChannel to a RspHandler
	// private Map rspHandlers = Collections.synchronizedMap(new HashMap());
	NIOSimulationInCoupler simulationInCoupler;

//	public TrickOrTreatNioClient(InetAddress hostAddress, int port,
//			boolean aSendUsingNIO) throws IOException {
//		sendUsingNIO = aSendUsingNIO;
//		this.hostAddress = hostAddress;
//		this.port = port;
//		this.selector = this.initSelector();
//		// ClientControllerFactory.getOrCreateSingleton().setServerId(hostAddress.toString()
//		// + ": " + port);
//		String aFullServerId = hostAddress.toString() + ": " + port;
//		RelayerClientControllerFactory.getOrCreateSingleton().setServerId(
//				"" + port);
//		RelayerClientControllerFactory.getOrCreateSingleton().setServerHost(
//				hostAddress.toString());
//		RelayerClientControllerFactory.getOrCreateSingleton().setRelayerId(
//				aFullServerId);
//
//	}
	public TrickOrTreatNioClient(String aHost, int port,
			boolean aSendUsingNIO) throws IOException {
		sendUsingNIO = aSendUsingNIO;
		this.host = aHost;
		this.port = port;
		this.selector = this.initSelector();
		// ClientControllerFactory.getOrCreateSingleton().setServerId(hostAddress.toString()
		// + ": " + port);
		String aFullServerId = host + ": " + port;
//		RelayerClientControllerFactory.getOrCreateSingleton().setServerId(
//				"" + port);
		RelayerClientControllerFactory.getOrCreateSingleton().setServerHost(
				aHost);
//		RelayerClientControllerFactory.getOrCreateSingleton().setRelayerId(
//				aFullServerId);

	}

	public void send(byte[] data) throws IOException {
		// Start a new connection
		// SocketChannel socket = this.initiateConnection();

		// Register the response handler
		// this.rspHandlers.put(socket, handler);

		// And queue the data we want written
		synchronized (this.pendingData) {
			List queue = (List) this.pendingData.get(socket);
			if (queue == null) {
				queue = new ArrayList();
				this.pendingData.put(socket, queue);
			}
			queue.add(ByteBuffer.wrap(data));
		}
		SelectionKey aKey = socket.keyFor(selector);
		aKey.interestOps(SelectionKey.OP_WRITE);
//		socket.keyFor(selector).interestOps(SelectionKey.OP_WRITE);

		SocketChannelInterestOp.newCase(this, aKey, SelectionKey.OP_WRITE);


		// Finally, wake up our selecting thread so it can make the required
		// changes
		this.selector.wakeup();
	}

	public void run() {
		while (true) {
			try {
				// Process any pending changes
				synchronized (this.pendingChanges) {
					Iterator changes = this.pendingChanges.iterator();
					while (changes.hasNext()) {
						ChangeRequest change = (ChangeRequest) changes.next();
						switch (change.type) {
						case ChangeRequest.CHANGEOPS:
							SelectionKey key = change.socket
									.keyFor(this.selector);
							key.interestOps(change.ops);
							SocketChannelInterestOp.newCase(this, key, change.ops);

							break;
						case ChangeRequest.REGISTER:
							change.socket.register(this.selector, change.ops);
							SocketChannelRegistered.newCase(this, 
									socket, selector, change.ops);

							break;
						}
					}
					this.pendingChanges.clear();
				}

				// Wait for an event one of the registered channels
				this.selector.select();

				// Iterate over the set of keys for which events are available
				Iterator selectedKeys = this.selector.selectedKeys().iterator();
				while (selectedKeys.hasNext()) {
					SelectionKey key = (SelectionKey) selectedKeys.next();
					selectedKeys.remove();

					if (!key.isValid()) {
						continue;
					}

					// Check what event is available and deal with it
					if (key.isConnectable()) {
						this.finishConnection(key);
					} else if (key.isReadable()) {
						this.read(key);
					} else if (key.isWritable()) {
						this.write(key);
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		// Clear out our read buffer so it's ready for new data
		this.readBuffer.clear();

		// Attempt to read off the channel
		int numRead;
		try {
			numRead = socketChannel.read(this.readBuffer);
			SocketChannelRead.newCase(this, socketChannel, readBuffer, numRead);
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			key.cancel();
			socketChannel.close();
			return;
		}

		if (numRead == -1) {
			// Remote entity shut the socket down cleanly. Do the
			// same from our end and cancel the channel.
			key.channel().close();
			key.cancel();
			return;
		}

		// Handle the response
		this.handleResponse(socketChannel, this.readBuffer.array(), numRead);
	}

	private void handleResponse(SocketChannel socketChannel, byte[] data,
			int numRead) throws IOException {
		// Make a correctly sized copy of the data before handing it
		// to the client
		byte[] rspData = new byte[numRead];
		System.arraycopy(data, 0, rspData, 0, numRead);

		// Look up the handler for this channel
		// RspHandler handler = (RspHandler)
		// this.rspHandlers.get(socketChannel);
		TrickOrTreatClientControllerFactory.getOrCreateSingleton()
				.getSendReceiveSettings().maybeDelayReceive();

		simulationInCoupler.handleResponse(rspData);
		// And pass the response to it
		// if (handler.handleResponse(rspData)) {
		// // The handler has seen enough, close the connection
		// socketChannel.close();
		// socketChannel.keyFor(this.selector).cancel();
		// }
	}

	private void write(SelectionKey key) throws IOException {
		TrickOrTreatClientControllerFactory.getOrCreateSingleton()
				.getSendReceiveSettings().maybeDelaySend();

		SocketChannel socketChannel = (SocketChannel) key.channel();

		synchronized (this.pendingData) {
			List queue = (List) this.pendingData.get(socketChannel);

			// Write until there's not more data ...
			while (!queue.isEmpty()) {
				ByteBuffer buf = (ByteBuffer) queue.get(0);
				socketChannel.write(buf);
				SocketChannelWritten.newCase(this, socketChannel, buf);
				if (buf.remaining() > 0) {
					// ... or the socket's buffer fills up
					break;
				}
				queue.remove(0);
			}

			if (queue.isEmpty()) {
				// We wrote away all data, so we're no longer interested
				// in writing on this socket. Switch back to waiting for
				// data.
				key.interestOps(SelectionKey.OP_READ);
				SocketChannelInterestOp.newCase(this, key, SelectionKey.OP_READ);
			}
		}
	}

	HalloweenCommandProcessor commandProcessor;

	void createOutCoupler() {
		if (sendUsingNIO)
			new AnNIOSimulationOutCoupler(commandProcessor, this);
	}

	public void createUI(String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset) {
		HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject
				.createSimulation(aPrefix, anXOffset, aYOffset, aWidth,
						aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
		createUI(aCommandProcessor);
		// try {
		// commandProcessor = BeauAndersonFinalProject.createSimulation(
		// aPrefix, anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset,
		// aCommandWindowYOffset);
		// simulationInCoupler = new ASimulationInCoupler(commandProcessor);
		// socket = initiateConnection();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public void createUI(HalloweenCommandProcessor aCommandProcessor) {
		try {
			commandProcessor = aCommandProcessor;
			simulationInCoupler = new AnNIOSimulationInCoupler(commandProcessor);
			socket = initiateConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	private void finishConnection(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		// Finish the connection. If the ion operation failed
		// this will raise an IOException.
		try {
			socketChannel.finishConnect();
			createOutCoupler();
			// key.interestOps(ops)
			key.interestOps(SelectionKey.OP_READ);
			SocketChannelConnected.newCase(this, socketChannel, null);
			SocketChannelInterestOp.newCase(this, key, SelectionKey.OP_READ);


		} catch (IOException e) {
			// Cancel the channel's registration with our selector
			System.out.println(e);
			key.cancel();
			return;
		}

		// Register an interest in writing on this channel
		// key.interestOps(SelectionKey.OP_WRITE);
	}

	public SocketChannel initiateConnection() throws IOException {
		// Create a non-blocking socket channel
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		String aHost = ClientControllerFactory.getOrCreateSingleton().getServerHost();
//		InetAddress aHostAddress = InetAddress.getByName(aHost);
		SocketAddress aSocketAddress = new InetSocketAddress(InetAddress.getByName(aHost), this.port);
		// Kick off connection establishment
		socketChannel
				.connect(
						aSocketAddress);
		SocketChannelConnectInitiated.newCase(this, socketChannel, aSocketAddress);		
		String aFullServerId = host + ": " + port;
		RelayerClientControllerFactory.getOrCreateSingleton().setServerId(
				"" + port);	
		RelayerClientControllerFactory.getOrCreateSingleton().setRelayerId(
				aFullServerId);


		// Queue a channel registration since the caller is not the
		// selecting thread. As part of the registration we'll register
		// an interest in connection events. These are raised when a channel
		// is ready to complete connection establishment.
		synchronized (this.pendingChanges) {
			this.pendingChanges.add(new ChangeRequest(socketChannel,
					ChangeRequest.REGISTER, SelectionKey.OP_CONNECT));
		}
		selector.wakeup();

		return socketChannel;
	}

	private Selector initSelector() throws IOException {
		// Create a new selector
		return SelectorProvider.provider().openSelector();
	}

	// public static void main(String[] args) {
	// try {
	// NioClient client = new NioClient(InetAddress.getByName("www.google.com"),
	// 80);
	// //NioClient client = new NioClient(InetAddress.getByName("localhost"),
	// 9090);
	// Thread t = new Thread(client);
	// t.setDaemon(true);
	// t.start();
	// RspHandler handler = new RspHandler();
	// client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
	// handler.waitForResponse();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static void launchClient(boolean aSendUsingNIO, String aPrefix,
			int anXOffset, int aYOffset, int aWidth, int aHeight,
			int aCommandWindowXOffset, int aCommandWindowYOffset) {
		try {
			HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject
					.createSimulation(aPrefix, anXOffset, aYOffset, aWidth,
							aHeight, aCommandWindowXOffset,
							aCommandWindowYOffset);
			launchClient(aCommandProcessor, aSendUsingNIO);

			// // String currentDir = System.getProperty("user.dir");
			// // System.out.println("Current dir using System:" +currentDir);
			//
			// TrickOrTreatNioClient client = new
			// TrickOrTreatNioClient(InetAddress.getByName("localhost"), 9090);
			// Thread t = new Thread(client);
			// t.setDaemon(true);
			// t.start();
			// // HalloweenCommandProcessor aCommandProcessor =
			// BeauAndersonFinalProject.createSimulation(
			// // SIMULATION2_PREFIX, 0,
			// TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET,
			// SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
			// client.createUI(aCommandProcessor);
			// // client.initiateConnection();
			// // RspHandler handler = new RspHandler();
			// // client.send("Hello World".getBytes(), handler);
			// // handler.waitForResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void launchClient(
			HalloweenCommandProcessor aCommandProcessor, boolean aSendUsingNIO) {
		try {

			// String currentDir = System.getProperty("user.dir");
			// System.out.println("Current dir using System:" +currentDir);

//			TrickOrTreatNioClient client = new TrickOrTreatNioClient(
//					InetAddress.getByName(SERVER_HOST), 9090, aSendUsingNIO);
			TrickOrTreatNioClient client = new TrickOrTreatNioClient(
					SERVER_HOST, 9090, aSendUsingNIO);
			Thread t = new Thread(client);
			t.setName("NIO Client Selector");
			t.setDaemon(true);
			t.start();
			// HalloweenCommandProcessor aCommandProcessor =
			// BeauAndersonFinalProject.createSimulation(
			// SIMULATION2_PREFIX, 0,
			// TrickOrTreatNioClient.SIMULATION_COMMAND_Y_OFFSET,
			// SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
			client.createUI(aCommandProcessor);
			// client.initiateConnection();
			// RspHandler handler = new RspHandler();
			// client.send("Hello World".getBytes(), handler);
			// handler.waitForResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// commandProcessor = BeauAndersonFinalProject.createSimulation(
		// "SIMULATION1_PREFIX", 0, SIMULATION_COMMAND_Y_OFFSET,
		// SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);

		try {

			TrickOrTreatNioClient client = new TrickOrTreatNioClient(
					SERVER_HOST, 9090, true);
			Thread clientThread = new Thread(client);
			clientThread.setDaemon(true);
			clientThread.setName("NIO Client");
			clientThread.start();
			client.createUI(SIMULATION1_PREFIX, 0, SIMULATION_COMMAND_Y_OFFSET,
					SIMULATION_WIDTH, SIMULATION_HEIGHT, 0, 0);
			// client.initiateConnection();
			// RspHandler handler = new RspHandler();
			// client.send("Hello World".getBytes(), handler);
			// handler.waitForResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
