
package cn.northpark.annotation;

import java.lang.annotation.*;

/**
 * login check|use for need check login 's method
 *
 * @author bruce
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {

    boolean validate() default true;
}
