package cn.northpark.aspect;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {

    private final  static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
    
    //cn.northpark.action.UserAction.*(..)
    @Before("execution(public * cn.northpark.action.UserAction.*(..))")
    public void log(JoinPoint  joinPoint)
    {
    	//获取request对象的所有属性
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
	      //url
	      //method
	      //ip
	      //类方法-使用joinPoint对象取
	      //参数
        logger.info("url={}----->"+request.getRequestURI());
        logger.info("method={}----->"+request.getMethod());
        logger.info("ip={}----->"+request.getRemoteAddr());
        logger.info("class_method={}----->"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("args={}----->"+joinPoint.getArgs());

    }

}