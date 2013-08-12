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

import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.Method;
import com.lkw.media.rtsp.protocol.RTSPResponse;
import com.lkw.media.rtsp.protocol.RTSPTypes;
import com.lkw.media.rtsp.protocol.RTSPVersion;
import com.lkw.media.rtsp.protocol.StatusLine;
import com.lkw.utility.SerializableUtil;

public class RtspServer implements Runnable {
	
	private final static Logger logger = Logger.getLogger(RtspServer.class.getName());
	
	private final static int serPort = Integer.parseInt(RtspProperties.getInstance().getPort());
	
	private static boolean terminal_flag = false;
	
	@Override
	public void run() {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {
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
			while (selector.select() > 0 && !terminal_flag) {
				// Someone is ready for I/O, get the ready keys
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();

				// Walk through the ready keys collection and process date requests.
				while (it.hasNext() && !terminal_flag) {
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
			
			RTSPTypes request = receiveData(socketChannel);
			
			logger.log(Level.INFO, request.toString());
			RTSPResponse resp = null;
			if (request.getPdu().getRequest().getRequestLine().getMethod() == Method.TEARDOWN) {
				terminal_flag = true;
				resp = new RTSPResponse(new StatusLine(new RTSPVersion(1,1), 400, "error"), new HeaderStruct(), new byte[1024]);
			} else {
				resp = new RTSPResponse(new StatusLine(new RTSPVersion(1,1), 200, "ok"), new HeaderStruct(), new byte[1024]);
			}
			
			RTSPTypes response = new RTSPTypes(resp);
			sendData(socketChannel, response);
		} finally {
			try {  
                socketChannel.close();  
            } catch(Exception e) {
            	e.printStackTrace();
            }  
		}
		
	}
	
	private static RTSPTypes receiveData(SocketChannel socketChannel) throws IOException {
		RTSPTypes request = null;
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
			request = (RTSPTypes) obj;
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
	
	private static void sendData(SocketChannel socketChannel, RTSPTypes response) throws IOException {  
		byte[] bytes = SerializableUtil.toBytes(response);  
        ByteBuffer buffer = ByteBuffer.wrap(bytes);  
        socketChannel.write(buffer); 
	}

	
}
