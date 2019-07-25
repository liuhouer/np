package cn.northpark.aspect;
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
///**
// * @author YangZhang
// *	可以定义一些切面来对某些方法 进行xx操作
// */
//@Aspect
//@Component
//public class HttpAspect {
//
//    
//    //cn.northpark.action.UserAction.*(..) public * cn.northpark.action.UserAction.*(..)) &&
//    @Before("execution(public * cn.northpark.action.UserAction.*(..))")
//    public void log(JoinPoint  joinPoint){
//    	//获取request对象的所有属性
//        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request=attributes.getRequest();
//	      //url
//	      //method
//	      //ip
//	      //类方法-使用joinPoint对象取
//	      //参数
//        log.info("url={}----->",request.getRequestURI());
//        log.info("method={}----->",request.getMethod());
//        log.info("ip={}----->",request.getRemoteAddr());
//        log.info("class_method={}----->",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
//        log.info("args={}----->",request.getQueryString());
//
//    }
//
//}