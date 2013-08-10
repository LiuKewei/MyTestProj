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
<script type="text/javascript">
    function LoadPlayer() {
        alert("LoadPlayer!");
        
        $.ajax({
            url : 'video/startSer.action',
            data : {
            	serverId : "aaaaaaaaaaaaaa"
            },
            complete : function(xmlHttpRequest) {
                alert("complete!");
            },
            success : function() {
                alert("success");
                var divtemp = '<OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" width="640" height="480" id="vlc" events="True"><param name="MRL" value="" /><param name="ShowDisplay" value="True" /><param name="AutoLoop" value="False" /><param name="AutoPlay" value="False" /><param name="Volume" value="50" /><param name="toolbar" value="true" /><param name="StartTime" value="0" /><EMBED pluginspage="http://www.videolan.org" type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"  width="640" height="480" toolbar="true" text="Waiting for video" name="vlc"></EMBED></OBJECT>';
                
                $("#msgDIVID").show();
                $("#msgDIVID").html(divtemp);
            },
            error : function() {
                alert("fuck!");
            }

        });
    }
</script>
<title>Login Ok!</title>
</head>
<body>
	<center>
		<h1>Hello world! <!-- ${requestScope.user.name } --></h1>
		<TABLE>
            <TR>
                <TD colspan="2"><INPUT type=submit value="LoadPlayer"
                    onClick="LoadPlayer();">
                </TD>
            </TR>
        </TABLE>
        <div id="msgDIVID">
        
        </div>
	</center>
</body>
</html>