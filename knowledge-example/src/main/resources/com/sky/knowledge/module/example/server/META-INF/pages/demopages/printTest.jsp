<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style media=print>
	.Noprint{display:none;}
	.PageNext{page-break-after : always;}
	</style>
<title>Insert title here</title>
</head>
<body>
<DIV>你好</DIV>

	<center class=noprint>
    <table width="100%" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#000044" align="center">
	<tr>
	    <td colspan="6" class="tdbgbutton" align="center">
		  <input onclick="document.all.webbrowser.execwb(6,1);" type="button" value="打印">&nbsp;&nbsp;
		  <input onclick="document.all.webbrowser.execwb(8,1)" type="button" value="页面设置">&nbsp;&nbsp;
		  <input onclick="document.all.webbrowser.execwb(7,1)" type="button" value="打印预览">&nbsp;&nbsp;
	      <input type="button" value="关闭" onclick="window.close();">
	    </td>
	</tr>
	</table>
	</center>
	<!-- 插入打印控件 -->
	<object id="webbrowser" height="0" width="0" classid="clsid:8856f961-340a-11d0-a96b-00c04fd705a2" viewastext> 
	</object>
</body>

</html>