package cn.northpark.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author 解析自定义的注解
 *
 */
public class ParseAnnotation {
	
	public static void main(String[] args) {
		//1.使用类加载器加载类
		try {
			Class<?> c = Class.forName("cn.northpark.action.SoftAction");
			//2.找到类上面的注释
			boolean flag = c.isAnnotationPresent(BruceOperation.class);
			
			if(flag) {
				//3.获取注解的实例
				BruceOperation d = c.getAnnotation(BruceOperation.class);
				System.out.println(d.validate());
			}
			
			//4.另外一种解析方法
			Method[] methods = c.getMethods();
			for (Method m:methods) {
				Annotation[] ans = m.getAnnotations();
				for(Annotation a:ans) {
					if(a instanceof BruceOperation) {
						BruceOperation b = (BruceOperation) a;
						System.out.println(b.validate());
					}else if(a instanceof CheckLogin) {
						CheckLogin b = (CheckLogin) a;
						System.out.println(b.validate());
					}else if(a instanceof Desc) {
						Desc b = (Desc) a;
						System.out.println(b.value());
					}
				}
				
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

}

