/**
 * 
 */
package com.bruce.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.constant.BC_Constant;
import com.bruce.model.User;

/**
 * 登陆拦截器.
 * @author zhangyang
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	
    	if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
    		CheckLogin checklogin = ((HandlerMethod) handler).getMethodAnnotation(CheckLogin.class);

    		//没有声明需要权限,或者声明不验证权限
    		if(checklogin == null || checklogin.validate() == false){
    			return true;
    		}else{                
    			//在这里实现自己的权限验证逻辑
    			User user = (User) request.getSession().getAttribute("user");
    			if(user!=null){//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
    				return true;
    			}else{//如果验证失败
    				//返回到登录界面
    				String url = request.getRequestURL().toString();
    				String[] strs = url.split("8082/");
    				String postfix = strs[1];
    				url = "http://"+BC_Constant.Domain+"/"+postfix;
    				response.sendRedirect("/login?redirectURI="+url);
    				return false;
    			}       
    		}
    	}else{
    		return true;   
    	}
    	 
    	 
      }
    	
    	
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}