package cn.northpark.constant;


public interface CookieConstant {

    String TOKEN = "token";

    /**
     * 过期时间（单位s）
     * 7天有效cookie
     */
    Integer expire = 60 * 60 * 24 * 7;

}