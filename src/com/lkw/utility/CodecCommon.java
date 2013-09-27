package com.lkw.utility;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CodecCommon {
	
	public final static String CRLF = "\r\n";
	
	private static Charset utf8charset = Charset.forName("UTF-8");
	private static CharsetDecoder utf8charsetDecoder = utf8charset.newDecoder();
	
	public static String utf8charsetDecode(byte[] bytes) throws Exception {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		return utf8charsetDecoder.decode(buffer).toString();
	}
	
	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1)
					+ " ";
		}
		return result;
	}
}
