package com.lkw.media.rtsp.protocol;

import java.util.HashMap;

public class SessionDescriptionProtocol {

	private class SessionDescriptionTable {

		int protocolVersion = -1;
		/**
		 * any
		 */
		HashMap<String, String> sessionAttr = null;
		
		/**
		 * ownerUsername
		 * sessionId
		 * sessionVersion
		 * ownerNetworkType
		 * ownerAddressType
		 * ownerAddress
		 */
		HashMap<String, String> origin = null;
		
		String sessionName = null;

		String sessionInformation = null;
		String uri = null;
		String emailAddress = null;
		String phoneAddress = null;
		
		
	}
	
	
	private class TimeDescriptionTable {
		String startTime = null;
		String stopTime = null;
	}
	
	
	private class MediaDescriptionTable {

		/**
		 * mediaType
		 * mediaPort
		 * mediaProtocol
		 * mediaFormat
		 */
		HashMap<String, String> mediaDesc = null;
		
		/**
		 * connNetworkType
		 * connAddressType
		 * connAddress
		 */
		HashMap<String, String> connInfo = null;
		
		/**
		 * bandwidthModifier
		 * bandwidthValue
		 */
		HashMap<String, String> bandwidthInfo = null;
		
		/**
		 * any
		 */
		HashMap<String, String> mediaAttr = null;
		
		
	}
	
	
	
}
