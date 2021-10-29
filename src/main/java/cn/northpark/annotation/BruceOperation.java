
package cn.northpark.annotation;

import java.lang.annotation.*;

/**
 * admin check
 *
 * @author bruce
 */
@Documented	  //java doc	
@Inherited    //允许子类继承
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BruceOperation {

    boolean validate() default true;
}
