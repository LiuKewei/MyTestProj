package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public class RTSPRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7958472370420599659L;
	private RequestLine requestLine;
	private HeaderStruct header;
	private Object body = null;

	public RTSPRequest(RequestLine requestLine, HeaderStruct header, Object body) {
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

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName() + " {\n\t\t");
		sb.append(this.requestLine.toString());
		sb.append("\n\t\t");
		sb.append(this.header.toString());
		sb.append("\n\t\t");
		// sb.append("Body : " + Arrays.toString(this.body));
		sb.append("Body : "
				+ (this.body == null ? "{}" : this.body.toString()));
		sb.append("\n\t}");
		return sb.toString();
	}

}
