package com.lkw.media.rtsp.protocol;


public class RequestLine {

	private Method method;
	private String requestURI;
	private RTSPVersion version;
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String getRequestURI() {
		return requestURI;
	}
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	public RTSPVersion getVersion() {
		return version;
	}
	public void setVersion(RTSPVersion version) {
		this.version = version;
	}

	
	
	
}
