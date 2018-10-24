
package cn.northpark.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author bruce
 * @category 处理404找不到 并且打打印信息
 */
public class NPDispatchServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

	private String fileNotFondUrl = "/building";

    private static final Logger logger = LoggerFactory.getLogger(NPDispatchServlet.class);
    
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
			logger.info("NorthPark处理统计404路径----->{}",request.getRequestURI());
	        logger.info("url={}----->",requestUri);
	        logger.info("method={}----->",request.getMethod());
	        logger.info("ip={}----->",request.getRemoteAddr());
	        logger.info("args={}----->",request.getQueryString());

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