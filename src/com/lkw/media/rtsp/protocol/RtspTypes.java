package com.lkw.media.rtsp.protocol;

import java.util.ArrayList;

public class RtspTypes {

	public enum strict_crlf_mode {
		ERROR, WARNING, WARNING_ONCE, ACCEPT;
	}

	public class HeaderLine {
		String header_name;
		String header_value;

		public HeaderLine(String header_name, String header_value) {
			super();
			this.header_name = header_name;
			this.header_value = header_value;
		}
	}

	public class PDU_RTSP {
		RTSPRequest request = null;
		RTSPResponse response = null;
		RTSP_erronous_msg erronous_msg = null;

		public PDU_RTSP(Object obj) {
			if (obj instanceof RTSPRequest) {
				this.request = (RTSPRequest) obj;
			} else if (obj instanceof RTSPResponse) {
				this.response = (RTSPResponse) obj;
			} else if (obj instanceof RTSP_erronous_msg) {
				this.erronous_msg = (RTSP_erronous_msg) obj;
			}
		}
	}

	public class RTSPRequest {
		RequestLine requestLine;
		HeaderStruct header;
		byte[] body;

		public RTSPRequest(RequestLine requestLine, HeaderStruct header,
				byte[] body) {
			super();
			this.requestLine = requestLine;
			this.header = header;
			this.body = body;
		}
	}

	public class RequestLine {
		Method method;
		String requestURI;
		RTSPVersion version;

		public RequestLine(Method method, String requestURI, RTSPVersion version) {
			super();
			this.method = method;
			this.requestURI = requestURI;
			this.version = version;
		}
	}

	public enum Method {
		DESCRIBE, ANNOUNCE, GET_PARAMETER, OPTIONS, PAUSE, PLAY, RECORD, REDIRECT, SETUP, SET_PARAMETER, TEARDOWN;
	}

	public class RTSPVersion {
		int major;
		int minor;

		public RTSPVersion(int major, int minor) {
			super();
			this.major = major;
			this.minor = minor;
		}
	}

	public class HeaderStruct {
		String accept; // 12.1
		String acceptEncoding; // 12.2
		String acceptLanguage; // 12.3
		String allow; // 12.4
		String authorization; // 12.5
		String bandwidth; // 12.6
		String blocksize; // 12.7
		String cacheControl; // 12.8 g opt SETUP
		String conference; // 12.9. R opt SETUP
		String connection; // 12.10 g
		String contentBase; // 12.11
		String contentEncoding; // 12.12
		String contentLanguage; // 12.13
		String contentLength; // 12.14
		String contentLocation; // 12.15
		String contentType; // 12.16
		String cSeq; // 12.17
		String date; // 12.18
		String expires; // 12.19
		String fromField; // 12.20
		String host; // 12.21
		String ifMatch; // 12.22
		String ifModifiedSince; // 12.23
		String lastModified; // 12.24
		String location; // 12.25
		String proxyAuth; // 12.26
		String proxyRequire; // 12/27
		String publicField; // 12.28
		String range; // 12.29
		String rdtFeatureLevel; // additional fields found in James's log
		String realChallenge1; // additional fields found in James's log
		String reconnect; // additional fields found in James's log
		String referer; // 12.30
		String retryAfter; // 12.31
		String require; // 12.32
		String rtcpInterval; // additional fields found in James's log
		String rtpInfo; // 12.33
		String scale; // 12.34
		String speed; // 12.35
		String server; // 12.36
		String session; // 12.37
		String statsMask; // additional fields found in James's log
		String timeStamp; // 12.38
		String transport; // 12.39
		String unsupported; // 12.40
		String userAgent; // 12.41
		String vary; // 12.42
		String via; // 12.43
		String vsrc; // additional fields found in James's log
		String wwwAuth; // 12.44
		String xRealUsestrackid;// additional fields found in James's log
		String xVigBno; // additional fields found in James's log
		String xVigMsisdn; // additional
		String xRetransmit; // additional fields found in log of Anders
		String xDynamicRate; // additional fields found in log of Anders
		String xTransportOptions; // additional fields found in log of Anders
		String xPrebuffer; // additional fields found in log of Anders
		String xAction; // Interface Description RTSPx
		String xEncodingFiles; // Interface Description RTSPx
		String xUdpPipe; // Interface Description RTSPx
		String xMbmsSync; // Interface Description RTSPx
		String xBandwidth; // Interface Description RTSPx
		String xContent; // Interface Description RTSPx
		String xFec; // Interface Description RTSPx
		String xUserPlaneDest; // Interface Description RTSPx
		String xFluteBitrate; // Interface Description RTSPx
		String xTsi; // Interface Description RTSPx
		String xContentFdtSendInterval; // Interface Description RTSPx
		String xReporting; // Interface Description RTSPx
		// extensionHeaders:
		ArrayList<HeaderLine> extensionHeaders;

		public HeaderStruct(String accept, String acceptEncoding,
				String acceptLanguage, String allow, String authorization,
				String bandwidth, String blocksize, String cacheControl,
				String conference, String connection, String contentBase,
				String contentEncoding, String contentLanguage,
				String contentLength, String contentLocation,
				String contentType, String cSeq, String date, String expires,
				String fromField, String host, String ifMatch,
				String ifModifiedSince, String lastModified, String location,
				String proxyAuth, String proxyRequire, String publicField,
				String range, String rdtFeatureLevel, String realChallenge1,
				String reconnect, String referer, String retryAfter,
				String require, String rtcpInterval, String rtpInfo,
				String scale, String speed, String server, String session,
				String statsMask, String timeStamp, String transport,
				String unsupported, String userAgent, String vary, String via,
				String vsrc, String wwwAuth, String xRealUsestrackid,
				String xVigBno, String xVigMsisdn, String xRetransmit,
				String xDynamicRate, String xTransportOptions,
				String xPrebuffer, String xAction, String xEncodingFiles,
				String xUdpPipe, String xMbmsSync, String xBandwidth,
				String xContent, String xFec, String xUserPlaneDest,
				String xFluteBitrate, String xTsi,
				String xContentFdtSendInterval, String xReporting,
				ArrayList<HeaderLine> extensionHeaders) {
			super();
			this.accept = accept;
			this.acceptEncoding = acceptEncoding;
			this.acceptLanguage = acceptLanguage;
			this.allow = allow;
			this.authorization = authorization;
			this.bandwidth = bandwidth;
			this.blocksize = blocksize;
			this.cacheControl = cacheControl;
			this.conference = conference;
			this.connection = connection;
			this.contentBase = contentBase;
			this.contentEncoding = contentEncoding;
			this.contentLanguage = contentLanguage;
			this.contentLength = contentLength;
			this.contentLocation = contentLocation;
			this.contentType = contentType;
			this.cSeq = cSeq;
			this.date = date;
			this.expires = expires;
			this.fromField = fromField;
			this.host = host;
			this.ifMatch = ifMatch;
			this.ifModifiedSince = ifModifiedSince;
			this.lastModified = lastModified;
			this.location = location;
			this.proxyAuth = proxyAuth;
			this.proxyRequire = proxyRequire;
			this.publicField = publicField;
			this.range = range;
			this.rdtFeatureLevel = rdtFeatureLevel;
			this.realChallenge1 = realChallenge1;
			this.reconnect = reconnect;
			this.referer = referer;
			this.retryAfter = retryAfter;
			this.require = require;
			this.rtcpInterval = rtcpInterval;
			this.rtpInfo = rtpInfo;
			this.scale = scale;
			this.speed = speed;
			this.server = server;
			this.session = session;
			this.statsMask = statsMask;
			this.timeStamp = timeStamp;
			this.transport = transport;
			this.unsupported = unsupported;
			this.userAgent = userAgent;
			this.vary = vary;
			this.via = via;
			this.vsrc = vsrc;
			this.wwwAuth = wwwAuth;
			this.xRealUsestrackid = xRealUsestrackid;
			this.xVigBno = xVigBno;
			this.xVigMsisdn = xVigMsisdn;
			this.xRetransmit = xRetransmit;
			this.xDynamicRate = xDynamicRate;
			this.xTransportOptions = xTransportOptions;
			this.xPrebuffer = xPrebuffer;
			this.xAction = xAction;
			this.xEncodingFiles = xEncodingFiles;
			this.xUdpPipe = xUdpPipe;
			this.xMbmsSync = xMbmsSync;
			this.xBandwidth = xBandwidth;
			this.xContent = xContent;
			this.xFec = xFec;
			this.xUserPlaneDest = xUserPlaneDest;
			this.xFluteBitrate = xFluteBitrate;
			this.xTsi = xTsi;
			this.xContentFdtSendInterval = xContentFdtSendInterval;
			this.xReporting = xReporting;
			this.extensionHeaders = extensionHeaders;
		}
	}

	public class RTSPResponse {
		StatusLine statusLine;
		HeaderStruct header;
		byte[] body;

		public RTSPResponse(StatusLine statusLine, HeaderStruct header,
				byte[] body) {
			super();
			this.statusLine = statusLine;
			this.header = header;
			this.body = body;
		}
	}

	public class StatusLine {
		RTSPVersion version;
		int statusCode;
		String reasonPhrase;

		public StatusLine(RTSPVersion version, int statusCode,
				String reasonPhrase) {
			super();
			this.version = version;
			this.statusCode = statusCode;
			this.reasonPhrase = reasonPhrase;
		}
	}

	public static class StatusInfo {
		int statusCode; // three-digit
		String reasonPhrase; // "*<TEXT, excluding CR, LF"

		public StatusInfo(int statusCode, String reasonPhrase) {
			super();
			this.statusCode = statusCode;
			this.reasonPhrase = reasonPhrase;
		}
	}

	public static final ArrayList<StatusInfo> c_statusInfoList = new ArrayList<StatusInfo>();

	static {
		c_statusInfoList.add(new StatusInfo(100, "Continue"));

	}

	public class RTSP_erronous_msg {
		String msg;

		public RTSP_erronous_msg(String msg) {
			super();
			this.msg = msg;
		}
	}

}
