package com.lkw.media.rtsp.protocol;

import java.io.Serializable;
import java.util.ArrayList;

public class RTSPTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1845853972225582515L;
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
