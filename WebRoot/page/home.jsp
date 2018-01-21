<%@ page language="java" import="java.util.*,com.eec.entity.*"
	pageEncoding="utf-8" errorPage="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/home.css">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
</head>

<body>
	<div class="top1">
		<h1>
			首页，欢迎您：<%=((FounderUser) (session.getAttribute("user")))
					.getUsername()%>
			<a href="LoginServlet?flag=exit">退出</a>
		</h1>
		<div class="top2">
		<!-- 查看所有人的注册信息 -->
			<li><a href="LoginServlet?flag=showall" target="main">查看信息</a></li>
			<li><a href="UserServlet?flag=deleteuser" target="main">增加用户</a></li>
			<li><a href="page/fileupload.jsp" target="main">文件上传</a></li>
			<li><a href="page/filequery.jsp" target="main">文件查询</a></li>
			<!-- 删除其中一个用户 -->
			<li><a href="UserServlet?flag=deleteuser" target="main">删除用户</a></li>
			<li><a href="#" target="main">修改信息</a></li>
		</div>
	</div>

	<div class="content">
		<iframe src="" name="main" width="100%" height="600px"></iframe>
	</div>

	<script type="text/javascript">
		$("li a").mouseover(function() {
			$(this).css("color", "green");
		});
		$("li a").mouseout(function() {
			$(this).css("color", "white");
		});
	</script>
</body>
</html>
