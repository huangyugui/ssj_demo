<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>Keys-登录111</title>
<link rel="stylesheet" href="/css/login.css" />
<!--End login-->
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	function changeImage() {
		//验证码置换src要加时间戳
		var path = "${pageContext.request.contextPath}/login/getImage?date=" + new Date();
		$("#captchaImage").attr("src", path);
	}
	$(function() {
		if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) {

			$('input:-webkit-autofill').each(function() {
				var text = $(this).val();
				var name = $(this).attr('name');
				$(this).after(this.outerHTML).remove();
				$('input[name=' + name + ']').val(text);
			});

		}
	})
</script>
</head>

<body>
	<form action="/j_spring_security_check" method="post">
		<div class="login">
			<a href="/" class="login-logo"></a>
			<sec:csrfInput/>
			<div class="login-form">
				<div class="login-row">欢迎登录</div>
				<div class="login-tip hidden">为了您的账户安全，请输入验证码。</div>
				<input type="text" class="login-name" name="username" placeholder="请输入用户名"> 
				<input type="password" class="login-password" name="password" placeholder="请输入6位数密码">
				Remember Me: <input type="checkbox" name="remember-me" />
				<div class="login-row login-row-check hidden">
					<input type="text" class="login-checkinput fl" placeholder="请输入右侧验证码"> <span class="login-checkcord fl"> <img src="/images/checkcord.jpg">
					</span> <span class="login-changecord fr">看不清<br>换一张
					</span>
				</div>
				<div class="login-row tc">
					<input type="submit" class="login-btn" value="登录">
				</div>
			</div>
		</div>
	</form>
</body>
</html>