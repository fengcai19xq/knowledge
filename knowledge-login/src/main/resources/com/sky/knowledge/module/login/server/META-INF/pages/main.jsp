<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/ext" prefix="ext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <title>财务自助平台</title>
  <link rel="shortcut icon" href="${images}/favicon.ico">
  <%-- Ext必需的js/css文件 --%>
  <link rel="stylesheet" type="text/css" href="${resources}/extjs4/resources/css/ext-all.css" />
  <%--自定义CSS文件 --%>
  
  <link href="${styles}/icon.css" rel="stylesheet" type="text/css" />
  <link href="${styles}/loading.css" rel="stylesheet" type="text/css" />
  <link href="${styles}/fins-all.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${resources}/extjs4/bootstrap.js"></script>
  <script type="text/javascript" src="${resources}/extjs4/locale/ext-lang-zh_CN.js"></script>
  <%--自定义JS文件 --%>
  	<OBJECT ID="openIE"  HEIGHT=0
 CLASSID="CLSID:31414BE8-BFA2-4752-8635-AE23BA3BA82B" codebase="${scripts}/plugin/DLPClient.ocx">    
</OBJECT>

</head>
<body>
	<input id="basePath" type="hidden" value="<%= basePath %>"/>
	<div id="loading">
		<div class="loading-indicator">
			<img src="${images}/extanim32.gif" alt="" width="32" height="32"
				style="margin-right: 8px;" align="middle" /><ext:getI18nMsg key="page.module.login.message_waiting"/>
		</div>
	</div>
	<div id="loading-mask"></div>
	<%-- 需要放在最下面，原因：用户没权限报500错误时，会调用 id=basePath 标签 --%>
	<script type="text/javascript" src="${scripts}/Main.js"></script>
</body>
</html>