
package cn.northpark.action;

import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.utils.CookieUtil;
import cn.northpark.vo.StatisticsVO;
import cn.northpark.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author bruce
 * @category 处理404找不到 并且打打印信息
 */
@Slf4j
public class NPDispatchServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

	private String fileNotFondUrl = "/building";

    
    @Override
	public void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (pageNotFoundLogger.isWarnEnabled()) {
			String requestUri = urlPathHelper.getRequestUri(request);
			pageNotFoundLogger.warn("No mapping found for HTTP request with URI [" + requestUri +
					"] in DispatcherServlet with name '" + getServletName() + "'");
			//获取request对象的所有属性
		      //url
		      //method
		      //ip
		      //类方法-使用joinPoint对象取
		      //参数
			StatisticsVO statisticsVO = StatisticsVO.builder()
					.url(request.getRequestURI())
					.method(request.getMethod())
					.ip(request.getRemoteAddr())
					.args(request.getQueryString())
					.build();

			UserVO userInfo = RequestHolder.getUserInfo(request);
			if(Objects.nonNull(userInfo)){
				statisticsVO.setUserVO(userInfo);

			}else{
				Map<String, String> cookieMap = CookieUtil.readCookieUA(request);
				statisticsVO.setCookieMap(cookieMap);
			}
			log.info("[NorthPark处理统计404路径]^"+statisticsVO.toString());

		}

		response.sendRedirect(request.getContextPath() + fileNotFondUrl);
	}

	public String getFileNotFondUrl() {
		return fileNotFondUrl;
	}

	public void setFileNotFondUrl(String fileNotFondUrl) {
		this.fileNotFondUrl = fileNotFondUrl;
	}

}	