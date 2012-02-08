package com.dps.module;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.VoidAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;

/**
 * 文件上传处理模块
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 */
@IocBean
public class UploadModule {

	private static final Log log = Logs.get();

	/**
	 * KindEditor 4.0.5 文件处理模块
	 * 
	 * @param file
	 * @param dirName
	 * @return json
	 */
	@At("/upload/kindeditor")
	@AdaptBy(args = {"ioc:upload"})
	@Fail("->:/handleOutOfSize")
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
		StringBuilder savePath = new StringBuilder(request.getServletContext().getRealPath("/"));
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
	 * 处理KindEditor 4.0.5 上传文件大小超过限制
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
	 * 封装KindEditor上传文件返回的消息对象
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
	 * 参数未注解，该方法还不能使用
	 * 
	 * @param tempFile
	 * @return
	 */
	@At("/upload/normal")
	@AdaptBy(args = {"ioc:upload"})
	public Object upload(TempFile tempFile) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (tempFile != null)
			re.put("ok", true);
		else
			re.put("ok", false);
		return re;
	}

	@At("/upload/html5")
	@AdaptBy(type = VoidAdaptor.class)
	public Object uploadHtml5(HttpServletRequest req) {
		Map<String, Object> re = new HashMap<String, Object>();
		try {
			File file = File.createTempFile("nutzmvc", "upload");
			Files.write(file, req.getInputStream());
			if (log.isDebugEnabled())
				log.debug("Upload success!! File save to " + file.getAbsolutePath());
			re.put("ok", true);
		}
		catch (IOException e) {
			if (log.isInfoEnabled())
				log.info("Upload fail?!", e);
			re.put("ok", false);
		}
		return re;
	}
}
