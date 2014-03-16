package com.lkw.media.rtsp.pdu;

import java.io.Serializable;

public enum StrictCRLFMode implements Serializable {
	ERROR, WARNING, WARNING_ONCE, ACCEPT;
}
