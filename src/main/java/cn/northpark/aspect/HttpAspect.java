package cn.northpark.aspect;

import cn.northpark.model.NotifyRemind;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.CookieUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.vo.StatisticsVO;
import cn.northpark.vo.UserVO;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author YangZhang
 *  可以定义一些切面来对某些方法 进行xx操作
 *  BRUCE TIPS!
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {


    public static ImmutableList<String> PASS_URI =
            ImmutableList.of("/error","/building");

    public static ImmutableList<String> PASS_ID =
            ImmutableList.of("507723","508200","518821","519802","518518","507630");

    //cn.northpark.action.UserAction.*(..) public * cn.northpark.action.UserAction.*(..)) &&
    @Before("execution(public * cn.northpark.action.*.*(..))")
    public void log(JoinPoint  joinPoint){
    	//获取request对象的所有属性
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
	      //url
	      //method
	      //ip
	      //类方法-使用joinPoint对象取
	      //参数
//        log.info("url={}----->",request.getRequestURI());
//        log.info("method={}----->",request.getMethod());
//        log.info("ip={}----->",request.getRemoteAddr());
//        log.info("class_method={}----->",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
//        log.info("args={}----->",request.getQueryString());

        String url = request.getRequestURL().toString();
        if(!PASS_URI.contains(request.getRequestURI())){

            StatisticsVO statisticsVO = StatisticsVO.builder()
                    .url(url)
                    .method(request.getMethod())
                    .ip(request.getRemoteAddr())
                    .class_method(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
                    .args(request.getQueryString())
                    .build();

            UserVO userInfo = RequestHolder.getUserInfo(request);

            //注册用户
            if(Objects.nonNull(userInfo)){
                statisticsVO.setUserVO(userInfo);

                //数据埋点-统计登录用户请求的uri
                if(!PASS_ID.contains(userInfo.getId().toString())){
                    //===================================异步操作=================================================
                    ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {

                            //发送异步站长通知消息
                            try {
                                //=================================消息提醒====================================================

                                //判断主题类型
                                NotifyEnum match = NotifyEnum.WEBMASTER;

                                //提醒系统赋值
                                NotifyRemind nr = new NotifyRemind();

                                //common
                                nr.setObjectLinks(url);
                                nr.setMessage(userInfo.toString()+"---"+ TimeUtils.nowTime()+"---请求了"+url+"界面---");
                                nr.setStatus("0");

                                match.notifyInstance.execute(nr);

                                //=================================消息提醒====================================================
                            }catch (Exception ig){
                                log.error("统计登录用户请求的uri-notice-has-ignored-------:",ig);
                            }
                        }



                    });
                    //===================================异步操作=================================================
                }



            }else{
                //匿名用户
                Map<String, String> cookieMap = CookieUtil.readCookieUA(request);
                statisticsVO.setCookieMap(cookieMap);
            }

            log.info("[Statistics Info]^"+statisticsVO.toString());
        }

    }

}