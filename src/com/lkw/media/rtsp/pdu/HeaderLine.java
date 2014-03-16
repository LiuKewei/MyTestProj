package com.lkw.media.rtsp.pdu;

import java.io.Serializable;

public class HeaderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280354818001342027L;
	
	private String header_name;
	private String header_value;
	
	public HeaderLine(String header_name, String header_value) {
		super();
		this.header_name = header_name;
		this.header_value = header_value;
	}
	public String getHeader_name() {
		return header_name;
	}
	public void setHeader_name(String header_name) {
		this.header_name = header_name;
	}
	public String getHeader_value() {
		return header_value;
	}
	public void setHeader_value(String header_value) {
		this.header_value = header_value;
	}
	
	
}
