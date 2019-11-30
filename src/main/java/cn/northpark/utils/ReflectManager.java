package cn.northpark.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectManager {

    /*
     * 获取给定类的父类的指定位置的实际泛型参数
     */

    public static Class<?> getGenericTypeSuperclass(Class<?> clazz, int index) {

        Type type = clazz.getGenericSuperclass();

        /*
         * 如果当前类没有自定义的父类，就放回Object类型
         */

        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        /*
         * 否则就返回自定义的父类的信息
         */

        Type[] parameter = ((ParameterizedType) type).getActualTypeArguments();

        if (index >= parameter.length || index < 0) {
            throw new RuntimeException("你指定的泛型参数索引"
                    + (index >= parameter.length ? "已造成越界访问" : "为负数"));
        }

        if (!(parameter[index] instanceof Class)) {
            return Object.class;
        }

        return (Class<?>) parameter[index];
    }

    /*
     * 获取父类的第一个泛型参数
     */
    public static Class<?> getFirstGenericTypeSuperclass(Class<?> clazz) {
        return getGenericTypeSuperclass(clazz, 0);
    }

}
