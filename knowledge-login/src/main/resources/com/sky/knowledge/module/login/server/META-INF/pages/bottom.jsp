<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib uri="/ext" prefix="ext"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>FOSS</title>
<link href="${styles}/common.css" rel="stylesheet" type="text/css" />
<link href="${styles}/fins-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	 //自动识别浏览器
	 var Sys = {};

     var ua = navigator.userAgent.toLowerCase();

     var s;

     (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :

     (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :

     (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :

     (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :

     (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

	function getNowDate(){
		var date = new Date();
		var year = 0;
		//以下进行测试

        if (Sys.ie){
       	 	year = date.getYear();
        }else{
         	year = date.getYear() + 1900;
        }
		var copyText = 'Copyright © '+year+' 德邦物流股份有限公司. All rights reserved.';
		document.getElementById('coppyId').innerHTML = copyText;
	}
</script>
</head>

<body class="bottom" onload="getNowDate();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr style="background-image:url(../images/login/foss/top_bg.gif)">
			<td width="100" align="right"><span class="MainbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.currentuserloginName" />:</span></td>
			<td width="70" align="left" ><span class="homeTopSearchName">${currentUserLoginName}</span>
			</td>
			<td width="70" align="right" ><span class="MainbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.currentusername" />:</span></td>
			<td width="70" align="left" ><span class="homeTopSearchName">${currentUserName}</span>
			</td>
			<td width="10" align="left" ></td>
			<td width="70" align="right" ><span class="MainbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.currentuserdeptname" />:</span>
			</td>
			<td width="300" align="left" ><span class="homeTopSearchName">${currentUserDeptName}</span>
			</td>
			<td align="left" ><span class="LoginbottomText" id="coppyId"></span>
			</td>
		</tr>
	</table>
</body>
</html>
