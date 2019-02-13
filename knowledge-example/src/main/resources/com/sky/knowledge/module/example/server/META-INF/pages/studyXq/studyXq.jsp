<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<<validateBtn:isShow urls="/fsi/proImproveReportAdd,/fsi/proImproveReportModify,/fsi/proImproveReportDelete"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- Ext必需的js/css文件 --%>
<link rel="stylesheet" type="text/css"
	href="${resources}/extjs4/resources/css/ext-all.css" />

<script type="text/javascript" src="${resources}/extjs4/bootstrap.js"></script>
<script type="text/javascript"
	src="${resources}/extjs4/locale/ext-lang-zh_CN.js"></script>
	<%--自定义JS文件 --%>
<script type="text/javascript" src="${scripts}/common/common.js"></script>
<script type="text/javascript" src="${scripts}/common/commonModel.js"></script>
<script type="text/javascript" src="${scripts}/common/commonStore.js"></script>
<script type="text/javascript" src="${scripts}/common/commonFun.js"></script>
<script type="text/javascript" src="${scripts}/common/commomForm.js"></script>
<script type="text/javascript" src="${scripts}/common/commonGrid.js"></script>
<script type="text/javascript" src="${scripts}/common/commonWin.js"></script>

<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitFun.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitField.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitForm.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitModel.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitStore.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitGrid.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitMain.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/XqPrivateRemitWin.js"></script>

<script type="text/javascript" src="${scripts}/studyXq/ux/grid/DPrinter.js"></script>
<script type="text/javascript" src="${scripts}/studyXq/ux/grid/Printer.js"></script>

<link rel="stylesheet" type="text/css"
	href="${resources}/extjs4/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="${scripts}/studyXq/ux/grid/gridPrinterCss/print.css}" />
</head>

<body>
<div id="loading">
		<div class="loading-indicator">
			<img src="${images}/extanim32.gif" alt="" width="32" height="32"
				style="margin-right: 8px;" align="middle" /> <span>正在加载数据,请稍候......${scripts}2223</span>
		</div>
	</div>
	<div id="loading-mask"></div>
</body>
</html>