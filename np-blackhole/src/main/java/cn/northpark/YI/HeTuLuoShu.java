package cn.northpark.YI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeTuLuoShu {
    static  // 创建地支与河图洛书数字的映射表
            Map<String, Integer> mapping = new HashMap<>();



    static {
        mapping.put("子", 1);
        mapping.put("丑", 8);
        mapping.put("寅", 8);
        mapping.put("卯", 3);
        mapping.put("辰", 4);
        mapping.put("巳", 4);
        mapping.put("午", 9);
        mapping.put("未", 2);
        mapping.put("申", 2);
        mapping.put("酉", 7);
        mapping.put("戌", 6);
        mapping.put("亥", 6);
    }

    public static void main(String[] args) {


        // 输入地支
        String input = "巳午未";
        // 去除重复的地支
        String uniqueInput = removeDuplicates(input);

        // 根据地支查找对应的河图洛书数字
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < uniqueInput.length(); i++) {
            String zhi = String.valueOf(uniqueInput.charAt(i));
            if (mapping.containsKey(zhi)) {
                int number = mapping.get(zhi);
                result.append(number);
            }
        }

        System.out.println("河图洛书数字：" + result.toString());
    }


    // 获取河图洛书对应的编号
    public static String getHeLuoNo(List<String> list) {

        // 根据地支查找对应的河图洛书数字
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String zhi = list.get(i);
            if (mapping.containsKey(zhi)) {
                int number = mapping.get(zhi);
                result.append(number);
            }
        }

        System.out.println("河图洛书数字：" + result.toString());

        return result.toString();
    }

    // 去除字符串中的重复字符
    private static String removeDuplicates(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (result.indexOf(String.valueOf(c)) == -1) {
                result.append(c);
            }
        }
        return result.toString();
    }
}