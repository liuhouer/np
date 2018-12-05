package cn.northpark.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.constant.CookieConstant;
import cn.northpark.constant.RedisConstant;
import cn.northpark.model.User;
import cn.northpark.utils.CookieUtil;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;

/**
 * 登陆拦截器.
 *
 * @author zhangyang
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            CheckLogin checklogin = ((HandlerMethod) handler).getMethodAnnotation(CheckLogin.class);

            //没有声明需要权限,或者声明不验证权限
            if (checklogin == null || checklogin.validate() == false) {
                return true;
            } else {

            	//cookie登录
            	Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            	if (cookie != null) {
            		String userstr = RedisUtil.get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()));

            		if(StringUtils.isNotEmpty(userstr)) {
            			User user = JsonUtil.json2Bean(userstr, User.class);

            			request.setAttribute("user", user); 
            			if (user != null) {//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
            				return true;
            			} else {//如果验证失败
            				//返回到登录界面
            				String url = request.getRequestURL().toString();
            				response.sendRedirect("/login?redirectURI=" + url);
            				return false;
            			}
            		}

            	}else {
            		//返回到登录界面
    				String url = request.getRequestURL().toString();
    				response.sendRedirect("/login?redirectURI=" + url);
    				return false;
            	}

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