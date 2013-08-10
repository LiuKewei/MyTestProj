function getVLC(name) {
	if (window.document[name]) {
		return window.document[name];
	}
	if (navigator.appName.indexOf("Microsoft Internet") == -1) {
		if (document.embeds && document.embeds[name])
			return document.embeds[name];
	} else // if (navigator.appName.indexOf("Microsoft Internet")!=-1)
	{
		return document.getElementById(name);
	}
}

/* actions */

function doGo(targetURL) {
	alert("111111" + targetURL);
}

