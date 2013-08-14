package com.lkw.media.codec;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.RTSPPdu;
import com.lkw.media.rtsp.protocol.RTSPPdu.PduType;
import com.lkw.media.rtsp.protocol.RequestLine;

public class RTSPCodec {

	private final static String CRLF = "\r\n";

	public static RTSPPdu RTSPDecode(byte[] bytes) {

		try {
			String tmpstr = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object obj = null;
		RTSPPdu pdu = new RTSPPdu(obj);
		return pdu;
	}

	public static byte[] RTSPEncode(RTSPPdu pdu, PduType type) {
		StringBuilder sb = new StringBuilder();

		switch (type) {
		case REQ:
			RequestLine rl = pdu.getRequest().getRequestLine();
			sb.append(rl.getMethod().toString() + " ")
					.append(rl.getRequestURI() + " ")
					.append(rl.getVersion().toString() + CRLF);
			HeaderStruct hs = pdu.getRequest().getHeader();
			HashMap<String,String> hm = hs.getAllValidField();
			Set<String> hmKeys = hm.keySet();
			Iterator<String> it = hmKeys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				sb.append(key + hm.get(key) + CRLF);
			}
			break;
		case RESP:
			sb.append("s");
			break;
		case ERR:
		default:
			sb.append("s");
			break;
		}
		sb.append(CRLF+CRLF);
		byte[] bytes = null;
		try {
			bytes = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bytes;
	}
}
