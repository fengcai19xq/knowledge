<html>  
<head>  
<meta http-equiv="content-type" content="text/html;chartset=UTF-8"; >  
</head>  
<body>
<font  color="#000099" style="font-size:14pt; font-family:微软雅黑"><B>您好, 财务检查过程中发现您负责部门有一个财务问题需要进行整改并提交整改报告。问题报告信息如下：</B></font>
<br>
<br>
<ul>
 
  <table border="1" bordercolor="#000099" style="border-collapse:collapse; font-family:微软雅黑" cellspacing="1" cellpadding="1" align="center">
		<tr>
			<td align = "center"><B>问题编码</B></td>
			<td align = "center"><B>报告日期</B></td>
			<td align = "center"><B>部门</B></td>
			<td align = "center"><B>部门类型</B></td>
			<td align = "center"><B>部门经理</B></td>
			<td align = "center"><B>改善指定人</B></td>
			<td align = "center"><B>检查问题</B></td>
			<td align = "center"><B>指导意见</B></td>
			<td align = "center"><B>备注说明信息</B></td>
		</tr>
		<#--循环-->
	  	<#list messageList as MailMessage>
		<tr>
			<td width=80 align = "center">${MailMessage.number?default('空')}</td>
			<td align = "center">${MailMessage.reportDate?date?string('yyyy-MM-dd')?default('空')}</td>
			<td width=160 align = "center">${MailMessage.dept?string?default('空')}</td>
			<td width=120 align = "center">
				<#if MailMessage.depType == 0>
					职能部门
					<#elseif (MailMessage.depType == 1)>
					经营部门
					<#elseif (MailMessage.depType == 2)>
					营运部门
				</#if>
			</td>
			<td width=80 align = "center">${MailMessage.deptLeader?string?default('空')}</td>
			<td width=80 align = "center">${MailMessage.director?string?default('空')}</td>
			<td width=200 align = "center">${MailMessage.question?string?default('空')}</td>
			<td width=200 align = "center">${MailMessage.suggestion?string?default('空')}</td>
			<td width=200 align = "center">${MailMessage.description?string}</td>
		</tr>

	  </#list>
  </table>

  </ul>
<br>
<br>
<font color="red" style="font-size:14pt; font-family:微软雅黑"><B>点击链接：</B></font>
<a href="http://www.baidu.com" name="detail" style="color:red; font-size:12pt; font-family:微软雅黑" ><B>问题改善报告详情</B></a>
<br>
<font color="#000099" style="font-size:14pt; font-family:微软雅黑"><B>请于两天内进行问题整改并进行反馈，感谢您的支持与配合！</B></font>
<br>
<font color="#000099" style="font-size:14pt; font-family:微软雅黑"><B>祝您工作顺利！</B></font>
<br>
<font color="red" style="font-size:14pt; font-family:微软雅黑"><B>请不要直接回复本邮件。</B></font>
</body>  
</html>  