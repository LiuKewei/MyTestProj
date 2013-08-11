package com.lkw.media.rtsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lkw.media.rtsp.protocol.RtspTypes;
import com.lkw.utility.SerializableUtil;

public class RtspServer {
	
	private final static Logger logger = Logger.getLogger(RtspServer.class.getName());
	public RtspServer() {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {
			int serPort = Integer.parseInt(RtspProperties.getInstance().getPort());
			// Selector for incoming time requests
			selector = Selector.open();
			// Create a new server socket and set to non blocking mode
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			// Bind the server socket to the local host and port
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(serPort));
			
			// Register accepts on the server socket with the selector. This
			// step tells the selector that the socket wants to be put on the
			// ready list when accept operations occur, so allowing multiplexed
			// non-blocking I/O to take place.
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			// Here's where everything happens. The select method will
			// return when any operations registered above have occurred, the
			// thread has been interrupted, etc.
			while (selector.select() > 0) {
				// Someone is ready for I/O, get the ready keys
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();

				// Walk through the ready keys collection and process date requests.
				while (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();

					// The key indexes into the selector so you
					// can retrieve the socket that's ready for I/O
					execute((ServerSocketChannel) readyKey.channel());
				}
			}
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, null, e);
		} catch (SocketException e) {
			logger.log(Level.SEVERE, null, e);
		} catch (ClosedChannelException e) {
			logger.log(Level.SEVERE, null, e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, null, e);
		} finally {
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				serverSocketChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void execute(ServerSocketChannel serverSocketChannel) throws IOException {
		SocketChannel socketChannel = null;
		try {
			socketChannel = serverSocketChannel.accept();
			
			RtspTypes request = receiveData(socketChannel);
			
			logger.log(Level.INFO, request.toString());
			
			RtspTypes response = new RtspTypes();
			sendData(socketChannel, response);
		} finally {
			try {  
                socketChannel.close();  
            } catch(Exception e) {
            	e.printStackTrace();
            }  
		}
		
	}
	
	private static RtspTypes receiveData(SocketChannel socketChannel) throws IOException {
		RtspTypes request = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
			byte[] bytes;
			int size = 0;
			while ( (size = socketChannel.read(buffer)) >= 0 ) {
				buffer.flip();
				bytes = new byte[size];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
			Object obj = SerializableUtil.toObject(bytes);
			request = (RtspTypes) obj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return request;
	}
	
	private static void sendData(SocketChannel socketChannel, RtspTypes response) throws IOException {  
		byte[] bytes = SerializableUtil.toBytes(response);  
        ByteBuffer buffer = ByteBuffer.wrap(bytes);  
        socketChannel.write(buffer); 
	}
}
