package com.dps.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * 后台管理模块
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 * @since 2012-2-12
 */
public class AdminModule {
	@Inject
	private Dao dao;

	@At("login")
	@Ok("jsp:jsp.admin.login")
	public void login() {}

	@At("admin")
	@Ok("jsp:jsp.admin.admin")
	public void admin(HttpServletRequest request) {
		Criteria cri = Cnd.cri();
		cri.where().and("loginName", "=", request.getParameter("username"));

		// dao.fetch(User.class, );
	}

	@At("logout")
	@Ok("jsp:jsp.admin.login")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@At("publish")
	@Ok("jsp:jsp.admin.publish_article")
	public void publishArticle() {}

}
