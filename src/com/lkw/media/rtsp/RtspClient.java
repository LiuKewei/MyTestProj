package com.lkw.media.rtsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
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

	@Override
	public void run() {
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress(serHost,
					serPort);
			socketChannel.connect(socketAddress);
			HeaderStruct hs = new HeaderStruct();
			hs.setcSeq("1");
			hs.setUserAgent("VLC media palyer(LIVE555 Streaming Media v2013.03.31)");
			RTSPPdu pduReq = new RTSPPdu((Object) new RTSPRequest(
					new RequestLine(Method.OPTIONS, "rtsp://localhost/01.ts",
							new RTSPVersion("1", "0")), hs, null));
			logger.log(Level.INFO, "Data Construct : \n" + pduReq.toString());
			byte[] myRequestObject = RTSPCodec.RTSPEncode(pduReq);
			logger.log(Level.INFO,
					"Encode As : \n\t" + Arrays.toString(myRequestObject));
			sendData(socketChannel, myRequestObject);

			byte[] myResponseObject = receiveData(socketChannel);
			logger.log(Level.INFO,
					"Received Data : \n\t" + Arrays.toString(myResponseObject));
			RTSPPdu pduResp = RTSPCodec.RTSPDecode(myResponseObject);
			logger.log(Level.INFO, "Decode As : \n" + pduResp.toString());

		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			try {
				socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private void sendData(SocketChannel socketChannel,
			byte[] myRequestObject) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(myRequestObject);
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();
	}

	private byte[] receiveData(SocketChannel socketChannel)
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
