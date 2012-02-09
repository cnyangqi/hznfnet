package com.dps.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;

@IocBean
public class TestModule {

	@At("/test")
	public void test() {
		System.out.println(Mvcs.getServletContext().getRealPath("/"));
	}
}
