package cn.northpark.aspect;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import cn.northpark.annotation.Redis;
import cn.northpark.constant.BC_Constant.RedisReturnType;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 优先取redis缓存
 *
 */
@Aspect
@Component
@Slf4j
public class RedisAspect {
	/**
	 * 获取或添加缓存
	 */
	@Around("@annotation(cn.northpark.annotation.Redis)")
	public Object Redis(final ProceedingJoinPoint jp) throws Throwable {
		Method method = getMethod(jp);
		Redis cache = method.getAnnotation(Redis.class);
		//根据类名、方法名和参数生成key
		final String key = parseKey(cache.field(), method, jp.getArgs());
		if (log.isInfoEnabled()) {
			log.info("生成key:" + key);
		}
		//得到被代理的方法上的注解
		RedisReturnType modelType = method.getAnnotation(Redis.class).returnType();
		String type = modelType.getType();
		
		
		String redisRS = RedisUtil.get(key);
		
		Object result = null;
		
		if(StringUtils.isNotEmpty(redisRS)) {
			
			
			//缓存命中
			if (log.isInfoEnabled()) {
				log.info("缓存命中");
			}
			
			
			    //返回listmap
			if(type.equals(RedisReturnType.listmap.getType())) {
				result = JsonUtil.json2ListMap(redisRS);
			}else if(type.equals(RedisReturnType.map.getType())){
				//返回map
				result = JsonUtil.json2map(redisRS);
			}else if(type.equals(RedisReturnType.string.getType())){
				//返回string
				result = redisRS;
			}else {
				//得到被代理方法的返回值类型
				Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();
				
				result = JsonUtil.json2list(redisRS, returnType);
			}
			
			
			
		}else {
			//缓存未命中
			if (log.isInfoEnabled()) {
				log.info("缓存未命中");
			}
			//去数据库查询
			result = jp.proceed(jp.getArgs());
			//把序列化结果放入缓存
			RedisUtil.set(key, JsonUtil.object2json(result),cache.expire());
			
		}
		
		
		return result;
	}

	/**
	 * 删除缓存
	 */
//	@Around("@annotation(cn.northpark.annotation.RedisEvict)")
//	public Object RedisEvict(final ProceedingJoinPoint jp) throws Throwable {
//		//得到被代理的方法
//		Method method = getMethod(jp);
//		//得到被代理方法上的注解
//		Class modelType = method.getAnnotation(RedisEvict.class).type();
//		if (log.isInfoEnabled()) {
//			log.info("清空缓存:" + modelType.getName());
//		}
//		//判断是否指定了field
//		String[] fields = method.getAnnotation(RedisEvict.class).field();
//		if (fields.length == 0) {
//			//清除类全限定名对应Hash缓存
//			redisTemplate.delete(modelType.getName());
//		} else {
//			//清除指定的field的缓存
//			List<Object> objects = new ArrayList<>();
//			for (String field : fields) {
//				if (!StringUtils.isEmpty(field)) {
//					objects.add(field);
//				}
//			}
//			if (objects.size() > 0) {
//				redisTemplate.opsForHash().delete(modelType.getName(), (Object[]) fields);
//			}
//		}
//		return jp.proceed(jp.getArgs());
//	}

	/**
	 * 获取被拦截方法对象
	 * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
	 * 而缓存的注解在实现类的方法上
	 * 所以应该使用反射获取当前对象的方法对象
	 */
	private Method getMethod(ProceedingJoinPoint pjp) {
		//获取参数的类型
		Class[] argTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
		Method method = null;
		try {
			method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return method;

	}

	/**
	 * 生成redis key策略
	 * @param field
	 * @param method
	 * @param args
	 * @return
	 */
	private String parseKey(String field, Method method, Object[] args) {
		//SpEL表达式为空默认返回方法名
		if (StringUtils.isEmpty(field)) {
			return method.getName();
		}
		//_号分割
		String SpEL = field.replace("_", "+'_'+");
		//获得被拦截方法参数列表
		LocalVariableTableParameterNameDiscoverer nd = new LocalVariableTableParameterNameDiscoverer();
		String[] parameterNames = nd.getParameterNames(method);
		//使用SpEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		//SpEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		//把方法参数放入SpEL上下文中
		for (int i = 0; i < parameterNames.length; i++) {
			context.setVariable(parameterNames[i], args[i]);
		}
		return method.getName() + parser.parseExpression(SpEL).getValue(context, String.class);
	}

}
