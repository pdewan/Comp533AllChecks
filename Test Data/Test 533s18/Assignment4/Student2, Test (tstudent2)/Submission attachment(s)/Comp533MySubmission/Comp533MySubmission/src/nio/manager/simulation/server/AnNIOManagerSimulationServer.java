package nio.manager.simulation.server;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import assignments.util.inputParameters.AnAbstractSimulationParametersBean;
import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerArgsProcessor;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.factories.classes.AReadingAcceptCommandFactory;
import inputport.nio.manager.factories.selectors.AcceptCommandFactorySelector;
import util.interactiveMethodInvocation.SimulationParametersControllerFactory;
import util.trace.Tracer;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.nio.SocketChannelBound;

public class AnNIOManagerSimulationServer extends AnAbstractSimulationParametersBean implements NIOManagerSimulationServer {
	// ObservableNIOManager nioManager;
	ServerSocketChannel serverSocketChannel;
	NIOManagerSimulationServerMulticaster multicaster;
	NIOManagerSimulationServerReceiver receiver;
	public static final String READ_THREAD_NAME = "Read Thread";

//	public AnNIOManagerSimulationServer(int aServerPort) {
////		AcceptCommandSelector.setFactory(new AReadingAcceptCommandFactory());
//		ServerSocketChannel aServerSocketChannel = createSocketChannel(aServerPort);
//		NIOManagerFactory.getSingleton().enableListenableAccepts(
//				aServerSocketChannel, this);
//
//	}

	protected void createMulticaster() {
		multicaster = new AnNIOManagerSimulationServerMulticaster();
		Thread aMultiCasterThread = new Thread(multicaster);
		aMultiCasterThread.setName(READ_THREAD_NAME);
		aMultiCasterThread.start();
	}

	protected void createReceiver() {
		receiver = new AnNIOManagerSimulationServerReceiver(multicaster);
	}

	protected void setFactories() {
		AcceptCommandFactorySelector.setFactory(new AReadingAcceptCommandFactory());
	}


	protected void createCommunicationObjects() {
		createMulticaster();
		createReceiver();
	}

	protected ServerSocketChannel createSocketChannel(int aServerPort) {
		try {
			ServerSocketChannel retVal = ServerSocketChannel.open();
			InetSocketAddress isa = new InetSocketAddress(aServerPort);
			retVal.socket().bind(isa);
			SocketChannelBound.newCase(this, retVal, isa);
			return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void makeServerConnectable(int aServerPort) {
//		ServerSocketChannel aServerSocketChannel = createSocketChannel(aServerPort);
		NIOManagerFactory.getSingleton().enableListenableAccepts(
				serverSocketChannel, this);
	}
	@Override
	public void initialize(int aServerPort) {
		setFactories();
		serverSocketChannel = createSocketChannel(aServerPort);
		createCommunicationObjects();
		makeServerConnectable(aServerPort);
		SimulationParametersControllerFactory.getSingleton().addSimulationParameterListener(this);
//		SimulationParametersControllerFactory.getSingleton().processCommands();
	}

	protected void addListeners(SocketChannel aSocketChannel) {
		addReadListener(aSocketChannel);
//		addWriteBufferListener(aSocketChannel);
	}

	protected void addReadListener(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().addReadListener(aSocketChannel,
				receiver);
	}

	protected void joinMulticast(SocketChannel aSocketChannel) {
		multicaster.join(aSocketChannel);
	}

	@Override
	public void socketChannelAccepted(ServerSocketChannel aServerSocketChannel,
			SocketChannel aSocketChannel) {
		joinMulticast(aSocketChannel);
		addListeners(aSocketChannel);
//		addReadListener(aSocketChannel);
//		addWriteBufferListener(aSocketChannel);
	}
	protected void addWriteBufferListener(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().addWriteBoundedBufferListener(
				aSocketChannel, this);
	}
	@Override
	public void writeBufferIsEmpty(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().enableReads(aSocketChannel);
	}
	
	protected static NIOManagerSimulationServer nioManagerSimulationServer;
	public static NIOManagerSimulationServer getNIOManagerSimulationServer() {
		return nioManagerSimulationServer;
	}
	public static void launch(String[] args) {
		args = ServerArgsProcessor.removeEmpty(args);
		NIOManagerSimulationServer anNIOManagerSimulationServer = 
				new AnNIOManagerSimulationServer();
		anNIOManagerSimulationServer.initialize(ServerArgsProcessor.getServerPort(args));
		nioManagerSimulationServer = anNIOManagerSimulationServer;
		SimulationParametersControllerFactory.getSingleton().processCommands();

	}

	

	@Override
	public void experimentInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit(int aCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void simulationCommand(String aCommand) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		NIOTraceUtility.setTracing();
//		Tracer.setBufferTracedMessages(true);
		NIOManagerSimulationServer anNIOManagerSimulationServer = 
				new AnNIOManagerSimulationServer();
		args = ServerArgsProcessor.removeEmpty(args);
		anNIOManagerSimulationServer.initialize(ServerArgsProcessor.getServerPort(args));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Buffered messages");
		System.out.println(Tracer.getBufferedTracedMessages());
	}
	

}
