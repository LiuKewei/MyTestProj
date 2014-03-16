package com.lkw.media.rtsp.socket;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lkw.media.base.MediaProperties;
import com.lkw.media.rtsp.service.RtspSessionManager;

public class RtspClient implements Runnable {

	private final static Logger logger = Logger.getLogger(RtspClient.class
			.getName());

	private Selector selector = null;

	private int cSeq = 1;

	private RtspSessionManager sm = new RtspSessionManager();

	@Override
	public void run() {
		SocketChannel socketChannel = null;
		try {
			SocketAddress socketAddress = new InetSocketAddress(MediaProperties
					.getInstance().getRtspHost(), Integer.parseInt(MediaProperties
					.getInstance().getRtspPort()));
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(socketAddress);
			listen();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			try {
				System.out.println("socketChannel closed");
				socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private void listen() throws Exception {
		while (true) {
			selector.select();
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey sk = it.next();
				it.remove();
				handleKey(sk);
			}
		}
	}

	public void handleKey(SelectionKey sk) throws Exception {
		if (sk.isConnectable()) {
			SocketChannel client = (SocketChannel) sk.channel();
			System.out.println("client connect");
			if (client.isConnectionPending()) {
				client.finishConnect();
				System.out.println("connect finished");
				sm.handleSessionReq(client, cSeq);
			}
			System.out.println(sk.attachment());
			client.register(selector, SelectionKey.OP_READ);
			System.out.println("register  OP_READ");
		} else if (sk.isReadable()) {
			SocketChannel client = (SocketChannel) sk.channel();
			if (!client.isConnected()) {
				System.out.println("connect aborted");
				sm.getStates().clear();
				return;
			}

			if (!sm.handleSessionResp(client, cSeq++)) {
				client.close();
				sk.cancel();
				sm.getStates().clear();
				System.out.println("connect terminate");
				return;
			}

			client.register(selector, SelectionKey.OP_WRITE);
		} else if (sk.isWritable()) {
			SocketChannel client = (SocketChannel) sk.channel();
			sm.handleSessionReq(client, cSeq);
			client.register(selector, SelectionKey.OP_READ);
		}
	}

}
