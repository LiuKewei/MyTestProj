package com.lkw.media.rtsp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
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
import com.lkw.media.rtsp.protocol.RTSPPdu.PduType;

public class RtspClientNio implements Runnable {

	private final static Logger logger = Logger.getLogger(RtspClientNio.class
			.getName());

	private final static int serPort = Integer.parseInt(RtspProperties
			.getInstance().getPort());

	private final static String serHost = RtspProperties.getInstance()
			.getHost();

	protected Charset charset = Charset.forName("UTF-8");
	protected CharsetEncoder charsetEncoder = charset.newEncoder();
	protected CharsetDecoder charsetDecoder = charset.newDecoder();

	private Selector selector = null;

	@Override
	public void run() {
		SocketChannel socketChannel = null;
		try {
			System.out.println(serHost);
			System.out.println(serPort);
			SocketAddress socketAddress = new InetSocketAddress(serHost,
					serPort);
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(socketAddress);
			listen();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			try {
				System.out.println("socketChannel closed");
				socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private void listen() throws Exception {
		int i = 0;
		while (true) {
			selector.select();
			System.out.println(i);
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey sk = it.next();
				it.remove();
				handleKey(sk);
			}
			System.out.println("listen");
			i++;
		}
	}

	public void handleKey(SelectionKey sk) throws Exception {
		RTSPPdu pduResp = null;
		HeaderStruct hs = new HeaderStruct();
		hs.setcSeq("1");
		hs.setUserAgent("VLC media palyer(LIVE555 Streaming Media v2013.03.31)");
		RTSPPdu pduReq = new RTSPPdu((Object) new RTSPRequest(new RequestLine(
				Method.OPTIONS, "rtsp://localhost/01.ts", new RTSPVersion("1",
						"0")), hs, null));
		if (sk.isConnectable()) {
			SocketChannel client = (SocketChannel) sk.channel();
			System.out.println("client connect");
			if (client.isConnectionPending()) {
				client.finishConnect();
				System.out.println("connect finished");

				logger.log(Level.INFO,
						"Data Construct : \n" + pduReq.toString());
				sendData(client, pduReq);
			}
			System.out.println(sk.attachment());
			client.register(selector, SelectionKey.OP_READ);
			System.out.println("register  OP_READ");
		} else if (sk.isReadable()) {
			SocketChannel client = (SocketChannel) sk.channel();
			if (!client.isConnected()) {
				System.out.println("connect aborted");
				return;
			}

			System.out.println("connect read");
			pduResp = receiveData(client);
			if (pduResp.getPduType().equals(PduType.RESP)) {
				if (pduResp.getResponse().getHeader().getAllValidField().containsValue("application/sdp")) {
					client.close();
					sk.cancel();
					System.out.println("connect terminate");
					return;
				}
			}
			client.register(selector, SelectionKey.OP_WRITE);
		} else if (sk.isWritable()) {
			SocketChannel client = (SocketChannel) sk.channel();
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

			client.register(selector, SelectionKey.OP_READ);
		}
	}

	private void sendData(SocketChannel socketChannel, RTSPPdu myRequestObject)
			throws IOException {
		byte[] bytes = RTSPCodec.RTSPEncode(myRequestObject);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		socketChannel.write(buffer);
	}

	private RTSPPdu receiveData(SocketChannel socketChannel) throws IOException {
		byte[] bytes = null;
		StringBuilder sb = new StringBuilder();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (socketChannel.read(buffer) > 0) {
			buffer.flip();
			sb.append(charsetDecoder.decode(buffer).toString());
		}
		bytes = sb.toString().getBytes();
		logger.log(Level.INFO, "Received Data : \n\t" + Arrays.toString(bytes));
		RTSPPdu pduResp = RTSPCodec.RTSPDecode(bytes);
		return pduResp;
	}
}
