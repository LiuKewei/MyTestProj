package com.lkw.media.codec;

import com.lkw.media.rtsp.protocol.RTSPTypes;

public class RTSPCodec {
	public static RTSPTypes RTSPDecode(byte[] bytes) {
		
		Object obj = null;
		RTSPTypes pdu = new RTSPTypes(obj);
		return pdu;
	}
	
	public static byte[] RTSPEncode(RTSPTypes pdu) {
		
		
		
		return new byte[1024];
	}
}
