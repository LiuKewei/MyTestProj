package com.lkw.media.rtsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.catalina.tribes.util.Arrays;

import com.lkw.media.codec.RTSPCodec;
import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.Method;
import com.lkw.media.rtsp.protocol.RTSPPdu;
import com.lkw.media.rtsp.protocol.RTSPRequest;
import com.lkw.media.rtsp.protocol.RTSPVersion;
import com.lkw.media.rtsp.protocol.RequestLine;

public class RtspClient implements Runnable {

	private final static Logger logger = Logger.getLogger(RtspClient.class
			.getName());

	private final static int serPort = Integer.parseInt(RtspProperties
			.getInstance().getPort());

	private final static String serHost = RtspProperties.getInstance()
			.getHost();

	private Selector selector = null;
	
	@Override
	public void run() {
		SocketChannel socketChannel = null;
		try {
			System.out.println(serHost);
			SocketAddress socketAddress = new InetSocketAddress(serHost,
					serPort);
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(socketAddress);
			listen(selector);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			try {
				socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private void sendData(SocketChannel socketChannel, RTSPPdu myRequestObject)
			throws IOException {
		byte[] bytes = RTSPCodec.RTSPEncode(myRequestObject);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.flip();
		socketChannel.write(buffer);
	}

	private RTSPPdu receiveData(SocketChannel socketChannel) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			int count = 0;
			while ((count = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[count];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
			}
		}
		logger.log(Level.INFO, "Received Data : \n\t" + Arrays.toString(bytes));
		RTSPPdu pduResp = RTSPCodec.RTSPDecode(bytes);
		return pduResp;
	}

	private void listen(Selector selector) throws Exception {
		while (true) {
			selector.select();
			Set<SelectionKey> selectionkeys = selector.selectedKeys();
			Iterator<SelectionKey> it = selectionkeys.iterator();
			while (it.hasNext()) {
				SelectionKey sk = it.next();
				handleKey(sk);
			}
			selectionkeys.clear();
		}
	}

	public void handleKey(SelectionKey sk) throws Exception {
		SocketChannel client = null;

		HeaderStruct hs = new HeaderStruct();
		hs.setcSeq("1");
		hs.setUserAgent("VLC media palyer(LIVE555 Streaming Media v2013.03.31)");
		RTSPPdu pduReq = new RTSPPdu((Object) new RTSPRequest(new RequestLine(
				Method.OPTIONS, "rtsp://localhost/01.ts", new RTSPVersion("1",
						"0")), hs, null));
		logger.log(Level.INFO, "Data Construct : \n" + pduReq.toString());

		client = (SocketChannel) sk.channel();
		if (sk.isConnectable()) {
			System.out.println("client connect");
			if (client.isConnectionPending()) {
				client.finishConnect();
				System.out.println("connect finished");
				sendData(client, pduReq);
			}
			client.register(selector, SelectionKey.OP_READ);
		} else if (sk.isReadable()) {
			receiveData(client);
			client.register(selector, SelectionKey.OP_WRITE);
		} else if (sk.isWritable()) {
			pduReq.getRequest().getRequestLine().setMethod(Method.DESCRIBE);
			pduReq.getRequest()
					.getHeader()
					.setcSeq(
							Integer.parseInt(pduReq.getRequest().getHeader()
									.getcSeq())
									+ 1 + "");
			pduReq.getRequest().getHeader().setAccept("application/sdp");
			logger.log(Level.INFO, "Data Construct : \n" + pduReq.toString());
			sendData(client, pduReq);
		}
	}

}
