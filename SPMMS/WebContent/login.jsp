<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<%String path = request.getContextPath();%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link  rel="stylesheet" type="text/css"  href="<%=path%>/theme/tax_v1/css/module.css"/>
<title>中国联通税务管理应用</title>
<style>

</style>
<script type="text/javascript" language="javascript1.5">
	if(top.window.location!=window.location){top.window.location=window.location;}//防止被包含
	
	function checkItem(){
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		if (username.value == ""){
			alert("用户名不能为空");
			return false;
		}
		if (password.value == ""){
			alert("密码不能为空");
			return false;
		}
		return true;
	}
	
	function onOK(){
		if (checkItem()){
			var userForm = document.getElementById("userForm");
			userForm.submit();
		}
	}
	
	function onReset(){
		var userForm = document.getElementById("userForm");
		userForm.reset();
	
	}
function KeyDownOnOK(){
    if (event.keyCode == 13){
		event.returnValue=false;
		event.cancel = true;
		var userForm = document.getElementById("userForm");
		userForm.submit();
    }
}

</script>
</head>

<body style=" background-color:#cdcdcd ;">
<form name="userForm" id="userForm" action="<%=path %>/doLogin.do" method="post" onsubmit="return checkItem();">
<div class="logintop"></div>
<div class="loginlogo"></div>
<div class="loginmain"><div class="loginmainfront">
<ul>
<li><span class="one">用户名：</span>
<span  class="two">
<input name="username" id="username" style="width:220px; height:18px;" name="cbzx" value="" type="text" class="new_login_table_Form1" /></span>
</li>
<li>
<span class="one">密&nbsp;&nbsp;码：</span>
<span class="two"><input name="password" id="password" style="width:220px; height:18px;" name="cbzx" value="" type="password" class="new_login_table_Form1" onkeydown="javascript:KeyDownOnOK()" autocomplete="off"/></span>
</li>
<li><span class="three"><a href="#" target="_self" onclick="javascript:onOK();"><img src="<%=path%>/theme/tax_v1/images/loginbotton01.gif" width="98" height="32" /></a></span><span class="four"><a href="#" target="_self" onclick="javascript:onReset();"><img src="<%=path%>/theme/tax_v1/images/loginbotton02.gif" width="98" height="33" /></a></span></li>
</ul>
</div></div>
<div class="clear"></div>
<div class="loginbottom"></div>
</form>
</body>
</html>
