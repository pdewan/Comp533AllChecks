package nio.manager.simulation.common;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface ChannelByteBuffer {

	public abstract SocketChannel getSocketChannel();

	public abstract void setSocketChannel(SocketChannel socketChannel);

	public abstract ByteBuffer getByteBuffer();

	public abstract void setByteBuffer(ByteBuffer byteBuffer);

}