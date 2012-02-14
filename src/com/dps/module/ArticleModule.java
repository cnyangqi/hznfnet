package com.dps.module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.EntityService;

import com.dps.bean.Article;
import com.dps.tools.MessageHelp;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 文章管理模块
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 */
@At("/article")
@IocBean(fields = {"dao"})
public class ArticleModule extends EntityService<Article> {

	private static final Log log = Logs.get();

	@At
	public Object list(@Param("page") int page, @Param("rows") int rows) {
		if (rows < 1)
			rows = 10;
		Pager pager = dao().createPager(page, rows);

		Condition c = Cnd.where("reviewStatus", "=", true)
							.and("showStatus", "=", true)
							.desc("topStatus")
							.desc("sequNum");

		List<Article> list = dao().query(Article.class, c, pager);
		Map<String, Object> map = new HashMap<String, Object>();
		if (pager != null) {
			pager.setRecordCount(dao().count(Article.class));
			map.put("pager", pager);
		}
		map.put("list", list);
		return map;
	}

	@At
	public Object add(@Param("..") Article obj) {
		if (obj == null)
			return MessageHelp.rtn(false);

		try {
			/** 处理 正文资源路径问题 */
			obj.setContent(obj.getContent().replaceAll("/html/attached/", "attached/"));

			obj.setCreateDate(new Date());// 创建日期

			/**
			 * 修改日期格式为yyMMddHHmmssS，获取的长度过长会造成，前后台通过JSON格式传递的数据会有细微偏差，表现在末尾一位
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssS");

			obj.setSequNum(Long.valueOf(sdf.format(new Date())));// 文章序列

			obj.setReviewStatus(true);// 暂时默认系统自动审核发布文章
			obj.setShowStatus(true);// 暂时默认直接显示发布文章

			dao().insert(obj);

			/** 生成新闻对象页面 */
			generateNewsPage(obj);

			generateAllPage();

			return MessageHelp.rtn(true);
		}
		catch (Throwable e) {
			if (log.isDebugEnabled())
				log.debug("E!!", e);
			return MessageHelp.rtn(false);
		}
	}

	@At
	public boolean delete(@Param("..") Article obj) {
		try {
			dao().delete(obj);
			return true;
		}
		catch (Throwable e) {
			if (log.isDebugEnabled())
				log.debug("E!!", e);
			return false;
		}
	}

	@At
	public boolean update(@Param("..") Article obj) {
		try {
			dao().update(obj);
			return true;
		}
		catch (Throwable e) {
			if (log.isDebugEnabled())
				log.debug("E!!", e);
			return false;
		}
	}

	/**
	 * 生成网站首页
	 */
	@SuppressWarnings("unchecked")
	@At("/genindex")
	public boolean generateIndexPage() {

		String base = Mvcs.getServletContext().getRealPath("/");
		/** 模板目录 */
		StringBuilder templateDir = new StringBuilder(base).append("/WEB-INF/template");
		/** 输出文件 */
		StringBuilder filePath = new StringBuilder(base).append("/html/index.html");

		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir.toString()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			Template template = cfg.getTemplate("index.ftl");

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("site", "杭州农副物流网络科技有限公司");
			root.put("title", "首页");

			Map<String, Object> map = (Map<String, Object>) list(1, 6);
			root.put("result", map.get("list"));

			Writer out = new BufferedWriter(new OutputStreamWriter(	new FileOutputStream(new File(filePath.toString())),
																	"utf-8"));
			template.process(root, out);
			out.flush();
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("E!!", e);
			}
			return false;
		}

		return true;
	}

	/**
	 * 生成新闻索引页面
	 */
	@SuppressWarnings("unchecked")
	@At("/gennewslist")
	public boolean generateNewsListPage() {
		/** 定义索引页面每页显示文章数量 */
		final int rows = 10;

		String base = Mvcs.getServletContext().getRealPath("/");
		/** 模板目录 */
		StringBuilder templateDir = new StringBuilder(base).append("/WEB-INF/template");
		/** 输出文件 */
		String filePath = new StringBuilder(base).append("/html/newsList1.html").toString();

		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir.toString()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			Template template = cfg.getTemplate("newslist.ftl");

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("site", "杭州农副物流网络科技有限公司");
			root.put("title", "公司要闻");

			Map<String, Object> map = (Map<String, Object>) list(1, rows);
			root.put("result", map.get("list"));
			root.put("pager", map.get("pager"));
			root.put("index", 1);

			/** 遍历生成所有索引分页面 */
			Pager pager = (Pager) map.get("pager");
			int pageCount = pager.getPageCount();
			if (pageCount > 1) {
				for (int i = 2; i <= pageCount; i++) {
					Map<String, Object> sunRoot = new HashMap<String, Object>();
					sunRoot.put("site", "杭州农副物流网络科技有限公司");
					sunRoot.put("title", "公司要闻");

					/** 页面索引 */
					sunRoot.put("index", i);

					Map<String, Object> tmp = (Map<String, Object>) list(i, rows);
					sunRoot.put("result", tmp.get("list"));
					sunRoot.put("pager", tmp.get("pager"));

					Writer sunOut = new BufferedWriter(new OutputStreamWriter(	new FileOutputStream(new File(filePath.replaceAll(	"newsList\\d+",
																																	"newsList"
																																			+ i))),
																				"utf-8"));

					template.process(sunRoot, sunOut);
					sunOut.flush();
				}
			}
			/** 遍历生成所有索引页面结束 */

			Writer out = new BufferedWriter(new OutputStreamWriter(	new FileOutputStream(new File(filePath)),
																	"utf-8"));
			template.process(root, out);
			out.flush();
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("E!!", e);
			}
			return false;
		}

		return true;
	}

	/**
	 * 生成新闻页面
	 */
	@At("/gennews")
	public boolean generateNewsPage(Article article) {

		String base = Mvcs.getServletContext().getRealPath("/");
		/** 模板目录 */
		StringBuilder templateDir = new StringBuilder(base).append("/WEB-INF/template");
		/** 输出文件 */
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuilder newFileName = new StringBuilder(df.format(new Date())).append("_");// 由于中文文件名可能会导致问题，所以采取随机非中文文件名
		newFileName.append(new Random().nextInt(1000)).append(".").append("html");
		StringBuilder filePath = new StringBuilder(base).append("/html/").append(newFileName);

		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir.toString()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			Template template = cfg.getTemplate("news.ftl");

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("site", "杭州农副物流网络科技有限公司");
			root.put("title", "公司要闻");

			/** 将生成页面的文件名记录到文章对象中，并更新数据库 */
			article.setUrl(newFileName.toString());
			dao().update(article);
			root.put("article", article);

			Writer out = new BufferedWriter(new OutputStreamWriter(	new FileOutputStream(new File(filePath.toString())),
																	"utf-8"));
			template.process(root, out);
			out.flush();
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("E!!", e);
			}
			return false;
		}

		return true;
	}

	/**
	 * 自动生成前台所有页面
	 */
	@At("/genall")
	public void generateAllPage() {

		/** 更新网站首页 */
		generateIndexPage();
		/** 更新新闻索引页面 */
		generateNewsListPage();

	}

	/**
	 * 转换Html特殊字符
	 * 
	 * @param str
	 * @return
	 */
	private String htmlspecialchars(String str) {
		// str = str.replaceAll("&", "&amp;");
		// str = str.replaceAll("<", "&lt;");
		// str = str.replaceAll(">", "&gt;");
		// str = str.replaceAll("\"", "&quot;");

		str = str.replaceAll("&amp;", "&;");
		str = str.replaceAll("&lt;", "<;");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");

		return str;
	}

}