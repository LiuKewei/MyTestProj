package com.lkw.media.rtsp.protocol;

import java.io.Serializable;


public class RTSPRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7958472370420599659L;
	private RequestLine requestLine;
	private HeaderStruct header;
	private byte[] body;
	
	public RTSPRequest(RequestLine requestLine, HeaderStruct header, byte[] body) {
		super();
		this.requestLine = requestLine;
		this.header = header;
		this.body = body;
	}

	public RequestLine getRequestLine() {
		return requestLine;
	}
	public void setRequestLine(RequestLine requestLine) {
		this.requestLine = requestLine;
	}
	public HeaderStruct getHeader() {
		return header;
	}
	public void setHeader(HeaderStruct header) {
		this.header = header;
	}
	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}

}
