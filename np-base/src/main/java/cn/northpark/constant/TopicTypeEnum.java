package cn.northpark.constant;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

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
    DONATE("7"),
    LEARN("8"),

    ;

    //    1-碎碎念
    //    2-图册
    //    3-软件
    //    4-电影
    //    5-诗词秀
    //    6-情商提升
    //    7-赞助我们
    //    8-学习
    @Getter
    private String code;

    TopicTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TopicTypeEnum{" +
                "code='" + code + '\'' +
                '}';
    }

    /**
     * 根据NorthPark的评论控件来获取消息提醒的类型
     * @param code
     * @return
     */
    public static String getMatchNotifyName(String code){
        if(StringUtils.equals(code,NOTE.getCode())){
            return "NOTE_REPLY";
        }else if(StringUtils.equals(code,SOFT.getCode()) || StringUtils.equals(code,MOVIE.getCode())
                || StringUtils.equals(code, POEM.getCode())
                || StringUtils.equals(code, EQ.getCode())
                || StringUtils.equals(code, LEARN.getCode())
                || StringUtils.equals(code, DONATE.getCode())) {
            return "ART_REPLY";
        }

        return "";
    }

    public static void main(String[] args) {
        String matchVal = getMatchNotifyName("1");
        System.err.println(matchVal);
    }

}
