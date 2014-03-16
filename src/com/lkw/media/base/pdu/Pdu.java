package com.lkw.media.base.pdu;

public abstract class Pdu {

	protected static final int BYTE_LENGTH = 8;
	protected static final int MAX_INDEX_IN_BYTE = 7;

	/**
	 * Decodes byte array 'buf[index..index+len-1]' into an integer.
	 * 
	 * @param buf
	 *            concrete PDU
	 */
	public static int decodeBytesToInt(byte[] buf, int index, int len) {
		int iDecoded = 0x00;
		int iShift, iTemp;

		for (int i = 0; i < len; i++) {
			iShift = (len - 1 - i) * 8;
			iTemp = buf[index + i] & 0x000000ff;
			iDecoded = iDecoded | (iTemp << iShift);
		}

		return iDecoded;
	}

	/**
	 * Decodes byte array 'buf[index..index+len-1]' into a long.
	 * 
	 * @param buf
	 *            concrete PDU
	 */
	public static long decodeBytesToLong(byte[] buf, int index, int len) {
		long lDecoded = 0x00, lTemp;
		int iShift;

		for (int i = 0; i < len; i++) {
			iShift = (len - 1 - i) * 8;
			lTemp = buf[index + i] & 0x000000FF;
			lDecoded = lDecoded | (lTemp << iShift);
		}

		return lDecoded;
	}

	public static int decodeBitsFromByte(byte buf, int index, int len) {
		int iDecoded = 0x00;
		int iShift, iTemp;

		iShift = (8 - len);

		iTemp = (buf << index) & 0x000000FF;
		iDecoded = iTemp >>> iShift;

		return iDecoded;
	}

	/**
	 * Encodes an integer into byte array 'buf[index..index+len-1]'.
	 * 
	 * @param value
	 *            the integer value
	 * @param buf
	 *            the concrete PDU
	 * @param index
	 *            first byte of integer encoding in concrete PDU
	 * @param len
	 *            length of integer encoding
	 */
	public static void encodeIntToBytes(int value, byte[] buf, int index,
			int len) {
		for (int i = 0; i < len; i++) {
			int shift = (len - 1 - i) * 8;
			buf[index + i] = (byte) ((value >>> shift) & 0x00ff);
		}
	}

	/**
	 * Encodes a long into byte array 'buf[index..index+len-1]'.
	 * 
	 * @param value
	 *            the long value
	 * @param buf
	 *            the concrete PDU
	 * @param index
	 *            first byte of integer encoding in concrete PDU
	 * @param len
	 *            length of integer encoding
	 */
	public static void encodeLongToBytes(long value, byte[] buf, int index,
			int len) {
		for (int i = 0; i < len; i++) {
			int shift = (len - 1 - i) * 8;
			buf[index + i] = (byte) ((value >>> shift) & 0x00ff);
		}
	}

	/**
	 * Encodes some bits from a value and puts them in a certain location from a
	 * byte.
	 * 
	 * @param original
	 *            the original byte into which the value must be placed
	 * @param value
	 *            the value to put in the byte, if the value is longer, it is
	 *            trimmed
	 * @param index
	 *            position from where to put the bits in the byte
	 * @param len
	 *            number of bits to get from the value and put in the byte
	 * @return the new byte with inserted bits.
	 */
	public static byte encodeBitsToByte(byte original, int value, int index,
			int len) {
		byte bEncoded;
		int iTemp, iEncoded;

		bEncoded = (byte) (value << (8 - len));
		bEncoded = (byte) (bEncoded >>> index);

		iEncoded = original & 0x000000FF;
		iTemp = 0xFFFFFF7F >> index;
		for (int i = 0; i < len; i++) {
			iEncoded = iEncoded & (iTemp >> i);
		}

		return (byte) (iEncoded | bEncoded);
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * Returns a string representation of a concrete PDU.
	 * 
	 * @param buf
	 *            the concrete PDU
	 * @param len
	 *            concrete PDU length
	 * @return concrete PDU representation
	 */
	public static String toString(byte[] buf, int len) {
		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < len; i++) {
			sb.append("|" + i + ":" + ((int) buf[i] & 0xff));
		}
		sb.append("|");
		return sb.toString();
	}

	/**
	 * Encodes this entire PDU: must be overridden by concrete sub-classes.
	 */
	public abstract byte[] encode() throws InvalidPduException;

	/**
	 * Compares the given object with it's own instance. For PDU's, equals ==
	 * true if the type is the same.
	 * 
	 * @param o
	 *            Given object to compare
	 * @return Boolean True if the PDU is of the same type, false otherwise.
	 */
	public abstract boolean equals(Object o);

}
