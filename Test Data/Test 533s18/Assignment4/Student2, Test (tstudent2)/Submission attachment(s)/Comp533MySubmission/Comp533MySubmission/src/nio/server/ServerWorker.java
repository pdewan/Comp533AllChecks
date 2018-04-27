package nio.server;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import common.controller.TrickOrTreatClientControllerFactory;
import util.misc.ClearanceManagerFactory;
import util.remote.RelayerController;
import util.remote.RelayerControllerFactory;
import util.trace.Tracer;

public class ServerWorker implements Runnable {
	private List queue = new LinkedList();
	
	public void processData(SimulationNioRelayer server, SocketChannel socket, byte[] data, int count) {
		TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelayReceive();

		byte[] dataCopy = new byte[count];
		System.arraycopy(data, 0, dataCopy, 0, count);
		synchronized(queue) {
//			queue.add(new ServerDataEvent(server, socket, dataCopy));
			List<SocketChannel> allClients = server.getAllClients();
			for (SocketChannel client:allClients) {
				if (client == socket && !RelayerControllerFactory.getOrCreateSingleton().isEchoBack()) {
					Tracer.info(this, "Not Sending message to:" + client);

					continue;
				}
//				if (!TrickOrTreatClientControllerFactory.getOrCreateSingleton().getSendReceiveSettings().maybeDelaySend()) {
//					maybeWaitForRelay();
//				};
				Tracer.info(this, "Sending message to:" + client);
				queue.add(new ServerDataEvent(server, client, dataCopy));

			}
			queue.notify();
		}
	}
	protected void maybeWaitForRelay() {
		RelayerController aRelayerController =  RelayerControllerFactory.getOrCreateSingleton();
		if (aRelayerController.isWaitForRelay()) {
			ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
		}

	}
	
	public void run() {
		ServerDataEvent dataEvent;
		
		while(true) {
			// Wait for data to become available
			synchronized(queue) {
				while(queue.isEmpty()) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
					}
				}
				dataEvent = (ServerDataEvent) queue.remove(0);
			}
			
			// Return to sender
			dataEvent.server.send(dataEvent.socket, dataEvent.data);
		}
	}
}

