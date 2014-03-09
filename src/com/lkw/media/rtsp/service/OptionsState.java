package com.lkw.media.rtsp.service;

import java.nio.channels.SocketChannel;

import com.lkw.media.rtsp.protocol.HeaderStruct;
import com.lkw.media.rtsp.protocol.Method;
import com.lkw.media.rtsp.protocol.RTSPPdu;
import com.lkw.media.rtsp.protocol.RTSPRequest;
import com.lkw.media.rtsp.protocol.RTSPVersion;
import com.lkw.media.rtsp.protocol.RequestLine;

public class OptionsState extends RTSPState {

	

	@Override
	public void handleReq(SocketChannel client, int cSeq, SessionManager sm)
			throws Exception {

		HeaderStruct hs = new HeaderStruct();
		hs.setcSeq(cSeq + "");
		hs.setUserAgent("LIVE555 Streaming Media v2013.03.31");
		pdu = new RTSPPdu((Object) new RTSPRequest(new RequestLine(
				Method.OPTIONS, url, new RTSPVersion("1", "0")), hs, null));
		pdu.sendData(client, pdu);
		sm.getStates().put(cSeq, this);
	}

	@Override
	public boolean handleResp(SocketChannel client, int cSeq, SessionManager sm)
			throws Exception {
		if(client == null)
		{
			System.out.println("handleResp client == null");
			return false;
		}
		pdu = pdu.receiveData(client);

		sm.getStates().put(cSeq+1, new DescribeState());
		return true;
	}

}
