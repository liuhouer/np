package cn.northpark.constant;

/**
 * @author bruce
 * @date 2020年11月28日 12:32:54
 */
public enum TopicTypeEnum {

    NOTE("1"),
    LOVE("2"),
    SOFT("3"),
    MOVIE("4"),
    POEM("5"),
    EQ("6"),

    ;

    //    1-碎碎念
    //    2-图册
    //    3-软件
    //    4-电影
    //    5-诗词秀
    //    6-情商提升
    private String code;

    TopicTypeEnum(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "TopicTypeEnum{" +
                "code='" + code + '\'' +
                '}';
    }

}
