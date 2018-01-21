<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"%>
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
<link rel="icon" href="img/23.jpg" type="image/x-icon" />
<title>登录界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/text.css">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
</head>

<body>
	<div class="top">
		<h1 class="zhuti">登录界面</h1>
	</div>
	<div class="content">
		<form action="LoginServlet" method="post" class="form_t">
			<input type="hidden" name="flag" value="login" /> 
			用户名： <input type="text" name="username" class="input1" onclick="checkName()" />
			      <label id="judge" style="font-size:5px;color: red"></label> 
			      <br />
			密&nbsp;&nbsp;&nbsp;码： <input type="password" name="password" class="input2" /> 
			 	  <br /> 
			验证码：<input type="text" name="useryzm" class="input3" />  
			      <img alt="验证码" src="ImageServlet" onclick="goNext()" id="vcode" style="width:60px;height:40px;">
			      <br /> 
			<input type="submit" value="登录" class="button1" disabled="disabled" style="background:green" /> 
			<input type="button" value="注册" class="button2" onclick="rePrompts()" />
		</form>
	</div>
	<div id="box23" style="display: none"></div>
	<script type="text/javascript">
		function goNext() {
			var vcode = document.getElementById("vcode");
			vcode.src = "ImageServlet?" + Math.random();
		}
	</script>

	<script type="text/javascript">
		function checkName() {
			
			/*取到用户名的名称*/
			var username = $(".input1").val();
			$.post("LoginServlet", {
				"username" : username,
				flag : "check"
			}, function(data) {
				/*返回一些文本，用于判断结果*/
				if (data == "ok") {

					$("#judge").html("该用户存在");
					$(".button1").css("background", "blue");
					$(".button1").attr("disabled", false);
				} else {
					$("#judge").html("该用户不存在");
					$(".button1").css("background", "green");
					$(".button1").attr("disabled", true);
				}
			}, "text");
		}
	</script>

	<!-- 点击注册会显示的页面 -->
	<div id="div1" style="display:none;" class="realEffect">
		<h1 onclick="exitRegis()" class="exitKey">x</h1>
		<form action="#" method="post" class="form1" id="form1">
			<input type="hidden" name="flag" value="register" /> 
			姓&nbsp;&nbsp;名:<input type="text" name="username1" class="username1" onclick="checkUserName()" id="get" /> 
			                <label style="display:none" class="lab1" id="judge1"></label> 
			                <br /> 
			密&nbsp;&nbsp;码:<input type="password" name="password1" class="password1" onchange="judgePsw()" /> 
			                <br /> 
			确认密码:<input type="password" name="password12" class="password12" onchange="judgePsw()" id="checkPsw" /> 
			      <label style="display: none" class="statePrompt1"></label> 
			      <br /> 
			账&nbsp;&nbsp;号:<input type="text" name="userid1" class="userid1" id="checkID" onclick="checkId()" /> 
			                <label style="display:none" id="judge2"></label>
			                <br /> 
			性&nbsp;&nbsp;别:&nbsp;&nbsp; <input type="radio" checked="checked" name="usersex1" value="男" />男
			                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			                            <input type="radio" name="usersex1" value="女" />女
			                             <br /> 
			 手&nbsp;&nbsp;机:<input type="text" name="usertel1" class="usertel1" onmouseout="checkPhone()" id="phone" /> 
			                <label style="display:none" id="labtel1"></label> 
			                <br /> 
			 生&nbsp;&nbsp;日:<input type="date" name="userbirth1" class="userbirth1" /> 
			                <br /> 
			<input type="button" value="提交" class="sub11" onclick="subThing()" />
		</form>

	</div>
	<script type="text/javascript">
	   /* 判断号码是否符合正则表达式 */
		function checkPhone() {
			var phone = document.getElementById("phone").value;
			if (!(/^1[34578]\d{9}$/.test(phone))) {
				$("#labtel1").html("号码输入错误，请重新输入");
				$("#labtel1").show().addClass("dis");
				document.getElementById("phone").value = null;
			} else {
				$("#labtel1").html("号码输入正确");
				$("#labtel1").show().addClass("dis");
			}
		}
	   /* 判断用户名是否在数据库中已存在 */
		function checkUserName() {
			var username = $(".username1").val();
			$.post("LoginServlet", {
				"username" : username,
				flag : "check"
			}, function(data) {
				/*返回一些文本，用于判断结果*/
				if (data == "ok") {
					$("#judge1").html("该用户已存在,请重新输入");
					$("#judge1").show().addClass("dis");
					document.getElementById("get").value = null;
				} else {
					$("#judge1").html("该用户名可以使用");
					$("#judge1").show().addClass("dis");
				}
			}, "text");
		}
	   /* 判断前后密码值是否相等 */
		function judgePsw() {
			var password = $(".password1").val();
			var password1 = $(".password12").val();
			if (password == password1) {
				$(".statePrompt1").html("密码相同，可以登陆");
				$(".statePrompt1").show().addClass("dis");
			} else {
				$(".statePrompt1").html("密码不同，请重新输入");
				$(".statePrompt1").show().addClass("dis");
				document.getElementById("checkPsw").value = null;
			}
		}
	  /*  判断id号在数据库中是否已存在 */
		function checkId() {
			var userid = $(".userid1").val();
			$.post("LoginServlet", {
				"userid" : userid,
				flag : "checkId"
			}, function(data) {
				if (data == "OK") {
					$("#judge2").html("账号不存在，可以输入");
					$("#judge2").show().addClass("dis");
				} else {
					$("#judge2").html("账号已存在，不可以输入");
					$("#judge2").show().addClass("dis");
					document.getElementById("checkID").value = null;
				}
			}, "text");
		}
	</script>

	<script type="text/javascript">
	/* 点击登录页面的注册，隐藏的注册页面会显现出来 */
		function rePrompts() {
			$("#div1").show().addClass("realEffect");
		}
	/* 点击右上角的x就可以将注册页面再次隐藏起来 */
		function exitRegis() {
			document.getElementById("form1").reset();
			$("#div1").hide().removeClass("realEffect");
		}
	/* 注册页面中的提交按钮，用于完成注册*/
		function subThing() {
			var username = $(".username1").val();
			if (username != "") {
				var password = $(".password1").val();
				if (password != "") {
					var password1 = $(".password12").val();
					if (password1 != ""&& password1 == password) {
						var userid = $(".userid1").val();
						if (userid != "") {
							var usersex = $("input[name='usersex1']:checked")
									.val();
							var usertel = $(".usertel1").val();
							if (usertel != "") {
								var userbirth = $(".userbirth1").val();
								if (userbirth != "") {
									$.post("LoginServlet", {
										"username" : username,
										"password" : password,
										"userid" : userid,
										"usersex" : usersex,
										"usertel" : usertel,
										"userbirth" : userbirth,
										flag : "regis"
									}, function(data) {
										if ("No" == data) {
											alert("注册失败,请重新注册！");
										} else {
											document.getElementById("form1")
													.reset();
											$("#div1").hide().removeClass(
													"realEffect");
										}
									}, "text");
								} else {
									alert("生日不能为空！");
								}
							} else {
								alert("手机号不能为空！");
							}
						} else {
							alert("账号不能为空！");
						}
					} else {
						alert("确认密码不能为空或者前后密码不对应");
					}
				} else {
					alert("密码不能为空！");
				}
			} else {
				alert("用户名不能为空！");
			}
		}
	</script>
</body>
</html>
