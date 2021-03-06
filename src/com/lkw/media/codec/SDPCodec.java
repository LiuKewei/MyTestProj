package com.lkw.media.codec;

import com.lkw.media.rtsp.pdu.SessionDescriptionProtocol;
import com.lkw.media.rtsp.pdu.SessionDescriptionProtocol.MediaDescriptionTable;
import com.lkw.media.rtsp.pdu.SessionDescriptionProtocol.SessionDescriptionTable;
import com.lkw.media.rtsp.pdu.SessionDescriptionProtocol.TimeDescriptionTable;

public class SDPCodec extends CodecCommon {

	private static int sdpDecodeIdx = -1;
	private static int sdpFieldsLen = -1;
	private static SessionDescriptionProtocol sdp = new SessionDescriptionProtocol();

	private static SessionDescriptionTable SessionTableDec(String[] fields) {
		SessionDescriptionTable sessionDesc = sdp.new SessionDescriptionTable();
		for (; sdpDecodeIdx < sdpFieldsLen; sdpDecodeIdx++) {
			String[] field = fields[sdpDecodeIdx].split("=", 2);
			if ("v".equals(field[0])) {
				sessionDesc.setProtocolVersion(field[1]);
			} else if ("o".equals(field[0])) {
				sessionDesc.setOrigin(field[1]);
			} else if ("s".equals(field[0])) {
				sessionDesc.setSessionName(field[1]);
			} else if ("i".equals(field[0])) {
				sessionDesc.setSessionInformation(field[1]);
			} else if ("a".equals(field[0])) {
				sessionDesc.setSessionAttr(field[1]);
			} else if ("m".equals(field[0])) {
				break;
			}
		}
		return sessionDesc;
	}

	private static TimeDescriptionTable TimeTableDec(String[] fields) {
		TimeDescriptionTable timeDesc = sdp.new TimeDescriptionTable();
		for (; sdpDecodeIdx < sdpFieldsLen; sdpDecodeIdx++) {
			if (fields[sdpDecodeIdx].equals("")) {
				break;
			}
			String[] field = fields[sdpDecodeIdx].split("=", 2);
			if ("t".equals(field[0])) {
				timeDesc.setActiveTime(field[1]);
			} else if ("r".equals(field[0])) {
				timeDesc.setRepeatInterval(field[1]);
			}
		}
		return timeDesc;
	}

	private static MediaDescriptionTable MediaTableDec(String[] fields) {
		MediaDescriptionTable mediaDesc = sdp.new MediaDescriptionTable();
		for (; sdpDecodeIdx < sdpFieldsLen; sdpDecodeIdx++) {
			if (fields[sdpDecodeIdx].equals("")) {
				break;
			}
			String[] field = fields[sdpDecodeIdx].split("=", 2);
			if ("m".equals(field[0])) {
				mediaDesc.setMediaDesc(field[1]);
			} else if ("c".equals(field[0])) {
				mediaDesc.setConnInfo(field[1]);
			} else if ("b".equals(field[0])) {
				mediaDesc.setBandwidthInfo(field[1]);
			} else if ("a".equals(field[0])) {
				mediaDesc.setMediaAttr(field[1]);
			}
		}
		return mediaDesc;
	}

	public static SessionDescriptionProtocol SDPDecode(String sdpMessage/*
																		 * byte[]
																		 * bytes
																		 */)
			throws Exception {
		// String sdpMessage = utf8charsetDecode(bytes);
		if (sdpMessage.startsWith("v=")) {
			String[] fields = sdpMessage.split(CRLF);
			sdpFieldsLen = fields.length;
			sdpDecodeIdx = 0;
			sdp.setSessionDesc(SessionTableDec(fields));
			sdp.setMediaDesc(MediaTableDec(fields));
			sdpDecodeIdx = 0;
			sdp.setTimeDesc(TimeTableDec(fields));
		}

		return sdp;
	}
}
