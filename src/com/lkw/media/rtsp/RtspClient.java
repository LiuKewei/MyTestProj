package com.lkw.media.rtsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lkw.media.codec.RTSPCodec;
import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.Method;
import com.lkw.media.rtsp.protocol.RTSPPdu;
import com.lkw.media.rtsp.protocol.RTSPPdu.PduType;
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SocketChannel socketChannel = null;
		try {
			// System.out.println("client run  port num" + serPort);
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress(serHost,
					serPort);
			socketChannel.connect(socketAddress);
			// HeaderStruct header = new HeaderStruct();
			// header.setAccept("accept");
			// byte[] reqPaylaod = new byte[1];
			// reqPaylaod[0] = (byte)1;
			// RTSPRequest req = new RTSPRequest(
			// new RequestLine(Method.TEARDOWN, "rtsp://192.168.0.104/01.ts",
			// new RTSPVersion(1, 1)),
			// header,
			// reqPaylaod);
			// RTSPPdu myRequestObject = new RTSPPdu(req);
			// logger.log(Level.INFO,
			// myRequestObject.getPdu().getRequest().getHeader().getAccept());
			// sendData(socketChannel, myRequestObject);

			// String myRequestObject =
			// "OPTIONS rtsp://localhost/01.ts RTSP/1.0"
			// + CRLF
			// + "CSeq: 1"
			// + CRLF
			// +
			// "User-Agent: VLC media palyer(LIVE555 Streaming Media v2013.03.31)"
			// + CRLF + CRLF + CR VLC mediLF;
			// sendData4test(socketChannel, myRequestObject);
			HeaderStruct hs = new HeaderStruct();
			hs.setcSeq("1");
			hs.setUserAgent("VLC media palyer(LIVE555 Streaming Media v2013.03.31)");
			RTSPPdu pdu = new RTSPPdu((Object) new RTSPRequest(new RequestLine(
					Method.OPTIONS, "rtsp://localhost/01.ts", new RTSPVersion(
							"1", "0")), hs, null));
			byte[] myRequestObject = RTSPCodec.RTSPEncode(pdu, PduType.REQ);
			sendData4test(socketChannel, myRequestObject);
			logger.log(Level.INFO, new String(myRequestObject, "UTF-8"));
			byte[] myResponseObject = receiveData4test(socketChannel);
			RTSPCodec.RTSPDecode(myResponseObject);
			logger.log(Level.INFO, new String(myResponseObject, "UTF-8"));

		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			try {
				socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private void sendData4test(SocketChannel socketChannel,
			byte[] myRequestObject) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(myRequestObject);
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();
	}

	private byte[] receiveData4test(SocketChannel socketChannel)
			throws IOException {
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
			socketChannel.socket().shutdownInput();
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
			}
		}
		return bytes;
	}

}
