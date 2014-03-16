package com.lkw.media.base;

import java.io.Serializable;
import java.util.Properties;

import com.lkw.utility.ReadProperties;

public class MediaProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1487672420151678307L;

	private String rtspPort;
	private String rtspHost;
	private String clientRtpPort;
	private String clientRtcpPort;

	public String getRtspPort() {
		return rtspPort;
	}

	public void setRtspPort(String rtspPort) {
		this.rtspPort = rtspPort;
	}

	public String getRtspHost() {
		return rtspHost;
	}

	public void setRtspHost(String rtspHost) {
		this.rtspHost = rtspHost;
	}

	public String getClientRtpPort() {
		return clientRtpPort;
	}

	public void setClientRtpPort(String clientRtpPort) {
		this.clientRtpPort = clientRtpPort;
	}

	public String getClientRtcpPort() {
		return clientRtcpPort;
	}

	public void setClientRtcpPort(String clientRtcpPort) {
		this.clientRtcpPort = clientRtcpPort;
	}

	private static class RtspPropertiesHolder {
		static Properties p = ReadProperties.getInstance().getProperty(
				"config/rtsp/rtspServer.properties");
		/**
		 * 
		 */
		static final MediaProperties INSTANCE = new MediaProperties(
				p.getProperty("rtspPort"), p.getProperty("rtspHost"),
				p.getProperty("clientRtpPort"), p.getProperty("clientRtcpPort"));
	}

	public static MediaProperties getInstance() {
		return RtspPropertiesHolder.INSTANCE;
	}

	public MediaProperties(String rtspPort, String rtspHost,
			String clientRtpPort, String clientRtcpPort) {
		this.rtspPort = rtspPort;
		this.rtspHost = rtspHost;
		this.clientRtpPort = clientRtpPort;
		this.clientRtcpPort = clientRtcpPort;
	}

	private Object readResolve() {
		return getInstance();
	}

}
