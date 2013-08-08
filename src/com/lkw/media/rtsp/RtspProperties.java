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
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	private static class RtspPropertiesHolder {
		static Properties p = ReadProperties.getInstance().getProperty("config/rtsp/rtspServer.properties");
		static String port = p.getProperty("listenPort");
		/**
		 * 
		 */
		static final RtspProperties INSTANCE = new RtspProperties(port);
	}
	
	public static RtspProperties getInstance() {
		return RtspPropertiesHolder.INSTANCE;
	}

	private RtspProperties(String port) {
		this.port = port;
	}

	private Object readResolve() {
		return getInstance();
	}

}
