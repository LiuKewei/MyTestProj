package com.lkw.media.rtsp.service;

import java.nio.channels.SocketChannel;

import com.lkw.media.base.service.SessionManager;

public class RtspSessionManager extends SessionManager {

	public void handleSessionReq(SocketChannel client, int cSeq)
			throws Exception {
		RtspState state = (RtspState) states.get(cSeq);
		if (state == null) {
			System.out.println("handleSessionReq state == null");
			state = new OptionsState();
		}
		state.handleReq(client, cSeq, this);

	}

	public boolean handleSessionResp(SocketChannel client, int cSeq)
			throws Exception {
		RtspState state = (RtspState) states.get(cSeq);
		if (state == null) {
			System.out.println("handleSessionResp state == null");
			return false;
		}
		return state.handleResp(client, cSeq, this);

	}

}
