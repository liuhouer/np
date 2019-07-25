package cn.northpark.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author Bruce
 * spring手动获取bean的工具类
 */
public class SpringContextUtils implements ApplicationContextAware {//extends ApplicationObjectSupport{

    private static ApplicationContext context = null;
    private static SpringContextUtils stools = null;

    public synchronized static SpringContextUtils init() {
        if (stools == null) {
            stools = new SpringContextUtils();
        }
        return stools;
    }


    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        // TODO Auto-generated method stub
        context = applicationContext;
    }

}
