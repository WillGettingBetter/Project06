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

<title>My JSP 'fileinfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<script type="text/javascript"
	src="js/edit/xheditor-1.1.10-zh-cn.min.js"></script>
<style type="text/css">
.pic_dis {
	width: 650px;
	height: 400px;
	border: 2px solid red;
	margin: 0:auto;
	position: absolute;
	top: 50px;
	left: 300px;
	top: 50px;
}

.pic_dis .phone_info_display1,.word_info_display1,.price_info_display1,.des_info_display1
	{
	float: left;
}

.phone_info_display1 {
	width: 400px;
	height: 400px;
	margin-left: 0px;
	position: relative;
}

.word_info_display1 {
	width: 200px;
	height: 50px;
	font-size: 30px;
	font-weight: bold;
	position: relative;
	top: 80px;
	left: 10px;
}

.price_info_display1 {
	width: 200px;
	height: 50px;
	color: red;
	font-size: 30px;
	font-weight: bold;
	margin-left: 15px;
	position: relative;
	top: 100px;
}

.des_info_display1 {
	width: 200px;
	height: 50px;
	font-size: 30px;
	position: relative;
	top: 140px;
	left: 10px;
}

.messageArea {
	width: 500px;
	height: 50px;
	position: relative;
	top: 420px;
	left: 200px;
}

.msg_display {
	position: relative;
	top: 600px;
}

.msg_display .msg_display_title {
	text-align: center;
	position: relative;
}

.operations li {
	display: inline;
	position: relative;
	left: 500px;
	text-align: center;
}
</style>

</head>

<body>
	<h2>显示商品详细信息</h2>
	<div class="pic_dis">
		<input type="hidden" name="fileid" id="fileid"
			value="${requestScope.fileInfo.fileid}" />
		<!-- 图片放置区 -->
		<div class="phone_info_display1">
			<img alt="图片正在加载" src="${requestScope.fileInfo.filePic}"
				style="width:400px;height:400px;" />
		</div>
		<div class="word_info_display1">
			${requestScope.fileInfo.fileName}</div>
		<div class="price_info_display1">
			<label>${requestScope.fileInfo.filePrice}￥</label>
		</div>
		<div class="des_info_display1">
			<label>${requestScope.fileInfo.fileDes}</label>
		</div>
	</div>
	<div class="messageArea">
		<h2>此处为留言区，${sessionScope.user.username}请留言:</h2>
		<div>
			<textarea id="messageContent" class="xheditor" rows="10" cols="100"></textarea>
			<br /> <input type="button" value="提交" onclick="writeMessage()" /><label
				style="color:red" id="checkInfo"></label>
		</div>
	</div>

	<!-- 显示留言 -->
	<div class="msg_display">
		<hr />
		<div class="msg_display_title" style="text-align: center">
			<h3>以下是留言信息区域</h3>
		</div>
		<ul style="list-style-type: none" id="getmsg">
			<li>
				<p>
					<lable>admin</lable>
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp;发表于
					<lable>2017/12/28 15:07:36</lable>
				</p>
				<p>留言的内容</p>
				<hr />
			</li>
		</ul>
		<ul style="list-style-type: none" class="operations">
			<li>总页数是:<label id="totalpages"></label></li>
			<li>当前页数是:<label id="currentPage"></label></li>
			<li><input type="button" value="首页" onclick="getFirstPage()" /></li>
			<li><input type="button" value="上一页" onclick="getPreviousPage()" /></li>
			<li><input type="button" value="下一页" onclick="getNextPage( )" /></li>
			<li><input type="button" value="尾页" onclick="getLastPage()" /></li>
		</ul>
	</div>

	<script type="text/javascript">
		var totalPages = 3;//总页数
		var currentPage = 1;//当前页数
		var pageCount = 3;//页记录数
		var fileid = $("#fileid").val();//文件id

		/*更新总页数*/
		function updateTotalPages() {
			$.post("GetFileServlet", {
				"fileid" : fileid,
				"currentPage" : currentPage,
				"pageCount" : pageCount,
				"flag" : "getTotalPages"
			}, function(data) {
				totalPages = parseInt(data);
				$("#totalpages").html(totalPages);
			}, "text");
		}

		/*查看首页*/
		function getFirstPage() {
			updateTotalPages();
			currentPage = 1;
			getPages();
		}

		/*查看尾页*/
		function getLastPage() {
			updateTotalPages();
			currentPage = totalPages;
			getPages();
		}

		/*上一页*/
		function getPreviousPage() {
			updateTotalPages()
			if (currentPage > 1) {
				currentPage -= 1;
			}
			getPages();
		}
		/*下一页*/
		function getNextPage() {
			updateTotalPages()
			if (currentPage < 3) {
				currentPage += 1;
			}
			getPages();
		}

		function getPages() {
			$("#currentPage").html(currentPage);
			$
					.post(
							"GetFileServlet",
							{
								"fileid" : fileid,
								"currentPage" : currentPage,
								"pageCount" : pageCount,
								"flag" : "getPages"
							},
							function(data) {
								/* alert(data.length); */
								$("#getmsg").empty();
								var str = "";
								for (var i = 0; i < data.length; i++) {
									str = str
											+ "<li><p><lable>"
											+ data[i].userName
											+ "</lable>"
											+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
											+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
											+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
											+ "&nbsp;发表于<lable>"
											+ data[i].messageDate + "</lable>"
											+ "</p> <p>"
											+ data[i].messageContent
											+ "</p> <hr /> </li>";
								}
								$("#getmsg").html(str);
							}, "json");
		}

		/*写留言*/
		function writeMessage() {
			var messageContent = $("#messageContent").val();
			var fileid = $("#fileid").val();
			$.post("GetFileServlet", {
				"messageContent" : messageContent,
				"fileid" : fileid,
				"flag" : "writeMessage"
			}, function(data) {
				/* alert(data); */
				if (data == "success") {
					$("#messageContent").val("");
					$("#checkInfo").html("留言成功");
					/* updateMessage(); */
					updateTotalPages();
					getPages();
				} else {
					$("#checkInfo").html("留言失败");
				}
			}, "text");

		}

		/*更新并在页面上显示留言*/
		function updateMessage() {
			var fileid = $("#fileid").val();
			$
					.post(
							"GetFileServlet",
							{
								"fileid" : fileid,
								"flag" : "getMessage"
							},
							function(data) {
								$("#getmsg").empty();
								var str = "";
								for (var i = 0; i < data.length; i++) {
									str = str
											+ "<li><p><lable>"
											+ data[i].userName
											+ "</lable>"
											+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
											+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
											+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
											+ "&nbsp;发表于<lable>"
											+ data[i].messageDate + "</lable>"
											+ "</p> <p>"
											+ data[i].messageContent
											+ "</p> <hr /> </li>";
								}
								$("#getmsg").html(str);
							}, "json");
		}

		$(document).ready(function() {
			/* updateMessage(); */
			updateTotalPages();
			getPages();
		});
	</script>
</body>
</html>
