package nio.manager.simulation.common;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import assignments.util.MiscAssignmentUtils;

public class AChannelByteBuffer implements ChannelByteBuffer {
	SocketChannel socketChannel;
	ByteBuffer byteBuffer;
	
	public AChannelByteBuffer(SocketChannel socketChannel,
			ByteBuffer aByteBuffer) {
		super();
		this.socketChannel = socketChannel;
		this.byteBuffer = MiscAssignmentUtils.deepDuplicate(aByteBuffer);
	}
	
	@Override
	public SocketChannel getSocketChannel() {
		return socketChannel;
	}
	
	@Override
	public void setSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public ByteBuffer getByteBuffer() {
		return byteBuffer;
	}
	
	@Override
	public void setByteBuffer(ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}
	
	

}
