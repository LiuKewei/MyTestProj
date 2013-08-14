package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public class RTSPVersion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -39069449746065610L;
	private String major;
	private String minor;
	
	
	public RTSPVersion(String major, String minor) {
		super();
		this.major = major;
		this.minor = minor;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	@Override
	public String toString() {
		return "RTSP/" + this.major + "." + this.minor;
	}
	
}
