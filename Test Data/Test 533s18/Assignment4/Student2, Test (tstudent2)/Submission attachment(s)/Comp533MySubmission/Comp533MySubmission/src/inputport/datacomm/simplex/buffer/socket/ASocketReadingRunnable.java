package inputport.datacomm.simplex.buffer.socket;

import inputport.nio.manager.AScatterGatherSelectionManager;
import inputport.nio.manager.commands.classes.AReadCommand;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

import util.misc.AStoppableRunnable;
import util.trace.Tracer;



public class ASocketReadingRunnable extends AStoppableRunnable {
	SocketReadListener readListener;
	SocketCloseListener closeListener;	
	Socket socket;
	InputStream socketInputStream;
	byte[] readMessagerBytes = new byte[AReadCommand.READ_BUFFER_SIZE];
	byte[] readHeaderBytes = new byte[AScatterGatherSelectionManager.BYTES_IN_INT];
	
	public ASocketReadingRunnable(Socket aSocket, SocketReadListener aReadListener, SocketCloseListener aCloseListener) {
		socket = aSocket;
		try {
			socketInputStream = socket.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		readListener = aReadListener;
		closeListener = aCloseListener;
		
	}
	
	public static int read(InputStream inputStream, byte[] buf, int offset, int length) throws IOException, InterruptedException  {
		int nextLength = length;
		int nextOffset = 0;
		
		while (true) {
			try {
		
				int retVal = inputStream.read(buf, nextOffset, nextLength);
				if (retVal == -1)
					 throw new EOFException();
				else if (retVal == nextLength) {
					return length;
				} else {
					nextOffset += retVal;
					nextLength -= retVal;
				}	
			} catch (SocketException e) {
//				e.printStackTrace();
				throw new IOException(e.getMessage());
				
			}
		
		}
	}
	
//	public ASocketReadingRunnable(Socket aSocket) {
//		socket = aSocket;
//		try {
//			socketInputStream = socket.getInputStream();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public synchronized void registerReadListener(
//			SocketReadListener aReadListener) {
//		readListener = aReadListener;		
//	}
//	@Override
//	public synchronized void registerCloseListener(
//			SocketCloseListener aCloseListener) {
//		closeListener = aCloseListener;	
//	}
	@Override
	public void run() {
		while (execute) {
			try {
				Tracer.info(this, "About to read from socket:" + socket);
				int retVal = read(socketInputStream, readHeaderBytes, 0, readHeaderBytes.length);
				Tracer.info(this, "read header bytes " + readHeaderBytes + " from " + socket);
				ByteBuffer readHeader = ByteBuffer.wrap(readHeaderBytes);
				int nextMessageLength = readHeader.getInt();
				retVal = read(socketInputStream, readMessagerBytes, 0, nextMessageLength);	
				Tracer.info(this, "read message bytes " + readMessagerBytes + " from " + socket);

				readListener.socketRead(socket, ByteBuffer.wrap(readMessagerBytes, 0, nextMessageLength));		
				
			} catch (InterruptedException ie) {
				closeListener.socketClosed(socket, ie);

				break;
			}
			catch (Exception e) {
				e.printStackTrace();
				closeListener.socketClosed(socket, e);
				break;
			}
			
		}
		
	}

}
