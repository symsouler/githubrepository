<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/">
    
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="style/style.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	function exit(param){
		if(param){
			//alert("asd");
			window.parent.location.href="${pageContext.request.contextPath}/user/logout.action";
		
			
		}
	}
	</script>
	</head>
	<!--left-->
	<div id="left" style="float: left;">
		<!--s_left-->
		<div class="s_left" id="nav">
			<!-- 
		<p>
			您好：&nbsp;&nbsp;zxy
		</p>
		 -->
			<p>
				超市进出货管理
			</p>
			<ul>

				<li>
					<a  onclick='exit(confirm("确认退出吗?"));'
						style="background: url(images/zx.jpg) no-repeat 15px center;"
						target="_top">退出系统</a>
				</li>
				<li>
					<a href="buyCargo/index.action"
						style="background: url(images/grzlxg.jpg) no-repeat 15px center;"
						target="content">进货管理</a>
				</li>
				<!-- <li>
					<a href="content.jsp"
						style="background: url(images/grzlxg.jpg) no-repeat 15px center;"
						target="content">出货管理</a>

				</li> -->

				<li>
					<a href="user/examsScore.html"
						style="background: url(images/grzlxg.jpg) no-repeat 15px center;"
						target="content">售货管理</a>
				</li>
				<li>
					<a href="repository/index.action"
						style="background: url(images/grzlxg.jpg) no-repeat 15px center;"
						target="content">库存管理</a>
				</li>
			</ul>

		</div>

		<div class="s_left">
			<p>
				系统管理
			</p>
			<ul>
				<li>
					<a href="gogoing.html"
						style="background: url(images/user.png) no-repeat 15px center;"
						target="content">用户管理</a>
				</li>
				<li>

					<a href="gogoing.html"
						style="background: url(images/tb.gif) no-repeat 15px center;"
						>权限管理</a>
				</li>

				<li>

					<a href="gogoing.html"
						style="background: url(images/top_14.gif) no-repeat 15px center;"
						target="content">日志管理</a>
				</li>
				<!--<li>

					<a href="exammanager/examAdd.html"
						style="background: url(images/top_8.gif) no-repeat 15px center;"
						target="content">添加试题</a>
				</li>
				<li>
					<a href="exammanager/exams.html"
						style="background: url(images/top_6.gif) no-repeat 15px center;"
						target="content">试题管理</a>

				</li> -->
			</ul>
		</div>

	</div>