package cn.northpark.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.constant.CookieConstant;
import cn.northpark.constant.RedisConstant;
import cn.northpark.model.User;
import cn.northpark.utils.CookieUtil;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理员拦截器.
 *
 * @author zhangyang
 */
@Slf4j
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            BruceOperation bruceOperation = ((HandlerMethod) handler).getMethodAnnotation(BruceOperation.class);

            //没有声明需要权限,或者声明不验证权限
            if (bruceOperation == null || bruceOperation.validate() == false) {
                return true;
            } else {
            	
            	//1.cookie登录
            	Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            	if (cookie != null) {
            		String userstr = RedisUtil.get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()));

            		if(StringUtils.isNotEmpty(userstr)) {
            			User user = JsonUtil.json2Bean(userstr, User.class);

            			if (user != null) {//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
            				if (user.getEmail().equals("654714226@qq.com")||user.getEmail().equals("qhdsoft@126.com")) {
            					request.setAttribute("user", user); 
                        		return true;
                            }else {
        	                	log.error("url={}----->",request.getRequestURI());
        	                	log.error("method={}----->",request.getMethod());
        	                	log.error("ip={}----->此IP恶意操作管理员方法，需要封锁",request.getRemoteAddr());
        	                	log.error("args={}----->",request.getQueryString());
        	                	return false;
                        	}
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
            	
            	return false;
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