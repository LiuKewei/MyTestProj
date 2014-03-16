package com.lkw.media.rtsp.pdu;

import java.io.Serializable;

public class RTSPErronousMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7464784809263324127L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
