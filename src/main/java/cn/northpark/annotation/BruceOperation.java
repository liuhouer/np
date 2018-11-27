
package cn.northpark.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
