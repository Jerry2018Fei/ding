package com.saas.config.properties;


import com.saas.utils.io.PropertiesLoader;
import com.saas.utils.string.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局配置类
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {


	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<>();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("resource/sys.properties");




	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : "");
		}
		try {
			return StringUtils.isEmpty(value) ?"": new String(value.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	







	
}
