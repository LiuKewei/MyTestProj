package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public class RTSPVersion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -39069449746065610L;
	private int major;
	private int minor;
	
	
	public RTSPVersion(int major, int minor) {
		super();
		this.major = major;
		this.minor = minor;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}

	
}
