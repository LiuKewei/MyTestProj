package com.lkw.media.rtsp.pdu;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lkw.media.codec.CodecCommon;
import com.lkw.media.codec.RTSPCodec;

public class RTSPPdu implements Serializable {

	private final static Logger logger = Logger.getLogger(RTSPPdu.class
			.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -742992704128044227L;
	private RTSPRequest request = null;
	private RTSPResponse response = null;
	private RTSPErronousMsg erronousMsg = null;

	public enum PduType {
		REQ, RESP, ERR;
	}

	public RTSPPdu(Object obj) {
		if (obj instanceof RTSPRequest) {
			this.request = (RTSPRequest) obj;
		} else if (obj instanceof RTSPResponse) {
			this.response = (RTSPResponse) obj;
		} else if (obj instanceof RTSPErronousMsg) {
			this.erronousMsg = (RTSPErronousMsg) obj;
		}
	}

	public RTSPRequest getRequest() {
		return request;
	}

	public void setRequest(RTSPRequest request) {
		this.request = request;
	}

	public RTSPResponse getResponse() {
		return response;
	}

	public void setResponse(RTSPResponse response) {
		this.response = response;
	}

	public RTSPErronousMsg getErronous_msg() {
		return erronousMsg;
	}

	public void setErronous_msg(RTSPErronousMsg erronousMsg) {
		this.erronousMsg = erronousMsg;
	}

	@Override
	public String toString() {
		String pduStr = null;
		if (this.request != null) {
			pduStr = this.request.toString();
		} else if (this.response != null) {
			pduStr = this.response.toString();
		} else if (this.erronousMsg != null) {
			pduStr = this.erronousMsg.toString();
		}
		return "RTSP PDU {\n\t" + pduStr + "\n}";
	}

	public PduType getPduType() {
		PduType type = null;
		if (this.getRequest() != null) {
			type = PduType.REQ;
		} else if (this.getResponse() != null) {
			type = PduType.RESP;
		} else if (this.getErronous_msg() != null) {
			type = PduType.ERR;
		}
		return type;
	}
	
	public void sendData(SocketChannel socketChannel, RTSPPdu rtspPdu)
			throws Exception {
		logger.log(Level.INFO, "Data Construc : \n\t" + rtspPdu.toString());
		byte[] bytes = RTSPCodec.RTSPEncode(rtspPdu);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		socketChannel.write(buffer);
	}

	public RTSPPdu receiveData(SocketChannel socketChannel) throws Exception {
		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int count = 0;
		while ((count = socketChannel.read(buffer)) > 0) {
			buffer.flip();
			bytes = new byte[count];
			buffer.get(bytes);
			baos.write(bytes);
			buffer.clear();
		}
		bytes = baos.toByteArray();
		logger.log(Level.INFO,
				"Received Data : \n\t" + CodecCommon.getHexString(bytes));
		return RTSPCodec.RTSPDecode(bytes);
	}

}
