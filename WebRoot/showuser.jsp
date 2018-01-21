<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 分页技术 -->
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

<title>My JSP 'showuser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/showinfo.css">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<style type="text/css">
.box1 {
	position: absolute;
	top: 600px;
	left: 400px;
}
</style>
</head>

<body>
	<h1>用户信息</h1>
	<div>
		<table class="tab1" id="tabtable" cellspacing="0px">
		</table>
	</div>
	<div class="box1">
		<input type="button" value="首页" onclick="goFirstPage()" /> <input
			type="button" value="上一页" onclick="goPreviousPage()" /> <input
			type="button" value="下一页" onclick="goNextPage()" /> <label>跳转到<input
			type="text" name="jumpResult" class="jumpResult" value=""
			style="width:30px" /><label onclick="jump()">go</label></label> <label
			onclick="getAllPage()">总页数为:<input type="text"
			class="allPage" value="" style="width:30px" readonly="readonly"></label>
		<input type="button" value="尾页" onclick="getLastPage()" /> <label>当前页数:<input
			type="text" value="" class="presentPage" style="width:30px"
			readonly="readonly" /></label>
	</div>
	<script type="text/javascript">
		var currentPage = 1;//当前页面
		var pageCount = 5;//当前页面的记录数
		var totalPages = 3;//总页面

		/*返回上一页*/
		function goPreviousPage() {
			getAllPage();
			if (currentPage != 1) {
				currentPage = currentPage - 1;
			}
			getResult(currentPage, pageCount);
		}

		/*返回下一页*/
		function goNextPage() {
			getAllPage();
			if (currentPage < totalPages) {
				currentPage = parseInt(currentPage) + parseInt(1);
			}
			getResult(currentPage, pageCount);

		}

		/*跳转到某一页面*/
		function jump() {
			getAllPage();
			var result = $(".jumpResult").val();

			if (parseInt(result) <= totalPages) {

				if (parseInt(result) >= 1) {
					currentPage = parseInt(result);
					getResult(currentPage, pageCount);
				} else {
					/*如果输入值过小，可以跳转到首页*/
					goFirstPage();
				}
			} else {
				/*如果输入值过大，可以跳转到尾页*/
				getLastPage();
			}
		}

		/* 获得总页数 */
		function getAllPage() {
			$.post("UserServlet", {
				"pageCount" : pageCount,
				flag : "getAllPage"
			}, function(data) {
				/* alert(data);  */
				totalPages = parseInt(data);
				$(".allPage").val(totalPages);
			}, "text");
		}

		/*尾页*/
		function getLastPage() {
			getAllPage();
			currentPage = totalPages;
			getResult(currentPage, pageCount);
		}

		/*首页*/
		function goFirstPage() {
			getAllPage();
			currentPage = 1;
			getResult(currentPage, pageCount);
		}

		function getResult(currentPage, pageCount) {
			$
					.post(
							"UserServlet",
							{
								"currentPage" : currentPage,
								"pageCount" : pageCount,
								flag : "getResult"
							},
							function(data) {
								$("#tabtable").html();
								var height = data.length + 1;
								var str = "<tr> <td>id</td> <td>姓名</td> <td>密码</td> <td>性别</td> <td>号码</td> <td>生日</td> </tr>";
								/* alert(data.length); */
								for (var i = 0; i < data.length; i++) {
									str += "<tr style='background-color:#8BB3E5'><td>"
											+ data[i].userid
											+ "</td><td>"
											+ data[i].username
											+ "</td><td>"
											+ data[i].userpsw
											+ "</td><td>"
											+ data[i].usersex
											+ "</td><td>"
											+ data[i].usertel
											+ "</td><td>"
											+ data[i].userbirth + "</td></tr>";
								}
								$("#tabtable").css("height",
										(height * 83) + "px");
								$("#tabtable").html(str);
								$(".presentPage").val(currentPage);
							}, "json");
		}
		$(document).ready(function() { /*这是为了防止在文档完全加载之前就运行jQuery代码*/
			getAllPage();
			getResult(currentPage, pageCount);
		});
		/*在文档还没有完全加载之前就运行函数，操作可能会失败，例如：1、试图隐藏一个不存在的元素；2、获得微完全加载的图片的大小*/
	</script>
</body>
</html>
