package com.lkw.media.rtsp.protocol;

import java.util.ArrayList;

public class RTSPTypes {

	public static final ArrayList<StatusInfo> c_statusInfoList = new ArrayList<StatusInfo>();

	static {
		c_statusInfoList.add(new StatusInfo(100, "Continue"));

	}

	private RTSPPdu pdu;
	
	public RTSPTypes(Object obj) {
		this.pdu = new RTSPPdu(obj);
	}

	public RTSPPdu getPdu() {
		return pdu;
	}

	public void setPdu(RTSPPdu pdu) {
		this.pdu = pdu;
	}

}
