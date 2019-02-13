<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- Ext必需的js/css文件 --%>
<link rel="stylesheet" type="text/css" href="${resources}/extjs4/resources/css/ext-all.css" />
 <link rel="stylesheet" type="text/css" href="${styles}/fins-all2.css" />
 <style type="text/css">
    .x-grid-record-gray { color: #948d8e;!important }
	.x-grid-record-red { color: red; !important}
	.x-grid-record-yellow { color: blue;!important }
	.x-grid-record-green { color: green;!important }
	.x-grid-record-orange { color: orange;!important }
 </style>
 
<script type="text/javascript" src="${resources}/extjs4/bootstrap.js"></script>
<script type="text/javascript"
	src="${resources}/extjs4/locale/ext-lang-zh_CN.js"></script>
	<%--自定义JS文件 --%>
	<script type="text/javascript" src="${scripts}/common/commonfun.js"></script>
	<script type="text/javascript" src="${scripts}/demoscript/demoModel.js"></script>
	<script type="text/javascript" src="${scripts}/demoscript/demoStore.js"></script>
	<script type="text/javascript" src="${scripts}/demoscript/demoFun.js"></script>
<script type="text/javascript" src="${scripts}/demoscript/demoFieldSet.js"></script>
<script type="text/javascript" src="${scripts}/demoscript/demoForm.js"></script>
<script type="text/javascript" src="${scripts}/demoscript/demoGrid.js"></script>
<script type="text/javascript" src="${scripts}/demoscript/demoWin.js"></script>
<script type="text/javascript" src="${scripts}/demoscript/demoMain.js"></script>

<script type="text/javascript" src="${scripts}/ux/grid/DPrinter.js"></script>
<script type="text/javascript" src="${scripts}/ux/grid/Printer.js"></script>

<link rel="stylesheet" type="text/css" href="${scripts}/ux/grid/gridPrinterCss/print.css}" />
</head>

<body>
<div id="loading">
		<div class="loading-indicator">
			<img src="${images}/extanim32.gif" alt="" width="32" height="32"
				style="margin-right: 8px;" align="middle" /> <span>正在加载数据,请稍候......</span>
		</div>
	</div>
	<div id="loading-mask"></div>
</body>
</html>