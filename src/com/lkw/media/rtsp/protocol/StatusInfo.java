package com.lkw.media.rtsp.protocol;

public class StatusInfo {
	private int statusCode; // three-digit
	private String reasonPhrase; // "*<TEXT, excluding CR, LF"
	
	public StatusInfo(int statusCode, String reasonPhrase) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
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
