package com.lkw.media.rtsp.protocol;

import java.util.ArrayList;

public class RtspTypes {

	public enum strict_crlf_mode {
		ERROR, WARNING, WARNING_ONCE, ACCEPT;
	}
	
	public class HeaderLine{
		String header_name;
		String header_value;
	}
	
	public ArrayList<HeaderLine> HeaderLines;
	
	public class PDU_RTSP {
		RTSPRequest request = null;
		RTSPResponse response = null;
		
		public PDU_RTSP(Object obj) {
			if (obj instanceof RTSPRequest) {
				this.request = (RTSPRequest) obj;
			}
			else if (obj instanceof RTSPResponse) {
				this.response = (RTSPResponse) obj;
			}
		}
	}
	
	public class RTSPRequest {
		RequestLine requestLine;
		HeaderStruct header;
		byte body;
	}
	
	public class RequestLine {
		Method method;
		String requestURI;
		RTSPVersion version;
	}
	
	public enum Method {
		DESCRIBE, ANNOUNCE, GET_PARAMETER, OPTIONS, PAUSE, PLAY, RECORD, REDIRECT, SETUP, SET_PARAMETER, TEARDOWN;
	}
	
	public class RTSPVersion {
		int major;
		int minor;
	}
	
	public class HeaderStruct {
		
	}
	
	public class RTSPResponse {
		StatusLine statusLine;
		HeaderStruct header;
		byte body;
	}
	
	public class StatusLine {
		RTSPVersion version;
		int statusCode;
		String reasonPhrase;
	}
}
