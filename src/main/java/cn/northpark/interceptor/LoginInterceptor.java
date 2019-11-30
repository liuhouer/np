package cn.northpark.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.annotation.NotRecommand;
import cn.northpark.model.User;
import cn.northpark.threadLocal.RequestHolder;

/**
 * 登陆拦截器.
 *
 * @author zhangyang
 */
@NotRecommand(value="写入threadLocal|用法本应该是用filter来拦截某些url，有user写入threadLocal，"
		+ "然后在intercepter结束时销毁，这里是为了试验下threadLocal的用法.不是正常的用法")
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            CheckLogin checklogin = ((HandlerMethod) handler).getMethodAnnotation(CheckLogin.class);

            //没有声明需要权限,或者声明不验证权限
            if (checklogin == null || checklogin.validate() == false) {
                return true;
            } else {
                //在这里实现自己的权限验证逻辑
                User user = (User) request.getSession().getAttribute("user");
                if (user != null) {//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                	//TODO 写入threadLocal
                	//用法本应该是用filter来拦截某些url，有user写入threadLocal，
                	//然后在intercepter结束时销毁，这里是为了试验下threadLocal的用法.不是正常的用法
                	RequestHolder.add(user);
                    return true;
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


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 销毁 threadLocal
		RequestHolder.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}