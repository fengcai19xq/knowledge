<%@ page language="java" pageEncoding="UTF-8"%>
 <%@taglib uri="/ext" prefix="ext"%> 
<html>
<head>
  <title><ext:getI18nMsg key="com.sky.knowledge.system"/></title>
  <link rel="shortcut icon" href="${images}/favicon.ico">
  <%-- Ext必需的js/css文件 --%>
  <link rel="stylesheet" type="text/css" href="${resources}/extjs4/resources/css/ext-all.css" />
  <%--自定义CSS文件 --%>
  <link href="${styles}/icon.css" rel="stylesheet" type="text/css" />
  <link href="${styles}/loading.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${resources}/extjs4/bootstrap.js"></script>
  <script type="text/javascript" src="${resources}/extjs4/locale/ext-lang-zh_CN.js"></script>
  <%--自定义JS文件 --%>
  <script type="text/javascript" src="${scripts}/Main.js"></script>
</head>
<body>
	<div id="loading">
		<div class="loading-indicator">
			<img src="${images}/extanim32.gif" alt="" width="32" height="32"
				style="margin-right: 8px;" align="middle" /><ext2:getI18nMsg key="page.module.login.message_waiting"/>
		</div>
	</div>
	<div id="loading-mask"></div>
</body>
</html>