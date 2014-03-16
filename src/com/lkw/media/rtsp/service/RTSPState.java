package com.lkw.media.rtsp.service;

import com.lkw.media.base.MediaProperties;
import com.lkw.media.base.service.State;
import com.lkw.media.rtsp.pdu.RTSPPdu;

public abstract class RtspState implements State {
	
	protected final static int rtspPort = Integer.parseInt(MediaProperties
			.getInstance().getRtspPort());

	protected final static String rtspHost = MediaProperties.getInstance()
			.getRtspHost();

	protected final static int clientRtpPort = Integer.parseInt(MediaProperties
			.getInstance().getClientRtpPort());

	protected final static String clientRtcpPort = MediaProperties.getInstance()
			.getClientRtcpPort();

	protected final static String url = "rtsp://" + rtspHost + ":" + rtspPort
			+ "/01.ts";

	protected static RTSPPdu pdu = null;

}
