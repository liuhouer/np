package cn.northpark.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	public static final JsonUtil jsonUtil = new JsonUtil();
	private static final Logger LOGGER = Logger
            .getLogger(JsonUtil.class);

	
	//以下是常用的方法==========================================================================================================
	
	
	/**
	 * 对象转换成json
	 * 
	 * @param obj
	 *            Object对象
	 * @return String
	 */
	public static String object2json(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	/**
	 * List转换成json(空值返回"")
	 * 
	 * @param obj
	 *            List对象
	 * @return String
	 */
	public static String list2json(List<?> list) {
		return JSON.toJSONString(list, SerializerFeature.WriteNullListAsEmpty);
	}

	/**
	 * Map转换成json(空值返回"")
	 * 
	 * @param obj
	 *            Map对象
	 * @return String
	 */
	public static String map2json(Map<String,Object> map) {
		return JSON.toJSONString(map, SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * Json字符串转换成Model
	 * 
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            目标Model.class
	 * @return 目标Model
	 */
	public static <T> T json2Model(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * Json字符串转换成Model
	 * 
	 * @param json
	 *            待转换的json字符串
	 * @param clazz
	 *            目标Model.class
	 * @return List<Model>
	 */
	public static <T extends Serializable> List<T> json2List(String json, Class<T> clazz) {
		if(null == json || null == clazz) return null;
		return JSON.parseArray(json, clazz);
	}

	
 
	/***
	 * 把JSON文本parse为JSONObject或者JSONArray
	 * 
	 * @param text
	 * @return
	 */
	public static Object json2Object(String text) {
		return JSON.parse(text);
	}

	/***
	 * 把JSON文本parse成JSONObject [Map]
	 * 
	 * @param text
	 * @return
	 */
	public static JSONObject json2map(String text) {
		return JSON.parseObject(text);
	}

	/***
	 * 把JSON文本parse成JSONArray
	 * 
	 * @param text
	 * @return
	 */
	public  static JSONArray json2JsonArray(String text) {
		return JSON.parseArray(text);
	}

	/***
	 * 将对象转化成map
	 * 
	 * @param text
	 * @return
	 */
	public static JSONObject obj2map(Object object) {
		String str = JSON.toJSONString(object);
		return JSON.parseObject(str);
	}

	/***
	 * 将JavaBean序列化为带格式的JSON文本
	 * 
	 * @param text
	 * @return
	 */
	public static String modelToFMTJson(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}

	
	
	//以上是常用的方法==========================================================================================================
	
	
	public static void main(String[] args) {
		String json = "{'subscribe':1,'openid':'oRYhMuA2O-86SJl1n_HQ_G3ueWOc','nickname':'周旭','sex ':1,'language':'zh_CN','city':'郑州','province':'河南','country':'中国','head imgurl':'http://wx.qlogo.cn/mmopen/LwcbhAmMnZAf2viaialRIfZYT0YHmTOR7AcKrK AwwEQpsIuhvraxN8r8dJOJfNfWkZVqiahicoHxS969cJ3qMl2rTA/0','subscribe_time':1478599840,'unionid':'omTTptybOJVz6LhFnQJcK6K_Cxhk','remark':'','groupid':101,'t agid_list':[101,103,111]}";
	
		JSONObject map = json2map(json);
		
		LOGGER.debug(map);
	}
}
