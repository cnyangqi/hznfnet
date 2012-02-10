package com.dps.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 与前台JSON数据消息交互帮助类
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 */
public class MessageHelp {

	public static Map<String, Object> rtn(boolean b) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (b) {
			map.put("rtn", "true");
			return map;
		} else {
			map.put("rtn", "false");
			return map;
		}
	}
}
