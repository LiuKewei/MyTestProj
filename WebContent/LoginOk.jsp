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
<script type="text/javascript">
	function LoadServer() {
		alert("LoadServer!");

		$
				.ajax({
					url : 'video/startSer.action',
					data : {
						serverId : "aaaaaaaaaaaaaa"
					},
					complete : function(xmlHttpRequest) {
						alert("server complete!");
					},
					success : function() {
						alert("server success");
						var div4ie = '<TABLE><TR><TD colspan="2">MRL: <INPUT size="90" id="targetTextField" value="">'
								+ '<INPUT type=submit value="Go" onClick="doGo(document.getElementById(\'targetTextField\').value);">'
								+ '</TD></TR></TABLE>'
								+ '<OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" width="640" height="480" id="vlc" events="True">'
								+ '<param name="MRL" value="rtsp://localhost/01.ts" /><param name="ShowDisplay" value="True" />'
								+ '<param name="AutoLoop" value="False" /><param name="AutoPlay" value="False" />'
								+ '<param name="Volume" value="50" /><param name="toolbar" value="true" />'
								+ '<param name="StartTime" value="0" />'
								+ '<EMBED pluginspage="http://www.videolan.org" type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"  width="640" height="480" toolbar="true" text="Waiting for video" name="vlc"></EMBED>'
								+ '</OBJECT>';

						var div4firefox = '<EMBED type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"'
							  +' width="640" height="480" toolbar="true" text="Waiting for video"'+' autoplay="no" loop="no" hidden="no" name="vlc"'+' target="rtsp://192.168.1.101/01.ts">'
								+ '</EMBED>';

						$("#msgDIVID").show();
						$("#msgDIVID").html(div4firefox);
					},
					error : function() {
						alert("server fuck!");
					}

				});
	}

	function LoadClient() {
		alert("LoadClient!");

		$.ajax({
			url : 'video/startCli.action',
			complete : function(xmlHttpRequest) {
				alert("client complete!");
			},
			success : function() {
				alert("client success");
			},
			error : function() {
				alert("client fuck!");
			}

		});
	}
</script>
<title>Login Ok!</title>
</head>
<body>
	<center>
		<h1>
			!
			<!-- ${requestScope.user.name } -->
		</h1>
		<TABLE>
			<TR>
				<TD colspan="2"><INPUT type=submit value="LoadServer"
					onClick="LoadServer();"></TD>
			</TR>
			<TR>
				<TD colspan="2"><INPUT type=submit value="LoadClient"
					onClick="LoadClient();"></TD>
			</TR>
		</TABLE>
		<form action="video/startSer" method="post">
			ServerId<input type="text" name="serverId" id="serverId" /><br /> <input
				type="submit" value="LoadPlayer" />
		</form>
		<div id="msgDIVID"></div>
	</center>
</body>
</html>