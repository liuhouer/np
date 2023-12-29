
package cn.northpark.annotation;

import java.lang.annotation.*;

/**
 * 使用ClickHouse数据源
 * @author Bruce
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UseCK {
    boolean validate() default true;
}
