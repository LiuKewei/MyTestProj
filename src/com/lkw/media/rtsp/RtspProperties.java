package com.lkw.media.rtsp;

import java.io.Serializable;
import java.util.Properties;

import com.lkw.utility.ReadProperties;

public class RtspProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1487672420151678307L;
	
	private String port;
	private String host;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	private static class RtspPropertiesHolder {
		static Properties p = ReadProperties.getInstance().getProperty("config/rtsp/rtspServer.properties");
		static String port = p.getProperty("listenPort");
		static String host = p.getProperty("listenHost");
		/**
		 * 
		 */
		static final RtspProperties INSTANCE = new RtspProperties(port,host);
	}
	
	public static RtspProperties getInstance() {
		return RtspPropertiesHolder.INSTANCE;
	}

	private RtspProperties(String port, String host) {
		this.port = port;
		this.host = host;
	}

	private Object readResolve() {
		return getInstance();
	}

}
