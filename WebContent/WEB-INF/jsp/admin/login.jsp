<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>杭州农副物流网络科技有限公司 | 管理后台登录界面</title>
<link rel="stylesheet" href="${ctx}/styles/admin/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/styles/admin/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/styles/admin/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/styles/admin/datePicker.css" type="text/css" media="screen" />
<!--[if lte IE 7]>
<link rel="stylesheet" href="styles/ie.css" type="text/css" media="screen" />
<![endif]-->
<script type="text/javascript" src="${ctx}/script/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx}/script/admin/facebox.js"></script>
<script type="text/javascript" src="${ctx}/script/admin/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/script/admin/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx}/script/admin/jquery.datePicker-min.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="script/DD_belatedPNG_0.0.8a.js"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('.png_bg, img, li');
</script>
<![endif]-->
</head>
<body id="login">

	<div id="login-wrapper" class="png_bg">
		<div id="login-top">
			<h1>网络公司网站后台管理</h1>
			<!-- Logo (221px width) -->
			<img id="logo" src="${ctx}/images/admin/logo.png" alt="网络公司网站后台管理logo" />
		</div>
		<!-- End #logn-top -->

		<div id="login-content">
			<form action="/cms/admin/login" method="post">
				<div class="notification information png_bg">
					<div>请输入用户账户和密码后登录</div>
				</div>

				<p>
					<label> 用户账户 </label> <input class="text-input" type="text" name="username" />
				</p>
				<div class="clear"></div>
				<p>
					<label> 用户密码 </label> <input class="text-input" type="password" name="password" />
				</p>
				<div class="clear"></div>
				<p id="remember-password">
					<input type="checkbox" /> 记住我
				</p>
				<div class="clear"></div>
				<p>
					<input class="button" type="submit" value="登录" />
				</p>

			</form>
		</div>
		<!-- End #login-content -->

	</div>
	<!-- End #login-wrapper -->

</body>
</html>