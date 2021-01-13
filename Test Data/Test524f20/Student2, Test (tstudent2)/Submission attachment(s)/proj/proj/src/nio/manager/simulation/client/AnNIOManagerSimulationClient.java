package nio.manager.simulation.client;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.factories.classes.AReadingWritingConnectCommandFactory;
import inputport.nio.manager.factories.selectors.ConnectCommandFactorySelector;
import main.BeauAndersonFinalProject;
import nio.manager.simulation.server.AnNIOManagerSimulationServer;
import stringProcessors.HalloweenCommandProcessor;
import util.annotations.Tags;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;
import util.interactiveMethodInvocation.SimulationParametersControllerFactory;
import util.tags.DistributedTags;
import util.trace.Tracer;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.PerformanceExperimentEnded;
import util.trace.port.PerformanceExperimentStarted;
import util.trace.port.nio.NIOTraceUtility;
@Tags({DistributedTags.CLIENT})
public class AnNIOManagerSimulationClient implements NIOManagerSimulationClient {
	String clientName;
	public static final String SIMULATION1_PREFIX = "1:";
	public static final String SIMULATION2_PREFIX = "2:";
	public static final String SIMULATION3_PREFIX = "3:";
	public static final int SIMULATION_2_X_OFFSET = 250;
	public static final int SIMULATION_3_X_OFFSET = 500;
	public static int SIMULATION_COMMAND_Y_OFFSET = 0;
	public static int SIMULATION_WIDTH = 400;
	public static int SIMULATION_HEIGHT = 765;
	protected HalloweenCommandProcessor commandProcessor;
	protected NIOManagerSimulationInCoupler nioManagerSimulationInCoupler;
	protected NIOManagerSimulationOutCoupler nioSimulationOutCoupler;
	protected int NUM_EXPERIMENT_COMMANDS = 500;
	public static final String EXPERIMENT_COMMAND_1 = "move 1 -1";
	public static final String EXPERIMENT_COMMAND_2 = "undo";
	SocketChannel socketChannel;
//	boolean localProcessingOnly = false;

	public AnNIOManagerSimulationClient(String aClientName) {
		// ConnectCommandSelector.setFactory(new AConnectCommandFactory());
		// ConnectCommandSelector.setFactory(new
		// AReadingWritingConnectCommandFactory());
		clientName = aClientName;
		// socketChannel = createSocketChannel();
		// InetAddress aServerAddress = InetAddress.getByName(aServerHost);
		// nioManager.connect(socketChannel, aServerAddress, aServerPort, this);
	}

	protected void setFactories() {
		ConnectCommandFactorySelector
				.setFactory(new AReadingWritingConnectCommandFactory());

	}

	protected SocketChannel createSocketChannel() {
		try {
			SocketChannel retVal = SocketChannel.open();
			return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void connectToServer(String aServerHost, int aServerPort) {
		try {
			InetAddress aServerAddress = InetAddress.getByName(aServerHost);

			NIOManagerFactory.getSingleton().connect(socketChannel,
					aServerAddress, aServerPort, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void createUI(HalloweenCommandProcessor aCommandProcessor) {
	// try {
	// commandProcessor = aCommandProcessor;
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// public void createUI(String aPrefix, int anXOffset, int aYOffset,
	// int aWidth, int aHeight, int aCommandWindowXOffset,
	// int aCommandWindowYOffset) {
	// HalloweenCommandProcessor aCommandProcessor = BeauAndersonFinalProject
	// .createSimulation(aPrefix, anXOffset, aYOffset, aWidth,
	// aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
	// createUI(aCommandProcessor);
	// // try {
	// // commandProcessor = BeauAndersonFinalProject.createSimulation(
	// // aPrefix, anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset,
	// // aCommandWindowYOffset);
	// // simulationInCoupler = new ASimulationInCoupler(commandProcessor);
	// // socket = initiateConnection();
	// // } catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// }
	protected void createCommunicationObjects(SocketChannel aSocketChannel) {
		nioSimulationOutCoupler = new AnNIOManagerSimulationClientOutCoupler(
				aSocketChannel);
		nioManagerSimulationInCoupler = new AnNIOManagerSimulationClientInCoupler(
				commandProcessor);
		Thread aThread = new Thread(nioManagerSimulationInCoupler);
		aThread.setName(AnNIOManagerSimulationServer.READ_THREAD_NAME);
		aThread.start();

	}

	protected void addListeners(SocketChannel aSocketChannel) {
		// nioSimulationOutCoupler = new
		// AnNIOManagerSimulationOutCoupler(aSocketChannel);
		commandProcessor.addPropertyChangeListener(nioSimulationOutCoupler);
		// nioManagerSimulationInCoupler = new AnNIOManagerSimulationInCoupler(
		// commandProcessor);
		NIOManagerFactory.getSingleton().addReadListener(aSocketChannel,
				nioManagerSimulationInCoupler);

//		addWriteBufferListener(aSocketChannel);
	}

	public void connected(SocketChannel aSocketChannel) {
		// createCommunicationObjects(aSocketChannel);
		// addListeners(aSocketChannel);
		//
		// nioSimulationOutCoupler = new
		// AnNIOManagerSimulationOutCoupler(aSocketChannel);
		// commandProcessor.addPropertyChangeListener(nioSimulationOutCoupler);
		//
		// NIOManagerFactory.getSingleton().addReadListener(aSocketChannel,
		// nioManagerSimulationInCoupler);
	}

	@Override
	public void notConnected(SocketChannel theSocketChannel, Exception e) {

	}

	@Override
	public void createModelAndUI(String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset) {
		commandProcessor = BeauAndersonFinalProject.createSimulation(aPrefix,
				anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset,
				aCommandWindowYOffset);
	}

	@Override
	public void initialize(String aServerHost, int aServerPort,
			String aClientName, String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset) {
		setFactories();
		createModelAndUI(aPrefix, anXOffset, aYOffset, aWidth, aHeight,
				aCommandWindowXOffset, aCommandWindowYOffset);
		socketChannel = createSocketChannel();
		createCommunicationObjects(socketChannel);
		addListeners(socketChannel);
		connectToServer(aServerHost, aServerPort);
		SimulationParametersControllerFactory.getSingleton().addSimulationParameterListener(this);
		SimulationParametersControllerFactory.getSingleton().processCommands();		
	}

	protected void addWriteBufferListener(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().addWriteBoundedBufferListener(
				aSocketChannel, this);
	}

	@Override
	public void writeBufferIsEmpty(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().enableReads(aSocketChannel);
	}

	public static void launchClient(String aServerHost, int aServerPort,
			String aClientName, String aPrefix, int anXOffset, int aYOffset,
			int aWidth, int aHeight, int aCommandWindowXOffset,
			int aCommandWindowYOffset) {
		NIOManagerSimulationClient aClient = new AnNIOManagerSimulationClient(
				aClientName);
		aClient.initialize(aServerHost, aServerPort, aClientName, aPrefix,
				anXOffset, aYOffset, aWidth, aHeight, aCommandWindowXOffset,
				aCommandWindowYOffset);
		// aClient.createModelAndUI(aPrefix, anXOffset, aYOffset, aWidth,
		// aHeight, aCommandWindowXOffset, aCommandWindowYOffset);
		// aClient.connectToServer(aServerHost, aServerPort);

	}

	public static void main(String[] args) {
//		NIOTraceUtility.setTracing();
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		launchClient(ClientArgsProcessor.getServerHost(args),
				ClientArgsProcessor.getServerPort(args),
				ClientArgsProcessor.getClientName(args), SIMULATION1_PREFIX, 0,
				SIMULATION_COMMAND_Y_OFFSET, SIMULATION_WIDTH,
				SIMULATION_HEIGHT, 0, 0);
	}

	@Override
	public void atomicBroadcast(boolean newValue) {
		commandProcessor.setConnectedToSimulation(!newValue);
	}

	@Override
	public void ipcMechanism(IPCMechanism newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void experimentInput() {
		Tracer.showInfo(false);
		long aStartTime = System.currentTimeMillis();
		PerformanceExperimentStarted.newCase(this, aStartTime, NUM_EXPERIMENT_COMMANDS);
		for (int i = 0; i < NUM_EXPERIMENT_COMMANDS; i++) {
			commandProcessor.setInputString(EXPERIMENT_COMMAND_1);
			commandProcessor.setInputString(EXPERIMENT_COMMAND_2);
		}
		Tracer.showInfo(true);
		long anEndTime = System.currentTimeMillis();
		PerformanceExperimentEnded.newCase(this, aStartTime, anEndTime, anEndTime - aStartTime, NUM_EXPERIMENT_COMMANDS);
//		System.out.println("Elapsed time:" + (System.currentTimeMillis() - aStartTime));

	}

	@Override
	public void broadcastMetaState(boolean newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void waitForBroadcastConsensus(boolean newValue) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void broadcastIPCMechanism(boolean newValue) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void waitForIPCMechanismConsensus(boolean newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void consensusAlgorithm(ConsensusAlgorithm newValue) {
		// TODO Auto-generated method stub

	}

//	@Override
	public void localProcessingOnly(boolean newValue) {
		nioSimulationOutCoupler.localProcessingOnly(newValue);
		
	}
//	@Override
//	public boolean isLocalProcessingOnly() {
//		return localProcessingOnly;
//	}
//	@Override
//	public void setLocalProcessingOnly(boolean localProcessingOnly) {
//		nioSimulationOutCoupler.localProcessingOnly(localProcessingOnly);
//	}
//	

	@Override
	public void quit(int aCode) {
		try {
			socketChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(aCode);
	}

	@Override
	public void simulationCommand(String aCommand) {
		commandProcessor.setInputString(aCommand);
	}
}
