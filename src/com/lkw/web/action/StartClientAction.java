package com.lkw.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lkw.media.rtsp.RtspClientNio;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("request")
@Controller("startClientAction")
public class StartClientAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		RtspClientNio rtspCli = new RtspClientNio();
//		RtspClient rtspCli = new RtspClient();
		Thread thread = new Thread(rtspCli);
		thread.start();
		return super.execute();
	}

	
}
