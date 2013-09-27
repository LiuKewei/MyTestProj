package com.lkw.media.rtsp.protocol;

import java.util.HashMap;

public class SessionDescriptionProtocol {

	private SessionDescriptionTable sessionDesc = null;
	private TimeDescriptionTable timeDesc = null;
	private MediaDescriptionTable mediaDesc = null;
	
	public SessionDescriptionTable getSessionDesc() {
		return sessionDesc;
	}

	public void setSessionDesc(SessionDescriptionTable sessionDesc) {
		this.sessionDesc = sessionDesc;
	}

	public TimeDescriptionTable getTimeDesc() {
		return timeDesc;
	}

	public void setTimeDesc(TimeDescriptionTable timeDesc) {
		this.timeDesc = timeDesc;
	}

	public MediaDescriptionTable getMediaDesc() {
		return mediaDesc;
	}

	public void setMediaDesc(MediaDescriptionTable mediaDesc) {
		this.mediaDesc = mediaDesc;
	}

	public class SessionDescriptionTable {

		private String protocolVersion = null;
		/**
		 * any
		 */
		private HashMap<String, String> sessionAttr = null;

		/**
		 * ownerUsername sessionId sessionVersion ownerNetworkType
		 * ownerAddressType ownerAddress
		 */
		private HashMap<String, String> origin = null;

		/**
		 * connNetworkType connAddressType connAddress
		 */
		private HashMap<String, String> connInfo = null;

		/**
		 * bandwidthModifier bandwidthValue
		 */
		private HashMap<String, String> bandwidthInfo = null;

		private String sessionName = null;
		private String sessionInformation = null;
		private String uri = null;
		private String emailAddress = null;
		private String phoneAddress = null;

		public String getProtocolVersion() {
			return protocolVersion;
		}

		public void setProtocolVersion(String protocolVersion) {
			this.protocolVersion = protocolVersion;
		}

		public HashMap<String, String> getSessionAttr() {
			return sessionAttr;
		}

		public void setSessionAttr(String attribute) {
			if (null == this.sessionAttr) {
				this.sessionAttr = new HashMap<String, String>();
			}
			String[] singleAttr = attribute.split(":");
			this.sessionAttr.put(singleAttr[0], singleAttr[1]);
		}

		public HashMap<String, String> getOrigin() {
			return origin;
		}

		public void setOrigin(String originValue) {
			if (null == this.origin) {
				this.origin = new HashMap<String, String>();
			}
			String[] values = originValue.split(" ");
			this.origin.put("ownerUsername", values[0]);
			this.origin.put("sessionId", values[1]);
			this.origin.put("sessionVersion", values[2]);
			this.origin.put("ownerNetworkType", values[3]);
			this.origin.put("ownerAddressType", values[4]);
			this.origin.put("ownerAddress", values[5]);
		}

		public HashMap<String, String> getConnInfo() {
			return connInfo;
		}

		public void setConnInfo(HashMap<String, String> connInfo) {
			this.connInfo = connInfo;
		}

		public HashMap<String, String> getBandwidthInfo() {
			return bandwidthInfo;
		}

		public void setBandwidthInfo(HashMap<String, String> bandwidthInfo) {
			this.bandwidthInfo = bandwidthInfo;
		}

		public String getSessionName() {
			return sessionName;
		}

		public void setSessionName(String sessionName) {
			this.sessionName = sessionName;
		}

		public String getSessionInformation() {
			return sessionInformation;
		}

		public void setSessionInformation(String sessionInformation) {
			this.sessionInformation = sessionInformation;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public String getPhoneAddress() {
			return phoneAddress;
		}

		public void setPhoneAddress(String phoneAddress) {
			this.phoneAddress = phoneAddress;
		}

	}

	public class TimeDescriptionTable {
		private String startTime = null;
		private String stopTime = null;

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getStopTime() {
			return stopTime;
		}

		public void setStopTime(String stopTime) {
			this.stopTime = stopTime;
		}

	}

	public class MediaDescriptionTable {

		/**
		 * mediaType mediaPort mediaProtocol mediaFormat
		 */
		private HashMap<String, String> mediaDesc = null;

		/**
		 * connNetworkType connAddressType connAddress
		 */
		private HashMap<String, String> connInfo = null;

		/**
		 * bandwidthModifier bandwidthValue
		 */
		private HashMap<String, String> bandwidthInfo = null;

		/**
		 * any
		 */
		private HashMap<String, String> mediaAttr = null;

		public HashMap<String, String> getMediaDesc() {
			return mediaDesc;
		}

		public void setMediaDesc(String mediaDescValue) {
			if (null == this.mediaDesc) {
				this.mediaDesc = new HashMap<String, String>();
			}
			String[] values = mediaDescValue.split(" ");
			this.mediaDesc.put("mediaType", values[0]);
			this.mediaDesc.put("mediaPort", values[1]);
			this.mediaDesc.put("mediaProtocol", values[2]);
			this.mediaDesc.put("mediaFormat", values[3]);
		}

		public HashMap<String, String> getConnInfo() {
			return connInfo;
		}

		public void setConnInfo(String connInfoValue) {
			if (null == this.connInfo) {
				this.connInfo = new HashMap<String, String>();
			}
			String[] values = connInfoValue.split(" ");
			this.connInfo.put("connNetworkType", values[0]);
			this.connInfo.put("connAddressType", values[1]);
			this.connInfo.put("connAddress", values[2]);
		}

		public HashMap<String, String> getBandwidthInfo() {
			return bandwidthInfo;
		}

		public void setBandwidthInfo(String bandwidthInfoValue) {
			if (null == this.bandwidthInfo) {
				this.bandwidthInfo = new HashMap<String, String>();
			}
			String[] values = bandwidthInfoValue.split(":");
			this.bandwidthInfo.put("bandwidthModifier", values[0]);
			this.bandwidthInfo.put("bandwidthValue", values[1]);
		}

		public HashMap<String, String> getMediaAttr() {
			return mediaAttr;
		}

		public void setMediaAttr(String attribute) {
			if (null == this.mediaAttr) {
				this.mediaAttr = new HashMap<String, String>();
			}
			String[] singleAttr = attribute.split(":");
			this.mediaAttr.put(singleAttr[0], singleAttr[1]);
		}

	}

}
