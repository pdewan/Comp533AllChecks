package inputport.datacomm.duplex.object.explicitreceive;

import java.util.concurrent.LinkedBlockingQueue;


public class ABlockingQueueWithBlockedThreadCount<E> extends LinkedBlockingQueue<E> implements BlockingQueueWithBlockedThreadCount<E> {
	protected int numBlockedThreads;
	@Override
	public synchronized E take() throws InterruptedException {
		numBlockedThreads++;
		E retVal = super.take();
		numBlockedThreads--;
		return retVal;
		
	}	
	/* (non-Javadoc)
	 * @see inputport.datacomm.duplex.object.explicitreceive.BlockingQueueWithBlockedThreadCount#getNumBlockedThreads()
	 */
	@Override
	public int getNumBlockedThreads() {
		return numBlockedThreads;
	}
}
