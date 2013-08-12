<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/vlc/loading-player.js"></script>


<title>Welcome to MyTestProj!!!</title>
</head>

<body>
	<center>
		<TABLE>
			<TR>
				<TD colspan="2">MRL: <INPUT size="90" id="targetTextField"
					value=""> <INPUT type=submit value="Go"
					onClick="doGo(document.getElementById('targetTextField').value);">
				</TD>
			</TR>
		</TABLE>
	</center>
	<!-- IE 
	<OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921"
		width="640" height="480" id="vlc" events="True">
		<param name="MRL" value="rtsp://192.168.229.1/01.ts" />
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
		</EMBED>
	</OBJECT>-->
	<!-- FIRE FOX-->
	<EMBED type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"
		width="640" height="480" toolbar="true" text="Waiting for video"
		autoplay="no" loop="no" hidden="no" name="vlc"
		target="rtsp://192.168.1.103/01.ts">
	</EMBED>
</body>
</html>