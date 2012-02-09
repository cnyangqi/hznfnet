package com.dps.module;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;

/**
 * KindEditor 4.0.5 Nutz 插件
 * 
 * <p>
 * 目前支持功能
 * <li>1.本地文件分类上传</li>
 * <li>2.服务器文件浏览</li>
 * </p>
 * 
 * @version 1.0.0
 * @author yangq(qi.yang.cn@gmail.com)
 */
@IocBean
@At("/kindeditor")
public class KindeditorModule {

	private static final Log log = Logs.get();

	/**
	 * 本地文件上传
	 * 
	 * @param file
	 * @param dirName
	 * @return json
	 */
	@At
	@AdaptBy(args = {"ioc:upload"})
	@Fail("->:/handleOutOfSize")
	@Aop("log")
	public Object upload(	@Param("imgFile") TempFile file,
							@Param("dir") String dirName,
							HttpServletRequest request) {

		// 文件分类定义
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 检查扩展名
		String fileName = file.getFile().getName();
		dirName = dirName != null ? dirName : "image";// 文件分类为空的时候默认为图片文件类型

		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
			return msgResponse(1, "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());

		// 文件保存目录路径
		StringBuilder savePath = new StringBuilder(Mvcs.getServletContext().getRealPath("/"));
		savePath.append("kindeditor/attached/");
		savePath.append(dirName).append("/");
		savePath.append(ymd).append("/");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		// savePath.append(file.getMeta().getFileLocalName());// 默认为本地上传文件原名

		StringBuilder newFileName = new StringBuilder(df.format(new Date())).append("_");// 由于中文文件名可能会导致问题，所以采取随机非中文文件名
		newFileName.append(new Random().nextInt(1000)).append(".").append(fileExt);

		savePath.append(newFileName);

		// 文件保存目录URL
		StringBuilder saveUrl = new StringBuilder(request.getContextPath());
		saveUrl.append("/kindeditor/attached/");
		saveUrl.append(dirName).append("/");
		saveUrl.append(ymd).append("/");
		saveUrl.append(newFileName);

		// 自动归档文件
		try {
			if (Files.copyFile(	new File(file.getFile().getAbsolutePath()),
								new File(savePath.toString()))) {
				return msgResponse(0, saveUrl.toString());
			}

			return msgResponse(1, "服务器自动归档文件失败。");
		}
		catch (IOException e) {

			if (log.isDebugEnabled()) {
				StringBuilder tmp = new StringBuilder("Move file fail!! File ");
				tmp.append(file.getFile().getAbsolutePath())
					.append(" move to ")
					.append(savePath)
					.append(" fail ");
				log.debug(tmp.toString());
			}

			return msgResponse(1, "服务器自动归档文件失败。");
		}

	}

	/**
	 * 处理上传文件大小超过限制异常
	 * 
	 * @param throwable
	 */
	@At
	public Map<String, Object> handleOutOfSize(@Attr("obj") Throwable throwable) {

		// 上传文件大小超过限制
		if (throwable.getMessage().indexOf("out of size") != -1) {
			return msgResponse(1, "上传文件大小超过限制。");
		} else {
			return msgResponse(1, "上传文件失败。");
		}

	}

	/**
	 * 封装上传文件返回的消息对象
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	private Map<String, Object> msgResponse(int code, String message) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (code == 0) {// 上传文件成功
			re.put("error", 0);
			re.put("url", message);
		} else {
			re.put("error", 1);
			re.put("message", message);
		}
		return re;
	}

	/**
	 * 服务器文件浏览
	 * 
	 * @param request
	 * @return
	 */
	@At
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object list(HttpServletRequest request) {

		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};// 图片扩展名
		StringBuilder rootPath = new StringBuilder(Mvcs.getServletContext().getRealPath("/")).append("kindeditor/attached/");
		StringBuilder rootUrl = new StringBuilder(request.getContextPath()).append("/kindeditor/attached/");

		// 检查请求目录合法性
		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(new String[]{"image", "flash", "media", "file"})
						.contains(dirName)) {
				if (log.isDebugEnabled()) {
					log.debug("KindEditor请求的文件目录 " + dirName + " 无效。");
				}
				return null;
			}

			rootPath.append(dirName).append("/");
			rootUrl.append(dirName).append("/");

			File saveDirFile = new File(rootPath.toString());
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}

		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0	? str.substring(0, str.lastIndexOf("/") + 1)
														: "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order")
																		.toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			if (log.isDebugEnabled()) {
				log.debug("不允许使用..移动到上一级目录");
			}
			return null;
		}

		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			if (log.isDebugEnabled()) {
				log.debug("Kindeditor 传递的 path 参数：" + path + " 无效");
			}
			return null;
		}

		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			if (log.isDebugEnabled()) {
				log.debug("Kindeditor 当前浏览目录：" + currentPath + " 无效");
			}
			return null;
		}

		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
												.toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put(	"datetime",
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		return result;
	}

	@SuppressWarnings("rawtypes")
	class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
