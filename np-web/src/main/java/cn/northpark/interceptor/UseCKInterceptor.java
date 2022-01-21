package cn.northpark.interceptor;

import cn.northpark.annotation.UseCK;
import cn.northpark.constant.DataSourceEnum;
import cn.northpark.threadLocal.CustomerContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bruce
 * @date 2021年10月29日 22:04:34
 * 切换ck数据源
 * BRUCE TIPS!
 * interceptor可适用于同步方法
 */
@Slf4j
public class UseCKInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            UseCK UseCK = ((HandlerMethod) handler).getMethodAnnotation(UseCK.class);

            //没有声明需要权限,或者声明不验证权限
            if (UseCK == null || UseCK.validate() == false) {
                return true;
            } else {
                //验证
                CustomerContextHolder.setCustomerType(DataSourceEnum.ck);
                return true;
            }
        } else {
            return true;
        }


    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
