package cn.northpark.threadLocal;

import cn.northpark.constant.CookieConstant;
import cn.northpark.constant.RedisConstant;
import cn.northpark.utils.CookieUtil;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.vo.UserVO;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jeyy
 * ThreadLocal基础的添加、删除、获取操作。
 */
public class RequestHolder {

	public final static ThreadLocal<UserVO> requestHolder = new ThreadLocal<>();
	
	public static void add(UserVO user) {
		requestHolder.set(user);
	}
	
	public static UserVO get() {
		return requestHolder.get();
	}
	
	public static void remove() {
		requestHolder.remove();
	}

	/**
	 * 通用方法-从cookie获取登录信息
	 * @param request
	 * @return
	 */
	public static UserVO getUserInfo(HttpServletRequest request) {

		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

		if (cookie != null) {

			String userStr = RedisUtil.getInstance().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()));

			if (StringUtils.isNotEmpty(userStr)) {

				UserVO userVO = null;

				try {

					userVO = JsonUtil.json2object(userStr, UserVO.class);

				}catch (Exception ignore){

				}


				return userVO;
			}

		}

		return null;
	}

	/**
	 * 通用方法-从cookie获取登录信息
	 * @param request
	 * @return
	 */
	public static void updateUserInfoInRedis(HttpServletRequest request,UserVO newVO) {

		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

		if (cookie != null) {
			RedisUtil.getInstance().set(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()),JsonUtil.object2json(newVO));
		}

	}
}

