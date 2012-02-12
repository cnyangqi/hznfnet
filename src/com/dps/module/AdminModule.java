package com.dps.module;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * 后台管理模块
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 * @since 2012-2-12
 */
public class AdminModule {

	@At("login")
	@Ok("jsp:jsp.admin.login")
	public void login() {}

	@At("admin")
	@Ok("jsp:jsp.admin.admin")
	public void admin() {}

	@At("logout")
	@Ok("jsp:jsp.admin.login")
	public void logout() {}

	@At("publish")
	@Ok("jsp:jsp.admin.publish_article")
	public void publishArticle() {}

}
