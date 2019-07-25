
package cn.northpark.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
