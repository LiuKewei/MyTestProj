package com.lkw.media.rtsp.protocol;

public class StatusLine {
	private RTSPVersion version;
	private int statusCode;
	private String reasonPhrase;

	public StatusLine(RTSPVersion version, int statusCode,
			String reasonPhrase) {
		this.version = version;
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	public RTSPVersion getVersion() {
		return version;
	}

	public void setVersion(RTSPVersion version) {
		this.version = version;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}
	
	
}
