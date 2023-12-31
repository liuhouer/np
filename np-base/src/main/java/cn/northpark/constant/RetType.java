package cn.northpark.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bruce
 * @date 2021年09月12日 14:53:43
 * northPark爬虫的媒体数据类型
 */
public enum RetType {


    Movie("movies"),Soft("soft"),Love("album"),Head("heads"),MV("mv"),BZ("BZ");

    @Getter
    @Setter
    private String typeName;

    RetType(String typeName) {
        this.typeName = typeName;
    }
}
