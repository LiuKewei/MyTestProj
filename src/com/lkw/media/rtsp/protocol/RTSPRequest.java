package com.lkw.media.rtsp.protocol;


public class RTSPRequest {

	private RequestLine requestLine;
	private HeaderStruct header;
	private byte[] body;
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
