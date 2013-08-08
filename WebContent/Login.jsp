<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to MyTestProj!</title>
</head>
<body>

	<!-- IE <OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921"
		width="640" height="480" id="vlc" events="True">
		<param name="MRL" value="rtsp://192.168.159.1/01.ts" />
		<param name="ShowDisplay" value="True" />
		<param name="AutoLoop" value="False" />
		<param name="AutoPlay" value="False" />
		<param name="Volume" value="50" />
		<param name="toolbar" value="true" />
		<param name="StartTime" value="0" />
		<EMBED pluginspage="http://www.videolan.org"
			type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"
			width="640" height="480" toolbar="true" text="Waiting for video"
			name="vlc">
		</EMBED> -->
	<!-- firefox 
	<EMBED type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"
		autoplay="no" loop="no" hidden="no" width="640" height="480"
		toolbar="true" text="Waiting for video"
		target="rtsp://192.168.159.1/01.ts" name="vlc">
	</EMBED>
	</OBJECT>-->
	<center>
		<form action="login" method="post">
			UserID<input type="text" name="idl" id="idl" /><br /> <input
				type="submit" value="login" />
		</form>
	</center>
</body>
</html>