<%@ page language="java" import="java.util.*,com.eec.session.*"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body onload="countDown()">
	历史访问人数：
	<%
	Object totalhistory = application.getAttribute("totalhistory");
	if (totalhistory != null) {
		application.setAttribute("totalhistory",
				(Integer) totalhistory + 1);
	} else {
		application.setAttribute("totalhistory", 1);
	}
%>
	<%=(Integer) application.getAttribute("totalhistory")%>

	统计在线人数：
	<%=SessionCounter.getActiveSessions() %>
	
	
	<h1>注册成功！！！</h1>
	<input type="text" readonly="true" value="5" id="time" />
	<script type="text/javascript">
		var t = 5;
		function countDown() {
			var time = document.getElementById("time");
			t--;
			time.value = t;
			if (t <= 0) {
				location.href = "http://127.0.0.1:8080/Project06/login.jsp";
				clearInterval(inter);
			}
			;
		}
		var inter = setInterval("countDown()", 1000);
	</script>
	
</body>
</html>
