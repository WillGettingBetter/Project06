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

<title>My JSP 'filequery.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<style type="text/css">
body,* {
	padding: 0px;
	margin: 0px;
}

.box1 {
	background-color: #B49ACA;
	width: 100%;
	height: 100px;
}

.box2_content {
	/* background-color: #215B83; */
	height: 400px;
	width: 100%;
}

ul li img {
	width: 300px;
	height: 300px;
}

.Li_picture {
	float: left;
	padding-top: 30px;
	padding-left: 30px;
}

.price {
	color: red;
	font-size: bold;
}

.box3_operation {
	position: relative;
	left: 600px;
}
</style>

</head>

<body>
	<h2>文件查询</h2>
	<div class="box1">
		<table cellspacing="10px"
			style="text-align: center; position: absolute;top:40px;left:400px;">
			<form action="" method="post">
				<tr>
					<td>文件编号:</td>
					<td><input type="text" name="fileid" id="fileid" /></td>
					<td>文件名称:</td>
					<td><input type="text" name="filename" id="filename" /></td>
				</tr>
				<tr>
					<td>文件描述:</td>
					<td><input type="text" name="filedes" id="filedes" /></td>
					<td>价格:</td>
					<td><input type="text" name="filepricemin" id="filepricemin"
						placeholder="最低价" /></td>
					<td>---</td>
					<td><input type="text" name="filepricemax" id="filepricemax"
						placeholder="最高价" /></td>
					<!-- 慎用submit，在使用表单的时候使用 -->
					<td><input type="button" value="查询" onclick="getResult()" /></td>
				</tr>
			</form>
		</table>
	</div>

	<div class="box2_content">
		<ul style="list-style-type: none; text-align: center;" id="ul_phone">
			<li class="Li_picture">
				<p>
					<a href="GetFileServlet?flag=gotogoods&fileid="></a> <img
						src="img/001.jpg" />
				</p>
				<p>
					<label class="phone">魅族手机</label>
				</p>
				<p>
					<label class="price">价格:2099￥</label>
				</p>
			</li>
			<li class="Li_picture">
				<p>
					<img src="img/001.jpg" />
				</p>
				<p>
					<label class="phone">魅族手机</label>
				</p>
				<p>
					<label class="price">价格:2099￥</label>
				</p>
			</li>
			<li class="Li_picture">
				<p>
					<img src="img/001.jpg" />
				</p>
				<p>
					<label class="phone">魅族手机</label>
				</p>
				<p>
					<label class="price">价格:2099￥</label>
				</p>
			</li>
			<li class="Li_picture">
				<p>
					<img src="img/001.jpg" />
				</p>
				<p>
					<label class="phone">魅族手机</label>
				</p>
				<p>
					<label class="price">价格:2099￥</label>
				</p>
			</li>
			<li class="Li_picture">
				<p>
					<img src="img/001.jpg" />
				</p>
				<p>
					<label class="phone">魅族手机</label>
				</p>
				<p>
					<label class="price">价格:2099￥</label>
				</p>
			</li>
			<li class="Li_picture">
				<p>
					<img src="img/001.jpg" />
				</p>
				<p>
					<label class="phone">魅族手机</label>
				</p>
				<p>
					<label class="price">价格:2099￥</label>
				</p>
			</li>
		</ul>
	</div>
	<div class="box3_operation">
		总页数:<label id="totalPages"></label>页&nbsp;&nbsp; 当前页数:<label
			id="currentPage"></label>页 <input type="button" value="首页"
			onclick="getFirstPage()" /> <input type="button" value="上一页"
			onclick="getPreviousPage()" /> <input type="button" value="下一页"
			onclick="getNextPage()" /> <input type="button" value="尾页"
			onclick="getLastPage()" />
	</div>

	<script type="text/javascript">
		var totalPages = 10;//总页数
		var currentPage = 1;//当前页面
		var pageCount = 2;//页记录数
		var fileid = 0;//文件编号
		var filename = "";//文件名称 
		var filedes = "";//文件描述
		var filepricemin = 0;//最低价
		var filepricemax = 0;//最高价

		/*获取到所有的查询条件*/
		function updataParam() {
			/*更新参数*/

			if ($("#fileid").val() == "" || $("#fileid").val() == null) {
				/*  alert(0000); */
				fileid = 0;
			} else {
				fileid = parseInt($("#fileid").val());
			}

			if ($("#filename").val() == "" || $("#filename").val() == null) {
				filename = "";
			} else {
				filename = $("#filename").val();
			}
			if ($("#filedes").val() == "" || $("#filedes").val() == null) {
				filedes = "";
			} else {
				filedes = $("#filedes").val();
			}

			if ($("#filepricemin").val() == ""
					|| $("#filepricemin").val() == null) {
				filepricemin = 0;
			} else {
				filepricemin = parseInt($("#filepricemin").val());
			}
			if ($("#filepricemax").val() == ""
					|| $("#filepricemax").val() == null) {
				filepricemax = 0;
			} else {
				filepricemax = parseInt($("#filepricemax").val());
			}

		}

		/*执行查询操作*/
		function getResult() {
			/*更新参数*/

			updataParam();
			currentPage = 1;
			updateTotalPages();
			getFiles();
		}

		/*首页*/
		function getFirstPage() {
			updateTotalPages();
			currentPage = 1;
			getFiles();
		}
		/*尾页*/
		function getLastPage() {
			updateTotalPages();
			currentPage = totalPages;
			getFiles();//获取数据
		}

		/*上一页*/
		function getPreviousPage() {
			updateTotalPages();
			if (currentPage > 1) {
				currentPage -= 1;
			}
			getFiles();
		}

		/*下一页*/
		function getNextPage() {
			updateTotalPages();
			if (currentPage < totalPages) {
				currentPage += 1;
			}
			getFiles();
		}

		/*获取总页数*/
		function updateTotalPages() {
			$.post("GetFileServlet", {
				"pageCount" : pageCount,
				"fileid" : fileid,
				"filename" : filename,
				"filedes" : filedes,
				"filepricemin" : filepricemin,
				"filepricemax" : filepricemax,
				flag : "gettotalpages"
			}, function(data) {
				totalPages = parseInt(data);
				$("#totalPages").html(totalPages);
			}, "text");
		}

		/*根据页码和页数获取数据，ajax*/
		function getFiles() {
			$("#currentPage").html(currentPage);
			$
					.post(
							"GetFileServlet",
							{
								"currentPage" : currentPage,
								"pageCount" : pageCount,
								"fileid" : fileid,
								"filename" : filename,
								"filedes" : filedes,
								"filepricemin" : filepricemin,
								"filepricemax" : filepricemax,
								flag : "getInfo"
							},
							function(data) {
								$("#ul_phone").empty();
								if (data.length == 0) {
									$("#ul_phone").html(
											"<p style='color:red'>没有查询到商品</p>");
								} else {
									var str = "";
									for (var i = 0; i < data.length; i++) {
										str = str
												+ " <li class='Li_picture'><p><a href='GetFileServlet?flag=gotogoods&fileid="
												+ data[i].fileid
												+ "'> <img src='" + data[i].filePic + "' /></a></p> <p><a href='GetFileServlet?flag=gotogoods&fileid="
												+ data[i].fileid
												+ "'> <label class='phone'>"
												+ data[i].fileName
												+ "</label></a> </p><p> <label class='price'>价格:"
												+ data[i].filePrice
												+ "￥</label> </p></li> ";
									}
									$("#ul_phone").html(str);

								}
							}, "json");
		}

		$(document).ready(function() {
			/*更新总页数*/
			updateTotalPages();
			getFiles();
		});
	</script>
</body>
</html>
