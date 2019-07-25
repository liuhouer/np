package cn.northpark.utils.safe;

public class WAQ {
    public static XSS forXSS() {
        return new XSS();
    }

    public static SqlInjection forSQL() {
        return new SqlInjection();
    }

}