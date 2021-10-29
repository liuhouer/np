
package cn.northpark.annotation;

import java.lang.annotation.*;

/**
 * the description of method type CONSTRUCTOR...
 *
 * @author bruce
 */
@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Desc {

    String value() ;
    String handlefor() default "";
}
