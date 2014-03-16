package com.lkw.media.base.service;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public abstract class SessionManager {

	protected Map<Integer, State> states = new HashMap<Integer, State>();

	public Map<Integer, State> getStates() {
		return states;
	}

	public abstract void handleSessionReq(SocketChannel client, int cSeq)
			throws Exception;

	public abstract boolean handleSessionResp(SocketChannel client, int cSeq)
			throws Exception;
}
