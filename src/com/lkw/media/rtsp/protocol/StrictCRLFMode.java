package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public enum StrictCRLFMode implements Serializable {
	ERROR, WARNING, WARNING_ONCE, ACCEPT;
}
