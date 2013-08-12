package com.lkw.media.rtsp.protocol;

public class HeaderLine {

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
