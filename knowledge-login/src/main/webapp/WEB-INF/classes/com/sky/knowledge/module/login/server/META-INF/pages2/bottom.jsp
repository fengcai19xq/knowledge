<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@taglib uri="/ext" prefix="ext"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>FOSS</title>
<link href="${styles}/common.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="100" align="right"><span class="MainbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.currentuserloginName" />:</span></td>
			<td width="70" align="left"><span class="homeTopSearchName">${currentUserLoginName}</span>
			</td>
			<td width="70" align="right"><span class="MainbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.currentusername" />:</span></td>
			<td width="70" align="left"><span class="homeTopSearchName">${currentUserName}</span>
			</td>
			<td width="10" align="left"></td>
			<td width="70" align="right"><span class="MainbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.currentuserdeptname" />:</span>
			</td>
			<td width="400" align="left"><span class="homeTopSearchName">${currentUserDeptName}</span>
			</td>
			<td align="right"><span class="LoginbottomText"><ext:getI18nMsg
						key="com.sky.knowledge.module.login.server.logo" />&copy;
					2011&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</td>
		</tr>
	</table>
</body>
</html>
