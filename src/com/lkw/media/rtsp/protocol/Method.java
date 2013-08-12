package com.lkw.media.rtsp.protocol;

import java.io.Serializable;

public enum Method implements Serializable {
	DESCRIBE, ANNOUNCE, GET_PARAMETER, OPTIONS, PAUSE, PLAY, RECORD, REDIRECT, SETUP, SET_PARAMETER, TEARDOWN;
}
