package cn.northpark.utils;

/**
 * @author bruce
 * @date 2022年08月17日 16:12:31
 */
public class YYY {
    public static void main(String[] args) {
        String title = "MetaImage 2.0.10 MacOS";
        String code = title.toLowerCase().replace("macos","").trim().replace(" ","-").replaceAll(".html", "");
        System.err.println(code);
    }
}
