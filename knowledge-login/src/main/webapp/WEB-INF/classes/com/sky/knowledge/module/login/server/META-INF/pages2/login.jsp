<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/ext" prefix="ext"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title><ext:getI18nMsg key="com.sky.knowledge.system" /></title>
<link rel="shortcut icon" href="${images}/favicon.ico">
<link href="${styles}/common.css" rel="stylesheet" type="text/css" />
<%--国际化信息 --%>
<ext:i18n
	keys="error.module.login.LoginPasswordIsNullException,
				error.module.login.UserNameIsNullException,
				com.sky.knowledge.module.login.server.login.username,
				com.sky.knowledge.module.login.server.login.password" />
<script type="text/javascript" src="${scripts}/md5.js"></script>
<script type="text/javascript" src="${scripts}/Login.js"></script>
</head>

<body>
	<dir style="text-align: center; color: red">
	</dir>
	<form id="login" action="login.action" method="post"
		onsubmit="return _submit()">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="278" align="center" valign="top"
					background="${images}/Login2_bg.jpg"><img
					src="${images}/Login2_ad.jpg" width="764" height="278" /></td>
			</tr>
			<tr>
				<td>
					<table width="730" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="left" valign="top">
								<table border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="21" align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="right">&nbsp;</td>
									</tr>
									<tr>
										<td height="29" align="left">&nbsp;</td>
										<td align="left" valign="top"><img
											src="${images}/Login2_info.jpg" width="163" height="22" /></td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="right">&nbsp;</td>
									</tr>
									<tr>
										<td width="10" align="left"><img
											src="${images}/Login2_left.jpg" width="10" height="45" /></td>
										<td align="left" background="${images}/Login2_middle.jpg"><input
											id="loginName" name="loginName" type="text"
											class="formTextareaLogin2"
											value='<ext:getI18nMsg key="com.sky.knowledge.module.login.server.login.username" />'
											onfocus="eraseUsername()" />
										</td>
										<td align="left" background="${images}/Login2_middle.jpg">&nbsp;</td>
										<td align="left" background="${images}/Login2_middle.jpg"><input
											id="pwd" name="password" type="password" id="pwd"
											class="formTextareaLogin2"
											value='<ext:getI18nMsg key="com.sky.knowledge.module.login.server.login.password" />'
											onfocus="erasepwd()" />
										</td>
										<td align="left" background="${images}/Login2_middle.jpg">&nbsp;</td>
										<td width="78" align="right"><input type="image" id="t"
											src="${images}/Login2_in.jpg" width="78" height="45"
											border="0" /></td>
									</tr>
									<tr>
										<td colspan="4" align="center">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="4" align="center"><font color="red"
											size="2px" id="message">${ERROR}&nbsp;</font>
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td height="53" align="left" valign="top">&nbsp;</td>
						</tr>
						<tr>
							<td height="27" align="left" valign="top"><img
								src="${images}/Login2_line.jpg" width="730" height="11" /></td>
						</tr>
						<tr>
							<td height="30" align="center" valign="middle"><span
								class="LoginbottomText"><ext:getI18nMsg
										key="com.sky.knowledge.module.login.server.logo" />&copy; 2011</span>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>
