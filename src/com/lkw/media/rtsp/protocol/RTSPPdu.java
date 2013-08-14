package com.lkw.media.rtsp.protocol;

import java.io.Serializable;


public class RTSPPdu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -742992704128044227L;
	private RTSPRequest request = null;
	private RTSPResponse response = null;
	private RTSPErronousMsg erronousMsg = null;
	
	public enum PduType {
		REQ,RESP,ERR;
	}

	public RTSPPdu(Object obj) {
		if (obj instanceof RTSPRequest) {
			this.request = (RTSPRequest) obj;
		} else if (obj instanceof RTSPResponse) {
			this.response = (RTSPResponse) obj;
		} else if (obj instanceof RTSPErronousMsg) {
			this.erronousMsg = (RTSPErronousMsg) obj;
		}
	}

	public RTSPRequest getRequest() {
		return request;
	}

	public void setRequest(RTSPRequest request) {
		this.request = request;
	}

	public RTSPResponse getResponse() {
		return response;
	}

	public void setResponse(RTSPResponse response) {
		this.response = response;
	}

	public RTSPErronousMsg getErronous_msg() {
		return erronousMsg;
	}

	public void setErronous_msg(RTSPErronousMsg erronousMsg) {
		this.erronousMsg = erronousMsg;
	}

	
}
