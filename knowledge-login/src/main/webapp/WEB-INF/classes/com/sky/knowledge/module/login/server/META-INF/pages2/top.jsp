<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/ext" prefix="ext"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${styles}/common.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<%-- <td width="28" align="left"><img src="${images}/top_bg1.jpg"
							width="28" height="52" /></td> --%>
                         <td align="left" background="${images}/bar.jpg" height="60"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>	
									<td width="5%" align="right" ><a href="logout.action" class="topLinkFont" target="_parent"><ext:getI18nMsg key="page.module.login.exitSystem" />&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
								</tr>
							</table></td>            
					<%-- 	<td width="259" align="left"><img
							src="${images}/top_logo.jpg" width="259" height="52" /></td>
						<td align="left" background="${images}/top_bg2.jpg"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
																		
									<td align="center">
										<table width="314" border="0" cellpadding="0" cellspacing="5">
											<tr>
												<td width="80" align="left" class="homeTopSearchName">运单查询</td>
												<td width="160" align="left"><input
													name="textfield3222" type="text" class="topSearchTextarea" />
												</td>
												<td width="80" align="left"><img
													src="${images}/yudanSearch.jpg" width="70" height="23" />
												</td>
											</tr>
										</table>
									</td>
									<td width="30%" align="center" class="topLinkFont">
										&nbsp;&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;&nbsp;</td>
									
									<td width="5%" align="right" ><a href="logout.action" class="topLinkFont" target="_parent"><ext:getI18nMsg key="page.module.login.exitSystem" />&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
								</tr>
							</table></td> --%>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
