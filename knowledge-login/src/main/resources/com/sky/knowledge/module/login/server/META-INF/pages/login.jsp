<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/ext" prefix="ext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>财务自助平台</title>
<link rel="shortcut icon" href="${images}/favicon.ico">

<style type="text/css" >
		*{
			font-size:14px;
			font-weight:bold;
		}
		ul,li{
			list-style:none
		}
		.input1{
			width:150px;
			margin-left:2px;
		}
    	#main{
			width:1006px;
			height:670px;
			margin:0 auto;
			background:url(${images}/itsm_login1.JPG);
		}
		#userform{
			padding-top:270px;
			padding-left:450px;
		}
		#userform form{
			width:450px;
			margin:0 auto;
		}
		.formitem {
			margin-top:10px;
		}
		.formitem2{
			margin-top:20px;
		}
		.formSubmit{
			//display:block;
			width:63px;
			height:28px;
			background:url(${images}/login_submit.jpg);
			border:0;
			margin-left:10px;
			cursor:pointer;
		}
		.formReset{
			//display:block;
			width:63px;
			height:28px;
			background:url(${images}/login_reset.jpg);
			border:0;
			margin-left:25px;
			cursor:pointer;
		}
    </style>
<%--国际化信息 --%>
<ext:i18n
	keys="error.module.login.LoginPasswordIsNullException,
				error.module.login.UserNameIsNullException,
				com.deppon.foss.module.login.server.login.username,
				com.deppon.foss.module.login.server.login.password" />
<script type="text/javascript" src="${scripts}/md5.js"></script>
<script type="text/javascript" src="${scripts}/Login.js"></script>

</head>

<body>
	<div id="main">
    	<div id="deppon">
        	<div id="userform">
              <form id="login" action="login.action" method="post" onSubmit="return _submit()">
                	<ul>
                   		 <li class="errInfo">
                        	<span id="message" style="color:#F00;">${ERROR}</span>
                        </li>
                    	<li  class="formitem">
                        	<span>用户账号 :</span><input type="text" name="loginName" id="loginName" class="input1"/>	
                        </li>
                        <li class="formitem">
                        	<span>登录密码 :</span><input type="password" name="password" id="pwd" class="input1"/>
                        </li>
                        <li class="formitem2">
                        	<input type="submit" value="" id="t" class="formSubmit"/>
                            <input type="reset" value="" class="formReset"/>
                        </li>
                        
                    </ul>
                 </form>
            </div>
        </div>
    </div>
	
</body>
</html>
