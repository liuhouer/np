package cn.northpark.annotation;

/**
 * @author bruce
 * @date 2022年07月05日 17:35:31
 */

import java.lang.annotation.*;

/**
 * 自定义注解可以不包含属性，成为一个标识注解
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
}
