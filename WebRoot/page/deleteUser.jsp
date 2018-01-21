<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>删除用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/showinfo.css">

</head>

<body>
	<h1>用户信息</h1>
	<div>
		<table class="tab1" cellspacing="0px";>
			<tr>
				<td>id</td>
				<td>姓名</td>
				<td>密码</td>
				<td>性别</td>
				<td>号码</td>
				<td>生日</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.userlist}" var="user"
				varStatus="status">
				<c:if test="${status.count % 2 ==0}">
					<tr style="background-color:#92D34F;">
						<td>${user.userid}</td>
						<td>${user.username}</td>
						<td>${user.userpsw}</td>
						<td>${user.usersex}</td>
						<td>${user.usertel}</td>
						<td>${user.userbirth}</td>
						<td><a href="UserServlet?flag=dodelete">删除</a></td>
					</tr>
				</c:if>
				<c:if test="${status.count % 2 !=0}">
					<tr style="background-color:#8BB3E5">
						<td>${user.userid}</td>
						<td>${user.username}</td>
						<td>${user.userpsw}</td>
						<td>${user.usersex}</td>
						<td>${user.usertel}</td>
						<td>${user.userbirth}</td>
						<td><a href="UserServlet?flag=dodelete">删除</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>
