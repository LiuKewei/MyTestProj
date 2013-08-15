package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public class StatusLine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4524435777143554923L;
	private RTSPVersion version;
	private String statusCode;
	private String reasonPhrase;

	public StatusLine(RTSPVersion version, String statusCode,
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName() + " {\n\t\t\t");
		sb.append(this.version.toString());
		sb.append("\n\t\t\t");
		sb.append("StatusCode : " + this.statusCode);
		sb.append("\n\t\t\t");
		sb.append("ReasonPhrase : " + this.reasonPhrase);
		sb.append("\n\t\t}");
		return sb.toString();
	}
	
}
