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

<title>My JSP 'cookieshow.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	显示所有Cookie信息
	<hr />
	<table bordercolor="red" border=1px style="background-color: skyblue">
	<tr>
	<th>键</th>
	<th>值</th>
	</tr>
		<%
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
		%>
		<tr>
			<td><%=cookie.getName()%></td>
			<td><%=cookie.getValue()%></td>
		</tr>
		<%
			}
		%>
	</table>
	
</body>
</html>
