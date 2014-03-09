package com.lkw.media.rtsp.service;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

	private Map<Integer, RTSPState> States = new HashMap<Integer, RTSPState>();

	public Map<Integer, RTSPState> getStates() {
		return States;
	}

	public void handleSessionReq(SocketChannel client, int cSeq)
			throws Exception {
		RTSPState state = States.get(cSeq);
		if (state == null) {
			System.out.println("handleSessionReq state == null");
			state = new OptionsState();
		}
		state.handleReq(client, cSeq, this);

	}

	public boolean handleSessionResp(SocketChannel client, int cSeq)
			throws Exception {
		RTSPState state = States.get(cSeq);
		if (state == null) {
			System.out.println("handleSessionResp state == null");
			return false;
		}
		return state.handleResp(client, cSeq, this);

	}

}
