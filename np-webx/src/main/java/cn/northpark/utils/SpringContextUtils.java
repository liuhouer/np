package cn.northpark.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author Bruce
 * spring手动获取bean的工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {//extends ApplicationObjectSupport{

    private static volatile ApplicationContext applicationContext = null;


    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            SpringContextUtils.applicationContext = appContext;
            System.out.println(
                    "========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext="
                            + applicationContext + "========");
        }
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }


}
