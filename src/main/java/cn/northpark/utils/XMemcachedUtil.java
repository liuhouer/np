package cn.northpark.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.log4j.Logger;

/**
 * 
 * 调用位置：
 * 
 * mainproject
 * 
 * 
 * @author Administrator
 * 
 */

public class XMemcachedUtil {
	// create a static client as most installs only need
	// a single instance
	protected static MemcachedClient client;
	protected static XMemcachedClientBuilder builder;
	
	private static final Logger LOGGER = Logger
			.getLogger(XMemcachedUtil.class);

	// set up connection pool once at class load
	static {
		try {
			builder = new XMemcachedClientBuilder(
					AddrUtil.getAddresses("123.56.129.117:11111"));
			builder.setSessionLocator(new KetamaMemcachedSessionLocator());
			builder.setConnectionPoolSize(3);
			client = builder.build();
			client.setConnectTimeout(1000);
			client.setOpTimeout(2000);

		} catch (IOException e) {
			LOGGER.error("init memcache ERROR:", e);
		}

	}

	public static void main(String[] args) {
		XMemcachedUtil.put("test",111);
		String a = XMemcachedUtil.get("test").toString();
		System.out.println(a);
	}
	
	public static Object get(String key) {
		try {
			return client.get(key);
		} catch (Exception ex) {
			LOGGER.error(
					"get key ERROR: " + key + ex.getMessage());
			return null;
		}
	}
	
	/**
	 * 放入
	 * 
	 */
	public static void put(String key, Object obj) {
		try {
			put(key, obj, 0);
		} catch (Exception ex) {
			LOGGER.error(
					"put1 exception: key= " + key + ex.getMessage());
		}

	}

	public static void put(String key, Object obj, long exp) {
		try {
			if (obj != null) {
				int e = (int) (exp / 1000);
				client.set(key, e, obj);
			}
		} catch (Exception ex) {
			LOGGER.error(
					"put exception: key= " + key + ex.getMessage());
		}

	}

	/**
	 * 删除
	 */
	public static void remove(String key) {
		try {
				client.delete(key);
		} catch (Exception ex) {
			LOGGER.error("exception: " + key + ex.getMessage());
		}
	}

	public static void clear() {
		try {
			client.flushAll();
		} catch (Exception ex) {
			LOGGER.error("exception:", ex);
		}
	}

	/**
	 * 批量获取
	 * @param keys
	 * @return
	 */
	public static Object[] getMultiArray(String[] keys) {
		try {
			if (keys == null || keys.length == 0) {
				return null;
			}
			List<String> keylist = new ArrayList<String>();
			for (int i = 0; i < keys.length; i++) {
				keylist.add(keys[i]);
			}
			Map<String, Object> map = client.get(keylist);
			Object[] obj = new Object[keys.length];
			for (int i = 0; i < keys.length; i++) {
				obj[i] = map.get(keys[i]);
			}
			return obj;
		} catch (Exception ex) {
			String tmp_key = "";
			for (int i = 0; keys != null && i < keys.length; i++) {
				tmp_key += keys[i] + ",";
			}
			LOGGER.error(
					"getMultiArray ERROR: keys=" + tmp_key + ex.getMessage());
			return null;
		}
	}

	

	

	public static void status() {
		String[] hosts = { "123.56.129.117" };
		try {
			for (String host : hosts) {
				Map<String, String> map = client.stats(new InetSocketAddress(
						host, 11211));
				for (String s : map.keySet()) {
					LOGGER.error(
							host + " | " + s + " = " + map.get(s));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	

}

