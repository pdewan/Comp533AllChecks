package inputport.datacomm.duplex.object.explicitreceive;

import java.util.concurrent.BlockingQueue;

public interface BlockingQueueWithBlockedThreadCount<E> extends BlockingQueue<E>{

	public abstract int getNumBlockedThreads();

}