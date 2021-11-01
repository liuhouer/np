package cn.northpark.utils;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;


/**
 * @author bruce
 * @see   HttpFilterConfig httpFilterConfig = ConfigLoadUtils.loadYmal("httpfilter", HttpFilterConfig.class, "application.yml");
 * 加载配置文件的工具类
 */
public class ConfigLoadUtils {
	
	/**
	 * @param configName 从yml读取的属性名字
	 * @param clazz 需要返回的实体
     * @param file 读取的配置文件地址
	 * @return
	 */
	public static <T> T loadYmal(String configName , Class<T> clazz , String file){
		Map map = new Yaml().loadAs(ConfigLoadUtils.class.getResourceAsStream(file),Map.class);
		return JsonUtil.json2object(JsonUtil.object2json(map.get(configName)), clazz);
	}
	
}
