package cn.northpark.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
    public static final JsonUtil jsonUtil = new JsonUtil();
    private static final Logger LOGGER = Logger
            .getLogger(JsonUtil.class);


    //以下是常用的方法==========================================================================================================

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        // compatible with the java.util.Date and the java.sql.Date
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * set the default value of the object which the value is null
     */
    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty,
    };

    /**
     * list to json
     *
     * @param list the list that will transform to json string
     * @return the json string of list transform
     */
    public static String list2json(List<?> list) {
        return JSON.toJSONString(list);
    }

    /**
     * map to json
     *
     * @param map the map that will transform to json string
     * @return the json string of map transform
     */
    public static String map2json(Map<String, Object> map) {
        return JSONObject.toJSONString(map);
    }

    /**
     * object array to json
     *
     * @param objects the object array that will transform to json string
     * @return the json string of array transform
     */
    public static String array2json(Object[] objects) {
        return JSON.toJSONString(objects);
    }

    /**
     * object to json
     *
     * @param object the object that will transform to json string
     * @return the json string of object
     */
    public static String object2json(Object object) {
        return JSON.toJSONString(object, config, features);
    }


    /**
     * json to list
     *
     * @param json  the json string that will transform to list
     * @param clazz the class of the list's element
     * @param <T>   the generic of the class
     * @return the list that json string transform
     */
    public static <T> List<T> json2list(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * json to map
     *
     * @param json json string that will transform to map
     * @return the map fo json string
     */
    public static Map<String, Object> json2map(String json) {
        return JSONObject.parseObject(json);
    }


    /**
     * json string to object array
     *
     * @param json  the json string will transform to object array
     * @param clazz the class of the json will transform
     * @param ts    the real object array
     * @param <T>   the real object
     * @param json
     * @param clazz
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> T[] json2array(String json, Class<T> clazz, T[] ts) {
        return JSON.parseArray(json, clazz).toArray(ts);
    }

    /**
     * json string to object
     *
     * @param json  the json string that will transform to object
     * @param clazz the class that json will transform
     * @param <T>   the object class
     * @return the object of json string
     */
    public static <T> Object json2object(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }


    /**
     * 用fastjson 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> getArrayJson(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getArrayJson(String jsonString) {
        List<T> list = new ArrayList<T>();
        try {
            list = (List<T>) JSON.parseArray(jsonString);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());

            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.error(e);
        }
        return list;
    }

    //以上是常用的方法==========================================================================================================


    public static void main(String[] args) {

    }
}
