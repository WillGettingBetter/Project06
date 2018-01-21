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

<title>文件上传</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
	.form_box1{
		line-height: 50px;
	}
</style>

</head>

<body>
	<h1>文件上传</h1>
	<div id="content">
		<form action="FileUploadServlet" method="post"
			enctype="multipart/form-data" class="form_box1">
			<input type="hidden" name="flag" value="fileupload" /> 
			
			文件名:<input type="text" name="filename" value="" /> 
			<br /> 
			文件描述:<textarea rows="5" cols="50" name="filedes" style="vertical-align:top"  value="" ></textarea>
			<br />
			 图片:<input type="file" name="picture" /> 
			 <br /> 
			 <input type="submit" value="提交" />
		</form>
	</div>
</body>
</html>
