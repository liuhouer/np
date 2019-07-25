package cn.northpark.threadLocal;

import cn.northpark.model.User;

/**
 * @author jeyy
 * ThreadLocal基础的添加、删除、获取操作。
 */
public class RequestHolder {

	public final static ThreadLocal<User> requestHolder = new ThreadLocal<>();
	
	public static void add(User user) {
		requestHolder.set(user);
	}
	
	public static User get() {
		return requestHolder.get();
	}
	
	public static void remove() {
		requestHolder.remove();
	}
}

