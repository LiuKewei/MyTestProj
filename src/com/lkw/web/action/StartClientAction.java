package com.lkw.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lkw.media.rtsp.RtspClient;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("request")
@Controller("startClientAction")
public class StartClientAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		RtspClient rtspCli = new RtspClient();
//		RtspClient rtspCli = new RtspClient();
		Thread thread = new Thread(rtspCli);
		thread.start();
		return super.execute();
	}

	
}
