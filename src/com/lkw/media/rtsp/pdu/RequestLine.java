package com.lkw.media.rtsp.pdu;

import java.io.Serializable;


public class RequestLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -966719392223137271L;
	private Method method;
	private String requestURI;
	private RTSPVersion version;
	
	public RequestLine(Method method, String requestURI, RTSPVersion version) {
		super();
		this.method = method;
		this.requestURI = requestURI;
		this.version = version;
	}
	
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName() + " {\n\t\t\t");
		sb.append("Method : " + this.method.toString());
		sb.append("\n\t\t\t");
		sb.append("RequestURI : " + this.requestURI);
		sb.append("\n\t\t\t");
		sb.append(this.version.toString());
		sb.append("\n\t\t}");
		return sb.toString();
	}

}
