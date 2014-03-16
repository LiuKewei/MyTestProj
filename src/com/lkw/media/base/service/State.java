package com.lkw.media.base.service;

import java.nio.channels.SocketChannel;

public interface State {
	public void handleReq(SocketChannel client, int cSeq,
			SessionManager sm) throws Exception;

	public boolean handleResp(SocketChannel client, int cSeq,
			SessionManager sm) throws Exception;
}
