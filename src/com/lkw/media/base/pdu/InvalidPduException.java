package com.lkw.media.base.pdu;

import java.io.IOException;

/**
 * Exception class for PDU encoding/decoding exceptions.
 * @see PDU
 */
public class InvalidPduException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor taking a message as parameter.
	 * @param s Message describing the Exception.
	 */
	public InvalidPduException(final String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
}
