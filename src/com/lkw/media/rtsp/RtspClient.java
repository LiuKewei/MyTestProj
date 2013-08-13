package com.lkw.media.rtsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lkw.media.rtsp.protocol.RTSPTypes;
import com.lkw.utility.SerializableUtil;

public class RtspClient implements Runnable {

	private final static Logger logger = Logger.getLogger(RtspClient.class
			.getName());

	private final static int serPort = Integer.parseInt(RtspProperties
			.getInstance().getPort());

	private final static String CRLF = "\r\n";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SocketChannel socketChannel = null;
		try {
			// System.out.println("client run  port num" + serPort);
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress("localhost",
					554);
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
			// RTSPTypes myRequestObject = new RTSPTypes(req);
			// logger.log(Level.INFO,
			// myRequestObject.getPdu().getRequest().getHeader().getAccept());
			// sendData(socketChannel, myRequestObject);

			String myRequestObject = "OPTIONS rtsp://localhost/01.ts RTSP/1.0"
					+ CRLF
					+ "CSeq: 1"
					+ CRLF
					+ "User-Agent: VLC media palyer(LIVE555 Streaming Media v2013.03.31)"
					+ CRLF + CRLF + CRLF;
			// + CRLF + "Accept: application/sdp";
			sendData4test(socketChannel, myRequestObject);
			logger.log(Level.INFO, myRequestObject);
			String myResponseObject = receiveData4test(socketChannel);
			// RTSPTypes myResponseObject = receiveData(socketChannel);
			// logger.log(Level.INFO,
			// myResponseObject.getPdu().getResponse().getStatusLine().getReasonPhrase());
			logger.log(Level.INFO, myResponseObject);

			// if
			// (myResponseObject.getPdu().getResponse().getStatusLine().getStatusCode()
			// == 400) {
			//
			// }

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
			String myRequestObject) throws IOException {
		byte[] bytes = myRequestObject.getBytes("UTF-8");
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();
	}

	private String receiveData4test(SocketChannel socketChannel)
			throws IOException {
		String myResponseObject = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			byte[] bytes;
			int count = 0;
			while ((count = socketChannel.read(buffer)) >= 0) {
				logger.log(Level.INFO, count + "");
				buffer.flip();
				bytes = new byte[count];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
			myResponseObject = new String(bytes, "UTF-8");
			socketChannel.socket().shutdownInput();
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
			}
		}
		return myResponseObject;
	}

	private void sendData(SocketChannel socketChannel, RTSPTypes myRequestObject)
			throws IOException {
		byte[] bytes = SerializableUtil.toBytes(myRequestObject);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();
	}

	private RTSPTypes receiveData(SocketChannel socketChannel)
			throws IOException {
		RTSPTypes myResponseObject = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			byte[] bytes;
			int count = 0;
			while ((count = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[count];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
			Object obj = SerializableUtil.toObject(bytes);
			myResponseObject = (RTSPTypes) obj;
			socketChannel.socket().shutdownInput();
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
			}
		}
		return myResponseObject;
	}

}
