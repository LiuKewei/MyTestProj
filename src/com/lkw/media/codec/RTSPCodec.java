package com.lkw.media.codec;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.Assert;

import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.Method;
import com.lkw.media.rtsp.protocol.RTSPErronousMsg;
import com.lkw.media.rtsp.protocol.RTSPPdu;
import com.lkw.media.rtsp.protocol.RTSPResponse;
import com.lkw.media.rtsp.protocol.RTSPVersion;
import com.lkw.media.rtsp.protocol.RequestLine;
import com.lkw.media.rtsp.protocol.StatusLine;

public class RTSPCodec {

	private final static Logger logger = Logger.getLogger(RTSPCodec.class
			.getName());

	private final static String CRLF = "\r\n";

	protected static Charset charset = Charset.forName("UTF-8");
	protected static CharsetDecoder charsetDecoder = charset.newDecoder();

	public static RTSPPdu RTSPDecode(byte[] bytes) throws Exception {

		RTSPPdu pdu = null;
		byte[] body = null;
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		String tmpstr = charsetDecoder.decode(buffer).toString();
		logger.log(Level.INFO, "Messages : \n" + tmpstr);
		if (tmpstr.startsWith("RTSP/")) {// this is response message

			String[] fields = tmpstr.split(CRLF);
			String[] statusLine = fields[0].split(" ");
			String reasonPhrase = "";
			for (int i = 2; i < statusLine.length; i++) {
				reasonPhrase += statusLine[i] + " ";
			}
			reasonPhrase = reasonPhrase.substring(0, reasonPhrase.length() - 1);
			Assert.isTrue(reasonPhrase != "");
			String[] version = statusLine[0].split("\\.");
			StatusLine slobj = new StatusLine(new RTSPVersion(
					version[0].split("/")[1], version[1]), statusLine[1],
					reasonPhrase);
			HeaderStruct header = new HeaderStruct();
			HashMap<String, String> hm = new HashMap<String, String>();
			int idx = 1;
			for (; idx < fields.length; idx++) {
				if (fields[idx].equals("")) {
					break;
				}
				hm.put(fields[idx].split(": ")[0], fields[idx].split(": ")[1]);
			}
			header.setAllValidField(hm);
			if ("application/sdp".equals(header.getContentType())) {
				int sdpContentLen = 0;
				StringBuilder bodySb = new StringBuilder();
				for (idx += 1; idx < fields.length; idx++) {
					if (fields[idx].equals("")) {
						sdpContentLen += CRLF.length();
						bodySb.append(CRLF);
						break;
					}
					sdpContentLen += fields[idx].length() + CRLF.length();
					bodySb.append(fields[idx]);
					bodySb.append(CRLF);
				}
				Assert.isTrue(Integer.parseInt(header.getContentLength()) == sdpContentLen);
				body = bodySb.toString().getBytes("UTF-8");
			}
			RTSPResponse resp = new RTSPResponse(slobj, header, body);
			pdu = new RTSPPdu((Object) resp);
		} else if (Method.matchAnyEnum(tmpstr)) {// this is request message

		} else {// error message
			RTSPErronousMsg errMsg = new RTSPErronousMsg();
			errMsg.setMsg(tmpstr);
			pdu = new RTSPPdu((Object) errMsg);
		}
		if (null != pdu) {
			logger.log(Level.INFO, "Decode As : \n" + pdu.toString());
		} else {
			logger.log(Level.INFO, "Decode Failure\n");
		}
		return pdu;
	}

	public static byte[] RTSPEncode(RTSPPdu pdu) throws Exception {
		StringBuilder sb = new StringBuilder();
		switch (pdu.getPduType()) {
		case REQ:
			RequestLine requestLine = pdu.getRequest().getRequestLine();
			sb.append(requestLine.getMethod().toString() + " ")
					.append(requestLine.getRequestURI() + " ")
					.append(requestLine.getVersion().toEncodeString() + CRLF);
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
		sb.append(CRLF);
		byte[] bytes = null;
		bytes = sb.toString().getBytes("UTF-8");
		logger.log(Level.INFO, "Messages : \n" + charsetDecoder.decode(ByteBuffer.wrap(bytes)).toString());
		logger.log(Level.INFO,
				"Encode As : \n\t" + RTSPCodec.getHexString(bytes));
		return bytes;
	}

	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1)
					+ " ";
		}
		return result;
	}
}
