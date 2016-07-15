package cn.northpark.utils.json;

import java.util.HashMap;
import java.util.Map;

public class JsonTest {
	private static JsonUtil json = new JsonUtil();
	public static void main(String[] args) {
		dataInput();
		dataOutput();
	}
	public static void dataInput(){
		json.put("name","shansj");
		json.put("darling","huangyanwen");
		int[] arr = new int[]{1,2,3,4,5};
		json.put("array",arr);
		Map<String,String> map = new HashMap<String,String>();
		map.put("darling","huangyanwen");
		map.put("age","23");
		json.put("map",map);
	}
	public static void dataOutput(){
		System.out.println(json);
	}
}
