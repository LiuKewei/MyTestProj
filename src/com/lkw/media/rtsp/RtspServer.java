package com.lkw.media.rtsp;

import java.io.IOException;
import java.net.*;

public class RtspServer {
	public RtspServer() {
		try {
			String serAddr = 
			int serPort = 
			Socket socket = new Socket(serAddr, serPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RtspServer(String serAddr, int serPort) {
		try {
			Socket socket = new Socket(serAddr, serPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
