package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public class RTSPResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6778138656107882120L;
	private StatusLine statusLine;
	private HeaderStruct header;
	private Object body;

	public RTSPResponse(StatusLine statusLine, HeaderStruct header, Object body) {
		this.statusLine = statusLine;
		this.header = header;
		this.body = body;
	}

	public StatusLine getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
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
		sb.append(this.statusLine.toString());
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
