package com.lkw.media.rtsp.service;

public interface RtspService {

	public void doOPTIONS();
	public void doDESCRIBE();
	public void doSETUP();
	public void doPLAY();
	public void handleMsg();
}
