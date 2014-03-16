package com.lkw.media.rtp.pdu;

import com.lkw.media.base.pdu.InvalidPduException;
import com.lkw.media.base.pdu.Pdu;

//RTP Fixed Header Fields
//
//The RTP header has the following format:
//
// 0                   1                   2                   3
// 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|V=2|P|X|  CC   |M|     PT      |       sequence number         |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|                           timestamp                           |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|           synchronization source (SSRC) identifier            |
//+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
//|            contributing source (CSRC) identifiers             |
//|                             ....                              |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

public abstract class RtpPdu extends Pdu {

	// field indices and lengths (in Bits) for the packet header of one byte
	protected static final int VER_INDEX = 0;
	protected static final int VER_LENGTH = 2;
	protected static final int PADDING_INDEX = VER_INDEX + VER_LENGTH;
	protected static final int PADDING_LENGTH = 1;
	protected static final int EXTENSION_INDEX = PADDING_INDEX + PADDING_LENGTH;
	protected static final int EXTENSION_LENGTH = 1;
	protected static final int CSRC_CNT_INDEX = EXTENSION_INDEX
			+ EXTENSION_LENGTH;
	protected static final int CSRC_CNT_LENGTH = 4;

	// field indices and lengths (in Bits) for the packet header of one byte
	protected static final int MARKER_INDEX = 0;
	protected static final int MARKER_LENGTH = 1;
	protected static final int PAYLOAD_TYPE_INDEX = MARKER_INDEX
			+ MARKER_LENGTH;
	protected static final int PAYLOAD_TYPE_LENGTH = 7;

	// field indices and lengths (in Bytes) for the packet header
	protected static final int SEQ_NUM_INDEX = 2;
	protected static final int SEQ_NUM_LENGTH = 2;
	protected static final int TIMESTAMP_INDEX = SEQ_NUM_INDEX + SEQ_NUM_LENGTH;
	protected static final int TIMESTAMP_LENGTH = 4;
	protected static final int SSRC_INDEX = TIMESTAMP_INDEX + TIMESTAMP_LENGTH;
	protected static final int SSRC_LENGTH = 4;
	protected static final int CSRC_INDEX = SSRC_INDEX + SSRC_LENGTH;
	protected static final int CSRC_LENGTH = 4;

	protected static final int MIN_RTP_LENGTH = 12;
	protected static final int MAX_RTP_LENGTH = MIN_RTP_LENGTH + 15;

	protected int version = -1;
	protected int padding = -1;
	protected int extension = -1;
	protected int csrcCnt = -1;
	protected int marker = -1;
	protected int payloadType = -1;
	protected int seqNum = -1;
	protected int timestamp = -1;
	protected int ssrc = -1;
	protected int[] csrc = { -1 };

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public int getCsrcCnt() {
		return csrcCnt;
	}

	public void setCsrcCnt(int csrcCnt) {
		this.csrcCnt = csrcCnt;
	}

	public int getMarker() {
		return marker;
	}

	public void setMarker(int marker) {
		this.marker = marker;
	}

	public int getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(int payloadType) {
		this.payloadType = payloadType;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getSsrc() {
		return ssrc;
	}

	public void setSsrc(int ssrc) {
		this.ssrc = ssrc;
	}

	public int[] getCsrc() {
		return csrc;
	}

	public void setCsrc(int[] csrc) {
		this.csrc = csrc;
	}

	public RtpPdu(int version, int padding, int extension, int csrcCnt,
			int marker, int payloadType, int seqNum, int timestamp, int ssrc,
			int[] csrc) {
		super();
		this.version = version;
		this.padding = padding;
		this.extension = extension;
		this.csrcCnt = csrcCnt;
		this.marker = marker;
		this.payloadType = payloadType;
		this.seqNum = seqNum;
		this.timestamp = timestamp;
		this.ssrc = ssrc;
		this.csrc = csrc;
	}

	public RtpPdu(final byte[] buf) throws InvalidPduException {
		if (buf.length < RtpPdu.MIN_RTP_LENGTH) {
			throw new InvalidPduException(
					"Cannot decode RtpPdu, byte array too short! ("
							+ buf.length + ")");
		}
		this.version = decodeVersion(buf);
		this.padding = decodePadding(buf);
		this.extension = decodeExtension(buf);
		this.csrcCnt = decodeCsrcCnt(buf);
		this.marker = decodeMarker(buf);
		this.payloadType = decodePayloadType(buf);
		this.seqNum = decodeSeqNum(buf);
		this.timestamp = decodeTimestamp(buf);
		this.ssrc = decodeSsrc(buf);
		if (this.csrcCnt != 0) {
			this.csrc = decodeCsrc(buf, this.csrcCnt);
		}

	}

	public static int decodeVersion(byte[] buf) {
		return decodeBitsFromByte(buf[0], VER_INDEX, VER_LENGTH);
	}

	public static int decodePadding(byte[] buf) {
		return decodeBitsFromByte(buf[0], PADDING_INDEX, PADDING_LENGTH);
	}

	public static int decodeExtension(byte[] buf) {
		return decodeBitsFromByte(buf[0], EXTENSION_INDEX, EXTENSION_LENGTH);
	}

	public static int decodeCsrcCnt(byte[] buf) {
		return decodeBitsFromByte(buf[0], CSRC_CNT_INDEX, CSRC_CNT_LENGTH);
	}

	public static int decodeMarker(byte[] buf) {
		return decodeBitsFromByte(buf[1], MARKER_INDEX, MARKER_LENGTH);
	}

	public static int decodePayloadType(byte[] buf) {
		return decodeBitsFromByte(buf[1], PAYLOAD_TYPE_INDEX, PADDING_LENGTH);
	}

	public static int decodeSeqNum(byte[] buf) throws InvalidPduException {
		if (buf.length < (SEQ_NUM_INDEX + SEQ_NUM_LENGTH)) {
			throw new InvalidPduException(
					"Given buffer is too short to decode SEQ_NUM field!"
							+ buf.length + ")");
		}
		return decodeBytesToInt(buf, SEQ_NUM_INDEX, SEQ_NUM_LENGTH);
	}

	public static int decodeTimestamp(byte[] buf) throws InvalidPduException {
		if (buf.length < (TIMESTAMP_INDEX + TIMESTAMP_LENGTH)) {
			throw new InvalidPduException(
					"Given buffer is too short to decode TIMESTAMP field!"
							+ buf.length + ")");
		}
		return decodeBytesToInt(buf, TIMESTAMP_INDEX, TIMESTAMP_LENGTH);
	}

	public static int[] decodeCsrc(byte[] buf, int csrcCnt)
			throws InvalidPduException {
		if (buf.length < (CSRC_INDEX + CSRC_LENGTH * csrcCnt)) {
			throw new InvalidPduException(
					"Given buffer is too short to decode CSRC field!"
							+ buf.length + ")");
		}
		int[] csrcArray = new int[csrcCnt];
		for (int i = 0; i < csrcCnt; i++) {
			csrcArray[i] = decodeBytesToInt(buf, CSRC_INDEX + 4 * i,
					CSRC_LENGTH);
		}
		return csrcArray;
	}

	public static int decodeSsrc(byte[] buf) throws InvalidPduException {
		if (buf.length < (SSRC_INDEX + SSRC_LENGTH)) {
			throw new InvalidPduException(
					"Given buffer is too short to decode SSRC field!"
							+ buf.length + ")");
		}
		return decodeBytesToInt(buf, SSRC_INDEX, SSRC_LENGTH);
	}

	public void encode(byte[] buf) throws InvalidPduException {

	}

	/**
	 * Decodes a datagram packet (concrete PDU) into an abstract PDU.
	 * 
	 * @param buf
	 *            concrete PDU
	 * @return abstract PDU, null if no match with abstract PDU.
	 */
	public static RtpPdu decode(byte[] buf) throws InvalidPduException {
		return null;
	}

}
