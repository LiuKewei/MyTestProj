package com.lkw.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lkw.media.rtsp.RtspServer;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("request")
@Controller("startSerAction")
public class StartClientAction extends ActionSupport {
	private String serverId; 
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	@Override
	public String execute() throws Exception {
		RtspServer rtspSer = new RtspServer();
		Thread thread = new Thread(rtspSer);
		thread.start();
		return super.execute();
	}

	
}
