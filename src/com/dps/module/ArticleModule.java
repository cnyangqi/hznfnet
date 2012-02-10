package com.dps.module;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.EntityService;

import com.dps.bean.Article;
import com.dps.tools.MessageHelp;

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
		List<Article> list = dao().query(Article.class, null, pager);
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
			obj.setContent(htmlspecialchars(obj.getContent()));// 转换文章正文中特殊的HTML字符
			obj.setCreateDate(new Date());// 创建日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			obj.setSequNum(Long.valueOf(sdf.format(new Date())));// 文章序列

			dao().insert(obj);
			return true;
		}
		catch (Throwable e) {
			if (log.isDebugEnabled())
				log.debug("E!!", e);
			return false;
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
	 * 转换Html特殊字符
	 * 
	 * @param str
	 * @return
	 */
	private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
}