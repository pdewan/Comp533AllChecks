package nio.manager.simulation.server;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import inputport.nio.manager.NIOManagerFactory;
import nio.manager.simulation.common.AChannelByteBuffer;
import nio.manager.simulation.common.ChannelByteBuffer;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

public class AnNIOManagerSimulationServerMulticaster implements NIOManagerSimulationServerMulticaster {
//	private List queue = new LinkedList();
	List<SocketChannel> allClients = new ArrayList();
	public static int MAX_QUEUE_SIZE = 100;
	BlockingQueue<ChannelByteBuffer> messageQueue = new ArrayBlockingQueue(MAX_QUEUE_SIZE);
//	protected boolean atomicBroadcast;
	
	
//	public void processData(SimulationNioRelayer server, SocketChannel socket, byte[] data, int count) {
//		TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelayReceive();
//
//		byte[] dataCopy = new byte[count];
//		System.arraycopy(data, 0, dataCopy, 0, count);
//		synchronized(queue) {
////			queue.add(new ServerDataEvent(server, socket, dataCopy));
//			List<SocketChannel> allClients = server.getAllClients();
//			for (SocketChannel client:allClients) {
//				if (client == socket && !RelayerControllerFactory.getOrCreateSingleton().isEchoBack()) {
//					Tracer.info(this, "Not Sending message to:" + client);
//
//					continue;
//				}
////				if (!TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelaySend()) {
////					maybeWaitForRelay();
////				};
//				Tracer.info(this, "Sending message to:" + client);
//				queue.add(new ServerDataEvent(server, client, dataCopy));
//
//			}
//			queue.notify();
//		}
//	}
//	protected void maybeWaitForRelay() {
//		RelayerController aRelayerController =  RelayerControllerFactory.getOrCreateSingleton();
//		if (aRelayerController.isWaitForRelay()) {
//			ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
//		}
//
//	}
//	
//	public void run() {
//		ServerDataEvent dataEvent;
//		
//		while(true) {
//			// Wait for data to become available
//			synchronized(queue) {
//				while(queue.isEmpty()) {
//					try {
//						queue.wait();
//					} catch (InterruptedException e) {
//					}
//				}
//				dataEvent = (ServerDataEvent) queue.remove(0);
//			}
//			
//			// Return to sender
//			dataEvent.server.send(dataEvent.socket, dataEvent.data);
//		}
//	}
	@Override
	public void multicast(SocketChannel aWriter, ByteBuffer aByteBuffer) {
		if (messageQueue.size() >= MAX_QUEUE_SIZE) {
			System.err.println("Message queue full, ignoring message");
			return;
		}
		
		messageQueue.add(new AChannelByteBuffer(aWriter, aByteBuffer));
//		doMulticast(aWriter, aByteBuffer);
//		byte[] aBytes = new byte[aByteBuffer.limit() - aByteBuffer.position()];
//		System.arraycopy(aByteBuffer.array(), aByteBuffer.position(), aBytes, 0, aBytes.length);
//
////		aByteBuffer.get(aBytes, aByteBuffer.position(), aBytes.length);
//				
//
//		for (SocketChannel aSocketChannel: allClients) {
//			if (aSocketChannel == aWriter) {
//				continue;
//			}
//			NIOManagerFactory.getSingleton().write(aSocketChannel, ByteBuffer.wrap(aBytes));
//		}		
	}
	protected void doMulticast(SocketChannel aWriter, ByteBuffer aByteBuffer) {
		byte[] aBytes = new byte[aByteBuffer.limit() - aByteBuffer.position()];
		System.arraycopy(aByteBuffer.array(), aByteBuffer.position(), aBytes, 0, aBytes.length);

//		aByteBuffer.get(aBytes, aByteBuffer.position(), aBytes.length);
		if (AnNIOManagerSimulationServer.getNIOManagerSimulationServer().isAtomicBroadcast() == null) {
			return;
		}

		for (SocketChannel aSocketChannel: allClients) {
			if (aSocketChannel == aWriter && !AnNIOManagerSimulationServer.getNIOManagerSimulationServer().isAtomicBroadcast()) {
				continue;
			}
			NIOManagerFactory.getSingleton().write(aSocketChannel, ByteBuffer.wrap(aBytes));
		}		
	}
	@Override
	public void join(SocketChannel aSocketChannel) {
		allClients.add(aSocketChannel);		
	}
	@Override
	public void run() {
		while (true) {
			try {
			ChannelByteBuffer aMessage = messageQueue.take();
			doMulticast(aMessage.getSocketChannel(), aMessage.getByteBuffer());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
//	@Override
//	public void atomicBroadcast(boolean newValue) {
//		atomicBroadcast = newValue;
//		
//	}
//	@Override
//	public void ipcMechanism(IPCMechanism newValue) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void experimentInput() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void broadcastBroadcastMode(boolean newValue) {
//		
//		
//	}
//	@Override
//	public void waitForBroadcastConsensus(boolean newValue) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void broadcastIPCMechanism(boolean newValue) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void waitForIPCMechanismConsensus(boolean newValue) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void consensusAlgorithm(ConsensusAlgorithm newValue) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void localProcessingOnly(boolean newValue) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void quit(int aCode) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void simulationCommand(String aCommand) {
//		System.out.println("Server cannot play aComand:" + aCommand);
//		
//	}
}

