package com.lkw.media.rtsp.pdu;

import java.io.Serializable;

public enum Method implements Serializable {
	DESCRIBE, ANNOUNCE, GET_PARAMETER, OPTIONS, PAUSE, PLAY, RECORD, REDIRECT, SETUP, SET_PARAMETER, TEARDOWN;

	public static boolean matchAnyEnum(String srcString) {
		if (srcString.startsWith(DESCRIBE.toString())
				|| srcString.startsWith(ANNOUNCE.toString())
				|| srcString.startsWith(GET_PARAMETER.toString())
				|| srcString.startsWith(OPTIONS.toString())
				|| srcString.startsWith(PAUSE.toString())
				|| srcString.startsWith(PLAY.toString())
				|| srcString.startsWith(RECORD.toString())
				|| srcString.startsWith(REDIRECT.toString())
				|| srcString.startsWith(SETUP.toString())
				|| srcString.startsWith(SET_PARAMETER.toString())
				|| srcString.startsWith(TEARDOWN.toString())) {
			return true;
		}

		return false;
	}
}
