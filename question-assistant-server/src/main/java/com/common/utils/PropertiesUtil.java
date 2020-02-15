package com.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * 载入属性文件
	 * 
	 * @param propName
	 *            属性文件名，如果不在根目录下，需要包含详细目录层级结构
	 * @return
	 * @throws Exception
	 */
	public synchronized static Map<String, String> loadProperties(final String propName) throws Exception {
			if (StringUtil.isNull(propName))
				throw new Exception("未传入指定属性文件名，无法载入");

			Map<String, String> result = new HashMap<String, String>();
			StringBuilder sbPropName = new StringBuilder().append(propName);
			if (!propName.endsWith(".properties"))
				sbPropName.append(".properties");

			StringBuilder sbPropFileName = new StringBuilder();
			URL url = PropertiesUtil.class.getResource("/" + sbPropName.toString());
			if (url == null || url.getPath() == null || "".equals(url.getPath())) {
				sbPropFileName.append(System.getProperty("user.dir")).append(
						File.separatorChar).append(sbPropName.toString());
			} else {
				sbPropFileName.append(url.getPath());
			}
			Properties props = new Properties();
			props.load(new FileInputStream(URLDecoder.decode(sbPropFileName
					.toString(), "UTF-8")));
			for (Object name : props.keySet()) {
				String value = props.getProperty((String) name);
				result.put((String) name, value);
			}
			return result;
		}

	/**
	 * 根据key读取value
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */
	public synchronized static String readValue(String filePath, String key) {
		try {
			Map<String, String> map = loadProperties(filePath);
			return map.get(key);
		} catch (Exception e) {
			return "";
		}
	}

    /**
     * 从属性配置中筛选过滤某前缀打头的属性，并返回映射Map（已经剔除了前缀）
     * @param properties
     * @param prefix 前缀
	 * @param removePrefoxFromKey 是否从key中移除前缀
     * @return
     */
    public static Map<String, String> startWithPrefix(final Properties properties, final String prefix, final boolean removePrefoxFromKey){
        Map<String, String> result=new HashMap<>(100);
        for(String name : properties.stringPropertyNames()){
            if (name.startsWith(prefix)){
                String t=properties.getProperty(name, "");
                if (!StringUtil.isNull(t)) {
                	String temp=name.trim();
                	if (removePrefoxFromKey){
						temp=temp.substring(prefix.length(), temp.length());
					}
                    result.put(temp, t);
                }
            }
        }
        return result;
    }

}
