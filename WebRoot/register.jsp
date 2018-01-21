<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>登录界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/register.css">

</head>

<body>
	<div class="top">
		<h1>注册界面</h1>
	</div>
	<div class="content">
		<form action="LoginServlet" method="post" class="form1">
			<input type="hidden" name="flag" value="register" />
			
			姓&nbsp;&nbsp;名:<input type="text" name="username" class="name1" /> <br /> 
			密&nbsp;&nbsp;码:<input type="password" name="password" class="psw1" /> <br />
			账&nbsp;&nbsp;号:<input type="text" name="userid" class="id1" /> <br /> 
			性&nbsp;&nbsp;别:&nbsp;&nbsp;
			                <input type="radio" checked="checked" name="sex" value="男" />男
			                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			                <input type="radio" name="sex" value="女" />女<br /> 
			手&nbsp;&nbsp;机:<input type="text" name="usertel" class="tel1" /> <br /> 
			生&nbsp;&nbsp;日:<input type="text" name="userbirth" class="birth1" /> <br /> 
			
			<input type="submit" value="提交" class="sub1" />
		</form>
	</div>
</body>
</html>
