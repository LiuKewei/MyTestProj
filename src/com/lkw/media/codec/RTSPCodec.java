package com.lkw.media.codec;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.util.Assert;

import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.RTSPPdu;
import com.lkw.media.rtsp.protocol.RTSPPdu.PduType;
import com.lkw.media.rtsp.protocol.RTSPResponse;
import com.lkw.media.rtsp.protocol.RTSPVersion;
import com.lkw.media.rtsp.protocol.RequestLine;
import com.lkw.media.rtsp.protocol.StatusLine;

public class RTSPCodec {

	private final static String CRLF = "\r\n";

	public static RTSPPdu RTSPDecode(byte[] bytes) {

		RTSPPdu pdu = null;
		try {
			String tmpstr = new String(bytes, "UTF-8");
			if (tmpstr.startsWith("RTSP/")) {
				String[] fields = tmpstr.split(CRLF);
				String[] statusLine = fields[0].split(" ");
				Assert.isTrue(statusLine.length == 3);
				StatusLine slobj = new StatusLine(new RTSPVersion(statusLine[0].split(".")[0].split("/")[1], statusLine[0].split(".")[1]), statusLine[1], statusLine[2]);
				HeaderStruct header = new HeaderStruct();
				HashMap<String, String> hm = new HashMap<String, String>();
				for (int idx = 1; idx < fields.length; idx++) {
					hm.put(fields[idx].split(": ")[0], fields[idx].split(": ")[1]);
				}
				header.setAllValidField(hm);
				RTSPResponse resp = new RTSPResponse(slobj, header, null);
				pdu = new RTSPPdu((Object) resp);
			} else {
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			HashMap<String, String> hm = hs.getAllValidField();
			Set<String> hmKeys = hm.keySet();
			Iterator<String> it = hmKeys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				sb.append(key + ": " + hm.get(key) + CRLF);
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
		sb.append(CRLF + CRLF);
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
