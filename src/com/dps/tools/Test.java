package com.dps.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Dao;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {

	Dao dao;

	public void printObjectType() {

	}

	public static void main(String[] args) throws IOException, TemplateException {
		// Integer tmp = Integer.valueOf("20120210113812828");
		// System.out.println(tmp);

		// System.out.println(Integer.MAX_VALUE);
		// System.out.println("20120210113812828".length());

		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("E:/project/hznfnet/WebContent/WEB-INF/template"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("index.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("site", "杭州农副物流网络科技有限公司");
		root.put("title", "首页");

		Writer out = new BufferedWriter(new OutputStreamWriter(	new FileOutputStream(new File("t.html")),
																"utf-8"));
		template.process(root, out);
		out.flush();

	}
}

class comp implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}

}