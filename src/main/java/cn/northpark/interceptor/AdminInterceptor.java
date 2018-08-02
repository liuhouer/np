package cn.northpark.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.model.User;

/**
 * 管理员拦截器.
 *
 * @author zhangyang
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	 private static final Logger LOGGER = Logger.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            BruceOperation bruceOperation = ((HandlerMethod) handler).getMethodAnnotation(BruceOperation.class);

            //没有声明需要权限,或者声明不验证权限
            if (bruceOperation == null || bruceOperation.validate() == false) {
                return true;
            } else {
                //在这里实现自己的权限验证逻辑
                User user = (User) request.getSession().getAttribute("user");
                if (user != null) {//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                	if (user.getEmail().equals("654714226@qq.com")||user.getEmail().equals("qhdsoft@126.com")) {
                		return true;
                    }else {
	                	LOGGER.error("url={}----->"+request.getRequestURI());
	                	LOGGER.error("method={}----->"+request.getMethod());
	                	LOGGER.error("ip={此IP恶意操作管理员方法，需要封锁}----->"+request.getRemoteAddr());
	                	LOGGER.error("args={}----->"+request.getQueryString());
	                	return false;
                	}
                } else {//如果验证失败
                    //返回到登录界面
                    String url = request.getRequestURL().toString();
                    response.sendRedirect("/login?redirectURI=" + url);
                    return false;
                }
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