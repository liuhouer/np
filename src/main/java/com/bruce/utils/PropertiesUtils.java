package com.bruce.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

	/** 
	 * @author zhangyang
	 * properties工具类
	 */
	public class PropertiesUtils {
		
		private PropertiesUtils() {
			// 构造方法私有化，外部不能实例化该类 
		}
		
		private static Properties confProperties ;
		static {
			confProperties = new Properties();
			try {
				// 解析文件config.properties
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
				confProperties.load(is);;
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 获取properties文件属性
		 * 
		 * @param key
		 *            conf.properties文件key
		 * @return conf.properties文件key对应value
		 */
		public static String getProp(String key) {
			String p = confProperties.getProperty(key);
			
			//##解决utf-8的配置文件乱码问题
			try {
				p = new String(p.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return p;
		}
	}
