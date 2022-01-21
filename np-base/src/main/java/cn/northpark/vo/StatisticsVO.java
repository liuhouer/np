package cn.northpark.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author bruce
 * @date 2021年11月17日 16:24:01
 */
@Builder
@Data
public class StatisticsVO {
    String url ;
    String method ;
    String ip ;
    String class_method ;
    String args ;

    UserVO userVO;

    Map<String, String> cookieMap;

    @Override
    public String toString() {

        String pre = "{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", class_method='" + class_method + '\'' +
                ", args='" + args + '\'';

        if (userVO != null) {
            pre += ", userVO=###" + userVO.toString() + "###";
        }
        if (cookieMap != null) {
            pre += ", cookieMap=###" + cookieMap + "###";
        }

        pre += '}';

        return pre;


    }
}
