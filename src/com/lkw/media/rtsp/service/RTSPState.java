package com.lkw.media.rtsp.service;

import java.nio.channels.SocketChannel;

import com.lkw.media.rtsp.RtspProperties;
import com.lkw.media.rtsp.protocol.RTSPPdu;

public abstract class RTSPState {
	
	protected final static int serPort = Integer.parseInt(RtspProperties
			.getInstance().getPort());

	protected final static String serHost = RtspProperties.getInstance()
			.getHost();

	protected final static String url = "rtsp://" + serHost + ":" + serPort
			+ "/01.ts";

	protected static RTSPPdu pdu = null;
	

	public abstract void handleReq(SocketChannel client, int cSeq,
			SessionManager sm) throws Exception;

	public abstract boolean handleResp(SocketChannel client, int cSeq,
			SessionManager sm) throws Exception;
}
