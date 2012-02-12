<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>杭州农副物流网络科技有限公司 | 管理后台</title>
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
<script type="text/javascript" src="${ctx}/script/admin/simpla.jquery.configuration.js"></script>
<script>
	function load(url) {
		$("#main-content").html("");
		var iframe = document.createElement("iframe");
		iframe.src = url;
		iframe.setAttribute('frameborder', '0', 0);
		iframe.setAttribute('scrolling', 'no', 0);
		$("#main-content").html(iframe);
	}
</script>
<style type="text/css">
iframe {
	height: 1500px;
	width: 100%;
}
</style>
</head>
<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<div id="sidebar">
			<div id="sidebar-wrapper">
				<!-- Sidebar with logo and menu -->

				<h1 id="sidebar-title">
					<a href="#">Cms Admin</a>
				</h1>

				<!-- Logo (221px wide) -->
				<a href="#"><img id="logo" src="${ctx}/images/admin/logo.png" alt="Simpla Admin logo" /></a>

				<!-- Sidebar Profile links -->
				<div id="profile-links">
					您好， <a href="#" title="编辑你的资料">admin</a>
					<br/>
					<a href="http://www.hznfnet.com" title="点击查看网站首页信息" target="_blank">【网站】</a> | <a href="/logout" title="注销">【注销】</a>
				</div>

				<ul id="main-nav">
					<!-- Accordion Menu -->
					<li>
						<!-- Add the class "current" to current menu item current--> <a href="#" class="nav-top-item ">内容管理</a>
						<ul>
							<li><a href="javascript:load('/cms/admin/article?currentPage=1');">文章管理</a></li>
						</ul> <!-- class="current" -->
					</li>

					<li><a href="#" class="nav-top-item">网站管理</a>
						<ul>
							<li><a href="javascript:load('/cms/admin/navmodel');">导航设置</a></li>
							<li><a href="javascript:load('/cms/admin/templete?currentPage=1');">模版管理</a></li>
							<li><a href="javascript:load('/cms/admin/indexpic?currentPage=1');">首页图片</a></li>
						</ul></li>

					<li><a href="#" class="nav-top-item">我的资料</a>
						<ul>
							<li><a href="javascript:load('/cms/admin/user/toupdatepwd')">修改密码</a></li>
						</ul></li>

					<li><a href="#" class="nav-top-item">系统设置</a>
						<ul>
							<li><a href="javascript:load('/cms/admin/user?currentPage=1');">人员管理</a></li>
							<li><a href="javascript:load('/cms/admin/role?currentPage=1');">角色管理</a></li>
							<li><a href="javascript:load('/cms/admin/role?currentPage=1');">权限管理</a></li>
							<li><a href="javascript:load('/cms/admin/role?currentPage=1');">资源管理</a></li>
							<li><a href="javascript:load('/cms/admin/role?currentPage=1');">字典管理</a></li>
						</ul></li>

				</ul>
				<!-- End #main-nav -->

			</div>
		</div>
		<!-- End #sidebar -->

		<!-- 内容显示区域 -->
		<div id="main-content">
			<!-- Main Content Section with everything -->

			<noscript>
				<!-- Show a notification if the user has disabled javascript -->
				<div class="notification error png_bg">
					<div>你的浏览器不支持JavaScript,请打开你的浏览器的脚本支持。或者下载firfox\ie7+\chrome等浏览器</div>
				</div>
			</noscript>

			<!-- Page Head -->
			<h2>欢迎admin使用网络公司网站内容管理系统</h2>
			<p id="page-intro">您想做什么呢？</p>

			<ul class="shortcut-buttons-set">

				<li><a class="shortcut-button" href="javascript:load('/publish');"><span> <img src="${ctx}/images/admin/pencil_48.png" alt="icon" /><br />
							发布文章
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img src="${ctx}/images/admin/paper_content_pencil_48.png"
							alt="icon" /><br /> 创建栏目
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img src="${ctx}/images/admin/image_add_48.png" alt="icon" /><br />
							上传资料
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img src="${ctx}/images/admin/clock_48.png" alt="icon" /><br />
							添加任务
					</span></a></li>

				<li><a class="shortcut-button" href="#messages" rel="modal"><span> <img src="${ctx}/images/admin/comment_48.png"
							alt="icon" /><br /> 查看帮助
					</span></a></li>

			</ul>
			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">
					<h3>我的资料</h3>
					<div class="clear"></div>
				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">

						<form action="#" method="post">

							<fieldset>
								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->

								<p>
									<label>上次登录时间：</label> <input class="text-input medium-input" type="text" value='2012/02/10 13:32:46'
										readonly="readonly" /> <span class="input-notification information png_bg">如与您上次登录时间不一致，请及时修改您的密码</span>
									<!-- Classes for input-notification: success, error, information, attention -->
									<br />
								</p>

								<p>
									<label>上次登录IP：</label> <input class="text-input medium-input" type="text" value="127.0.0.1" readonly="readonly" />
									<span class="input-notification information png_bg">上次登录的IP地址信息</span>

								</p>

								<p>
									<label>登录次数</label> <input class="text-input medium-input" type="text" value="19" readonly="readonly" /> <span
										class="input-notification information png_bg">记录了您总共登录系统的次数</span>
								</p>

							</fieldset>

							<div class="clear"></div>
							<!-- End .clear -->

						</form>

					</div>
					<!-- End #tab2 -->

				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->

			<div id="footer">

				&#169; Copyright 2012 杭州农副物流网络科技有限公司 | Powered by <a ref="mailto:qi.yang.cn@gmail.com">yangq</a>

			</div>
			<!-- End #footer -->

		</div>
		<!-- End #main-content -->

	</div>
</body>
</html>