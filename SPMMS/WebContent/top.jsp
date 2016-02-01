<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="style/style.css" rel="stylesheet" type="text/css" />
	</head>
<div id="top">
	<!--top_left-->
	<div class="top_left">
		<img src="images/logo.png"
			style="float :right;margin-right: 20px; margin-top: 13px;" />
	</div>
	<!--top_right-->
	<div class="top_right">

		<div style="margin-left: 300px; margin-top: 43px;">

			您好：&nbsp;
			<font color="red" style="font-size: 20px">${loginUser.username}</font>
			&nbsp;欢迎使用超市信息管理系统
		</div>
	</div>
</div>