package com.lkw.media.rtsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.Method;
import com.lkw.media.rtsp.protocol.RTSPRequest;
import com.lkw.media.rtsp.protocol.RTSPTypes;
import com.lkw.media.rtsp.protocol.RTSPVersion;
import com.lkw.media.rtsp.protocol.RequestLine;
import com.lkw.utility.SerializableUtil;

public class RtspClient implements Runnable  {

	private final static Logger logger = Logger.getLogger(RtspClient.class
			.getName());
	
	private final static int serPort = Integer.parseInt(RtspProperties.getInstance().getPort());

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SocketChannel socketChannel = null;
		try {
			System.out.println("client run  port num" + serPort);
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress("localhost",
					serPort);
			socketChannel.connect(socketAddress);
			HeaderStruct header = new HeaderStruct();
			header.setAccept("accept");
			byte[] reqPaylaod = new byte[1];
			reqPaylaod[0] = (byte)1; 
			RTSPRequest req = new RTSPRequest(
					new RequestLine(Method.TEARDOWN, "rtsp://192.168.0.104/01.ts", new RTSPVersion(1, 1)),
					header,
					reqPaylaod);
			RTSPTypes myRequestObject = new RTSPTypes(req);
			//logger.log(Level.INFO, myRequestObject.getPdu().getRequest().getHeader().getAccept());
			sendData(socketChannel, myRequestObject);

			RTSPTypes myResponseObject = receiveData(socketChannel);
			logger.log(Level.INFO, myResponseObject.getPdu().getResponse().getStatusLine().getReasonPhrase());

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
