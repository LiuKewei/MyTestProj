package com.lkw.media.rtsp.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HeaderStruct implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4564804801072786244L;
	private String accept = null; // 12.1
	private String acceptEncoding = null; // 12.2
	private String acceptLanguage = null; // 12.3
	private String allow = null; // 12.4
	private String authorization = null; // 12.5
	private String bandwidth = null; // 12.6
	private String blocksize = null; // 12.7
	private String cacheControl = null; // 12.8 g opt SETUP
	private String conference = null; // 12.9. R opt SETUP
	private String connection = null; // 12.10 g
	private String contentBase = null; // 12.11
	private String contentEncoding = null; // 12.12
	private String contentLanguage = null; // 12.13
	private String contentLength = null; // 12.14
	private String contentLocation = null; // 12.15
	private String contentType = null; // 12.16
	private String cSeq = null; // 12.17
	private String date = null; // 12.18
	private String expires = null; // 12.19
	private String fromField = null; // 12.20
	private String host = null; // 12.21
	private String ifMatch = null; // 12.22
	private String ifModifiedSince = null; // 12.23
	private String lastModified = null; // 12.24
	private String location = null; // 12.25
	private String proxyAuth = null; // 12.26
	private String proxyRequire = null; // 12/27
	private String publicField = null; // 12.28
	private String range = null; // 12.29
	private String rdtFeatureLevel = null; // additional fields found in James's log
	private String realChallenge1 = null; // additional fields found in James's log
	private String reconnect = null; // additional fields found in James's log
	private String referer = null; // 12.30
	private String retryAfter = null; // 12.31
	private String require = null; // 12.32
	private String rtcpInterval = null; // additional fields found in James's log
	private String rtpInfo = null; // 12.33
	private String scale = null; // 12.34
	private String speed = null; // 12.35
	private String server = null; // 12.36
	private String session = null; // 12.37
	private String statsMask = null; // additional fields found in James's log
	private String timeStamp = null; // 12.38
	private String transport = null; // 12.39
	private String unsupported = null; // 12.40
	private String userAgent = null; // 12.41
	private String vary = null; // 12.42
	private String via = null; // 12.43
	private String vsrc = null; // additional fields found in James's log
	private String wwwAuth = null; // 12.44
	private String xRealUsestrackid = null;// additional fields found in James's log
	private String xVigBno = null; // additional fields found in James's log
	private String xVigMsisdn = null; // additional
	private String xRetransmit = null; // additional fields found in log of Anders
	private String xDynamicRate = null; // additional fields found in log of Anders
	private String xTransportOptions = null; // additional fields found in log of Anders
	private String xPrebuffer = null; // additional fields found in log of Anders
	private String xAction = null; // Interface Description RTSPx
	private String xEncodingFiles = null; // Interface Description RTSPx
	private String xUdpPipe = null; // Interface Description RTSPx
	private String xMbmsSync = null; // Interface Description RTSPx
	private String xBandwidth = null; // Interface Description RTSPx
	private String xContent = null; // Interface Description RTSPx
	private String xFec = null; // Interface Description RTSPx
	private String xUserPlaneDest = null; // Interface Description RTSPx
	private String xFluteBitrate = null; // Interface Description RTSPx
	private String xTsi = null; // Interface Description RTSPx
	private String xContentFdtSendInterval = null; // Interface Description RTSPx
	private String xReporting = null; // Interface Description RTSPx
	// extensionHeaders:
	private ArrayList<HeaderLine> extensionHeaders = null;
	
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getAcceptEncoding() {
		return acceptEncoding;
	}
	public void setAcceptEncoding(String acceptEncoding) {
		this.acceptEncoding = acceptEncoding;
	}
	public String getAcceptLanguage() {
		return acceptLanguage;
	}
	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}
	public String getAllow() {
		return allow;
	}
	public void setAllow(String allow) {
		this.allow = allow;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public String getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getBlocksize() {
		return blocksize;
	}
	public void setBlocksize(String blocksize) {
		this.blocksize = blocksize;
	}
	public String getCacheControl() {
		return cacheControl;
	}
	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getContentBase() {
		return contentBase;
	}
	public void setContentBase(String contentBase) {
		this.contentBase = contentBase;
	}
	public String getContentEncoding() {
		return contentEncoding;
	}
	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}
	public String getContentLanguage() {
		return contentLanguage;
	}
	public void setContentLanguage(String contentLanguage) {
		this.contentLanguage = contentLanguage;
	}
	public String getContentLength() {
		return contentLength;
	}
	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}
	public String getContentLocation() {
		return contentLocation;
	}
	public void setContentLocation(String contentLocation) {
		this.contentLocation = contentLocation;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getcSeq() {
		return cSeq;
	}
	public void setcSeq(String cSeq) {
		this.cSeq = cSeq;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExpires() {
		return expires;
	}
	public void setExpires(String expires) {
		this.expires = expires;
	}
	public String getFromField() {
		return fromField;
	}
	public void setFromField(String fromField) {
		this.fromField = fromField;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIfMatch() {
		return ifMatch;
	}
	public void setIfMatch(String ifMatch) {
		this.ifMatch = ifMatch;
	}
	public String getIfModifiedSince() {
		return ifModifiedSince;
	}
	public void setIfModifiedSince(String ifModifiedSince) {
		this.ifModifiedSince = ifModifiedSince;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProxyAuth() {
		return proxyAuth;
	}
	public void setProxyAuth(String proxyAuth) {
		this.proxyAuth = proxyAuth;
	}
	public String getProxyRequire() {
		return proxyRequire;
	}
	public void setProxyRequire(String proxyRequire) {
		this.proxyRequire = proxyRequire;
	}
	public String getPublicField() {
		return publicField;
	}
	public void setPublicField(String publicField) {
		this.publicField = publicField;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getRdtFeatureLevel() {
		return rdtFeatureLevel;
	}
	public void setRdtFeatureLevel(String rdtFeatureLevel) {
		this.rdtFeatureLevel = rdtFeatureLevel;
	}
	public String getRealChallenge1() {
		return realChallenge1;
	}
	public void setRealChallenge1(String realChallenge1) {
		this.realChallenge1 = realChallenge1;
	}
	public String getReconnect() {
		return reconnect;
	}
	public void setReconnect(String reconnect) {
		this.reconnect = reconnect;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getRetryAfter() {
		return retryAfter;
	}
	public void setRetryAfter(String retryAfter) {
		this.retryAfter = retryAfter;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getRtcpInterval() {
		return rtcpInterval;
	}
	public void setRtcpInterval(String rtcpInterval) {
		this.rtcpInterval = rtcpInterval;
	}
	public String getRtpInfo() {
		return rtpInfo;
	}
	public void setRtpInfo(String rtpInfo) {
		this.rtpInfo = rtpInfo;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getStatsMask() {
		return statsMask;
	}
	public void setStatsMask(String statsMask) {
		this.statsMask = statsMask;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getUnsupported() {
		return unsupported;
	}
	public void setUnsupported(String unsupported) {
		this.unsupported = unsupported;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getVary() {
		return vary;
	}
	public void setVary(String vary) {
		this.vary = vary;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getVsrc() {
		return vsrc;
	}
	public void setVsrc(String vsrc) {
		this.vsrc = vsrc;
	}
	public String getWwwAuth() {
		return wwwAuth;
	}
	public void setWwwAuth(String wwwAuth) {
		this.wwwAuth = wwwAuth;
	}
	public String getxRealUsestrackid() {
		return xRealUsestrackid;
	}
	public void setxRealUsestrackid(String xRealUsestrackid) {
		this.xRealUsestrackid = xRealUsestrackid;
	}
	public String getxVigBno() {
		return xVigBno;
	}
	public void setxVigBno(String xVigBno) {
		this.xVigBno = xVigBno;
	}
	public String getxVigMsisdn() {
		return xVigMsisdn;
	}
	public void setxVigMsisdn(String xVigMsisdn) {
		this.xVigMsisdn = xVigMsisdn;
	}
	public String getxRetransmit() {
		return xRetransmit;
	}
	public void setxRetransmit(String xRetransmit) {
		this.xRetransmit = xRetransmit;
	}
	public String getxDynamicRate() {
		return xDynamicRate;
	}
	public void setxDynamicRate(String xDynamicRate) {
		this.xDynamicRate = xDynamicRate;
	}
	public String getxTransportOptions() {
		return xTransportOptions;
	}
	public void setxTransportOptions(String xTransportOptions) {
		this.xTransportOptions = xTransportOptions;
	}
	public String getxPrebuffer() {
		return xPrebuffer;
	}
	public void setxPrebuffer(String xPrebuffer) {
		this.xPrebuffer = xPrebuffer;
	}
	public String getxAction() {
		return xAction;
	}
	public void setxAction(String xAction) {
		this.xAction = xAction;
	}
	public String getxEncodingFiles() {
		return xEncodingFiles;
	}
	public void setxEncodingFiles(String xEncodingFiles) {
		this.xEncodingFiles = xEncodingFiles;
	}
	public String getxUdpPipe() {
		return xUdpPipe;
	}
	public void setxUdpPipe(String xUdpPipe) {
		this.xUdpPipe = xUdpPipe;
	}
	public String getxMbmsSync() {
		return xMbmsSync;
	}
	public void setxMbmsSync(String xMbmsSync) {
		this.xMbmsSync = xMbmsSync;
	}
	public String getxBandwidth() {
		return xBandwidth;
	}
	public void setxBandwidth(String xBandwidth) {
		this.xBandwidth = xBandwidth;
	}
	public String getxContent() {
		return xContent;
	}
	public void setxContent(String xContent) {
		this.xContent = xContent;
	}
	public String getxFec() {
		return xFec;
	}
	public void setxFec(String xFec) {
		this.xFec = xFec;
	}
	public String getxUserPlaneDest() {
		return xUserPlaneDest;
	}
	public void setxUserPlaneDest(String xUserPlaneDest) {
		this.xUserPlaneDest = xUserPlaneDest;
	}
	public String getxFluteBitrate() {
		return xFluteBitrate;
	}
	public void setxFluteBitrate(String xFluteBitrate) {
		this.xFluteBitrate = xFluteBitrate;
	}
	public String getxTsi() {
		return xTsi;
	}
	public void setxTsi(String xTsi) {
		this.xTsi = xTsi;
	}
	public String getxContentFdtSendInterval() {
		return xContentFdtSendInterval;
	}
	public void setxContentFdtSendInterval(String xContentFdtSendInterval) {
		this.xContentFdtSendInterval = xContentFdtSendInterval;
	}
	public String getxReporting() {
		return xReporting;
	}
	public void setxReporting(String xReporting) {
		this.xReporting = xReporting;
	}
	public ArrayList<HeaderLine> getExtensionHeaders() {
		return extensionHeaders;
	}
	public void setExtensionHeaders(ArrayList<HeaderLine> extensionHeaders) {
		this.extensionHeaders = extensionHeaders;
	}
	
	public HashMap<String,String> getAllValidField() {
		HashMap<String,String> hm = new HashMap<String,String>();
		if (this.accept != null) {
			hm.put("Accept: ", this.accept);
		}
		if (this.acceptEncoding != null) {
			hm.put("Accept-Encoding: ", this.acceptEncoding);
		}
		if (this.acceptLanguage != null) {
			hm.put("Accept-Language: ", this.acceptLanguage);
		}
		if (this.allow != null) {
			hm.put("Allow: ", this.allow);
		}
		if (this.authorization != null) {
			hm.put("Authorization: ", this.authorization);
		}
		if (this.bandwidth != null) {
			hm.put("Bandwidth: ", this.bandwidth);
		}
		if (this.blocksize != null) {
			hm.put("Blocksize: ", this.blocksize);
		}
		if (this.cacheControl != null) {
			hm.put("Cache-Control: ", this.cacheControl);
		}
		if (this.conference != null) {
			hm.put("Conference: ", this.conference);
		}
		if (this.connection != null) {
			hm.put("Connection: ", this.connection);
		}
		if (this.contentBase != null) {
			hm.put("Content-Base: ", this.contentBase);
		}
		if (this.contentEncoding != null) {
			hm.put("Content-Encoding: ", this.contentEncoding);
		}
		if (this.contentLanguage != null) {
			hm.put("Content-Language: ", this.contentLanguage);
		}
		if (this.contentLength != null) {
			hm.put("Content-Length: ", this.contentLength);
		}
		if (this.contentLocation != null) {
			hm.put("Content-Location: ", this.contentLocation);
		}
		if (this.contentType != null) {
			hm.put("Content-Type: ", this.contentType);
		}
		if (this.cSeq != null) {
			hm.put("CSeq: ", this.cSeq);
		}
		if (this.date != null) {
			hm.put("Date: ", this.date);
		}
		if (this.expires != null) {
			hm.put("Expires: ", this.expires);
		}
		if (this.fromField != null) {
			hm.put("From: ", this.fromField);
		}
		if (this.ifModifiedSince != null) {
			hm.put("If-Modified-Since: ", this.ifModifiedSince);
		}
		if (this.lastModified != null) {
			hm.put("Last-Modified: ", this.lastModified);
		}
		if (this.proxyAuth != null) {
			hm.put("Proxy-Authenticate: ", this.proxyAuth);
		}
		if (this.proxyRequire != null) {
			hm.put("Proxy-Require: ", this.proxyRequire);
		}
		if (this.publicField != null) {
			hm.put("Public: ", this.publicField);
		}
		if (this.range != null) {
			hm.put("Range: ", this.range);
		}
		if (this.referer != null) {
			hm.put("Referer: ", this.referer);
		}
		if (this.require != null) {
			hm.put("Require: ", this.require);
		}
		if (this.retryAfter != null) {
			hm.put("Retry-After: ", this.retryAfter);
		}
		if (this.rtpInfo != null) {
			hm.put("RTP-Info: ", this.rtpInfo);
		}
		if (this.scale != null) {
			hm.put("Scale: ", this.scale);
		}
		if (this.session != null) {
			hm.put("Session: ", this.session);
		}
		if (this.server != null) {
			hm.put("Server: ", this.server);
		}
		if (this.speed != null) {
			hm.put("Speed: ", this.speed);
		}
		if (this.transport != null) {
			hm.put("Transport: ", this.transport);
		}
		if (this.unsupported != null) {
			hm.put("Unsupported: ", this.unsupported);
		}
		if (this.userAgent != null) {
			hm.put("User-Agent: ", this.userAgent);
		}
		if (this.via != null) {
			hm.put("Via: ", this.via);
		}
		if (this.vary != null) {
			hm.put("Vary: ", this.vary);
		}
		if (this.wwwAuth != null) {
			hm.put("WWW-Authenticate: ", this.wwwAuth);
		}
		
		return hm;
	}
	
	
}
