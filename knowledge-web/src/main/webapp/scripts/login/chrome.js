
function isBrow()
{
	var ua = navigator.userAgent.toLowerCase();
		var cachedAvailable = false;
	if (typeof window["ActiveXObject"] != "undefined") {
			try {
			var obj = new ActiveXObject("ChromeTab.ChromeFrame");
	if (obj) {		    	
						cachedAvailable = true;
					 }
	} catch(e) {
								  }}
	if (ua.indexOf("chrome") >= 0 || ua.indexOf("x-clock") >= 0 ||ua.indexOf("firefox")>= 0) {
								cachedAvailable = true;	
	}
}