package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public class RTSPResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6778138656107882120L;
	private StatusLine statusLine;
	private HeaderStruct header;
	private byte[] body;

	public RTSPResponse(StatusLine statusLine, HeaderStruct header,
			byte[] body) {
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

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
	
	
}
